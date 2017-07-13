package com.airstem.airflow.ayush.airflow.adapters.search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.airstem.airflow.ayush.airflow.R;
import com.airstem.airflow.ayush.airflow.events.Search.SearchArtistListener;
import com.airstem.airflow.ayush.airflow.model.search.SearchArtistInfoAlbum;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by mcd-50 on 9/7/17.
 */

public class ArtistInfoAlbumAdapter extends RecyclerView.Adapter<ArtistInfoAlbumAdapter.RecyclerViewHolder> {

    private Context mContext;
    private ArrayList<SearchArtistInfoAlbum> mItems;
    private final SearchArtistListener mListener;

    public ArtistInfoAlbumAdapter(Context context, ArrayList<SearchArtistInfoAlbum> searchArtistInfoAlbums, SearchArtistListener listener) {
        mContext = context;
        mItems = searchArtistInfoAlbums;
        mListener = listener;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.search_album_fragment_content, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {
        SearchArtistInfoAlbum searchArtistInfoAlbum = mItems.get(position);
        holder.bindData(searchArtistInfoAlbum, mListener);

        holder.title.setText(searchArtistInfoAlbum.getTitle());
        holder.subTitle.setText(searchArtistInfoAlbum.getArtistName());
        Picasso.with(mContext).load(searchArtistInfoAlbum.getArtworkUrl().get(0).getUri()).placeholder(R.drawable.default_art).into(holder.image);
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
            title = (TextView) view.findViewById(R.id.search_album_fragment_content_title);
            subTitle = (TextView) view.findViewById(R.id.search_album_fragment_content_sub_title);
            image = (ImageView) view.findViewById(R.id.search_album_fragment_content_image);
        }

        void bindData(final SearchArtistInfoAlbum searchArtistInfoAlbum, final SearchArtistListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onArtistAlbumClick(searchArtistInfoAlbum);
                }
            });
        }

    }

}

