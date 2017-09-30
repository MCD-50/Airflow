package com.airstem.airflow.ayush.airflow.fragments.collection;

import android.app.ProgressDialog;
import android.content.Intent;
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
import com.airstem.airflow.ayush.airflow.CollectionArtistInfoActivity;
import com.airstem.airflow.ayush.airflow.CollectionPlaylistInfoActivity;
import com.airstem.airflow.ayush.airflow.R;
import com.airstem.airflow.ayush.airflow.adapters.collection.PlaylistAdapter;
import com.airstem.airflow.ayush.airflow.decorators.LineDivider;
import com.airstem.airflow.ayush.airflow.enums.collection.Action;
import com.airstem.airflow.ayush.airflow.events.collection.CollectionPlaylistListener;
import com.airstem.airflow.ayush.airflow.helpers.collection.CollectionConstant;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionPlaylist;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionTrack;

import io.realm.OrderedCollectionChangeSet;
import io.realm.OrderedRealmCollectionChangeListener;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by mcd-50 on 10/7/17.
 */

public class CollectionPlaylistFragment  extends Fragment implements CollectionPlaylistListener {

    Realm realm;

    ProgressDialog progressDialog;


    RealmResults<CollectionPlaylist> mItems;
    PlaylistAdapter mAdapter;
    RecyclerView listView;
    TextView empty;
    LinearLayoutManager linearLayoutManager;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.collection_playlist_fragment, container, false);

        progressDialog = new ProgressDialog(getContext());


        empty = (TextView) rootView.findViewById(R.id.collection_playlist_fragment_empty);
        listView = (RecyclerView) rootView.findViewById(R.id.collection_playlist_fragment_list);
        listView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getContext());
        listView.setLayoutManager(linearLayoutManager);
        listView.addItemDecoration(new LineDivider(getContext()));

        return rootView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        realm = ((CollectionActivity)getActivity()).getRealm();
        setAdapter();
    }


    private void setAdapter() {
        mItems = realm.where(CollectionPlaylist.class).findAllSorted("mTitle");
        mAdapter = new PlaylistAdapter(getContext(), mItems, this);
        listView.setAdapter(mAdapter);
        mItems.addChangeListener(new OrderedRealmCollectionChangeListener<RealmResults<CollectionPlaylist>>() {
            @Override
            public void onChange(RealmResults<CollectionPlaylist> collectionPlaylists, OrderedCollectionChangeSet changeSet) {
                // Query results are updated in real time with fine grained notifications.
                mItems = collectionPlaylists.sort("mTitle");
                mAdapter.notifyDataSetChanged();
                changeSet.getInsertions(); // => [0] is added.
            }
        });
    }



    @Override
    public void onPlaylistClick(CollectionPlaylist collectionPlaylist) {
        Intent collectionIntent = new Intent(getActivity(), CollectionPlaylistInfoActivity.class);
        collectionIntent.putExtra(CollectionConstant.SHARED_PASSING_COLLECTION_PLAYLIST_TITLE, collectionPlaylist.getTitle());
        startActivity(collectionIntent);
    }

    @Override
    public void onPlaylistTrackClick(CollectionTrack collectionTrack) {

    }

    @Override
    public void onPlaylistTrackOptions(CollectionTrack collectionTrack, Action action) {

    }

    @Override
    public void onPlaylistOptions(CollectionPlaylist collectionPlaylist, Action action) {

    }
}
