package com.airstem.airflow.ayush.airflow;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.os.Bundle;
import android.provider.Settings;
import android.speech.RecognizerIntent;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.airstem.airflow.ayush.airflow.adapters.collection.TrackAdapter;
import com.airstem.airflow.ayush.airflow.adapters.home.DiscoverAdapter;
import com.airstem.airflow.ayush.airflow.enums.collection.Action;
import com.airstem.airflow.ayush.airflow.events.collection.CollectionTrackListener;
import com.airstem.airflow.ayush.airflow.events.home.DiscoverListener;
import com.airstem.airflow.ayush.airflow.helpers.collection.CollectionConstant;
import com.airstem.airflow.ayush.airflow.helpers.internet.InternetHelper;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionTrack;
import com.airstem.airflow.ayush.airflow.model.home.Discover;
import com.airstem.airflow.ayush.airflow.model.home.DiscoverItem;
import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.google.android.gms.ads.AdView;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, CollectionTrackListener, DiscoverListener {


    Realm realm;

    boolean isLoading = false;
    InternetHelper internetHelper;
    ProgressDialog progressDialog;


    Toolbar toolbar;
    NavigationView navigationView;
    FloatingActionButton fab;
    FloatingSearchView mSearchView;
    CoordinatorLayout coordinatorLayout;
    protected DrawerLayout drawerLayout;


    TextView empty;
    RecyclerView listView;
    SwipeRefreshLayout swipeRefreshLayout;
    LinearLayoutManager linearLayoutManager;


    //when internet
    ArrayList<Discover> mItems;
    DiscoverAdapter mDiscoverAdapter;
    //when no internet
    RealmResults<CollectionTrack> mTracks;
    TrackAdapter mTrackAdapter;



    private AdView mAdView;
    int x = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        realm = Realm.getDefaultInstance();

        //init components
        initComponent();
    }

    private void initComponent() {


        progressDialog = new ProgressDialog(MainActivity.this);
        internetHelper = new InternetHelper(MainActivity.this);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        fab = (FloatingActionButton) findViewById(R.id.activity_main_fab);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.activity_main_coordinate_layout);
        empty = (TextView) findViewById(R.id.activity_main_empty);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.activity_main_refresh);
        listView = (RecyclerView) findViewById(R.id.activity_main_list);
        mSearchView = (FloatingSearchView) findViewById(R.id.activity_main_search_view);

        listView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        listView.setLayoutManager(linearLayoutManager);



        /*mAdView = (AdView) findViewById(R.id.activity_main_adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                viewPager.setPadding(0,0,0,0);
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                int heightDp = getHeight();
                if(heightDp <= 400)
                    viewPager.setPadding(0,0,0,32);
                else if(heightDp > 400 &&heightDp <= 720)
                    viewPager.setPadding(0,0,0,50);
                else if(heightDp > 720)
                    viewPager.setPadding(0,0,0,90);
                else
                    viewPager.setPadding(0,0,0,0);
            }
        });*/

        //set click listeners
        setListeners();

        //load data
    }

    private void setListeners() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        mSearchView.attachNavigationDrawerToMenuButton(drawerLayout);

        setAdapter();
        makeRequest(false);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        mSearchView.setOnSearchListener(new FloatingSearchView.OnSearchListener() {
            @Override
            public void onSuggestionClicked(SearchSuggestion searchSuggestion) {
                if(!TextUtils.isEmpty(searchSuggestion.getBody())){
                    Intent searchIntent = new Intent(MainActivity.this, SearchActivity.class);
                    searchIntent.putExtra(CollectionConstant.SHARED_PASSING_SEARCH_TEXT, searchSuggestion.getBody().trim());
                    startActivity(searchIntent);
                }
            }

            @Override
            public void onSearchAction(String currentQuery) {
                if(!TextUtils.isEmpty(currentQuery)){
                    Intent searchIntent = new Intent(MainActivity.this, SearchActivity.class);
                    searchIntent.putExtra(CollectionConstant.SHARED_PASSING_SEARCH_TEXT, currentQuery.trim());
                    startActivity(searchIntent);
                }
            }
        });

        mSearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, final String newQuery) {
                //pass them on to the search view
                mSearchView.swapSuggestions(new ArrayList<SearchSuggestion>());
            }

        });


        mSearchView.setOnLeftMenuClickListener(new FloatingSearchView.OnLeftMenuClickListener() {
            @Override
            public void onMenuOpened() {
                drawerLayout.openDrawer(GravityCompat.START);
            }

            @Override
            public void onMenuClosed() {
                int y = 1;
            }
        });
        mSearchView.setOnHomeActionClickListener(new FloatingSearchView.OnHomeActionClickListener() {
            @Override
            public void onHomeClicked() {

            }
        });

        mSearchView.setOnMenuItemClickListener(new FloatingSearchView.OnMenuItemClickListener() {
            @Override
            public void onActionMenuItemSelected(MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.action_speech) {
                    getTextFromSpeech();
                } /*else if (id == R.id.action_settings) {
                    startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                } else if (id == R.id.action_share) {

                }else if (id == R.id.action_rate) {

                }*/
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                makeRequest(true);
            }
        });

    }

    private void setAdapter() {
        mItems = new ArrayList<>();
        RealmResults<CollectionTrack> results = realm.where(CollectionTrack.class).findAllSorted("mModifiedOn", Sort.DESCENDING);
        mTracks = realm.where(CollectionTrack.class).findAllSorted("mModifiedOn", Sort.DESCENDING);
        mTrackAdapter = new TrackAdapter(MainActivity.this, mTracks, this);
        mDiscoverAdapter = new DiscoverAdapter(MainActivity.this, mItems, this);
    }

    public void makeRequest(boolean showDialog) {
        if (internetHelper.isNetworkAvailable()) {
            listView.setAdapter(mDiscoverAdapter);
            onNetworkAvailable(showDialog);
        } else {
            //changes here show last played tracks when network not available
            listView.setAdapter(mTrackAdapter);
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    private void onNetworkAvailable(final boolean showDialog) {
        loadData(showDialog);
        empty.setVisibility(View.GONE);
        listView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int totalItemCount = linearLayoutManager.getItemCount();
                int lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if (!isLoading && totalItemCount <= lastVisibleItem) {
                    loadData(showDialog);
                }
            }
        });
    }


    private void loadData(boolean showDialog) {
        try {
            isLoading = true;
            if (showDialog) {
                progressDialog.setMessage("Please wait...");
                progressDialog.setCancelable(false);
                progressDialog.show();
            }
            isLoading = false;

        } catch (Exception e) {
            isLoading = false;
            empty.setVisibility(View.VISIBLE);

            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        navigationView.setCheckedItem(R.id.nav_discover);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CollectionConstant.NETWORK_CODE) {
            if (internetHelper.isNetworkAvailable()) {
                showSnackBar("You are connected.");
            } else {
                showNetworkAlert(MainActivity.this);
            }
        } else if (requestCode == CollectionConstant.SPEECH_CODE) {
            ArrayList<String> stringArrayList = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if(stringArrayList.size() < 1) return;

            String currentQuery = String.valueOf(stringArrayList.get(0));
            if(!TextUtils.isEmpty(currentQuery)){
                mSearchView.setSearchText(currentQuery.trim());
                Intent searchIntent = new Intent(MainActivity.this, SearchActivity.class);
                searchIntent.putExtra(CollectionConstant.SHARED_PASSING_SEARCH_TEXT, currentQuery.trim());
                startActivity(searchIntent);
            }

        } else {
            showSnackBar("Something went wrong. Try again.");
        }
    }

    private void showNetworkAlert(Context context) {
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle("Couldn't Connect, Try again");
        alert.setMessage("You need a network connection to use airflow. Please connect your mobile network or WiFi.");
        alert.setPositiveButton("SETTINGS",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivityForResult(new Intent(Settings.ACTION_SETTINGS), CollectionConstant.NETWORK_CODE);
                    }
                });
        alert.setNegativeButton("CANCEL", null);
        alert.show();
    }


    private int getHeight() {
        int heightDp = 0;
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        heightDp = size.y;
        return heightDp;
    }

    private void showSnackBar(String message) {
        Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_LONG).show();
    }


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_discover) {
            startActivity(new Intent(MainActivity.this, MainActivity.class));
        } else if (id == R.id.nav_favorites) {
            startActivity(new Intent(MainActivity.this, FavActivity.class));
        } else if (id == R.id.nav_music_library) {
            startActivity(new Intent(MainActivity.this, CollectionActivity.class));
        } else if (id == R.id.nav_releases) {
            startActivity(new Intent(MainActivity.this, NewReleaseActivity.class));
        } else if (id == R.id.nav_setting) {
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
        } else if (id == R.id.nav_rate) {

        } else if (id == R.id.nav_share) {

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    public void getTextFromSpeech() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, CollectionConstant.SPEECH_TEXT);
        try {
            startActivityForResult(intent, CollectionConstant.SPEECH_CODE);
        } catch (ActivityNotFoundException e) {
            showSnackBar("Speech to text not supported.");
        }
    }


    @Override
    public void onTrackClick(CollectionTrack collectionTrack) {

    }

    @Override
    public void onTrackOptions(CollectionTrack collectionTrack, Action action) {

    }


    @Override
    public void onMoreClick() {

    }

    @Override
    public void onClick(DiscoverItem discoverItem) {

    }
}
