package com.airstem.airflow.ayush.airflow;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.test.suitebuilder.TestMethod;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.airstem.airflow.ayush.airflow.adapters.tab.CustomPagerAdapter;
import com.airstem.airflow.ayush.airflow.enums.search.Type;
import com.airstem.airflow.ayush.airflow.events.volly.Callback;
import com.airstem.airflow.ayush.airflow.fragments.match.MatchMp3PmFragment;
import com.airstem.airflow.ayush.airflow.fragments.match.MatchSoundCloudFragment;
import com.airstem.airflow.ayush.airflow.fragments.match.MatchViezFragment;
import com.airstem.airflow.ayush.airflow.fragments.search.SearchAlbumFragment;
import com.airstem.airflow.ayush.airflow.fragments.search.SearchArtistFragment;
import com.airstem.airflow.ayush.airflow.fragments.search.SearchRadioFragment;
import com.airstem.airflow.ayush.airflow.fragments.search.SearchTrackFragment;
import com.airstem.airflow.ayush.airflow.fragments.search.SearchVideoFragment;
import com.airstem.airflow.ayush.airflow.helpers.collection.ActionHelper;
import com.airstem.airflow.ayush.airflow.helpers.collection.CollectionConstant;
import com.airstem.airflow.ayush.airflow.helpers.collection.MatchHelper;
import com.airstem.airflow.ayush.airflow.helpers.internet.InternetHelper;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionArtist;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionTrack;
import com.airstem.airflow.ayush.airflow.model.search.ManualMatch;
import com.airstem.airflow.ayush.airflow.model.search.SearchAlbum;
import com.airstem.airflow.ayush.airflow.model.search.SearchArtist;
import com.airstem.airflow.ayush.airflow.model.search.SearchImage;
import com.airstem.airflow.ayush.airflow.model.search.SearchPaging;
import com.airstem.airflow.ayush.airflow.model.search.SearchRadio;
import com.airstem.airflow.ayush.airflow.model.search.SearchTrack;
import com.airstem.airflow.ayush.airflow.model.search.SearchVideo;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import io.realm.Realm;

/**
 * Created by mcd-50 on 1/10/17.
 */



public class ManualMatchActivity extends AppCompatActivity {

    Realm realm;
    ActionHelper actionHelper;

    Toolbar toolbar;
    ImageView image;
    TabLayout tabLayout;
    ViewPager viewPager;
    CollapsingToolbarLayout collapsingToolbarLayout;

    ProgressDialog progressDialog;
    InternetHelper internetHelper;

    ArrayList<ManualMatch> mItems;
    ArrayList<ManualMatch> manualMatches = new ArrayList<>();

    int currentItem = 0;

    CustomPagerAdapter adapter;
    CollectionTrack collectionTrack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.search_artist_info_page);

        //init realm
        realm = Realm.getDefaultInstance();
        //init helper
        actionHelper = new ActionHelper(ManualMatchActivity.this, realm);

        String id  = getIntent().getStringExtra(CollectionConstant.SHARED_PASSING_COLLECTION_TRACK_LOCAL_ID);

        if (!TextUtils.isEmpty(id)) {
            collectionTrack = realm.where(CollectionTrack.class).equalTo("mLocalId", id).findFirst();
            if(collectionTrack == null) finish();
        }else{
            finish();
        }

        //init components
        initComponent();
    }

    private void initComponent() {
        toolbar = (Toolbar) findViewById(R.id.search_artist_info_page_toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        progressDialog = new ProgressDialog(ManualMatchActivity.this);
        internetHelper = new InternetHelper(ManualMatchActivity.this);

        image = (ImageView) findViewById(R.id.search_artist_info_page_image);
        tabLayout = (TabLayout) findViewById(R.id.search_artist_info_page_tab);
        viewPager = (ViewPager) findViewById(R.id.search_artist_info_page_pager);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.search_artist_info_page_collapsing_toolbar);

        //set title
        collapsingToolbarLayout.setTitle(String.valueOf(collectionTrack.getTitle()));
        //set fragments
        setFragments();

        setScrollingActivity();
        //set click listeners
        setListeners();

    }

    private void setScrollingActivity() {
        String url = collectionTrack.getArtworkUrl();
        if(!TextUtils.isEmpty(url)){
            Picasso.with(ManualMatchActivity.this).load(url).placeholder(R.drawable.default_art).into(image);
        }else{
            Picasso.with(ManualMatchActivity.this).load(R.drawable.default_art).placeholder(R.drawable.default_art).into(image);
        }
    }

    private void setFragments(){
        tabLayout.addTab(tabLayout.newTab().setText("MP3PM"));
        tabLayout.addTab(tabLayout.newTab().setText("SOUNDCLOUD"));
        tabLayout.addTab(tabLayout.newTab().setText("VIEZ"));

        adapter = new CustomPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new MatchMp3PmFragment());
        adapter.addFragment(new MatchSoundCloudFragment());
        adapter.addFragment(new MatchViezFragment());

        viewPager.setAdapter(adapter);
        mItems = new ArrayList<>();
        loadData(true);
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
                currentItem = position;
                reloadData();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void reloadData(){
        manualMatches.clear();
        if(currentItem == 0){
            for(ManualMatch manualMatch : mItems){
                if(manualMatch.getProvider().equals(String.valueOf(Type.MP3PM_TRACK))){
                    manualMatches.add(manualMatch);
                }
            }
            ((MatchMp3PmFragment) adapter.getItem(currentItem)).reloadAdapter(manualMatches);
        }else if(currentItem == 1){
            for(ManualMatch manualMatch : mItems){
                if(manualMatch.getProvider().equals(String.valueOf(Type.SOUNDCLOUD_TRACK))){
                    manualMatches.add(manualMatch);
                }
            }
            ((MatchSoundCloudFragment) adapter.getItem(currentItem)).reloadAdapter(manualMatches);
        }else if(currentItem == 2){
            for(ManualMatch manualMatch : mItems){
                if(manualMatch.getProvider().equals(String.valueOf(Type.YOUTUBE_TRACK))){
                    manualMatches.add(manualMatch);
                }
            }
            ((MatchViezFragment) adapter.getItem(currentItem)).reloadAdapter(manualMatches);
        }
    }

    private void loadData(boolean showDialog) {
        try {
            if(showDialog){
                progressDialog.setMessage("Please wait...");
                progressDialog.setCancelable(false);
                progressDialog.show();
            }

            internetHelper.manualMatchTrack(collectionTrack.getTitle(), collectionTrack.getArtistName(), collectionTrack.getAlbumName(), new Callback() {
                @Override
                public void OnSuccess(ArrayList<Object> items) {
                    int x = 1;
                }

                @Override
                public void onSearch(ArrayList<SearchTrack> tracks, ArrayList<SearchAlbum> albums, ArrayList<SearchArtist> artists, ArrayList<SearchVideo> videos, ArrayList<SearchRadio> radios) {
                    int x = 1;
                }

                @Override
                public void onArtistImages(ArrayList<SearchImage> searchImages) {
                    int x = 1;
                }

                @Override
                public void onAlbumImages(ArrayList<SearchImage> searchImages) {
                    int x = 1;
                }

                @Override
                public void onLyrics(String text) {
                    int x = 1;
                }

                @Override
                public void onMatch(String downloadUrl) {

                }

                @Override
                public void onVideoMatch(ArrayList<JSONObject> videoMatchUrl) throws JSONException {
                    for(JSONObject jsonObject : videoMatchUrl){
                        try{
                            mItems.add(new ManualMatch(collectionTrack.getTitle(), collectionTrack.getArtistName(), jsonObject.getString("download_url"), jsonObject.getString("provider")));
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                    reloadData();
                    progressDialog.dismiss();

                }

                @Override
                public void onSuccess(ArrayList<SearchTrack> searchTracks, ArrayList<SearchArtist> searchArtists, ArrayList<SearchAlbum> searchAlbums, SearchPaging searchPaging) {

                }

                @Override
                public void onTracks(ArrayList<SearchTrack> searchTracks, String next) {
                    int x = 1;
                }

                @Override
                public void onVideos(ArrayList<SearchVideo> searchVideos, String next) {
                    int x = 1;
                }

                @Override
                public void onRadios(ArrayList<SearchRadio> searchRadios) {
                    int x = 1;
                }

                @Override
                public void OnFailure(String message) {
                    progressDialog.dismiss();
                }
            });

        } catch (Exception e) {
            progressDialog.dismiss();
            e.printStackTrace();
        }
    }

    public Realm getRealm(){
        return realm;
    }

    public ArrayList<ManualMatch> getCollectionTracks(){
        return mItems;
    }

    public CollectionTrack getCollectionTrack(){
        return collectionTrack;
    }

    public ActionHelper getActionHelper(){
        return actionHelper;
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
