package com.cmeon.nfchomeauto;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.Rating;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MovieInfo extends Activity implements AsyncTaskCompleteListener<String>{

    private static final int TOP_HEIGHT = 700;
    private ListView mList;
    private ImageView mBlurredImage;
    private View headerView;
    private ImageView mNormalImage;
    private ScrollableImageView mBlurredImageHeader;
    private ImageButton mSwitch;
    private float alpha;

    // shows the results
    @Override
    public void onTaskComplete(String result) {
        Toast.makeText(MovieInfo.this, result, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getActionBar();
        actionBar.hide();

        Intent intent = getIntent();
        final Integer position = intent.getIntExtra(VideosActivity.EXTRA_POS, 0);
        final Integer movieThumb = new Data().movieThumbIds[position];
        final String movieId = new Data().movieIds[position];

//        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.video_fragment);

        // Get the screen width
        final int screenWidth = ImageUtils.getScreenWidth(this);

        // Find the view
        mBlurredImage = (ImageView) findViewById(R.id.blurred_image);
        mNormalImage = (ImageView) findViewById(R.id.normal_image);
        mBlurredImageHeader = (ScrollableImageView) findViewById(R.id.blurred_image_header);
        mSwitch = (ImageButton) findViewById(R.id.background_switch);
        mList = (ListView) findViewById(R.id.list);

        // prepare the header ScrollableImageView
        mNormalImage.setImageResource(movieThumb);
        mBlurredImageHeader.setScreenWidth(screenWidth); //-getResources().getDimensionPixelSize(R.dimen.padding));

        // Action for the switch
        /*
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mBlurredImage.setAlpha(alpha);
                } else {
                    mBlurredImage.setAlpha(0f);
                }
            }
        });
        */

        mSwitch.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message msg = new Message("play_movie " + movieId);
                new HTTPGetTask(MovieInfo.this).execute(msg.getStringUrl());
            }
        });

        // Try to find the blurred image
        final File blurredImage = new File(getFilesDir() + movieId);
        if (!blurredImage.exists()) {

            // launch the progressbar in ActionBar
            // setProgressBarIndeterminateVisibility(true);

            new Thread(new Runnable() {

                @Override
                public void run() {

                    // No image found => let's generate it!
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inSampleSize = 2;
                    Bitmap image = BitmapFactory.decodeResource(getResources(), movieThumb, options);
                    Bitmap newImg = Blur.fastblur(MovieInfo.this, image, 12);
                    ImageUtils.storeImage(newImg, blurredImage);
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            updateView(screenWidth, movieId);

                            // And finally stop the progressbar
                            // setProgressBarIndeterminateVisibility(false);
                        }
                    });


                }
            }).start();

        } else {

            // The image has been found. Let's update the view
            updateView(screenWidth, movieId);

        }
        final int[][] result = new int[1][1];

        new Thread(new Runnable() {
            @Override
            public void run() {
                Bitmap image = BitmapFactory.decodeFile(getFilesDir() + movieId);
                try {
                    result[0] = MMCQ.compute(image, 5).get(0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                image.recycle();
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        int[] dominantColor = result[0];
                        findViewById(R.id.dominant_color).setBackgroundColor(
                                Color.rgb(dominantColor[0], dominantColor[1], dominantColor[2]));
                        Log.d("color", "colord");
                    }
                });


            }
        }).start();


        String[] info = {new Data().getInfo(position)};

        // Prepare the header view for our list
        headerView = new View(this);
        headerView.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, TOP_HEIGHT));
        TextView header = (TextView)findViewById(R.id.header_text);
        header.setText("new movie");

        mList.addHeaderView(headerView);
        //  mList.setAdapter(new ArrayAdapter<String>(this, R.layout.movie_info, info));
        mList.setAdapter(new MovieInfoAdapter(MovieInfo.this, R.layout.movie_info, info, position));

        mList.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            /**
             * Listen to the list scroll. This is where magic happens ;)
             */
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                // Calculate the ratio between the scroll amount and the list
                // header weight to determinate the top picture alpha
                alpha = (float) -headerView.getTop() / (float) TOP_HEIGHT;
                // Apply a ceil
                if (alpha > 1) {
                    alpha = 1;
                }

                // Apply on the ImageView if needed
                /*
                if (mSwitch.isChecked()) {
                    mBlurredImage.setAlpha(alpha);
                }
                */

                // Parallax effect : we apply half the scroll amount to our
                // three views
                mBlurredImage.setTop(headerView.getTop() / 10);
                mNormalImage.setTop(headerView.getTop() / 10);
                mBlurredImageHeader.handleScroll(headerView.getTop() / 10);

            }
        });

        // Set Rating
        //final RatingBar ratingBar = (RatingBar) findViewById(R.id.movie_rating);
        //ratingBar.setRating( (new Data().getRating("movie", position)) / 2.0f );
    }

    private void updateView(final int screenWidth, final String movieId) {
        Bitmap bmpBlurred = BitmapFactory.decodeFile(getFilesDir() + movieId);
        bmpBlurred = Bitmap.createScaledBitmap(bmpBlurred, screenWidth, (int) (bmpBlurred.getHeight()
                * ((float) screenWidth) / (float) bmpBlurred.getWidth()), false);

        mBlurredImageHeader.setoriginalImage(bmpBlurred);

        mBlurredImage.setImageBitmap(bmpBlurred);
    }
}

