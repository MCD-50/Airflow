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
import com.airstem.airflow.ayush.airflow.model.Base;
import com.airstem.airflow.ayush.airflow.model.search.SearchAlbum;
import com.airstem.airflow.ayush.airflow.model.search.SearchTrack;
import com.airstem.airflow.ayush.airflow.model.search.SearchVideo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by mcd-50 on 8/7/17.
 */

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.RecyclerViewHolder> {

    private Context mContext;
    private ArrayList<SearchVideo> mSearchVideos;
    private ClickListener mListener;

    public VideoAdapter(Context context, ArrayList<SearchVideo> searchVideos, ClickListener listener) {
        mContext = context;
        mSearchVideos = searchVideos;
        mListener = listener;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.search_video_fragment_content, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {
        SearchVideo searchVideo = mSearchVideos.get(position);
        holder.bindData(searchVideo, mListener);

        holder.title.setText(searchVideo.getTitle());
        holder.subTitle.setText(searchVideo.getAuthor());
        Picasso.with(mContext).load(searchVideo.getArtworkUrl()[0].getUri()).placeholder(R.drawable.default_art).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return mSearchVideos.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView title, subTitle;
        ImageView image;

        RecyclerViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.search_video_fragment_content_title);
            subTitle = (TextView) view.findViewById(R.id.search_video_fragment_content_sub_title);
            image = (ImageView) view.findViewById(R.id.search_video_fragment_content_image);
        }

        void bindData(final SearchVideo searchVideo, final ClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.OnItemClick(searchVideo);
                }
            });
        }

    }


    public int getCount() {
        return mSearchVideos.size();
    }


}

