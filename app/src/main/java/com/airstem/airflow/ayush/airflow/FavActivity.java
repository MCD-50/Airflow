package com.airstem.airflow.ayush.airflow;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.airstem.airflow.ayush.airflow.adapters.tabs.CustomPagerAdapter;
import com.airstem.airflow.ayush.airflow.fragments.fav.FavRadioFragment;
import com.airstem.airflow.ayush.airflow.fragments.fav.FavTrackFragment;
import com.airstem.airflow.ayush.airflow.fragments.fav.FavVideoFragment;

/**
 * Created by mcd-50 on 11/7/17.
 */

public class FavActivity extends AppCompatActivity {


    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;

    @Nullable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fav_page);

        //init components
        initComponent();
    }

    private void initComponent() {
        toolbar = (Toolbar) findViewById(R.id.fav_page_toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        tabLayout = (TabLayout) findViewById(R.id.fav_page_tab);
        viewPager = (ViewPager) findViewById(R.id.fav_page_pager);


        setFragments();
    }


    private void setFragments(){
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_track_white));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_video_white));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_radio_white));

        CustomPagerAdapter adapter = new CustomPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FavTrackFragment());
        adapter.addFragment(new FavVideoFragment());
        adapter.addFragment(new FavRadioFragment());

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

