package com.cmeon.nfchomeauto;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.content.res.Resources;
import android.widget.Toast;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class MusicActivity extends Activity implements AsyncTaskCompleteListener<String>
{
    // shows the results
    @Override
    public void onTaskComplete(String result) {
	Toast.makeText(MusicActivity.this, result, Toast.LENGTH_SHORT).show();
    }

    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	
	setContentView(R.layout.music);
	GridView gridView = (GridView) findViewById(R.id.music_grid);
	
	Resources res = getResources();

	gridView.setAdapter(new ImageAdapter(this, musicThumbIds));
       	gridView.setColumnWidth(res.getDimensionPixelSize(R.dimen.columnWidth));

	gridView.setOnItemClickListener(new OnItemClickListener() {
		public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		    // make a http request
		    Message msg = new Message("play_video break");
		    new HTTPGetTask(MusicActivity.this).execute(msg.getStringUrl());
		    Toast.makeText(MusicActivity.this, ""+position, Toast.LENGTH_SHORT).show();
		}
	    });
    }

    // references to our images
    private final Integer[] musicThumbIds = {
            R.drawable.tt0120903, R.drawable.tt0311429,
            R.drawable.tt0903747, R.drawable.tt1204975,
            R.drawable.tt1403865, R.drawable.tt1826590,
            R.drawable.tt2239832, R.drawable.tt1621045
    };
}
