package com.airstem.airflow.ayush.airflow.adapters.search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.airstem.airflow.ayush.airflow.R;
import com.airstem.airflow.ayush.airflow.events.search.SearchArtistListener;
import com.airstem.airflow.ayush.airflow.model.search.SearchAlbum;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by mcd-50 on 9/7/17.
 */

public class ArtistInfoAlbumAdapter extends RecyclerView.Adapter<ArtistInfoAlbumAdapter.RecyclerViewHolder> {

    private Context mContext;
    private ArrayList<SearchAlbum> mItems;
    private final SearchArtistListener mListener;

    public ArtistInfoAlbumAdapter(Context context, ArrayList<SearchAlbum> searchAlbums, SearchArtistListener listener) {
        mContext = context;
        mItems = searchAlbums;
        mListener = listener;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.search_album_fragment_content, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {
        SearchAlbum searchAlbum = mItems.get(position);
        holder.bindData(searchAlbum, mListener);

        holder.title.setText(searchAlbum.getTitle());
        holder.subTitle.setText(searchAlbum.getArtistName());
        Picasso.with(mContext).load(searchAlbum.getArtworkUrl().get(0).getUri()).placeholder(R.drawable.default_art).into(holder.image);
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

        void bindData(final SearchAlbum searchAlbum, final SearchArtistListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onArtistAlbumClick(searchAlbum);
                }
            });
        }

    }

}

