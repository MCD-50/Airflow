package com.airstem.airflow.ayush.airflow.adapters.collection;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.airstem.airflow.ayush.airflow.R;
import com.airstem.airflow.ayush.airflow.helpers.ClickListener;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionArtist;
import com.airstem.airflow.ayush.airflow.model.search.SearchArtist;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by mcd-50 on 9/7/17.
 */

public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ArtistViewHolder> {

    private Context mContext;
    private ArrayList<CollectionArtist> mCollectionArtists;
    private final ClickListener mListener;

    public ArtistAdapter(Context context, ArrayList<CollectionArtist> searchArtists, ClickListener listener) {
        mContext = context;
        mCollectionArtists = searchArtists;
        mListener = listener;
    }

    @Override
    public ArtistViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.search_album_fragment_content, parent, false);
        return new ArtistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ArtistViewHolder holder, int position) {
        CollectionArtist collectionArtist = mCollectionArtists.get(position);
        holder.bindData(collectionArtist, mListener);

        holder.title.setText(collectionArtist.getTitle());
        holder.title.setText(collectionArtist.getTitle());
        Picasso.with(mContext).load(collectionArtist.getArtworkOfflineUrl()).placeholder(R.drawable.default_art).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return mCollectionArtists.size();
    }

    public static class ArtistViewHolder extends RecyclerView.ViewHolder {

        TextView title, subTitle;
        ImageView image;

        ArtistViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.local_track_fragment_content_titleTextView);
            subTitle = (TextView) view.findViewById(R.id.local_track_fragment_content_titleTextView);
            image = (ImageView) view.findViewById(R.id.local_track_fragment_content_artworkImageView);
        }

        public void bindData(final CollectionArtist collectionArtist, final ClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.OnItemClick(collectionArtist);
                }
            });
        }

    }
}


