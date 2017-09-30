package com.airstem.airflow.ayush.airflow.adapters.collection;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.airstem.airflow.ayush.airflow.R;
import com.airstem.airflow.ayush.airflow.enums.collection.Action;
import com.airstem.airflow.ayush.airflow.events.collection.CollectionPlaylistListener;
import com.airstem.airflow.ayush.airflow.helpers.collection.CollectionHelper;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionPlaylist;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import io.realm.RealmResults;

/**
 * Created by mcd-50 on 9/7/17.
 */

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.RecyclerViewHolder> {

    private Context mContext;
    private RealmResults<CollectionPlaylist> mItems;
    private final CollectionPlaylistListener mListener;

    public PlaylistAdapter(Context context, RealmResults<CollectionPlaylist> collectionPlaylists, CollectionPlaylistListener listener) {
        mContext = context;
        mItems = collectionPlaylists;
        mListener = listener;
    }


    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.collection_playlist_fragment_content, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {
        CollectionPlaylist collectionPlaylist = mItems.get(position);
        holder.bindData(collectionPlaylist, mListener);

        holder.title.setText(collectionPlaylist.getTitle());
        holder.subTitle.setText(CollectionHelper.getSweetString(collectionPlaylist.getModifiedOn()));
        Picasso.with(mContext).load(R.drawable.default_art).placeholder(R.drawable.default_art).into(holder.image);
        /*if(!TextUtils.isEmpty(collectionPlaylist.getArtworkUrl())){
            Picasso.with(mContext).load(collectionPlaylist.getArtworkUrl()).placeholder(R.drawable.default_art).into(holder.image);
        }else{
            Picasso.with(mContext).load(R.drawable.default_art).placeholder(R.drawable.default_art).into(holder.image);
        }*/
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
            title = (TextView) view.findViewById(R.id.collection_playlist_fragment_content_title);
            subTitle = (TextView) view.findViewById(R.id.collection_playlist_fragment_content_sub_title);
            image = (ImageView) view.findViewById(R.id.collection_playlist_fragment_content_image);
        }

        public void bindData(final CollectionPlaylist collectionPlaylist, final CollectionPlaylistListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onPlaylistClick(collectionPlaylist);
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    listener.onPlaylistOptions(collectionPlaylist, Action.LONG_CLICK);
                    return true;
                }
            });
        }

    }

}