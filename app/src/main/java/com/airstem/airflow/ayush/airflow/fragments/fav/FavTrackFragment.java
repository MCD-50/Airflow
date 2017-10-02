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

import com.afollestad.materialdialogs.MaterialDialog;
import com.airstem.airflow.ayush.airflow.CollectionActivity;
import com.airstem.airflow.ayush.airflow.FavActivity;
import com.airstem.airflow.ayush.airflow.R;
import com.airstem.airflow.ayush.airflow.adapters.collection.TrackAdapter;
import com.airstem.airflow.ayush.airflow.decorators.LineDivider;
import com.airstem.airflow.ayush.airflow.enums.collection.Action;
import com.airstem.airflow.ayush.airflow.events.collection.CollectionTrackListener;
import com.airstem.airflow.ayush.airflow.helpers.collection.ActionHelper;
import com.airstem.airflow.ayush.airflow.helpers.collection.CollectionConstant;
import com.airstem.airflow.ayush.airflow.helpers.collection.CollectionHelper;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionTrack;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionTrack;

import java.util.ArrayList;

import io.realm.OrderedCollectionChangeSet;
import io.realm.OrderedRealmCollectionChangeListener;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by mcd-50 on 11/7/17.
 */

public class FavTrackFragment extends Fragment implements CollectionTrackListener {


    Realm realm;
    ActionHelper actionHelper;
    
    ProgressDialog progressDialog;


    RealmResults<CollectionTrack> mItems;
    TrackAdapter mAdapter;
    RecyclerView listView;
    TextView empty;
    LinearLayoutManager linearLayoutManager;



    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fav_track_fragment, container, false);

        progressDialog = new ProgressDialog(getContext());
        empty = (TextView) rootView.findViewById(R.id.fav_track_fragment_empty);
        listView = (RecyclerView) rootView.findViewById(R.id.fav_track_fragment_list);
        listView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getContext());
        listView.setLayoutManager(linearLayoutManager);
        listView.addItemDecoration(new LineDivider(getContext()));
        return rootView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        realm = ((FavActivity)getActivity()).getRealm();
        actionHelper = ((FavActivity)getActivity()).getActionHelper();
        setAdapter();
    }


    private void setAdapter() {
        mItems = realm.where(CollectionTrack.class).equalTo("mIsFav", true).findAllSorted("mTitle");
        mAdapter = new TrackAdapter(getContext(), mItems, this);
        listView.setAdapter(mAdapter);
        mItems.addChangeListener(new OrderedRealmCollectionChangeListener<RealmResults<CollectionTrack>>() {
            @Override
            public void onChange(RealmResults<CollectionTrack> collectionTracks, OrderedCollectionChangeSet changeSet) {
                // Query results are updated in real time with fine grained notifications.
                mItems = collectionTracks.sort("mTitle");
                mAdapter.notifyDataSetChanged();
                changeSet.getInsertions(); // => [0] is added.
            }
        });
    }
    
    

    @Override
    public void onTrackClick(final CollectionTrack collectionTrack) {

    }

    @Override
    public void onTrackOptions(final CollectionTrack collectionTrack, final Action action) {
        final ArrayList<String> options =   CollectionHelper.prepareOptionFromTrack(collectionTrack);
        new MaterialDialog.Builder(getContext())
                .title("Options")
                .items(options)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                        actionHelper.performAction(options.get(which), collectionTrack, getContext());
                    }
                })
                .show();
    }
}