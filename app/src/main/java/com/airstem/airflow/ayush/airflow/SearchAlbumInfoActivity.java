package com.airstem.airflow.ayush.airflow;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.airstem.airflow.ayush.airflow.adapters.search.AlbumInfoAdapter;
import com.airstem.airflow.ayush.airflow.behaviors.OverlayViewBehavior;
import com.airstem.airflow.ayush.airflow.behaviors.TitleBehavior;
import com.airstem.airflow.ayush.airflow.events.search.SearchAlbumListener;
import com.airstem.airflow.ayush.airflow.helpers.collection.CollectionConstant;
import com.airstem.airflow.ayush.airflow.helpers.internet.InternetHelper;
import com.airstem.airflow.ayush.airflow.model.search.SearchAlbum;
import com.airstem.airflow.ayush.airflow.model.search.SearchTrack;

import java.util.ArrayList;

import jp.satorufujiwara.scrolling.MaterialScrollingLayout;
import jp.satorufujiwara.scrolling.behavior.ParallaxBehavior;

/**
 * Created by mcd-50 on 9/7/17.
 */

public class SearchAlbumInfoActivity extends AppCompatActivity implements SearchAlbumListener {


    boolean isLoading;
    int nextPage = 1;
    ProgressDialog progressDialog;
    InternetHelper internetHelper;

    LinearLayoutManager linearLayoutManager;
    TextView empty, title;
    ImageView image;
    View overlayView;
    RecyclerView listView;
    MaterialScrollingLayout scrollingLayout;


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
        title = (TextView) findViewById(R.id.search_album_info_page_title);
        image = (ImageView) findViewById(R.id.search_album_info_page_image);
        overlayView = findViewById(R.id.search_album_info_page_overlay);
        scrollingLayout = (MaterialScrollingLayout) findViewById(R.id.search_album_info_page_material_scrolling);
        listView = (RecyclerView) findViewById(R.id.search_album_info_page_list);

        listView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        listView.setLayoutManager(linearLayoutManager);

        //set title
        title.setText(String.valueOf(searchAlbum.getTitle()));

        //set scrolling activity
        setScrollingActivity();

        //set adapter
        setAdapter();

        //make request
        makeRequest();
    }



    private void setScrollingActivity() {
        scrollingLayout.addBehavior(image, new ParallaxBehavior());
        scrollingLayout.addBehavior(overlayView, new OverlayViewBehavior(dp(56)));
        scrollingLayout.addBehavior(title, new TitleBehavior(getResources()));
    }

    private void setAdapter() {
        mItems = new ArrayList<>();
        mAdapter = new AlbumInfoAdapter(SearchAlbumInfoActivity.this, mItems, this);
        listView.setAdapter(mAdapter);
    }


    private void makeRequest(){
        if(internetHelper.isNetworkAvailable()){
            onNetworkAvailable(true);
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
                    nextPage = nextPage + 1;
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

        } catch (Exception e) {
            isLoading = false;
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
}
