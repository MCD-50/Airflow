package com.airstem.airflow.ayush.airflow.adapters.search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.airstem.airflow.ayush.airflow.R;
import com.airstem.airflow.ayush.airflow.events.search.SearchVideoListener;
import com.airstem.airflow.ayush.airflow.model.search.SearchImage;
import com.airstem.airflow.ayush.airflow.model.search.SearchVideo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by mcd-50 on 8/7/17.
 */

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.RecyclerViewHolder> {

    private Context mContext;
    private ArrayList<SearchVideo> mItems;
    private SearchVideoListener mListener;

    public VideoAdapter(Context context, ArrayList<SearchVideo> searchVideos, SearchVideoListener listener) {
        mContext = context;
        mItems = searchVideos;
        mListener = listener;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.search_video_fragment_content, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {
        SearchVideo searchVideo = mItems.get(position);
        holder.bindData(searchVideo, mListener);

        holder.title.setText(searchVideo.getTitle());
        holder.subTitle.setText(searchVideo.getAuthor());
        ArrayList<SearchImage> searchImages = searchVideo.getArtworkUrl();
        if(searchImages.size() > 0){
            int lastIndex = searchImages.size() - 1;
            Picasso.with(mContext).load(searchImages.get(lastIndex).getUri()).placeholder(R.drawable.default_art).into(holder.image);
            Picasso.with(mContext).load(searchImages.get(0).getUri()).placeholder(R.drawable.default_art).into(holder.smallImage);
        }else{
            Picasso.with(mContext).load(R.drawable.default_art).placeholder(R.drawable.default_art).into(holder.image);
            Picasso.with(mContext).load(R.drawable.default_art).placeholder(R.drawable.default_art).into(holder.smallImage);
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView title, subTitle;
        ImageView image, smallImage;

        RecyclerViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.search_video_fragment_content_title);
            subTitle = (TextView) view.findViewById(R.id.search_video_fragment_content_sub_title);
            image = (ImageView) view.findViewById(R.id.search_video_fragment_content_image);
            smallImage = (ImageView) view.findViewById(R.id.search_video_fragment_content_small_image);
        }

        void bindData(final SearchVideo searchVideo, final SearchVideoListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onVideoClick(searchVideo);
                }
            });
        }

    }


    public int getCount() {
        return mItems.size();
    }


}

