package com.cmeon.nfchomeauto;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.content.res.Resources;
import android.content.Intent;

public class VideosActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);

	setContentView(R.layout.videos);
	GridView gridView = (GridView) findViewById(R.id.videos_grid);

	Resources res = getResources();
	int h = res.getDimensionPixelSize(R.dimen.videoIconHeight);
	int w = res.getDimensionPixelSize(R.dimen.videoIconWidth);

	gridView.setAdapter(new ImageAdapter(this, h, w, movieThumbIds));
       	gridView.setColumnWidth(res.getDimensionPixelSize(R.dimen.columnWidth));

	gridView.setOnItemClickListener(new OnItemClickListener() {
		public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		    //  Toast.makeText(VideosActivity.this, ""+position, Toast.LENGTH_SHORT).show();
		    Intent intent = new Intent(VideosActivity.this, MovieInfo.class);
		    startActivity(intent);
		}
	    });
    }

    // references to our images
    private final Integer[] movieThumbIds = {
            R.drawable.tt0120903, R.drawable.tt0311429,
            R.drawable.tt0903747, R.drawable.tt1204975,
            R.drawable.tt1403865, R.drawable.tt1826590,
            R.drawable.tt2239832, R.drawable.tt1621045
    };
}
