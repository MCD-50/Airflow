package com.airstem.airflow.ayush.airflow.fragments.collection;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.airstem.airflow.ayush.airflow.CollectionActivity;
import com.airstem.airflow.ayush.airflow.CollectionArtistInfoActivity;
import com.airstem.airflow.ayush.airflow.R;
import com.airstem.airflow.ayush.airflow.SearchAlbumInfoActivity;
import com.airstem.airflow.ayush.airflow.adapters.collection.ArtistAdapter;
import com.airstem.airflow.ayush.airflow.adapters.option.OptionAdapter;
import com.airstem.airflow.ayush.airflow.decorators.OffsetDivider;
import com.airstem.airflow.ayush.airflow.enums.collection.Action;
import com.airstem.airflow.ayush.airflow.events.collection.CollectionArtistListener;
import com.airstem.airflow.ayush.airflow.helpers.collection.CollectionConstant;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionArtist;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionArtist;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionTrack;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnItemClickListener;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;

import io.realm.OrderedCollectionChangeSet;
import io.realm.OrderedRealmCollectionChangeListener;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by mcd-50 on 10/7/17.
 */

public class CollectionArtistFragment extends Fragment implements CollectionArtistListener {


    Realm realm;

    ProgressDialog progressDialog;


    RealmResults<CollectionArtist> mItems;
    ArtistAdapter mAdapter;
    RecyclerView listView;
    TextView empty;
    GridLayoutManager gridLayoutManager;

    OptionAdapter mOptionAdapter;

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
        mItems = realm.where(CollectionArtist.class).findAllSorted("mTitle");
        mAdapter = new ArtistAdapter(getContext(), mItems, this);
        mOptionAdapter = new OptionAdapter(getContext(), CollectionConstant.COLLECTION_ARTIST_OPTIONS);
        listView.setAdapter(mAdapter);
        mItems.addChangeListener(new OrderedRealmCollectionChangeListener<RealmResults<CollectionArtist>>() {
            @Override
            public void onChange(RealmResults<CollectionArtist> collectionArtists, OrderedCollectionChangeSet changeSet) {
                // Query results are updated in real time with fine grained notifications.
                mItems = collectionArtists.sort("mTitle");
                mAdapter.notifyDataSetChanged();
                changeSet.getInsertions(); // => [0] is added.
            }
        });
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
    public void onArtistTrackOptions(CollectionTrack collectionTrack, Action action) {

    }

    @Override
    public void onArtistOptions(CollectionArtist collectionArtist, Action action) {

        DialogPlus dialog = DialogPlus.newDialog(getActivity())
                .setAdapter(mOptionAdapter)
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(DialogPlus dialog, Object item, View view, int position) {
                        Toast.makeText(getContext(), mOptionAdapter.getItem(position).getText(), Toast.LENGTH_SHORT).show();
                    }
                })
                .setGravity(Gravity.BOTTOM)
                .setExpanded(true)  // This will enable the expand feature, (similar to android L share dialog)
                .create();
        dialog.show();
    }
}
