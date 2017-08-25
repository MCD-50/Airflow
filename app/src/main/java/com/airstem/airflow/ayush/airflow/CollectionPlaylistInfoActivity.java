package com.airstem.airflow.ayush.airflow;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.airstem.airflow.ayush.airflow.adapters.collection.PlaylistInfoAdapter;
import com.airstem.airflow.ayush.airflow.behaviors.OverlayViewBehavior;
import com.airstem.airflow.ayush.airflow.behaviors.TitleBehavior;
import com.airstem.airflow.ayush.airflow.events.collection.CollectionPlaylistListener;
import com.airstem.airflow.ayush.airflow.helpers.collection.CollectionConstant;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionPlaylist;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionTrack;

import java.util.ArrayList;

import jp.satorufujiwara.scrolling.MaterialScrollingLayout;
import jp.satorufujiwara.scrolling.behavior.ParallaxBehavior;

/**
 * Created by mcd-50 on 10/7/17.
 */

public class CollectionPlaylistInfoActivity extends AppCompatActivity implements CollectionPlaylistListener {


    LinearLayoutManager linearLayoutManager;
    TextView empty, title;
    ImageView image;
    View overlayView;
    RecyclerView listView;
    MaterialScrollingLayout scrollingLayout;



    ArrayList<CollectionTrack> mItems;
    PlaylistInfoAdapter mAdapter;


    CollectionPlaylist collectionPlaylist = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.collection_playlist_info_page);

        //get the intent
        collectionPlaylist = (CollectionPlaylist) getIntent().getSerializableExtra(CollectionConstant.SHARED_PASSING_COLLECTION_PLAYLIST);
        if (collectionPlaylist == null) {
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
        title = (TextView) findViewById(R.id.collection_playlist_info_page_title);
        image = (ImageView) findViewById(R.id.collection_playlist_info_page_image);
        overlayView = findViewById(R.id.collection_playlist_info_page_overlay);
        scrollingLayout = (MaterialScrollingLayout) findViewById(R.id.collection_playlist_info_page_material_scrolling);
        listView = (RecyclerView) findViewById(R.id.collection_playlist_info_page_list);

        listView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        listView.setLayoutManager(linearLayoutManager);

        //set title
        title.setText(String.valueOf(collectionPlaylist.getTitle()));

        //set scrolling activity
        setScrollingActivity();

        //set adapter
        setAdapter();
    }

    private void setScrollingActivity() {
        scrollingLayout.addBehavior(image, new ParallaxBehavior());
        scrollingLayout.addBehavior(overlayView, new OverlayViewBehavior(dp(56)));
        scrollingLayout.addBehavior(title, new TitleBehavior(getResources()));
    }

    private void setAdapter() {
        mItems = new ArrayList<>();
        mAdapter = new PlaylistInfoAdapter(CollectionPlaylistInfoActivity.this, mItems, this);
        listView.setAdapter(mAdapter);
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
    public void onPlaylistRemove(CollectionPlaylist collectionPlaylist) {

    }

    @Override
    public void onPlaylistTrackRemove(CollectionTrack collectionTrack) {

    }
}

