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
import com.airstem.airflow.ayush.airflow.adapters.search.ArtistInfoAlbumAdapter;
import com.airstem.airflow.ayush.airflow.events.search.SearchArtistListener;
import com.airstem.airflow.ayush.airflow.model.search.SearchArtist;
import com.airstem.airflow.ayush.airflow.model.search.SearchArtistInfoAlbum;
import com.airstem.airflow.ayush.airflow.model.search.SearchArtistInfoTrack;

import java.util.ArrayList;

/**
 * Created by mcd-50 on 9/7/17.
 */

public class ArtistInfoAlbumFragment extends Fragment implements SearchArtistListener {

    TextView textView;
    RecyclerView listView;

    ArrayList<SearchArtistInfoAlbum> mItems;
    ArtistInfoAlbumAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.search_artist_info_page_album_fragment, container, false);

        textView = (TextView) rootView.findViewById(R.id.search_artist_info_album_fragment_empty);
        listView = (RecyclerView) rootView.findViewById(R.id.search_artist_info_album_fragment_list);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public void onArtistClick(SearchArtist searchArtist) {

    }

    @Override
    public void onArtistTrackClick(SearchArtistInfoTrack searchArtistInfoTrack) {

    }

    @Override
    public void onArtistAlbumClick(SearchArtistInfoAlbum searchArtistInfoAlbum) {

    }
}
