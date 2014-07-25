package com.cmeon.nfchomeauto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * Created by cmeon on 25/07/14.
 */
public class MovieInfoAdapter extends ArrayAdapter<String> {
    private String[] info;
    private int position;
    Context context;
    int res;
    public MovieInfoAdapter(Context context, int res, String[] info, int position) {
        super(context, res, info);
        this.context = context;
        this.position = position;
        this.info = info;
        this.res = res;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(res, parent, false);

        RatingBar ratingBar = (RatingBar) row.findViewById(R.id.movie_rating);
        ratingBar.setRating((new Data().getRating("movie", position)) / 2 );

        TextView infoText =(TextView) row.findViewById(R.id.text1);
        infoText.setText(info[0]);

        return row;
    }
}
