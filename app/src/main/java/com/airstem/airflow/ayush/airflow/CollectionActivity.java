package com.airstem.airflow.ayush.airflow;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.airstem.airflow.ayush.airflow.adapters.tab.CustomPagerAdapter;
import com.airstem.airflow.ayush.airflow.fragments.collection.CollectionPlaylistFragment;
import com.airstem.airflow.ayush.airflow.fragments.collection.CollectionTrackFragment;
import com.airstem.airflow.ayush.airflow.fragments.collection.CollectionArtistFragment;
import com.airstem.airflow.ayush.airflow.fragments.collection.CollectionVideoFragment;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionArtist;

import io.realm.Realm;

/**
 * Created by ayush AS on 7/1/17.
 */

public class CollectionActivity extends MainActivity {

    Realm realm;

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    CoordinatorLayout coordinatorLayout;
    FloatingActionButton floatingActionButton;

    
    
    @Nullable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.collection_page);

        //init realm
        realm = Realm.getDefaultInstance();

        //init components
        initComponent();
    }

    private void initComponent() {
        toolbar = (Toolbar) findViewById(R.id.collection_page_toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        tabLayout = (TabLayout) findViewById(R.id.collection_page_tab);
        viewPager = (ViewPager) findViewById(R.id.collection_page_pager);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.collection_page_coordinate_layout);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.collection_page_fab);

        //set fragments
        setFragments();


        //set click listeners
        setListeners();

    }

    private void setFragments(){
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_track_white));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_artist_white));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_playlist_white));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_video_white));


        CustomPagerAdapter adapter = new CustomPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new CollectionTrackFragment());
        adapter.addFragment(new CollectionArtistFragment());
        adapter.addFragment(new CollectionPlaylistFragment());
        adapter.addFragment(new CollectionVideoFragment());

        viewPager.setAdapter(adapter);
    }

    private void setListeners() {
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //when page is changed
                if(position == 0){
                    floatingActionButton.setImageResource(R.drawable.ic_shuffle);
                    floatingActionButton.setVisibility(View.VISIBLE);
                    floatingActionButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //play music

                        }
                    });
                }else if(position == 2){
                    floatingActionButton.setImageResource(R.drawable.ic_add);
                    floatingActionButton.setVisibility(View.VISIBLE);
                    floatingActionButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //add playlist

                        }
                    });
                }else{
                    floatingActionButton.setVisibility(View.GONE);
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
