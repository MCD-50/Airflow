package com.airstem.airflow.ayush.airflow;

import android.Manifest;
import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.speech.RecognizerIntent;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.airstem.airflow.ayush.airflow.adapters.CategoryAdapter;
import com.airstem.airflow.ayush.airflow.adapters.FavAdapter;
import com.airstem.airflow.ayush.airflow.adapters.ListDiscoverAdapter;
import com.airstem.airflow.ayush.airflow.adapters.LocalTrackAdapter;
import com.airstem.airflow.ayush.airflow.adapters.TabsPagerAdapter;
import com.airstem.airflow.ayush.airflow.helpers.ActivityReference;
import com.airstem.airflow.ayush.airflow.helpers.CustomEvent;
import com.airstem.airflow.ayush.airflow.helpers.DatabaseEvent;
import com.airstem.airflow.ayush.airflow.helpers.GenresHelper;
import com.airstem.airflow.ayush.airflow.helpers.InternetHelper;
import com.airstem.airflow.ayush.airflow.helpers.LocalMusicHelper;
import com.airstem.airflow.ayush.airflow.helpers.MoodHelper;
import com.airstem.airflow.ayush.airflow.helpers.YouTubeApiHelper;
import com.airstem.airflow.ayush.airflow.model.Base;
import com.airstem.airflow.ayush.airflow.model.Mood;
import com.airstem.airflow.ayush.airflow.model.Track;
import com.airstem.airflow.ayush.airflow.service.MusicService;
import com.airstem.airflow.ayush.airflow.utils.CollectionUtils;
import com.airstem.airflow.ayush.airflow.utils.DatabaseUtils;
import com.airstem.airflow.ayush.airflow.utils.SoundCloudUtil;
import com.astuetz.PagerSlidingTabStrip;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements CustomEvent,NavigationView.OnNavigationItemSelectedListener {


    TabsPagerAdapter tabsPagerAdapter;
    NavigationView navigationView;
    FloatingActionButton fab;
    CoordinatorLayout coordinatorLayout;


    SoundCloudUtil soundCloudUtils;
    GenresHelper genreHelper;
    InternetHelper internetHelper;
    DatabaseUtils databaseUtils;

    ViewPager viewPager;
    ProgressDialog mProgressDialog;

    YouTubeApiHelper youTubeApiHelper;
    ArrayList<Track> tracks;
    int tryCount = 0;

    public MusicService musicService;

    private Intent playIntent;
    private boolean musicBound = false;


    private AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

        // Initialization
        viewPager = (ViewPager) findViewById(R.id.activity_main_pager);
        tabsPagerAdapter = new TabsPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tabsPagerAdapter);
        viewPager.setOffscreenPageLimit(3);
        mProgressDialog = new ProgressDialog(MainActivity.this);

        PagerSlidingTabStrip pagerSlidingTabStrip = (PagerSlidingTabStrip) findViewById(R.id.activity_main_slidingTabStrip);
        pagerSlidingTabStrip.setViewPager(viewPager);


        //not required....
        //ActivityReference.setRef(MainActivity.this);



        soundCloudUtils = new SoundCloudUtil();
        genreHelper = new GenresHelper();
        databaseUtils = new DatabaseUtils(MainActivity.this);
        internetHelper = new InternetHelper(MainActivity.this);
        youTubeApiHelper = new YouTubeApiHelper(MainActivity.this, getApplicationContext());


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setVisibility(View.GONE);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.activity_main_cordinateLayout);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent nIntent = new Intent(MainActivity.this, PlayerActivity.class);
                Track t = musicService.getCurrentData();
                if(t == null){
                    showSnackBar("Something went wrong.");
                }else {
                    startActivity(nIntent);
                }
            }
        });

        if (!internetHelper.isNetworkAvailable()) {
            showAlert(MainActivity.this);
        }
        askPermission();

    }


    private int getHeight(){
        int heightDp = 0;
        Point size = new Point();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
            getWindowManager().getDefaultDisplay().getSize(size);
            heightDp = size.y;
        }else {
            heightDp = getWindowManager().getDefaultDisplay().getHeight();
        }
        return heightDp;
    }

    @Override
    protected void onPause() {
        super.onPause();
        unbindService(musicConnection);
        playIntent = null;
    }


    @Override
    protected void onResume() {
        super.onResume();
        onStartService();
    }

    void makeVisible() {
        if (musicService.isMediaPlayerActive()) {
            fab.setVisibility(View.VISIBLE);
        }
    }

    public ArrayList<Track> getTracksFromDatabase(){
        return databaseUtils.getAllTracks();
    }


    public void executeIFeelFragmentListViewOnItemSelected(int position, CategoryAdapter adapter){
        if (internetHelper.isNetworkAvailable()) {
            Base moodSelected = adapter.getItem(position);
            new ExecuteAction().execute(moodSelected);
        } else {
            showAlert(MainActivity.this);
        }
    }

    public void executeMyFavFragmentListViewOnItemSelected(int position, FavAdapter favAdapter){
        if (internetHelper.isNetworkAvailable()) {
            mProgressDialog.setMessage("Starting playback...");
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();
            playSong(position, favAdapter.getList());
        } else {
            showAlert(MainActivity.this);
        }
    }

    public void executeLocalFragmentListViewOnItemSelected(int position, LocalTrackAdapter localTrackAdapter){
        playSong(position, localTrackAdapter.getList());
    }

    public void executeLocalFragmentListViewOnItemSelected(Track track, ArrayList<Track> tracks){
        int position = tracks.indexOf(track);
        playSong(position, tracks);
    }

    public void addRemoveFromToFav(int position, FavAdapter favAdapter) {
        Track currentTrack = favAdapter.getItem(position);
        if(databaseUtils.isInFavList(currentTrack.getId())){
            showRemoveAlert(currentTrack, favAdapter);
        }
    }

    public void playRadio(int position, ListDiscoverAdapter adapter){
        if (internetHelper.isNetworkAvailable()) {
            mProgressDialog.setMessage("Starting radio within 60 seconds...");
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();
            playSong(adapter.getItem(position));
        } else {
            showAlert(MainActivity.this);
        }
    }


    public void playRadio(Track track){
        if (internetHelper.isNetworkAvailable()) {
            mProgressDialog.setMessage("Starting radio within 60 seconds...");
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();
            playSong(track);
        } else {
            showAlert(MainActivity.this);
        }
    }

    private void showRemoveAlert(final Track currentTrack,final FavAdapter favAdapter) {
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        alert.setTitle("Remove track");
        alert.setMessage("Are you sure you want to remove this track from your fav list?");
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                databaseUtils.deleteTrack(currentTrack.getId(), true);
                favAdapter.removeData(getTracksFromDatabase());
            }
        });
        alert.setNegativeButton("CANCEL", null);
        alert.show();
    }


    public void onStartService() {
        if (playIntent == null) {
            playIntent = new Intent(MainActivity.this, MusicService.class);
            bindService(playIntent, musicConnection, Context.BIND_AUTO_CREATE);
            startService(playIntent);
        }
    }

    private ServiceConnection musicConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MusicService.MusicBinder binder = (MusicService.MusicBinder) service;
            musicService = binder.getService();
            //init our callback
            musicService.initEvent(MainActivity.this);
            musicBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            musicBound = false;
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 999){
             if(internetHelper.isNetworkAvailable()){
                 showSnackBar("You are connected.");
             }else {
                 showAlert(MainActivity.this);
                 try{
                     musicService.play();
                 }catch (Exception e){
                     e.printStackTrace();
                 }
             }
        }

        if(requestCode == 777 && data != null && resultCode == RESULT_OK){
            ArrayList<String> _stringData = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            playTracks(_stringData.get(0).toString());
        }
        else {
            showSnackBar("Something went wrong. Try again.");
        }
    }

    private void playTracks(String query){
        AsyncTask<String, Void, Void> playTask =  new AsyncTask<String, Void, Void>(){

            String query;
            private boolean exceptionWhileLoading = false;
            @Override
            protected Void doInBackground(String... params) {
               try{
                    query = youTubeApiHelper.setAndCleanString(params[0]);
                    tracks = youTubeApiHelper.getYoutubeSearchResults(20, query);
                    exceptionWhileLoading = false;
                }catch (Exception e){
                    tracks = new ArrayList<>();
                    exceptionWhileLoading = true;
                }
                return null;
            }

            protected void onPreExecute() {
                super.onPreExecute();
                mProgressDialog.setMessage("Starting playback...");
                mProgressDialog.setIndeterminate(true);
                mProgressDialog.setCancelable(false);
                mProgressDialog.show();
            }

            @Override
            protected void onPostExecute(Void o) {
                super.onPostExecute(o);
                if (!exceptionWhileLoading) {
                    try {
                        if(tracks.size() > 0){
                            tracks = CollectionUtils.shuffleMyList(tracks);
                            playSong(0, tracks);
                        }else {
                            mProgressDialog.dismiss();
                            showSnackBar("Something went wrong.");
                        }

                    }catch (Exception ex) {
                        showSnackBar("Something went wrong.");
                        ex.printStackTrace();
                        mProgressDialog.dismiss();
                    }
                }else {
                    showSnackBar("Unable to load tracks.");
                }
            }
        };

        playTask.execute(query);
    }

    private void showAlert(Context context) {
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle("Couldn't Connect, Try again");
        alert.setMessage("You need a network connection to use airflow. Please connect your mobile network or WiFi.");
        alert.setPositiveButton("SETTINGS",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivityForResult(new Intent(Settings.ACTION_SETTINGS), 999);
                    }
                });
        alert.setNegativeButton("CANCEL", null);
        alert.show();
    }


    public void playSong(int position, ArrayList<Track> myTracks) {
        if(musicBound){
            musicService.setSongs(myTracks);
            musicService.setSongIndex(position);
            musicService.play();
        }else {
            showSnackBar("Unable to bind music service.");
        }
    }

    public void playSong(Track track){
        if(musicBound){
            musicService.setRadio(track);
            musicService.play();
        }else {
            showSnackBar("Unable to bind music service.");
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            startActivity(new Intent(MainActivity.this, AboutActivity.class));
            return true;
        }else if(id == R.id.action_speech){
            speakClicked();
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_library)
            startActivity(new Intent(MainActivity.this, LibraryActivity.class));
        else if (id == R.id.nav_settings)
            startActivity(new Intent(MainActivity.this, AboutActivity.class));

//        if (id == R.id.nav_camera) {
//            // Handle the camera action
//        } else if (id == R.id.nav_gallery) {
//
//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private String sample = "You say we play" + "\n" + "Try saying like play maroon 5...";

    public void speakClicked() {

        Intent _intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        _intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        _intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        _intent.putExtra(RecognizerIntent.EXTRA_PROMPT, sample);
        try{
            startActivityForResult(_intent, 777);
        }
        catch (ActivityNotFoundException e){
            showSnackBar("Speech to text not supported.");
        }
    }



    void setTheme(Track t){

        AsyncTask<Track, Void, String> myTask =  new AsyncTask<Track, Void, String>(){
            @Override
            protected String doInBackground(Track... params) {
                int resId = -999;
                String mood = params[0].getMood();
                if(mood.equalsIgnoreCase("TODAY'S TOP")){
                    resId = R.style.AppTheme_TopMood;
                }else if(mood.equalsIgnoreCase("SHUFFLE ME")){
                    resId = R.style.AppTheme_ShuffleMood;
                }else if(mood.equalsIgnoreCase("NERVOUS")){
                    resId = R.style.AppTheme_NervousMood;
                }else if(mood.equalsIgnoreCase("ENERGY")){
                    resId = R.style.AppTheme_EnergyMood;
                }else if(mood.equalsIgnoreCase("ANGRY")){
                    resId = R.style.AppTheme_AngryMood;
                }else if(mood.equalsIgnoreCase("LOVE")){
                    resId = R.style.AppTheme_LoveMood;
                }else if(mood.equalsIgnoreCase("GOOD")){
                    resId = R.style.AppTheme_GoodMood;
                }else if(mood.equalsIgnoreCase("CALM")){
                    resId = R.style.AppTheme_CalmMood;
                }else if(mood.equalsIgnoreCase("DEPRESSED")){
                    resId = R.style.AppTheme_DepressedMood;
                }else if(mood.equalsIgnoreCase("RELAX")){
                    resId = R.style.AppTheme_RelaxMood;
                }else if(mood.equalsIgnoreCase("CRAZY")){
                    resId = R.style.AppTheme_CrazyMood;
                }else if(mood.equalsIgnoreCase("BORED")){
                    resId = R.style.AppTheme_BoredMood;
                }else if(mood.equalsIgnoreCase("SAD")){
                    resId = R.style.AppTheme_SadMood;
                }

                return String.valueOf(resId);
            }

            @Override
            protected void onPostExecute(String o) {
                super.onPostExecute(o);
                if(o.equalsIgnoreCase("-999")) return;
                getTheme().applyStyle(Integer.parseInt(o), true);
            }
        };

        myTask.execute(t);
    }

    private void askPermission() {

        int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.INTERNET);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.INTERNET, Manifest.permission.WAKE_LOCK}, 100);
        } else {
            int permissionCheck2 = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WAKE_LOCK);
            if (permissionCheck2 != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WAKE_LOCK}, 100);
            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 100) {
            for (int i = 0; i < permissions.length; i++) {
                String permission = permissions[i];
                int grantResult = grantResults[i];

                if (permission.equals(Manifest.permission.INTERNET) || permission.equals(Manifest.permission.WAKE_LOCK)) {
                    if (grantResult != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.INTERNET, Manifest.permission.WAKE_LOCK}, 100);
                    }
                }
            }
        }
    }


    @Override
    public void trackChanged() {
        if(mProgressDialog != null){
            try{
                mProgressDialog.dismiss();
            }catch (Exception e){
                //ignore...
            }
        }
        makeVisible();
    }

    @Override
    public void noInternet() {
       showAlert(MainActivity.this);
    }


    class ExecuteAction extends AsyncTask<Base, Void, Void> {

        private boolean exceptionWhileLoading = false;
        Mood mood;
        @Override
        protected Void doInBackground(Base... params) {

            try {
                Base base = params[0];
                if(base.getClass().equals(Mood.class)){
                    mood = (Mood) base;
                    if(mood.Name.equalsIgnoreCase("AIRFLOW 50")){
                        tracks = youTubeApiHelper.getYoutubeTopTracks(50, "");
                    }else {
                        String genre = genreHelper.getMoodFromGenre(mood.Name);
                        tracks = soundCloudUtils.getSoundCloudData(genre, 50, mood.Name);
                    }
                }

                exceptionWhileLoading = false;

            } catch (Exception e) {
                exceptionWhileLoading = true;
                tracks = new ArrayList<>();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog.setMessage("Starting playback...");
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (!exceptionWhileLoading) {
                try {
                    if(tracks.size() > 0){
                        tryCount = 0;



                      tracks = CollectionUtils.shuffleMyList(tracks);
                        playSong(0, tracks);
                    }else if(tryCount < 2) {
                        tryCount++;
                       new ExecuteAction().execute(mood);
                    }else {
                        tryCount = 0;
                        mProgressDialog.dismiss();
                        showSnackBar("Something went wrong.");
                    }

                }catch (Exception ex) {
                    showSnackBar("Something went wrong.");
                    ex.printStackTrace();
                    mProgressDialog.dismiss();
                }
            }else {
                showSnackBar("Unable to load tracks.");
            }
        }
    }

    private void showSnackBar(String message) {
        Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_LONG).show();
    }

}
