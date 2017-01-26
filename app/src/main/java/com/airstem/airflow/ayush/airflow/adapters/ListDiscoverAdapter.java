package com.airstem.airflow.ayush.airflow.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.airstem.airflow.ayush.airflow.R;
import com.airstem.airflow.ayush.airflow.model.Track;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by ayush on 18-11-16.
 */
public class ListDiscoverAdapter extends BaseAdapter {


    private Activity mActivity;
    private ArrayList<Track> mRadioArrayList;

    public ListDiscoverAdapter(Activity activity, ArrayList<Track> tracks){
        mActivity = activity;
        mRadioArrayList = tracks;
    }

    @Override
    public int getCount() {
        return mRadioArrayList.size();
    }

    @Override
    public Track getItem(int position) {
        return mRadioArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = convertView;
        if(rowView == null){
            rowView = LayoutInflater.from(mActivity).inflate(R.layout.discover_fragment_content, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.radioTextView = (TextView) rowView.findViewById(R.id.discover_fragment_content_radioTextView);
            viewHolder.radioImageView = (ImageView) rowView.findViewById(R.id.discover_fragment_content_radioImageView);
            rowView.setTag(viewHolder);
        }

        Track track = getItem(position);
        ViewHolder viewHolder = (ViewHolder) rowView.getTag();
        viewHolder.radioTextView.setText(track.getTitle());
        Picasso.with(mActivity).load(track.getRadioArtwork()).placeholder(R.drawable.default_art).into(viewHolder.radioImageView);
        return rowView;
    }


    static class ViewHolder{
        TextView radioTextView;
        ImageView radioImageView;
    }

}
