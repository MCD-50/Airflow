package com.airstem.airflow.ayush.airflow.adapters.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.airstem.airflow.ayush.airflow.R;
import com.airstem.airflow.ayush.airflow.adapters.search.AlbumAdapter;
import com.airstem.airflow.ayush.airflow.events.home.DiscoverListener;
import com.airstem.airflow.ayush.airflow.events.search.SearchAlbumListener;
import com.airstem.airflow.ayush.airflow.model.home.Discover;
import com.airstem.airflow.ayush.airflow.model.search.SearchAlbum;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by mcd-50 on 5/9/17.
 */

public class DiscoverAdapter extends RecyclerView.Adapter<DiscoverAdapter.RecyclerViewHolder> {

    private Context mContext;
    private ArrayList<Discover> mItems;
    private DiscoverListener mListener;
    private DiscoverItemAdapter mDiscoverItemAdapter;

    public DiscoverAdapter(Context context, ArrayList<Discover> items, DiscoverListener listener) {
        mContext = context;
        mItems = items;
        mListener = listener;
        mDiscoverItemAdapter = new DiscoverItemAdapter(mContext, mListener);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.discover_page, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {
        Discover discover = mItems.get(position);
        mDiscoverItemAdapter.setItems(discover.getItems());
        holder.title.setText(discover.getTitle());
        holder.listView.setAdapter(mDiscoverItemAdapter);
        holder.bindData(mListener);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        RecyclerView listView;

        RecyclerViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.discover_page_title);
            listView = (RecyclerView) view.findViewById(R.id.discover_page_list);
        }

        void bindData(final DiscoverListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onMoreClick();
                }
            });
        }
    }
}
