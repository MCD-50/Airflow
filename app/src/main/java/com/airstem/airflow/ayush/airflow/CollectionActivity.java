package com.airstem.airflow.ayush.airflow;

import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.airstem.airflow.ayush.airflow.adapters.CustomPagerAdapter;
import com.airstem.airflow.ayush.airflow.adapters.LibraryTabsAdapter;
import com.airstem.airflow.ayush.airflow.adapters.TabsPagerAdapter;
import com.airstem.airflow.ayush.airflow.fragments.search.SearchAlbumFragment;
import com.airstem.airflow.ayush.airflow.fragments.search.SearchArtistFragment;
import com.airstem.airflow.ayush.airflow.fragments.search.SearchRadioFragment;
import com.airstem.airflow.ayush.airflow.fragments.search.SearchTrackFragment;
import com.airstem.airflow.ayush.airflow.fragments.search.SearchVideoFragment;
import com.airstem.airflow.ayush.airflow.helpers.CustomEvent;
import com.airstem.airflow.ayush.airflow.helpers.FragmentEvents;
import com.airstem.airflow.ayush.airflow.model.Artist;
import com.airstem.airflow.ayush.airflow.model.Playlist;
import com.airstem.airflow.ayush.airflow.model.Track;
import com.airstem.airflow.ayush.airflow.service.MusicService;
import com.airstem.airflow.ayush.airflow.utils.CollectionUtils;
import com.astuetz.PagerSlidingTabStrip;

import java.util.ArrayList;

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
