package com.airstem.airflow.ayush.airflow.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.airstem.airflow.ayush.airflow.R;
import com.airstem.airflow.ayush.airflow.helpers.ClickListener;
import com.airstem.airflow.ayush.airflow.model.Artist;
import com.airstem.airflow.ayush.airflow.model.Track;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by ayush AS on 7/1/17.
 */

public class TracksAdapter extends RecyclerView.Adapter<TracksAdapter.DataViewHolder> {

    private Activity mActivity;
    private ArrayList<Track> mList;
    private final ClickListener mListener;

    public TracksAdapter(Activity activity, ClickListener listener, ArrayList<Track> list){
        mActivity = activity;
        mList = list;
        mListener = listener;
    }

    public ArrayList<Track> getmList(){
        return mList;
    }

    public Track getTrack(int pos){
        return mList.get(pos);
    }

    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.library_track_content_fragment, parent, false);
        return new DataViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onBindViewHolder(final DataViewHolder holder, int position) {

        Track track = mList.get(position);
        holder.bindData(track, mListener);

        holder.trackName.setText(track.getTitle());
        holder.artistName.setText(track.getMood());

        if(track.getArtwork() != null){
            Picasso.with(mActivity).load(track.getArtwork()).placeholder(R.drawable.default_art).into(holder.image);
        }
        else {
            holder.image.setImageResource(R.drawable.default_art);
        }

    }


    public static class DataViewHolder extends RecyclerView.ViewHolder{

        public TextView trackName, artistName;
        public ImageView image;


        public DataViewHolder(View view){
            super(view);
            trackName = (TextView) view.findViewById(R.id.trackName);
            artistName = (TextView) view.findViewById(R.id.artistName);
            image = (ImageView) view.findViewById(R.id.albumImage);
        }

        public void bindData(final Track track, final ClickListener listener){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.OnItemClick(track);
                }
            });
        }
    }
}
