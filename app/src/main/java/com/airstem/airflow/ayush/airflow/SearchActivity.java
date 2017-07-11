package com.airstem.airflow.ayush.airflow;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.airstem.airflow.ayush.airflow.adapters.tabs.CustomPagerAdapter;
import com.airstem.airflow.ayush.airflow.fragments.search.SearchAlbumFragment;
import com.airstem.airflow.ayush.airflow.fragments.search.SearchArtistFragment;
import com.airstem.airflow.ayush.airflow.fragments.search.SearchRadioFragment;
import com.airstem.airflow.ayush.airflow.fragments.search.SearchTrackFragment;
import com.airstem.airflow.ayush.airflow.fragments.search.SearchVideoFragment;

/**
 * Created by mcd-50 on 8/7/17.
 */

public class SearchActivity extends AppCompatActivity {


    TabLayout tabLayout;
    ViewPager viewPager;
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_page);


        //init component
        initComponents();


    }

    private void initComponents() {

        toolbar = (Toolbar) findViewById(R.id.search_page_toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        tabLayout = (TabLayout) findViewById(R.id.search_page_tab);
        viewPager = (ViewPager) findViewById(R.id.search_page_pager);

        setFragments();

    }


    private void setFragments(){
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_track_white));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_video_white));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_artist_white));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_album_white));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_radio_white));

        CustomPagerAdapter adapter = new CustomPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new SearchTrackFragment());
        adapter.addFragment(new SearchVideoFragment());
        adapter.addFragment(new SearchArtistFragment());
        adapter.addFragment(new SearchAlbumFragment());
        adapter.addFragment(new SearchRadioFragment());

        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
    }
}
