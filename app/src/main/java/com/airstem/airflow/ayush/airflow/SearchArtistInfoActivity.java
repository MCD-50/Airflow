package com.airstem.airflow.ayush.airflow;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import com.airstem.airflow.ayush.airflow.adapters.tab.CustomPagerAdapter;
import com.airstem.airflow.ayush.airflow.fragments.search.ArtistInfoAlbumFragment;
import com.airstem.airflow.ayush.airflow.fragments.search.ArtistInfoTrackFragment;
import com.airstem.airflow.ayush.airflow.helpers.collection.CollectionConstant;
import com.airstem.airflow.ayush.airflow.helpers.internet.InternetHelper;
import com.airstem.airflow.ayush.airflow.model.search.SearchArtist;
import com.airstem.airflow.ayush.airflow.model.search.SearchImage;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by mcd-50 on 9/7/17.
 */

public class SearchArtistInfoActivity extends AppCompatActivity{


    ProgressDialog progressDialog;
    InternetHelper internetHelper;

    Toolbar toolbar;
    ImageView image;
    TabLayout tabLayout;
    ViewPager viewPager;
    CollapsingToolbarLayout collapsingToolbarLayout;


    CustomPagerAdapter adapter;

    SearchArtist searchArtist = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_artist_info_page);

        //get the intent
        searchArtist = (SearchArtist) getIntent().getSerializableExtra(CollectionConstant.SHARED_PASSING_SEARCH_ARTIST);
        if (searchArtist == null) {
            finish();
        }

        //init components
        initComponents();
    }


    private void initComponents() {
        toolbar = (Toolbar) findViewById(R.id.search_artist_info_page_toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        internetHelper = new InternetHelper(SearchArtistInfoActivity.this);
        progressDialog = new ProgressDialog(SearchArtistInfoActivity.this);


        image = (ImageView) findViewById(R.id.search_artist_info_page_image);
        tabLayout = (TabLayout) findViewById(R.id.search_artist_info_page_tab);
        viewPager = (ViewPager) findViewById(R.id.search_artist_info_page_pager);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.search_artist_info_page_collapsing_toolbar);


        //set title
        collapsingToolbarLayout.setTitle(String.valueOf(searchArtist.getTitle()));

        //set fragments
        setFragments();

        setScrollingActivity();
        //set click listeners
        setListeners();
    }

    private void setScrollingActivity() {

        ArrayList<SearchImage> searchImages = searchArtist.getArtworkUrl();
        if(searchImages.size() > 0){
            int lastIndex = searchImages.size() - 1;
            Picasso.with(SearchArtistInfoActivity.this).load(searchImages.get(lastIndex).getUri()).placeholder(R.drawable.default_art).into(image);
        }else{
            Picasso.with(SearchArtistInfoActivity.this).load(R.drawable.default_art).placeholder(R.drawable.default_art).into(image);
        }
    }

    private void setFragments(){
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_track_white));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_album_white));


        adapter = new CustomPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ArtistInfoTrackFragment());
        adapter.addFragment(new ArtistInfoAlbumFragment());

        viewPager.setAdapter(adapter);
    }

    private void setListeners() {
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(position == 0 && positionOffsetPixels == 0 && positionOffset == 0.0){
                    ArtistInfoTrackFragment artistInfoTrackFragment = ((ArtistInfoTrackFragment) adapter.getItem(0));
                    if(!artistInfoTrackFragment.hasLoaded) artistInfoTrackFragment.makeRequest(true);
                    viewPager.setOffscreenPageLimit(2);
                }
            }

            @Override
            public void onPageSelected(int position) {
                if(position == 0){
                    ArtistInfoTrackFragment artistInfoTrackFragment = ((ArtistInfoTrackFragment) adapter.getItem(0));
                    if(!artistInfoTrackFragment.hasLoaded) artistInfoTrackFragment.makeRequest(true);
                }else if(position == 1){
                    ArtistInfoAlbumFragment artistInfoAlbumFragment = ((ArtistInfoAlbumFragment) adapter.getItem(1));
                    if(!artistInfoAlbumFragment.hasLoaded) artistInfoAlbumFragment.makeRequest(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    public SearchArtist getSearchArtist(){
        return searchArtist;
    }



    public int dp(final int dp) {
        return (int) (dp * getResources().getDisplayMetrics().density);
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
