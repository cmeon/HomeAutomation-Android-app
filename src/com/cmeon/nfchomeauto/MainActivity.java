package com.cmeon.nfchomeauto;

import android.app.Activity;
import android.os.Bundle;
import android.app.ActionBar;
import android.view.View;

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

    public void openVideosActivity(View view) {}

    public void openMusicActivity(View view) {}

    public void openPhotosActivity(View view) {}

    public void openLightsActivity(View view) {}
}
