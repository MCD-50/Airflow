package com.airstem.airflow.ayush.airflow;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.airstem.airflow.ayush.airflow.adapters.tab.CustomPagerAdapter;
import com.airstem.airflow.ayush.airflow.fragments.search.SearchAlbumFragment;
import com.airstem.airflow.ayush.airflow.fragments.search.SearchArtistFragment;
import com.airstem.airflow.ayush.airflow.fragments.search.SearchTrackFragment;
import com.airstem.airflow.ayush.airflow.fragments.search.SearchVideoFragment;

/**
 * Created by ayush AS on 7/1/17.
 */

public class CollectionActivity extends AppCompatActivity{


    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;

    @Nullable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.collection_page);

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


        setFragments();
    }


    private void setFragments(){
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_track_white));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_artist_white));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_playlist_white));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_video_white));


        CustomPagerAdapter adapter = new CustomPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new SearchTrackFragment());
        adapter.addFragment(new SearchVideoFragment());
        adapter.addFragment(new SearchArtistFragment());
        adapter.addFragment(new SearchAlbumFragment());

        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
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
