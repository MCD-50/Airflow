package com.airstem.airflow.ayush.airflow.adapters.collection;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airstem.airflow.ayush.airflow.R;
import com.airstem.airflow.ayush.airflow.enums.collection.Action;
import com.airstem.airflow.ayush.airflow.events.collection.CollectionTrackListener;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionTrack;
import com.orhanobut.dialogplus.DialogPlus;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import io.realm.RealmResults;

/**
 * Created by mcd-50 on 9/7/17.
 */

public class TrackAdapter extends RecyclerView.Adapter<TrackAdapter.RecyclerViewHolder> {

    private Context mContext;
    private RealmResults<CollectionTrack> mItems;
    private final CollectionTrackListener mListener;


    public TrackAdapter(Context context, RealmResults<CollectionTrack> collectionTracks, CollectionTrackListener listener) {
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
        final CollectionTrack collectionTrack = mItems.get(position);
        holder.bindData(collectionTrack, mListener);

        holder.title.setText(collectionTrack.getTitle());
        holder.subTitle.setText(collectionTrack.getArtistName());
        if(!TextUtils.isEmpty(collectionTrack.getArtworkUrl())){
            Picasso.with(mContext).load(collectionTrack.getArtworkUrl()).placeholder(R.drawable.default_art).into(holder.image);
        }else{
            Picasso.with(mContext).load(R.drawable.default_art).placeholder(R.drawable.default_art).into(holder.image);
        }

        if(!collectionTrack.getIsOffline() && !collectionTrack.getIsMatched() && collectionTrack.getIsMatchError()){
            holder.status.setText(String.valueOf("Match failed. Try manual match"));
            holder.relativeLayout.setAlpha(.5f);
        }else if(!collectionTrack.getIsOffline() && !collectionTrack.getIsMatched()){
            holder.status.setText(String.valueOf("Matching track..."));
            holder.relativeLayout.setAlpha(.5f);
        }else if(!collectionTrack.getIsOffline() && collectionTrack.getIsMatched()){
            holder.status.setText(String.valueOf("Online"));
            holder.relativeLayout.setAlpha(1);
        }else{
            holder.status.setText(String.valueOf(""));
            holder.relativeLayout.setAlpha(1);
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView title, subTitle, status;
        ImageView image;
        RelativeLayout relativeLayout;

        RecyclerViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.collection_track_fragment_content_title);
            subTitle = (TextView) view.findViewById(R.id.collection_track_fragment_content_sub_title);
            status = (TextView) view.findViewById(R.id.collection_track_fragment_content_status);
            image = (ImageView) view.findViewById(R.id.collection_track_fragment_content_image);
            relativeLayout = (RelativeLayout) view.findViewById(R.id.collection_track_fragment_content_holder);
        }

        public void bindData(final CollectionTrack collectionTrack, final CollectionTrackListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onTrackClick(collectionTrack);
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    listener.onTrackOptions(collectionTrack, Action.LONG_CLICK);
                    return true;
                }
            });
        }

    }

}

