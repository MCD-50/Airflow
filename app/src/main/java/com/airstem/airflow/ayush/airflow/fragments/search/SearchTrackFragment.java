package com.airstem.airflow.ayush.airflow.fragments.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.airstem.airflow.ayush.airflow.R;
import com.airstem.airflow.ayush.airflow.adapters.search.AlbumAdapter;
import com.airstem.airflow.ayush.airflow.helpers.ResourceHelper;

import java.util.ArrayList;



/**
 * Created by mcd-50 on 8/7/17.
 */

public class SearchTrackFragment extends Fragment {




    TextView textView;
    RecyclerView listView;
    SwipeRefreshLayout swipeRefreshLayout;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.search_track_fragment, container, false);

        textView = (TextView) rootView.findViewById(R.id.search_track_fragment_empty);
        listView = (RecyclerView) rootView.findViewById(R.id.search_track_fragment_list);
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.search_track_fragment_refresh);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}