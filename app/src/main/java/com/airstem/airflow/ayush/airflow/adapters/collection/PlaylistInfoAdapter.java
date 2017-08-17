package com.airstem.airflow.ayush.airflow.adapters.collection;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.airstem.airflow.ayush.airflow.R;
import com.airstem.airflow.ayush.airflow.events.collection.CollectionPlaylistListener;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionTrack;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by mcd-50 on 9/7/17.
 */

public class PlaylistInfoAdapter extends RecyclerView.Adapter<PlaylistInfoAdapter.RecyclerViewHolder> {

    private Context mContext;
    private ArrayList<CollectionTrack> mItems;
    private final CollectionPlaylistListener mListener;

    public PlaylistInfoAdapter(Context context, ArrayList<CollectionTrack> collectionTracks, CollectionPlaylistListener listener) {
        mContext = context;
        mItems = collectionTracks;
        mListener = listener;
    }


    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.collection_track_fragment_content, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {
        CollectionTrack collectionTrack = mItems.get(position);
        holder.bindData(collectionTrack, mListener);

        holder.title.setText(collectionTrack.getTitle());
        holder.subTitle.setText(collectionTrack.getArtistName());
        Picasso.with(mContext).load(collectionTrack.getArtworkUrl()).placeholder(R.drawable.default_art).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView title, subTitle;
        ImageView image;

        RecyclerViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.collection_track_fragment_content_title);
            subTitle = (TextView) view.findViewById(R.id.collection_track_fragment_content_sub_title);
            image = (ImageView) view.findViewById(R.id.collection_track_fragment_content_image);
        }

        public void bindData(final CollectionTrack collectionTrack, final CollectionPlaylistListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onPlaylistTrackClick(collectionTrack);
                }
            });
        }

    }

}
