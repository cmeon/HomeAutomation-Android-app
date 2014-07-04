package com.cmeon.nfchomeauto;

import android.app.Activity;
import android.os.Bundle;
import android.app.ActionBar;
import android.view.View;
import android.content.Intent;

public class MainActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
	ActionBar actionBar = getActionBar();
	actionBar.hide();
        setContentView(R.layout.main);
    }

    public void openVideosActivity(View view) {
	startOtherActivity(VideosActivity.class);
    }

    public void openMusicActivity(View view) {
	startOtherActivity(MusicActivity.class);
    }

    public void openPhotosActivity(View view) {
	startOtherActivity(PhotosActivity.class);
    }

    public void openLightsActivity(View view) {
        startOtherActivity(LightsActivity.class);
    }

    public void startOtherActivity(Class cls) {
	Intent intent = new Intent(MainActivity.this, cls);
	startActivity(intent);
    };
}
