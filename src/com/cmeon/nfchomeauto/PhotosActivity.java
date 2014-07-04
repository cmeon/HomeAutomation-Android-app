package com.cmeon.nfchomeauto;

import android.app.Activity;
import android.os.Bundle;
import android.app.ActionBar;
import android.view.View;

public class PhotosActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
	super.onCreate(savedInstanceState);
	ActionBar actionBar = getActionBar();
	actionBar.hide();
	setContentView(R.layout.photos);
    }
}
