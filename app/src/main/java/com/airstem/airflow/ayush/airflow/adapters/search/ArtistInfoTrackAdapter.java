package com.airstem.airflow.ayush.airflow.adapters.search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.airstem.airflow.ayush.airflow.R;
import com.airstem.airflow.ayush.airflow.helpers.ClickListener;
import com.airstem.airflow.ayush.airflow.model.search.SearchAlbumInfoTrack;
import com.airstem.airflow.ayush.airflow.model.search.SearchArtistInfoAlbum;
import com.airstem.airflow.ayush.airflow.model.search.SearchArtistInfoTrack;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by mcd-50 on 9/7/17.
 */

public class ArtistInfoTrackAdapter extends RecyclerView.Adapter<ArtistInfoTrackAdapter.RecyclerViewHolder> {

    private Context mContext;
    private ArrayList<SearchArtistInfoTrack> mSearchArtistInfoTracks;
    private final ClickListener mListener;

    public ArtistInfoTrackAdapter(Context context, ArrayList<SearchArtistInfoTrack> searchArtistInfoTracks, ClickListener listener) {
        mContext = context;
        mSearchArtistInfoTracks = searchArtistInfoTracks;
        mListener = listener;
    }


    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.search_track_fragment_content, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {
        SearchArtistInfoTrack searchArtistInfoTrack = mSearchArtistInfoTracks.get(position);
        holder.bindData(searchArtistInfoTrack, mListener);

        holder.title.setText(searchArtistInfoTrack.getTitle());
        holder.subTitle.setText(searchArtistInfoTrack.getArtistName());
        Picasso.with(mContext).load(searchArtistInfoTrack.getArtworkUrl()[0].getUri()).placeholder(R.drawable.default_art).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return mSearchArtistInfoTracks.size();
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

        void bindData(final SearchArtistInfoTrack searchArtistInfoTrack, final ClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.OnItemClick(searchArtistInfoTrack);
                }
            });
        }

    }
}
