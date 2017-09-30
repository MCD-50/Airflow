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
import com.airstem.airflow.ayush.airflow.FavActivity;
import com.airstem.airflow.ayush.airflow.R;
import com.airstem.airflow.ayush.airflow.adapters.collection.RadioAdapter;
import com.airstem.airflow.ayush.airflow.enums.collection.Action;
import com.airstem.airflow.ayush.airflow.events.collection.CollectionRadioListener;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionRadio;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionRadio;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionTrack;

import java.util.ArrayList;

import io.realm.OrderedCollectionChangeSet;
import io.realm.OrderedRealmCollectionChangeListener;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by mcd-50 on 11/7/17.
 */

public class FavRadioFragment  extends Fragment implements CollectionRadioListener {

    Realm realm;
    
    ProgressDialog progressDialog;


    RealmResults<CollectionRadio> mItems;
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
        realm = ((FavActivity)getActivity()).getRealm();
        setAdapter();
    }


    private void setAdapter() {
        mItems = realm.where(CollectionRadio.class).equalTo("mIsFav", true).findAllSorted("mTitle");
        mAdapter = new RadioAdapter(getContext(), mItems, this);
        listView.setAdapter(mAdapter);
        mItems.addChangeListener(new OrderedRealmCollectionChangeListener<RealmResults<CollectionRadio>>() {
            @Override
            public void onChange(RealmResults<CollectionRadio> collectionRadios, OrderedCollectionChangeSet changeSet) {
                // Query results are updated in real time with fine grained notifications.
                mItems = collectionRadios.sort("mTitle");
                mAdapter.notifyDataSetChanged();
                changeSet.getInsertions(); // => [0] is added.
            }
        });
    }


    @Override
    public void onRadioClick(CollectionRadio collectionRadio) {

    }

    @Override
    public void onRadioOptions(CollectionRadio collectionRadio, Action action) {

    }
}
