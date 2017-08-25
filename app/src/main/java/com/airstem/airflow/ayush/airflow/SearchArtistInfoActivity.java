package com.airstem.airflow.ayush.airflow;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.airstem.airflow.ayush.airflow.adapters.tab.CustomPagerAdapter;
import com.airstem.airflow.ayush.airflow.behaviors.OverlayViewBehavior;
import com.airstem.airflow.ayush.airflow.behaviors.TitleBehavior;
import com.airstem.airflow.ayush.airflow.fragments.search.ArtistInfoAlbumFragment;
import com.airstem.airflow.ayush.airflow.fragments.search.ArtistInfoTrackFragment;
import com.airstem.airflow.ayush.airflow.helpers.collection.CollectionConstant;
import com.airstem.airflow.ayush.airflow.helpers.internet.InternetHelper;
import com.airstem.airflow.ayush.airflow.model.search.SearchArtist;
import jp.satorufujiwara.scrolling.MaterialScrollingViewPager;
import jp.satorufujiwara.scrolling.behavior.ExitUntilCollapsedBehavior;
import jp.satorufujiwara.scrolling.behavior.ParallaxBehavior;
import jp.satorufujiwara.scrolling.behavior.ScrollUntilBehavior;

/**
 * Created by mcd-50 on 9/7/17.
 */

public class SearchArtistInfoActivity extends AppCompatActivity{


    ProgressDialog progressDialog;
    InternetHelper internetHelper;

    Toolbar toolbar;
    TextView title;
    ImageView image;
    View overlayView;
    TabLayout tabLayout;
    MaterialScrollingViewPager viewPager;


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

        title = (TextView) findViewById(R.id.search_artist_info_page_title);
        image = (ImageView) findViewById(R.id.search_artist_info_page_image);
        overlayView = findViewById(R.id.search_artist_info_page_overlay);
        tabLayout = (TabLayout) findViewById(R.id.search_artist_info_page_tab);
        viewPager = (MaterialScrollingViewPager) findViewById(R.id.search_artist_info_page_pager);


        //set title
        title.setText(String.valueOf(searchArtist.getTitle()));

        //set fragments
        setFragments();


        //set click listeners
        setListeners();
    }

    private void setFragments(){
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_track_white));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_album_white));


        CustomPagerAdapter adapter = new CustomPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ArtistInfoTrackFragment());
        adapter.addFragment(new ArtistInfoAlbumFragment());

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

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        viewPager.addBehavior(image, new ParallaxBehavior());
        viewPager.addBehavior(tabLayout, new ScrollUntilBehavior(0));
        viewPager.addBehavior(overlayView, new OverlayViewBehavior(dp(56)));
        viewPager.addBehavior(toolbar, new ExitUntilCollapsedBehavior(dp(56)));
        viewPager.addBehavior(title, new TitleBehavior(getResources()));
    }



    public int dp(final int dp) {
        return (int) (dp * getResources().getDisplayMetrics().density);
    }

}
