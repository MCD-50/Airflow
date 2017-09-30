package com.airstem.airflow.ayush.airflow;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.airstem.airflow.ayush.airflow.adapters.collection.PlaylistInfoAdapter;
import com.airstem.airflow.ayush.airflow.enums.collection.Action;
import com.airstem.airflow.ayush.airflow.events.collection.CollectionPlaylistListener;
import com.airstem.airflow.ayush.airflow.helpers.collection.CollectionConstant;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionPlaylist;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionTrack;
import com.squareup.picasso.Picasso;

import io.realm.OrderedCollectionChangeSet;
import io.realm.OrderedRealmCollectionChangeListener;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by mcd-50 on 10/7/17.
 */

public class CollectionPlaylistInfoActivity extends AppCompatActivity implements CollectionPlaylistListener {


    Realm realm;

    LinearLayoutManager linearLayoutManager;
    TextView empty;
    ImageView image;
    RecyclerView listView;
    CollapsingToolbarLayout collapsingToolbarLayout;



    RealmResults<CollectionTrack> mItems;
    PlaylistInfoAdapter mAdapter;


    CollectionPlaylist collectionPlaylist = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.collection_playlist_info_page);

        realm = Realm.getDefaultInstance();

        //get the intent
        String title  = getIntent().getStringExtra(CollectionConstant.SHARED_PASSING_COLLECTION_PLAYLIST_TITLE);

        if (!TextUtils.isEmpty(title)) {
            collectionPlaylist = realm.where(CollectionPlaylist.class).equalTo("mTitle", title).findFirst();
            if(collectionPlaylist == null) finish();
        }else{
            finish();
        }

        //init components
        initComponents();
    }

    private void initComponents() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.collection_playlist_info_page_toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        empty = (TextView) findViewById(R.id.collection_playlist_info_page_empty);
        image = (ImageView) findViewById(R.id.collection_playlist_info_page_image);
        listView = (RecyclerView) findViewById(R.id.collection_playlist_info_page_list);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collection_playlist_info_page_collapsing_toolbar);

        listView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        listView.setLayoutManager(linearLayoutManager);

        //set title
        collapsingToolbarLayout.setTitle(String.valueOf(collectionPlaylist.getTitle()));

        //set adapter
        setAdapter();
    }


    private void setAdapter() {

        if(collectionPlaylist.getTitle().equalsIgnoreCase("Last Added")){
            mItems =  realm.where(CollectionTrack.class).findAllSorted("mModifiedOn", Sort.DESCENDING);
        }else if(collectionPlaylist.getTitle().equalsIgnoreCase("Recent")){
            mItems =  realm.where(CollectionTrack.class).notEqualTo("mLastPlayed", "").findAllSorted("mLastPlayed", Sort.DESCENDING);
        }else if(collectionPlaylist.getTitle().equalsIgnoreCase("Most Played")){
            mItems =  realm.where(CollectionTrack.class).greaterThan("mPlayCount", 0).findAllSorted("mPlayCount", Sort.DESCENDING);
        }else{
            mItems =  realm.where(CollectionTrack.class).equalTo("mPlaylistId", collectionPlaylist.getTitle()).findAllSorted("mTitle");
        }

        mAdapter = new PlaylistInfoAdapter(CollectionPlaylistInfoActivity.this, mItems, this);
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

        //set scrolling activity
        setScrollingActivity();
    }

    private void setScrollingActivity() {
        String artworkUrl = "";
        if(mItems.size() > 0){
            artworkUrl = mItems.get(0).getArtworkUrl();
        }
        if(!TextUtils.isEmpty(artworkUrl)){
            Picasso.with(CollectionPlaylistInfoActivity.this).load(artworkUrl).placeholder(R.drawable.default_art).into(image);
        }else{
            Picasso.with(CollectionPlaylistInfoActivity.this).load(R.drawable.default_art).placeholder(R.drawable.default_art).into(image);
        }
    }

    public int dp(final int dp) {
        return (int) (dp * getResources().getDisplayMetrics().density);
    }


    @Override
    public void onPlaylistClick(CollectionPlaylist collectionPlaylist) {

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

