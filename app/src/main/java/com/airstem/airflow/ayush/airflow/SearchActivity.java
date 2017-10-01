package com.airstem.airflow.ayush.airflow;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.airstem.airflow.ayush.airflow.adapters.tab.CustomPagerAdapter;
import com.airstem.airflow.ayush.airflow.fragments.search.SearchAlbumFragment;
import com.airstem.airflow.ayush.airflow.fragments.search.SearchArtistFragment;
import com.airstem.airflow.ayush.airflow.fragments.search.SearchRadioFragment;
import com.airstem.airflow.ayush.airflow.fragments.search.SearchTrackFragment;
import com.airstem.airflow.ayush.airflow.fragments.search.SearchVideoFragment;
import com.airstem.airflow.ayush.airflow.helpers.collection.CollectionConstant;
import com.airstem.airflow.ayush.airflow.model.search.SearchAlbum;
import com.airstem.airflow.ayush.airflow.model.search.SearchRadio;

import io.realm.Realm;

/**
 * Created by mcd-50 on 8/7/17.
 */

public class SearchActivity extends AppCompatActivity {


    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    CoordinatorLayout coordinatorLayout;


    CustomPagerAdapter adapter;

    Realm realm;
    private String searchQuery = "";

    @Nullable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_page);


        realm = Realm.getDefaultInstance();

        searchQuery = getIntent().getStringExtra(CollectionConstant.SHARED_PASSING_SEARCH_TEXT);
        setTitle(searchQuery);

        //init components
        initComponent();
    }

    private void initComponent() {
        toolbar = (Toolbar) findViewById(R.id.search_page_toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        tabLayout = (TabLayout) findViewById(R.id.search_page_tab);
        viewPager = (ViewPager) findViewById(R.id.search_page_pager);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.search_page_coordinate_layout);

        //set fragments
        setFragments();


        //set click listeners
        setListeners();

    }

    private void setFragments(){
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_track_white));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_artist_white));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_album_white));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_radio_white));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_video_white));


        adapter = new CustomPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new SearchTrackFragment());
        adapter.addFragment(new SearchArtistFragment());
        adapter.addFragment(new SearchAlbumFragment());
        adapter.addFragment(new SearchRadioFragment());
        adapter.addFragment(new SearchVideoFragment());


        viewPager.setAdapter(adapter);
    }

    private void setListeners() {
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(position == 0 && positionOffsetPixels == 0 && positionOffset == 0.0){
                    SearchTrackFragment searchTrackFragment = ((SearchTrackFragment) adapter.getItem(0));
                    if(!searchTrackFragment.hasLoaded) searchTrackFragment.makeRequest(true);
                    viewPager.setOffscreenPageLimit(5);
                }
            }

            @Override
            public void onPageSelected(int position) {
                if(position == 0){
                    SearchTrackFragment searchTrackFragment = ((SearchTrackFragment) adapter.getItem(position));
                    if(!searchTrackFragment.hasLoaded) searchTrackFragment.makeRequest(true);
                }else if(position == 1){
                    SearchArtistFragment searchArtistFragment = ((SearchArtistFragment) adapter.getItem(position));
                    if(!searchArtistFragment.hasLoaded) searchArtistFragment.makeRequest(true);
                }else if(position == 2){
                    SearchAlbumFragment searchAlbumFragment = ((SearchAlbumFragment) adapter.getItem(position));
                    if(!searchAlbumFragment.hasLoaded) searchAlbumFragment.makeRequest(true);
                }else if(position == 3){
                    SearchRadioFragment searchRadioFragment = ((SearchRadioFragment) adapter.getItem(position));
                    if(!searchRadioFragment.hasLoaded) searchRadioFragment.makeRequest(true);
                }else if(position == 4){
                    SearchVideoFragment searchVideoFragment = ((SearchVideoFragment) adapter.getItem(position));
                    if(!searchVideoFragment.hasLoaded) searchVideoFragment.makeRequest(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    public Realm getRealm(){
        return realm;
    }

    public String getSearchQuery(){
        return searchQuery;
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
