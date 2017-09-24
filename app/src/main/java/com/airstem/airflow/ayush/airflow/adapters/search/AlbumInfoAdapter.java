package com.airstem.airflow.ayush.airflow.adapters.search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.airstem.airflow.ayush.airflow.R;
import com.airstem.airflow.ayush.airflow.events.search.SearchAlbumListener;
import com.airstem.airflow.ayush.airflow.model.search.SearchImage;
import com.airstem.airflow.ayush.airflow.model.search.SearchTrack;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by mcd-50 on 9/7/17.
 */

public class AlbumInfoAdapter extends RecyclerView.Adapter<AlbumInfoAdapter.RecyclerViewHolder> {

    private Context mContext;
    private ArrayList<SearchTrack> mItems;
    private final SearchAlbumListener mListener;

    public AlbumInfoAdapter(Context context, ArrayList<SearchTrack> searchTracks, SearchAlbumListener listener) {
        mContext = context;
        mItems = searchTracks;
        mListener = listener;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.search_track_fragment_content, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {
        SearchTrack searchTrack = mItems.get(position);
        holder.bindData(searchTrack, mListener);

        holder.title.setText(searchTrack.getTitle());
        holder.subTitle.setText(searchTrack.getArtistName());
        ArrayList<SearchImage> searchImages = searchTrack.getArtworkUrl();
        if(searchImages.size() > 0){
            Picasso.with(mContext).load(searchImages.get(0).getUri()).placeholder(R.drawable.default_art).into(holder.image);
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
            title = (TextView) view.findViewById(R.id.search_track_fragment_content_title);
            subTitle = (TextView) view.findViewById(R.id.search_track_fragment_content_sub_title);
            image = (ImageView) view.findViewById(R.id.search_track_fragment_content_image);
        }

        void bindData(final SearchTrack searchTrack, final SearchAlbumListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onAlbumTrackClick(searchTrack);
                }
            });
        }

    }

}
