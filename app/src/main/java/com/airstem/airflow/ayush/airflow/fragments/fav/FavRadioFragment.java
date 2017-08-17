package com.airstem.airflow.ayush.airflow.fragments.fav;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.airstem.airflow.ayush.airflow.R;
import com.airstem.airflow.ayush.airflow.adapters.collection.RadioAdapter;
import com.airstem.airflow.ayush.airflow.events.collection.CollectionRadioListener;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionRadio;

import java.util.ArrayList;

/**
 * Created by mcd-50 on 11/7/17.
 */

public class FavRadioFragment  extends Fragment implements CollectionRadioListener {
    TextView textView;
    RecyclerView listView;

    ArrayList<CollectionRadio> mItems;
    RadioAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fav_radio_fragment, container, false);

        textView = (TextView) rootView.findViewById(R.id.fav_radio_fragment_empty);
        listView = (RecyclerView) rootView.findViewById(R.id.fav_radio_fragment_list);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onRadioClick(CollectionRadio collectionRadio) {

    }
}
