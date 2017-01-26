package com.airstem.airflow.ayush.airflow.adapters;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.airstem.airflow.ayush.airflow.R;
import com.airstem.airflow.ayush.airflow.model.Track;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by ayush on 22-11-16.
 */
public class LocalTrackAdapter extends BaseAdapter {

    private Activity mActivity;
    private ArrayList<Track> mLocalArrayList;

    public LocalTrackAdapter(Activity activity, ArrayList<Track> tracks){
        mActivity = activity;
        mLocalArrayList = tracks;
    }


    @Override
    public int getCount() {
        return mLocalArrayList.size();
    }

    @Override
    public Track getItem(int position) {
        return mLocalArrayList.get(position);
    }

    public ArrayList<Track> getList(){
        return mLocalArrayList;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        if(rowView == null){
            //LayoutInflater lInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = LayoutInflater.from(mActivity).inflate(R.layout.local_track_fragment_content, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.titleTextView = (TextView) rowView.findViewById(R.id.local_track_fragment_content_titleTextView);
            viewHolder.moodTextView = (TextView) rowView.findViewById(R.id.local_track_fragment_content_moodTextView);
            viewHolder.imageView = (ImageView) rowView.findViewById(R.id.local_track_fragment_content_artworkImageView);
            rowView.setTag(viewHolder);
        }

        Track track = getItem(position);
        ViewHolder viewHolder = (ViewHolder) rowView.getTag();
        viewHolder.titleTextView.setText(track.getTitle());
        viewHolder.moodTextView.setText(track.getMood());
        if(track.getArtwork() != null){
            Picasso.with(mActivity).load(track.getArtwork()).placeholder(R.drawable.default_art).into(viewHolder.imageView);
        }
        else {
            viewHolder.imageView.setImageResource(R.drawable.default_art);
        }

        return rowView;
    }

    static class ViewHolder{
        TextView titleTextView;
        TextView moodTextView;
        ImageView imageView;
    }
}
