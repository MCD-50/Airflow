package com.airstem.airflow.ayush.airflow.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.airstem.airflow.ayush.airflow.R;
import com.airstem.airflow.ayush.airflow.helpers.ClickListener;
import com.airstem.airflow.ayush.airflow.model.Artist;
import com.airstem.airflow.ayush.airflow.model.Playlist;
import com.airstem.airflow.ayush.airflow.model.Track;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by ayush AS on 7/1/17.
 */

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.DataViewHolder> {

    private Activity mActivity;
    private ArrayList<Playlist> mList;
    private final ClickListener mListener;

    public PlaylistAdapter(Activity activity, ClickListener listener, ArrayList<Playlist> list){
        mActivity = activity;
        mList = list;
        mListener = listener;
    }

    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.library_playlist_content_fragment, parent, false);
        return new DataViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onBindViewHolder(final DataViewHolder holder, int position) {

        Playlist playlist = mList.get(position);
        holder.bindData(playlist, mListener);

        holder.playlistName.setText(playlist.getmName());
        holder.tracksCount.setText(playlist.getmTracksCount());

        if(playlist.getmImage() != null){
            //Picasso.with(mActivity).load(playlist.getmImage()).placeholder(R.drawable.default_art).into(holder.image);
            holder.image.setImageResource(R.drawable.default_art);
        }
        else {
            holder.image.setImageResource(R.drawable.default_art);
        }
    }

    public static class DataViewHolder extends RecyclerView.ViewHolder{

        public TextView playlistName, tracksCount;
        public ImageView image;


        public DataViewHolder(View view){
            super(view);
            playlistName = (TextView) view.findViewById(R.id.playlistName);
            tracksCount = (TextView) view.findViewById(R.id.trackCount);
            image = (ImageView) view.findViewById(R.id.playlistImage);
        }

        public void bindData(final Playlist playlist, final ClickListener listener){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.OnItemClick(playlist);
                }
            });
        }

    }
}
