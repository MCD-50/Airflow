package com.airstem.airflow.ayush.airflow.adapters.collection;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.airstem.airflow.ayush.airflow.R;
import com.airstem.airflow.ayush.airflow.events.collection.CollectionRadioListener;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionRadio;

import java.util.ArrayList;

/**
 * Created by mcd-50 on 11/7/17.
 */

public class RadioAdapter extends RecyclerView.Adapter<RadioAdapter.RecyclerViewHolder> {

    private Context mContext;
    private ArrayList<CollectionRadio> mItems;
    private CollectionRadioListener mListener;

    public RadioAdapter(Context context, ArrayList<CollectionRadio> searchRadios, CollectionRadioListener listener) {
        mContext = context;
        mItems = searchRadios;
        mListener = listener;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.search_radio_fragment_content, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {
        CollectionRadio collectionRadio = mItems.get(position);
        holder.bindData(collectionRadio, mListener);

        holder.title.setText(collectionRadio.getTitle());
        holder.subTitle.setText(collectionRadio.getCountry());
        holder._view.setBackgroundColor(Color.parseColor(collectionRadio.getColor()));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView title, subTitle;
        View _view;

        RecyclerViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.search_radio_fragment_content_title);
            subTitle = (TextView) view.findViewById(R.id.search_radio_fragment_content_sub_title);
            _view = view.findViewById(R.id.search_radio_fragment_content_view);
        }

        void bindData(final CollectionRadio collectionRadio, final CollectionRadioListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onRadioClick(collectionRadio);
                }
            });
        }

    }
}

