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
import com.airstem.airflow.ayush.airflow.model.search.SearchArtist;
import com.airstem.airflow.ayush.airflow.model.search.SearchImage;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by mcd-50 on 8/7/17.
 */

public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.RecyclerViewHolder> {

    private Context mContext;
    private ArrayList<SearchArtist> mItems;
    private final SearchArtistListener mListener;

    public ArtistAdapter(Context context, ArrayList<SearchArtist> searchArtists, SearchArtistListener listener) {
        mContext = context;
        mItems = searchArtists;
        mListener = listener;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.search_artist_fragment_content, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {
        SearchArtist searchArtist = mItems.get(position);
        holder.bindData(searchArtist, mListener);

        holder.title.setText(searchArtist.getTitle());
        ArrayList<SearchImage> searchImages = searchArtist.getArtworkUrl();
        if(searchImages.size() > 0){
            int lastIndex = searchImages.size() - 1;
            Picasso.with(mContext).load(searchImages.get(lastIndex).getUri()).placeholder(R.drawable.default_art).into(holder.image);
        }else{
            Picasso.with(mContext).load(R.drawable.default_art).placeholder(R.drawable.default_art).into(holder.image);
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView image;

        RecyclerViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.search_artist_fragment_content_title);
            image = (ImageView) view.findViewById(R.id.search_artist_fragment_content_image);
        }

        void bindData(final SearchArtist searchArtist, final SearchArtistListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onArtistClick(searchArtist);
                }
            });
        }

    }
}

