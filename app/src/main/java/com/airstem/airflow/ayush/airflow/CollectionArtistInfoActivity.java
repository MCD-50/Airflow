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
import com.airstem.airflow.ayush.airflow.model.search.SearchImage;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmList;
import jp.satorufujiwara.scrolling.MaterialScrollingLayout;
import jp.satorufujiwara.scrolling.behavior.ParallaxBehavior;

/**
 * Created by mcd-50 on 10/7/17.
 */

public class CollectionArtistInfoActivity extends AppCompatActivity implements CollectionArtistListener {


    Realm realm;

    LinearLayoutManager linearLayoutManager;
    TextView empty;
    ImageView image;
    RecyclerView listView;
    CollapsingToolbarLayout collapsingToolbarLayout;


    RealmList<CollectionTrack> mItems;
    ArtistInfoAdapter mAdapter;


    CollectionArtist collectionArtist = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.collection_artist_info_page);

        realm = Realm.getDefaultInstance();

        //get the intent
        String id  = getIntent().getStringExtra(CollectionConstant.SHARED_PASSING_COLLECTION_ARTIST_LOCAL_ID);

        if (!TextUtils.isEmpty(id)) {
            collectionArtist = realm.where(CollectionArtist.class).equalTo("mLocalId", id).findFirst();
            if(collectionArtist == null) finish();
        }else{
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
        image = (ImageView) findViewById(R.id.collection_artist_info_page_image);
        listView = (RecyclerView) findViewById(R.id.collection_artist_info_page_list);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collection_artist_info_page_collapsing_toolbar);

        listView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        listView.setLayoutManager(linearLayoutManager);

        //set title
        collapsingToolbarLayout.setTitle(String.valueOf(collectionArtist.getTitle()));

        //set scrolling activity
        setScrollingActivity();

        //set adapter
        setAdapter();
    }

    private void setScrollingActivity() {
        String artworkUrl = collectionArtist.getArtworkUrl();
        if(!TextUtils.isEmpty(artworkUrl)){
            Picasso.with(CollectionArtistInfoActivity.this).load(artworkUrl).placeholder(R.drawable.default_art).into(image);
        }else{
            Picasso.with(CollectionArtistInfoActivity.this).load(R.drawable.default_art).placeholder(R.drawable.default_art).into(image);
        }
    }

    private void setAdapter() {
        mItems = collectionArtist.getTracks();
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
