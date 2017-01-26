package com.airstem.airflow.ayush.airflow.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.airstem.airflow.ayush.airflow.R;
import com.airstem.airflow.ayush.airflow.helpers.ClickListener;
import com.airstem.airflow.ayush.airflow.model.Artist;
import com.airstem.airflow.ayush.airflow.model.Track;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by ayush AS on 7/1/17.
 */

public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.DataViewHolder> {

    private Activity mActivity;
    private ArrayList<Artist> mList;
    private final ClickListener mListener;

    public ArtistAdapter(Activity activity, ClickListener listener, ArrayList<Artist> list){
        mActivity = activity;
        mList = list;
        mListener = listener;
    }

    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.library_artist_content_fragment, parent, false);
        return new DataViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onBindViewHolder(final DataViewHolder holder, int position) {

        Artist artist = mList.get(position);
        holder.bindData(artist, mListener);

        holder.artistName.setText(artist.getmName());
        holder.tracksCount.setText(artist.getmTracksCount());

        if(artist.getmImage() != null){
            Picasso.with(mActivity).load(artist.getmImage()).placeholder(R.drawable.default_art).into(holder.artistImage);
        }
        else {
            holder.artistImage.setImageResource(R.drawable.default_art);
        }

    }

    public static class DataViewHolder extends RecyclerView.ViewHolder{

        public TextView artistName, tracksCount;
        public ImageView artistImage;


        public DataViewHolder(View view){
            super(view);
            artistName = (TextView) view.findViewById(R.id.artistName);
            tracksCount = (TextView) view.findViewById(R.id.trackCount);
            artistImage = (ImageView) view.findViewById(R.id.artistImage);
        }

        public void bindData(final Artist artist, final ClickListener listener){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.OnItemClick(artist);
                }
            });
        }

    }

}
