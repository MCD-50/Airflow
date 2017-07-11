package com.airstem.airflow.ayush.airflow.fragments.collection;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.airstem.airflow.ayush.airflow.R;
import com.airstem.airflow.ayush.airflow.adapters.collection.VideoAdapter;
import com.airstem.airflow.ayush.airflow.events.CollectionVideoClickListener;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionVideo;

import java.util.ArrayList;

/**
 * Created by mcd-50 on 10/7/17.
 */

public class CollectionVideoFragment  extends Fragment implements CollectionVideoClickListener {
    TextView textView;
    RecyclerView listView;

    ArrayList<CollectionVideo> mItems;
    VideoAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.collection_video_fragment, container, false);

        textView = (TextView) rootView.findViewById(R.id.collection_video_fragment_empty);
        listView = (RecyclerView) rootView.findViewById(R.id.collection_video_fragment_list);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onItemClick(CollectionVideo collectionVideo) {

    }
}
