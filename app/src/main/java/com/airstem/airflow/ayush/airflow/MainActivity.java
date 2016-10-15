package com.airstem.airflow.ayush.airflow;

import android.Manifest;
import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
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
import com.airstem.airflow.ayush.airflow.adapters.TabsPagerAdapter;
import com.airstem.airflow.ayush.airflow.helpers.ActivityReference;
import com.airstem.airflow.ayush.airflow.helpers.CustomEvent;
import com.airstem.airflow.ayush.airflow.helpers.DatabaseEvent;
import com.airstem.airflow.ayush.airflow.helpers.GenresHelper;
import com.airstem.airflow.ayush.airflow.helpers.InternetHelper;
import com.airstem.airflow.ayush.airflow.helpers.YouTubeApiHelper;
import com.airstem.airflow.ayush.airflow.model.Mood;
import com.airstem.airflow.ayush.airflow.model.Track;
import com.airstem.airflow.ayush.airflow.service.MusicService;
import com.airstem.airflow.ayush.airflow.utils.CollectionUtils;
import com.airstem.airflow.ayush.airflow.utils.DatabaseUtils;
import com.airstem.airflow.ayush.airflow.utils.SoundCloudUtil;
import com.astuetz.PagerSlidingTabStrip;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CustomEvent {

    CoordinatorLayout coordinatorLayout;
    FloatingActionButton fab;

    TabsPagerAdapter tabsPagerAdapter;

    SoundCloudUtil soundCloudUtils;
    GenresHelper genreHelper;
    InternetHelper internetHelper;
    DatabaseUtils databaseUtils;

    boolean isAllowed = false;

    ViewPager viewPager;
    ProgressDialog mProgressDialog;

    YouTubeApiHelper youTubeApiHelper;


    public MusicService musicService;

    private Intent playIntent;
    private boolean musicBound = false;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // Initialization
        viewPager = (ViewPager) findViewById(R.id.activity_main_pager);
        tabsPagerAdapter = new TabsPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tabsPagerAdapter);

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

        fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setVisibility(View.GONE);

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.activity_main_coordinatorLayout);

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

        if (internetHelper.isNetworkAvailable()) {
            isAllowed = true;
        } else {
            showAlert(MainActivity.this);
            isAllowed = false;
        }

        askPermission();

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
        if (isAllowed) {
            Mood moodSelected = adapter.getItem(position);
            new ExecuteAction().execute(moodSelected);
        } else {
            showAlert(MainActivity.this);
        }
    }

    public void executeMyFavFragmentListViewOnItemSelected(int position, FavAdapter favAdapter){
        if (isAllowed) {
            mProgressDialog.setMessage("Starting playback...");
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();

            playSong(position, favAdapter.getList());
        } else {
            showAlert(MainActivity.this);
        }
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


        //Track t = myTracks.get(0);
        //setTheme(t);
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
        }

        return super.onOptionsItemSelected(item);
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

                if (permission.equals(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    if (grantResult != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
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

    ArrayList<Track> tracks;
    private boolean exceptionWhileLoading = false;
    int tryCount = 0;
    class ExecuteAction extends AsyncTask<Mood, Void, Void> {

        Mood mood;
        @Override
        protected Void doInBackground(Mood... params) {

            try {
                mood = params[0];
                if(mood.Name.equalsIgnoreCase("AIRFLOW 50")){
                     tracks = youTubeApiHelper.getYoutubeTopTracks(50);
                }else {
                    String genre = genreHelper.getMoodFromGenre(mood.Name);
                    tracks = soundCloudUtils.getSoundCloudData(genre, 50, mood.Name);
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
