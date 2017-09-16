package com.airstem.airflow.ayush.airflow;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.airstem.airflow.ayush.airflow.adapters.tab.CustomPagerAdapter;
import com.airstem.airflow.ayush.airflow.fragments.player.LyricsFragment;
import com.airstem.airflow.ayush.airflow.fragments.player.PlayingFragment;
import com.airstem.airflow.ayush.airflow.fragments.player.SimilarTrackFragment;

import java.util.ArrayList;

/**
 * Created by ayush on 08-10-16.
 */


public class PlayerActivity extends  AppCompatActivity {



    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    CoordinatorLayout coordinatorLayout;


    @Nullable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_page);

        //init components
        initComponent();
    }

    private void initComponent() {
        toolbar = (Toolbar) findViewById(R.id.player_page_toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        tabLayout = (TabLayout) findViewById(R.id.player_page_tab);
        viewPager = (ViewPager) findViewById(R.id.player_page_pager);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.player_page_coordinate_layout);

        //set fragments
        setFragments();


        //set click listeners
        setListeners();

    }

    private void setFragments(){
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_play_icon));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_playlist_white));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_track_white));


        CustomPagerAdapter adapter = new CustomPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new PlayingFragment());
        adapter.addFragment(new LyricsFragment());
        adapter.addFragment(new SimilarTrackFragment());

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

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

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
