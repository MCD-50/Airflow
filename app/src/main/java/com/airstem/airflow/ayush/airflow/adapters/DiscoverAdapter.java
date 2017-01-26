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
import com.airstem.airflow.ayush.airflow.model.Track;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by ayush on 16-11-16.
 */
public class DiscoverAdapter extends RecyclerView.Adapter<DiscoverAdapter.MyViewHolder> {

    private Activity mActivity;
    private ArrayList<Track> mLocalArrayList;
    private final ClickListener mListener;

    public DiscoverAdapter(Activity activity, ClickListener listener, ArrayList<Track> radioArrayList){
        mActivity = activity;
        mLocalArrayList = radioArrayList;
        mListener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.local_track_fragment_content, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        Track track = mLocalArrayList.get(position);
        holder.bindData(track, mListener);

        holder.titleTextView.setText(track.getTitle());
        holder.moodTextView.setText(track.getMood());
        if(track.getArtwork() != null){
            Picasso.with(mActivity).load(track.getArtwork()).placeholder(R.drawable.default_art).into(holder.imageView);
        }
        else {
            holder.imageView.setImageResource(R.drawable.default_art);
        }

    }

    @Override
    public int getItemCount() {
        return mLocalArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView titleTextView;
        public TextView moodTextView;
        public ImageView imageView;


        public MyViewHolder(View view){
            super(view);
            titleTextView = (TextView) view.findViewById(R.id.local_track_fragment_content_titleTextView);
            moodTextView = (TextView) view.findViewById(R.id.local_track_fragment_content_moodTextView);
            imageView = (ImageView) view.findViewById(R.id.local_track_fragment_content_artworkImageView);
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
