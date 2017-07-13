package com.airstem.airflow.ayush.airflow.adapters.collection;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.airstem.airflow.ayush.airflow.R;
import com.airstem.airflow.ayush.airflow.events.Collection.CollectionArtistListener;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionArtist;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by mcd-50 on 9/7/17.
 */

public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.RecyclerViewHolder> {

    private Context mContext;
    private ArrayList<CollectionArtist> mItems;
    private final CollectionArtistListener mListener;

    public ArtistAdapter(Context context, ArrayList<CollectionArtist> searchArtists, CollectionArtistListener listener) {
        mContext = context;
        mItems = searchArtists;
        mListener = listener;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.collection_artist_fragment_content, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {
        CollectionArtist collectionArtist = mItems.get(position);
        holder.bindData(collectionArtist, mListener);

        holder.title.setText(collectionArtist.getTitle());
        holder.subTitle.setText(collectionArtist.getTracksLength());
        Picasso.with(mContext).load(collectionArtist.getArtworkUrl()).placeholder(R.drawable.default_art).into(holder.image);
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
            title = (TextView) view.findViewById(R.id.collection_artist_fragment_content_title);
            subTitle = (TextView) view.findViewById(R.id.collection_artist_fragment_content_sub_title);
            image = (ImageView) view.findViewById(R.id.collection_artist_fragment_content_image);
        }

        public void bindData(final CollectionArtist collectionArtist, final CollectionArtistListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(collectionArtist);
                }
            });
        }

    }
}


