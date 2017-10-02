package com.airstem.airflow.ayush.airflow.adapters.search;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.airstem.airflow.ayush.airflow.R;
import com.airstem.airflow.ayush.airflow.events.search.SearchRadioListener;
import com.airstem.airflow.ayush.airflow.helpers.collection.CollectionHelper;
import com.airstem.airflow.ayush.airflow.model.search.SearchRadio;

import java.util.ArrayList;

/**
 * Created by mcd-50 on 8/7/17.
 */

public class RadioAdapter extends RecyclerView.Adapter<RadioAdapter.RecyclerViewHolder> {

    private Context mContext;
    private ArrayList<SearchRadio> mItems;
    private SearchRadioListener mListener;

    public RadioAdapter(Context context, ArrayList<SearchRadio> searchRadios, SearchRadioListener listener) {
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
        SearchRadio searchRadio = mItems.get(position);
        holder.bindData(searchRadio, mListener);

        holder.title.setText(searchRadio.getTitle());
        holder.subTitle.setText(searchRadio.getCountry());
        holder._view.setBackgroundColor(Color.parseColor(CollectionHelper.getColor(searchRadio.getTitle())));
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

        void bindData(final SearchRadio searchRadio, final SearchRadioListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onRadioClick(searchRadio);
                }
            });
        }

    }
}

