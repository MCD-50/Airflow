package com.airstem.airflow.ayush.airflow.fragments.fav;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.airstem.airflow.ayush.airflow.CollectionActivity;
import com.airstem.airflow.ayush.airflow.R;
import com.airstem.airflow.ayush.airflow.adapters.collection.RadioAdapter;
import com.airstem.airflow.ayush.airflow.events.collection.CollectionRadioListener;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionRadio;

import java.util.ArrayList;

import io.realm.Realm;

/**
 * Created by mcd-50 on 11/7/17.
 */

public class FavRadioFragment  extends Fragment implements CollectionRadioListener {

    Realm realm;

    boolean isLoading;
    ProgressDialog progressDialog;


    ArrayList<CollectionRadio> mItems;
    RadioAdapter mAdapter;
    RecyclerView listView;
    TextView empty;
    LinearLayoutManager linearLayoutManager;



    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fav_radio_fragment, container, false);

        progressDialog = new ProgressDialog(getContext());
        empty = (TextView) rootView.findViewById(R.id.fav_radio_fragment_empty);
        listView = (RecyclerView) rootView.findViewById(R.id.fav_radio_fragment_list);
        listView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getContext());
        listView.setLayoutManager(linearLayoutManager);

        return rootView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        realm = ((CollectionActivity)getActivity()).getRealm();
        setAdapter();
    }


    private void setAdapter() {
        mItems = new ArrayList<CollectionRadio>(realm.where(CollectionRadio.class).findAll());
        mAdapter = new RadioAdapter(getContext(), mItems, this);
        listView.setAdapter(mAdapter);
    }


    @Override
    public void onRadioClick(CollectionRadio collectionRadio) {

    }

    @Override
    public void onRadioFav(CollectionRadio collectionRadio, boolean addToFav) {

    }
}
