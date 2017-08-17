package com.airstem.airflow.ayush.airflow.adapters.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.airstem.airflow.ayush.airflow.R;
import com.airstem.airflow.ayush.airflow.events.search.SearchTrackListener;
import com.airstem.airflow.ayush.airflow.model.search.SearchTrack;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by mcd-50 on 11/7/17.
 */

public class SimilarTrackAdapter extends RecyclerView.Adapter<SimilarTrackAdapter.RecyclerViewHolder> {

    private Context mContext;
    private ArrayList<SearchTrack> mItems;
    private SearchTrackListener mListener;

    public SimilarTrackAdapter(Context context, ArrayList<SearchTrack> searchTracks, SearchTrackListener listener) {
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
        Picasso.with(mContext).load(searchTrack.getArtworkUrl().get(0).getUri()).placeholder(R.drawable.default_art).into(holder.image);
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

        void bindData(final SearchTrack searchTrack, final SearchTrackListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onTrackClick(searchTrack);
                }
            });
        }

    }


}

