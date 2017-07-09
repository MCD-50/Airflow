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
import com.airstem.airflow.ayush.airflow.model.search.SearchAlbum;
import com.airstem.airflow.ayush.airflow.model.search.SearchAlbumInfo;
import com.airstem.airflow.ayush.airflow.model.search.SearchAlbumInfoTrack;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by mcd-50 on 9/7/17.
 */

public class AlbumInfoAdapter extends RecyclerView.Adapter<AlbumInfoAdapter.RecyclerViewHolder> {

    private Context mContext;
    private ArrayList<SearchAlbumInfoTrack> mSearchAlbumInfoTracks;
    private final ClickListener mListener;

    public AlbumInfoAdapter(Context context, ArrayList<SearchAlbumInfoTrack> searchAlbumInfoTracks, ClickListener listener) {
        mContext = context;
        mSearchAlbumInfoTracks = searchAlbumInfoTracks;
        mListener = listener;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.search_track_fragment_content, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {
        SearchAlbumInfoTrack searchAlbumInfoTrack = mSearchAlbumInfoTracks.get(position);
        holder.bindData(searchAlbumInfoTrack, mListener);

        holder.title.setText(searchAlbumInfoTrack.getTitle());
        holder.subTitle.setText(searchAlbumInfoTrack.getArtistName());
        Picasso.with(mContext).load(searchAlbumInfoTrack.getArtworkUrl()[0].getUri()).placeholder(R.drawable.default_art).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return mSearchAlbumInfoTracks.size();
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

        void bindData(final SearchAlbumInfoTrack searchAlbumInfoTrack, final ClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.OnItemClick(searchAlbumInfoTrack);
                }
            });
        }

    }

}
