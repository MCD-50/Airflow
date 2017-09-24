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

import com.airstem.airflow.ayush.airflow.adapters.search.AlbumInfoAdapter;
import com.airstem.airflow.ayush.airflow.decorators.LineDivider;
import com.airstem.airflow.ayush.airflow.events.search.SearchAlbumListener;
import com.airstem.airflow.ayush.airflow.events.volly.Callback;
import com.airstem.airflow.ayush.airflow.helpers.collection.CollectionConstant;
import com.airstem.airflow.ayush.airflow.helpers.internet.InternetHelper;
import com.airstem.airflow.ayush.airflow.model.search.SearchAlbum;
import com.airstem.airflow.ayush.airflow.model.search.SearchArtist;
import com.airstem.airflow.ayush.airflow.model.search.SearchImage;
import com.airstem.airflow.ayush.airflow.model.search.SearchPaging;
import com.airstem.airflow.ayush.airflow.model.search.SearchRadio;
import com.airstem.airflow.ayush.airflow.model.search.SearchTrack;
import com.airstem.airflow.ayush.airflow.model.search.SearchVideo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * Created by mcd-50 on 9/7/17.
 */

public class SearchAlbumInfoActivity extends AppCompatActivity implements SearchAlbumListener {


    boolean isLoading;
    int nextPage = 1;
    ProgressDialog progressDialog;
    InternetHelper internetHelper;

    SwipeRefreshLayout swipeRefreshLayout;
    LinearLayoutManager linearLayoutManager;
    TextView empty;
    ImageView image;
    RecyclerView listView;
    CollapsingToolbarLayout collapsingToolbarLayout;

    ArrayList<SearchTrack> mItems;
    AlbumInfoAdapter mAdapter;


    SearchAlbum searchAlbum = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_album_info_page);

        //get the intent
        searchAlbum = (SearchAlbum) getIntent().getSerializableExtra(CollectionConstant.SHARED_PASSING_SEARCH_ALBUM);
        if (searchAlbum == null) {
            finish();
        }

        //init components
        initComponents();
    }

    private void initComponents() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.search_album_info_page_toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        internetHelper = new InternetHelper(SearchAlbumInfoActivity.this);
        progressDialog = new ProgressDialog(SearchAlbumInfoActivity.this);

        empty = (TextView) findViewById(R.id.search_album_info_page_empty);
        image = (ImageView) findViewById(R.id.search_album_info_page_image);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.search_album_info_page_refresh);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.search_album_info_page_collapsing_toolbar);

        listView = (RecyclerView) findViewById(R.id.search_album_info_page_list);

        listView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        listView.setLayoutManager(linearLayoutManager);
        listView.addItemDecoration(new LineDivider(this));

        //set title
        collapsingToolbarLayout.setTitle(String.valueOf(searchAlbum.getTitle()));

        //set scrolling activity
        setScrollingActivity();

        //set adapter
        setAdapter();

        //make request
        makeRequest(true);
    }


    private void setScrollingActivity() {
        ArrayList<SearchImage> searchImages = searchAlbum.getArtworkUrl();
        if(searchImages.size() > 0){
            int lastIndex = searchImages.size() - 1;
            Picasso.with(SearchAlbumInfoActivity.this).load(searchImages.get(lastIndex).getUri()).placeholder(R.drawable.default_art).into(image);
        }else{
            Picasso.with(SearchAlbumInfoActivity.this).load(R.drawable.default_art).placeholder(R.drawable.default_art).into(image);
        }
    }

    private void setAdapter() {
        mItems = new ArrayList<>();
        mAdapter = new AlbumInfoAdapter(SearchAlbumInfoActivity.this, mItems, this);
        listView.setAdapter(mAdapter);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
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
                if (nextPage != -1 && !isLoading && totalItemCount <= lastVisibleItem) {
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

            internetHelper.searchAlbumById(searchAlbum.getId(), new Callback() {
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
                    mItems.addAll(searchTracks);
                    mAdapter.notifyDataSetChanged();
                    progressDialog.dismiss();
                    nextPage = -1;
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

    public void onAlbumClick(SearchAlbum searchAlbum) {

    }

    @Override
    public void onAlbumTrackClick(SearchTrack searchTrack) {

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
