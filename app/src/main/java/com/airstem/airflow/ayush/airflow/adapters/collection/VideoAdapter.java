package com.airstem.airflow.ayush.airflow.adapters.collection;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.airstem.airflow.ayush.airflow.R;
import com.airstem.airflow.ayush.airflow.enums.collection.Action;
import com.airstem.airflow.ayush.airflow.events.collection.CollectionVideoListener;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionVideo;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.File;
import java.util.ArrayList;

import io.realm.RealmResults;

/**
 * Created by mcd-50 on 9/7/17.
 */

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.RecyclerViewHolder> {

    private Context mContext;
    private RealmResults<CollectionVideo> mItems;
    private final CollectionVideoListener mListener;

    public VideoAdapter(Context context, RealmResults<CollectionVideo> collectionTracks, CollectionVideoListener listener) {
        mContext = context;
        mItems = collectionTracks;
        mListener = listener;
    }


    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.collection_video_fragment_content, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {
        CollectionVideo collectionVideo = mItems.get(position);
        holder.bindData(collectionVideo, mListener);

        holder.title.setText(collectionVideo.getTitle());
        holder.subTitle.setText(collectionVideo.getAuthor());
        if(collectionVideo.getArtworkUrl() != null && !TextUtils.isEmpty(collectionVideo.getArtworkUrl())){
            Picasso.with(mContext).load(collectionVideo.getArtworkUrl()).placeholder(R.drawable.default_art).into(holder.image);
        }else{
            Picasso.with(mContext).load(R.drawable.default_art).placeholder(R.drawable.default_art).into(holder.image);
        }
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
            title = (TextView) view.findViewById(R.id.collection_video_fragment_content_title);
            subTitle = (TextView) view.findViewById(R.id.collection_video_fragment_content_sub_title);
            image = (ImageView) view.findViewById(R.id.collection_video_fragment_content_image);
        }

        public void bindData(final CollectionVideo collectionVideo, final CollectionVideoListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onVideoClick(collectionVideo);
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    listener.onVideoOptions(collectionVideo, Action.LONG_CLICK);
                    return true;
                }
            });
        }

    }

}


