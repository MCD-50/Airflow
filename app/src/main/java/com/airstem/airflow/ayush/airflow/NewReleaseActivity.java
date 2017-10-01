package com.airstem.airflow.ayush.airflow;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.airstem.airflow.ayush.airflow.adapters.search.TrackAdapter;
import com.airstem.airflow.ayush.airflow.decorators.LineDivider;
import com.airstem.airflow.ayush.airflow.events.search.SearchTrackListener;
import com.airstem.airflow.ayush.airflow.events.volly.Callback;
import com.airstem.airflow.ayush.airflow.helpers.collection.CollectionConstant;
import com.airstem.airflow.ayush.airflow.helpers.collection.CollectionHelper;
import com.airstem.airflow.ayush.airflow.helpers.database.DatabaseHelper;
import com.airstem.airflow.ayush.airflow.helpers.internet.InternetHelper;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionArtist;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionTrack;
import com.airstem.airflow.ayush.airflow.model.search.SearchAlbum;
import com.airstem.airflow.ayush.airflow.model.search.SearchArtist;
import com.airstem.airflow.ayush.airflow.model.search.SearchImage;
import com.airstem.airflow.ayush.airflow.model.search.SearchPaging;
import com.airstem.airflow.ayush.airflow.model.search.SearchRadio;
import com.airstem.airflow.ayush.airflow.model.search.SearchTrack;
import com.airstem.airflow.ayush.airflow.model.search.SearchVideo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by mcd-50 on 1/10/17.
 */

public class NewReleaseActivity extends AppCompatActivity implements SearchTrackListener {
    Realm realm;

    boolean isLoading;
    String nextPage = null;
    ProgressDialog progressDialog;
    InternetHelper internetHelper;

    SwipeRefreshLayout swipeRefreshLayout;
    LinearLayoutManager linearLayoutManager;
    TextView empty;
    ImageView image;
    RecyclerView listView;
    CollapsingToolbarLayout collapsingToolbarLayout;

    ArrayList<SearchTrack> mItems;
    TrackAdapter mAdapter;
    

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_release_info_page);

        realm = Realm.getDefaultInstance();
        
        //init components
        initComponents();
    }

    private void initComponents() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.new_release_info_page_toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        internetHelper = new InternetHelper(NewReleaseActivity.this);
        progressDialog = new ProgressDialog(NewReleaseActivity.this);

        empty = (TextView) findViewById(R.id.new_release_info_page_empty);
        image = (ImageView) findViewById(R.id.new_release_info_page_image);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.new_release_info_page_refresh);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.new_release_info_page_collapsing_toolbar);

        listView = (RecyclerView) findViewById(R.id.new_release_info_page_list);

        listView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        listView.setLayoutManager(linearLayoutManager);
        listView.addItemDecoration(new LineDivider(this));

        //set title
        collapsingToolbarLayout.setTitle("New Releases");
        //set scrolling activity
        setScrollingActivity();

        //set adapter
        setAdapter();

        //make request
        makeRequest(true);
    }


    private void setScrollingActivity() {
        Picasso.with(NewReleaseActivity.this).load(R.drawable.top_icon).placeholder(R.drawable.default_art).into(image);
    }

    private void setAdapter() {
        mItems = new ArrayList<>();
        mAdapter = new TrackAdapter(NewReleaseActivity.this, mItems, this);
        listView.setAdapter(mAdapter);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                nextPage = null;
                mItems.clear();
                makeRequest(true);
            }
        });
    }


    private void makeRequest(boolean showDialog){
        if(internetHelper.isNetworkAvailable()){
            onNetworkAvailable(showDialog);
        }else {
            empty.setVisibility(View.VISIBLE);
        }
    }


    private void onNetworkAvailable(boolean showDialog){
        loadData(showDialog);
        empty.setVisibility(View.GONE);
        listView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int totalItemCount = linearLayoutManager.getItemCount();
                int lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if (nextPage != null && !isLoading && totalItemCount <= (lastVisibleItem + 1)) {
                    loadData(true);
                }
            }
        });
    }


    private void loadData(boolean showDialog) {
        try {
            isLoading = true;
            if(showDialog){
                progressDialog.setMessage("Please wait...");
                progressDialog.setCancelable(false);
                progressDialog.show();
            }

            internetHelper.searchNewData(new Callback() {
                @Override
                public void OnSuccess(ArrayList<Object> items) {
                    int x = 1;
                }

                @Override
                public void onSearch(ArrayList<SearchTrack> tracks, ArrayList<SearchAlbum> albums, ArrayList<SearchArtist> artists, ArrayList<SearchVideo> videos, ArrayList<SearchRadio> radios) {
                    int x = 1;
                }

                @Override
                public void onArtistImages(ArrayList<SearchImage> searchImages) {
                    int x = 1;
                }

                @Override
                public void onAlbumImages(ArrayList<SearchImage> searchImages) {
                    int x = 1;
                }

                @Override
                public void onLyrics(String text) {
                    int x = 1;
                }

                @Override
                public void onSuccess(ArrayList<SearchTrack> searchTracks, ArrayList<SearchArtist> searchArtists, ArrayList<SearchAlbum> searchAlbums, SearchPaging searchPaging) {
                    int x = 1;
                }

                @Override
                public void onTracks(ArrayList<SearchTrack> searchTracks, String next) {
                    mItems.addAll(searchTracks);
                    mAdapter.notifyDataSetChanged();
                    progressDialog.dismiss();
                    nextPage = null;
                    swipeRefreshLayout.setRefreshing(false);
                    isLoading = false;
                }

                @Override
                public void onVideos(ArrayList<SearchVideo> searchVideos, String next) {
                    int x = 1;
                }

                @Override
                public void onRadios(ArrayList<SearchRadio> searchRadios) {
                    int x = 1;
                }

                @Override
                public void OnFailure(String message) {
                    progressDialog.dismiss();
                    swipeRefreshLayout.setRefreshing(false);
                    isLoading = false;
                }
            });

        } catch (Exception e) {
            isLoading = false;
            progressDialog.dismiss();
            swipeRefreshLayout.setRefreshing(false);
            empty.setVisibility(View.VISIBLE);

            e.printStackTrace();
        }
    }


    public int dp(final int dp) {
        return (int) (dp * getResources().getDisplayMetrics().density);
    }

    @Override
    public void onTrackClick(SearchTrack searchTrack) {
        final CollectionArtist collectionArtist = CollectionHelper.getCollectionArtistFromTrack(searchTrack);
        final CollectionTrack collectionTrack = CollectionHelper.getCollectionTrack(searchTrack, collectionArtist);

        RealmResults<CollectionArtist> collectionArtistRealmResults = realm.where(CollectionArtist.class).equalTo("mTitle", collectionArtist.getTitle()).findAll();
        boolean updateOrCreate = true;
        if(collectionArtistRealmResults.size() > 0 && collectionArtistRealmResults.get(0).getIsOffline()){
            updateOrCreate = false;
        }

        if(updateOrCreate){
            DatabaseHelper.createOrUpdateArtists(realm, new ArrayList<CollectionArtist>(){{add(collectionArtist);}});
            DatabaseHelper.createOrUpdateTracks(realm, new ArrayList<CollectionTrack>(){{add(collectionTrack);}});
        }else{
            collectionTrack.setArtistId(collectionArtistRealmResults.get(0).getId());
            DatabaseHelper.createOrUpdateTracks(realm, new ArrayList<CollectionTrack>(){{add(collectionTrack);}});
        }
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

