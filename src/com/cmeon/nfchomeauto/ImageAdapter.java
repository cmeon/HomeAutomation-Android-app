package com.cmeon.nfchomeauto;

import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.util.Log;
import android.widget.GridView;
import android.content.res.Resources;

public class ImageAdapter extends BaseAdapter {

    private Context mContext;
    private int mHeight;
    private int mWidth;

    public ImageAdapter(Context c, int h, int w) {
	mContext = c;
	mHeight  = h;
	mWidth   = w;
    }

    public ImageAdapter(Context c) {
	mContext = c;
	mHeight = GridView.LayoutParams.MATCH_PARENT;
	mWidth  = GridView.LayoutParams.MATCH_PARENT;
    }

    public int getCount() {
	return mThumbIds.length;
    }

    public Object getItem(int position) {
	return null;
    }

    public long getItemId(int position) {
	return 0;
    }

    // create a new ImageView for each item referencedd by the Adapter
    public View getView(final int position, View convertView, ViewGroup parent) {
	ImageView imageView;

	// if it's not recycled, initialize some attributes
	if (convertView == null) {

	    imageView = new ImageView(mContext);
	    imageView.setLayoutParams(new GridView.LayoutParams(mWidth, mHeight));
	    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
	    imageView.setPadding(0, 0, 0, 0);
	    /*
	    imageView.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View view) {
			Log.d("onClick","position ["+position+"]");
		    }
		});
	    */
	} else {
	    imageView = (ImageView) convertView;
	}
	imageView.setImageResource(mThumbIds[position]);
	return imageView;
    }

    // references to our images
    private Integer[] mThumbIds = {
        R.drawable.sample_2, R.drawable.sample_3,
        R.drawable.sample_4, R.drawable.sample_5,
        R.drawable.sample_6, R.drawable.sample_7,
        R.drawable.sample_0, R.drawable.sample_1,
        R.drawable.sample_2, R.drawable.sample_3,
        R.drawable.sample_4, R.drawable.sample_5,
        R.drawable.sample_6, R.drawable.sample_7,
        R.drawable.sample_0, R.drawable.sample_1,
        R.drawable.sample_2, R.drawable.sample_3,
        R.drawable.sample_4, R.drawable.sample_5,
        R.drawable.sample_6, R.drawable.sample_7
    };
}
