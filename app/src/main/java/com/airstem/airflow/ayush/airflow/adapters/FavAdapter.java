package com.airstem.airflow.ayush.airflow.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.airstem.airflow.ayush.airflow.R;
import com.airstem.airflow.ayush.airflow.helpers.MoodHelper;
import com.airstem.airflow.ayush.airflow.model.Mood;
import com.airstem.airflow.ayush.airflow.model.PlayMode;
import com.airstem.airflow.ayush.airflow.model.Track;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by ayush on 09-10-16.
 */

public class FavAdapter extends BaseAdapter {

    private ArrayList<Track> mTracks;
    Context mContext;
    public FavAdapter(Context context, ArrayList<Track> tracks){
        mContext = context;
        mTracks = tracks;
    }

    public void addData(Track track){
        if(mTracks == null){
            mTracks = new ArrayList<>();
            mTracks.add(track);
        }else {
            if(!mTracks.contains(track))
                mTracks.add(track);
        }
    }

    public void removeData(ArrayList<Track> tracks){
        mTracks = tracks;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mTracks.size();
    }

    @Override
    public Track getItem(int position) {
        return mTracks.get(position);
    }

    public ArrayList<Track> getList(){
        return mTracks;
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
            rowView = LayoutInflater.from(mContext).inflate(R.layout.my_fav_fragment_content, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.titleTextView = (TextView) rowView.findViewById(R.id.my_fav_fragment_content_titleTextView);
            viewHolder.moodTextView = (TextView) rowView.findViewById(R.id.my_fav_fragment_content_moodTextView);
            viewHolder.imageView = (ImageView) rowView.findViewById(R.id.my_fav_fragment_content_artworkImageView);
            rowView.setTag(viewHolder);
        }


        Track track = getItem(position);
        ViewHolder viewHolder = (ViewHolder) rowView.getTag();
        viewHolder.titleTextView.setText(track.getTitle());
        viewHolder.moodTextView.setText(track.getMood());

        if(track.getMode() == PlayMode.OFFLINE){
            Picasso.with(mContext).load(track.getArtwork()).placeholder(R.drawable.default_art).into(viewHolder.imageView);
        }else{
            if(track.getBitmap() == null){
                viewHolder.imageView.setImageResource(R.drawable.default_art);
            }else {
                viewHolder.imageView.setImageBitmap(track.getBitmap());
            }
        }

        return rowView;
    }

    static class ViewHolder{
        TextView titleTextView;
        TextView moodTextView;
        ImageView imageView;
    }

}
