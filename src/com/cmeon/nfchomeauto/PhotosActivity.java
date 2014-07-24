package com.cmeon.nfchomeauto;

import android.app.Activity;
import android.os.Bundle;
import android.app.ActionBar;

public class PhotosActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
	super.onCreate(savedInstanceState);
	ActionBar actionBar = getActionBar();
        assert actionBar != null;
        actionBar.hide();
	setContentView(R.layout.photos);
    }
}
