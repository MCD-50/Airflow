package com.airstem.airflow.ayush.airflow.adapters.search;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.airstem.airflow.ayush.airflow.R;
import com.airstem.airflow.ayush.airflow.events.search.ManualMatchListener;
import com.airstem.airflow.ayush.airflow.events.search.SearchTrackListener;
import com.airstem.airflow.ayush.airflow.helpers.collection.CollectionHelper;
import com.airstem.airflow.ayush.airflow.model.search.ManualMatch;
import com.airstem.airflow.ayush.airflow.model.search.SearchImage;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by mcd-50 on 2/10/17.
 */

public class ManualMatchAdapter extends RecyclerView.Adapter<ManualMatchAdapter.RecyclerViewHolder> {

    private Context mContext;
    private ArrayList<ManualMatch> mItems;
    private ManualMatchListener mListener;

    public ManualMatchAdapter(Context context, ArrayList<ManualMatch> searchTracks, ManualMatchListener listener) {
        mContext = context;
        mItems = searchTracks;
        mListener = listener;
    }


    @Override
    public ManualMatchAdapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.manual_match_fragment_content, parent, false);
        return new ManualMatchAdapter.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ManualMatchAdapter.RecyclerViewHolder holder, int position) {
        ManualMatch manualmatch = mItems.get(position);
        holder.bindData(manualmatch, mListener);

        holder.title.setText(manualmatch.getTitle());
        holder.subTitle.setText(manualmatch.getArtistName());
        holder.heroText.setText(CollectionHelper.getHeroText(manualmatch.getTitle()));
        int backgroundColor = Color.parseColor(CollectionHelper.getColor(manualmatch.getTitle()));
        holder._view.setBackgroundColor(backgroundColor);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView title, subTitle, heroText;
        View _view;
        
        RecyclerViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.manual_match_fragment_content_title);
            subTitle = (TextView) view.findViewById(R.id.manual_match_fragment_content_sub_title);
            heroText = (TextView) view.findViewById(R.id.manual_match_fragment_content_hero_text);
            _view = view.findViewById(R.id.manual_match_fragment_content_hero_view);
        }

        void bindData(final ManualMatch matchTrack, final ManualMatchListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onTrackClick(matchTrack);
                }
            });
            
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    listener.onTrackOption(matchTrack);
                    return true;
                }
            });
        }

    }


}