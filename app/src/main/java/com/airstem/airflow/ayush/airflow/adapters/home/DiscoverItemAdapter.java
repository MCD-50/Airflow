package com.airstem.airflow.ayush.airflow.adapters.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.airstem.airflow.ayush.airflow.R;
import com.airstem.airflow.ayush.airflow.events.home.DiscoverListener;
import com.airstem.airflow.ayush.airflow.model.home.DiscoverItem;
import com.airstem.airflow.ayush.airflow.model.search.SearchAlbum;
import com.airstem.airflow.ayush.airflow.model.search.SearchArtist;
import com.airstem.airflow.ayush.airflow.model.search.SearchTrack;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by mcd-50 on 5/9/17.
 */

public class DiscoverItemAdapter extends RecyclerView.Adapter<DiscoverItemAdapter.RecyclerViewHolder> {

    private Context mContext;
    private ArrayList<DiscoverItem> mItems;
    private DiscoverListener mListener;

    public DiscoverItemAdapter(Context context, ArrayList<DiscoverItem> items, DiscoverListener listener) {
        mContext = context;
        mItems = items;
        mListener = listener;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.discover_page_content, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {
        DiscoverItem discoverItem = mItems.get(position);
        if(discoverItem.getClass() == SearchAlbum.class){
            SearchAlbum searchAlbum = (SearchAlbum) discoverItem;
            holder.title.setText(searchAlbum.getTitle());
            holder.subTitle.setText(searchAlbum.getArtistName());
            Picasso.with(mContext).load(searchAlbum.getArtworkUrl().get(0).getUri()).placeholder(R.drawable.default_art).into(holder.image);

            holder.bindData(searchAlbum, mListener);
        }else if(discoverItem.getClass() == SearchArtist.class){
            SearchArtist searchArtist = (SearchArtist) discoverItem;
            holder.title.setText(searchArtist.getTitle());
            holder.subTitle.setText("");
            Picasso.with(mContext).load(searchArtist.getArtworkUrl().get(0).getUri()).placeholder(R.drawable.default_art).into(holder.image);

            holder.bindData(searchArtist, mListener);
        }else if(discoverItem.getClass() == SearchTrack.class){
            SearchTrack searchTrack = (SearchTrack) discoverItem;
            holder.title.setText(searchTrack.getTitle());
            holder.subTitle.setText(searchTrack.getArtistName());
            Picasso.with(mContext).load(searchTrack.getArtworkUrl().get(0).getUri()).placeholder(R.drawable.default_art).into(holder.image);

            holder.bindData(searchTrack, mListener);
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
            title = (TextView) view.findViewById(R.id.discover_page_content_title);
            subTitle = (TextView) view.findViewById(R.id.discover_page_content_sub_title);
            image = (ImageView) view.findViewById(R.id.discover_page_content_image);
        }

        void bindData(final DiscoverItem discoverItem, final DiscoverListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(discoverItem);
                }
            });
        }
    }
}

