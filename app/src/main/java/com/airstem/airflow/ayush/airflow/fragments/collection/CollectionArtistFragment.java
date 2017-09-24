package com.airstem.airflow.ayush.airflow.fragments.collection;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.airstem.airflow.ayush.airflow.CollectionActivity;
import com.airstem.airflow.ayush.airflow.CollectionArtistInfoActivity;
import com.airstem.airflow.ayush.airflow.R;
import com.airstem.airflow.ayush.airflow.SearchAlbumInfoActivity;
import com.airstem.airflow.ayush.airflow.adapters.collection.ArtistAdapter;
import com.airstem.airflow.ayush.airflow.decorators.OffsetDivider;
import com.airstem.airflow.ayush.airflow.events.collection.CollectionArtistListener;
import com.airstem.airflow.ayush.airflow.helpers.collection.CollectionConstant;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionArtist;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionTrack;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by mcd-50 on 10/7/17.
 */

public class CollectionArtistFragment extends Fragment implements CollectionArtistListener {


    Realm realm;

    boolean isLoading;
    ProgressDialog progressDialog;


    RealmResults<CollectionArtist> mItems;
    ArtistAdapter mAdapter;
    RecyclerView listView;
    TextView empty;
    GridLayoutManager gridLayoutManager;


    int numberOfColumns = 2;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.collection_artist_fragment, container, false);

        progressDialog = new ProgressDialog(getContext());

        empty = (TextView) rootView.findViewById(R.id.collection_artist_fragment_empty);
        listView = (RecyclerView) rootView.findViewById(R.id.collection_artist_fragment_list);
        listView.setHasFixedSize(true);
        gridLayoutManager = new GridLayoutManager(getActivity(), numberOfColumns);
        listView.setLayoutManager(gridLayoutManager);
        listView.addItemDecoration(new OffsetDivider(getContext(), R.dimen.five_dp_margin));

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        realm = ((CollectionActivity)getActivity()).getRealm();
        setAdapter();
    }


    private void setAdapter() {
        mItems = realm.where(CollectionArtist.class).findAll();
        mAdapter = new ArtistAdapter(getContext(), mItems, this);
        listView.setAdapter(mAdapter);
    }


    @Override
    public void onArtistClick(CollectionArtist collectionArtist) {

        Intent collectionIntent = new Intent(getActivity(), CollectionArtistInfoActivity.class);
        collectionIntent.putExtra(CollectionConstant.SHARED_PASSING_COLLECTION_ARTIST_LOCAL_ID, collectionArtist.getLocalId());
        startActivity(collectionIntent);
    }

    @Override
    public void onArtistTrackClick(CollectionTrack collectionTrack) {

    }

    @Override
    public void onArtistRemove(CollectionArtist collectionArtist) {

    }

    @Override
    public void onArtistTrackRemove(CollectionTrack collectionTrack) {

    }


}
