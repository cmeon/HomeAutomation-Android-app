package com.cmeon.nfchomeauto;

import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

class ImageAdapter extends BaseAdapter {

    private final Context mContext;
    private final int mHeight;
    private final int mWidth;
    private final Integer[] mThumbIds;

    public ImageAdapter(Context c, int h, int w, Integer[] thumbs) {
	    this.mContext = c;
	    this.mHeight  = h;
	    this.mWidth   = w;
        this.mThumbIds = thumbs;
    }

    public ImageAdapter(Context c, Integer[] thumbs) {
	    this.mContext = c;
	    this.mHeight = GridView.LayoutParams.MATCH_PARENT;
	    this.mWidth  = GridView.LayoutParams.MATCH_PARENT;
        this.mThumbIds = thumbs;
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

	} else {
	    imageView = (ImageView) convertView;
	}
	imageView.setImageResource(mThumbIds[position]);
	return imageView;
    }
}
