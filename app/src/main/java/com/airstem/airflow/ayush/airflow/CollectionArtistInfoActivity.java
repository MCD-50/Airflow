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

import com.airstem.airflow.ayush.airflow.adapters.collection.ArtistInfoAdapter;
import com.airstem.airflow.ayush.airflow.behaviors.OverlayViewBehavior;
import com.airstem.airflow.ayush.airflow.behaviors.TitleBehavior;
import com.airstem.airflow.ayush.airflow.events.collection.CollectionArtistListener;
import com.airstem.airflow.ayush.airflow.helpers.collection.CollectionConstant;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionArtist;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionTrack;

import java.util.ArrayList;

import jp.satorufujiwara.scrolling.MaterialScrollingLayout;
import jp.satorufujiwara.scrolling.behavior.ParallaxBehavior;

/**
 * Created by mcd-50 on 10/7/17.
 */

public class CollectionArtistInfoActivity extends AppCompatActivity implements CollectionArtistListener {


    LinearLayoutManager linearLayoutManager;
    TextView empty, title;
    ImageView image;
    View overlayView;
    RecyclerView listView;
    MaterialScrollingLayout scrollingLayout;


    ArrayList<CollectionTrack> mItems;
    ArtistInfoAdapter mAdapter;


    CollectionArtist collectionArtist = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.collection_artist_info_page);

        //get the intent
        collectionArtist = (CollectionArtist) getIntent().getSerializableExtra(CollectionConstant.SHARED_PASSING_COLLECTION_ARTIST);
        if (collectionArtist == null) {
            finish();
        }

        //init components
        initComponents();
    }

    private void initComponents() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.collection_artist_info_page_toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        empty = (TextView) findViewById(R.id.collection_artist_info_page_empty);
        title = (TextView) findViewById(R.id.collection_artist_info_page_title);
        image = (ImageView) findViewById(R.id.collection_artist_info_page_image);
        overlayView = findViewById(R.id.collection_artist_info_page_overlay);
        scrollingLayout = (MaterialScrollingLayout) findViewById(R.id.collection_artist_info_page_material_scrolling);
        listView = (RecyclerView) findViewById(R.id.collection_artist_info_page_list);

        listView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        listView.setLayoutManager(linearLayoutManager);

        //set title
        title.setText(String.valueOf(collectionArtist.getTitle()));

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
        mAdapter = new ArtistInfoAdapter(CollectionArtistInfoActivity.this, mItems, this);
        listView.setAdapter(mAdapter);
    }

    public int dp(final int dp) {
        return (int) (dp * getResources().getDisplayMetrics().density);
    }


    @Override
    public void onArtistClick(CollectionArtist collectionArtist) {

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
