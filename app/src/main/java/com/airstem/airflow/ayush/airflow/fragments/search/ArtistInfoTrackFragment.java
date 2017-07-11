package com.airstem.airflow.ayush.airflow.fragments.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.airstem.airflow.ayush.airflow.R;
import com.airstem.airflow.ayush.airflow.adapters.search.ArtistInfoTrackAdapter;
import com.airstem.airflow.ayush.airflow.events.SearchArtistInfoTrackClickListener;
import com.airstem.airflow.ayush.airflow.model.search.SearchArtistInfoTrack;

import java.util.ArrayList;

/**
 * Created by mcd-50 on 9/7/17.
 */

public class ArtistInfoTrackFragment extends Fragment implements SearchArtistInfoTrackClickListener{

    TextView textView;
    RecyclerView listView;

    ArrayList<SearchArtistInfoTrack> mItems;
    ArtistInfoTrackAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.search_artist_info_track_fragment, container, false);

        textView = (TextView) rootView.findViewById(R.id.search_artist_info_track_fragment_empty);
        listView = (RecyclerView) rootView.findViewById(R.id.search_artist_info_track_fragment_list);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public void onItemClick(SearchArtistInfoTrack searchArtistInfoTrack) {

    }
}
