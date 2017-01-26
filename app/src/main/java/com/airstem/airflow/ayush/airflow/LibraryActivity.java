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
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.airstem.airflow.ayush.airflow.adapters.LibraryTabsAdapter;
import com.airstem.airflow.ayush.airflow.adapters.TabsPagerAdapter;
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

public class LibraryActivity extends AppCompatActivity implements CustomEvent {


    LibraryTabsAdapter tabsPagerAdapter;
    FloatingActionButton fab;
    CoordinatorLayout coordinatorLayout;
    ViewPager viewPager;
    ProgressDialog mProgressDialog;

    public MusicService musicService;
    private Intent playIntent;
    private boolean musicBound = false;
    private boolean isAdd = false;
    private FragmentEvents fragmentEvents;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.library_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.library_layout_pager);
        tabsPagerAdapter = new LibraryTabsAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tabsPagerAdapter);
        viewPager.setOffscreenPageLimit(2);
        mProgressDialog = new ProgressDialog(LibraryActivity.this);

        PagerSlidingTabStrip pagerSlidingTabStrip = (PagerSlidingTabStrip) findViewById(R.id.library_layout_slidingTabStrip);
        pagerSlidingTabStrip.setViewPager(viewPager);

        pagerSlidingTabStrip.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position == 2){
                    fab.setImageResource(R.drawable.ic_add);
                    isAdd = true;
                }
                else{
                    fab.setImageResource(R.drawable.ic_shuffle);
                    isAdd = false;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        fab = (FloatingActionButton) findViewById(R.id.fab);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.library_layout_cordinateLayout);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isAdd){
                    addNewPlaylist();
                }else{
                    shuffleMusic();
                }

            }
        });

    }

    public void setFragmentsEvent(FragmentEvents fragmentsEvent){
        this.fragmentEvents = fragmentsEvent;
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


    public void onStartService() {
        if (playIntent == null) {
            playIntent = new Intent(LibraryActivity.this, MusicService.class);
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
            musicService.initEvent(LibraryActivity.this);
            musicBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            musicBound = false;
        }


    };

    public void navigateToPlaylistPage(Playlist playlist){
        Intent i = new Intent(LibraryActivity.this, PlaylistDetailActivity.class);
        i.putExtra("playlist", playlist);
        startActivity(i);
    }

    public void navigateToArtistPage(Artist artist){
        Intent i = new Intent(LibraryActivity.this, ArtistDetailActivity.class);
        i.putExtra("artist", artist);
        startActivity(i);
    }

    public void playTrack(Track track, ArrayList<Track> tracks){
        mProgressDialog.setMessage("Starting playback...");
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
        musicService.setSongs(tracks);
        musicService.setSongIndex(tracks.indexOf(track));
        musicService.play();
    }

    private void shuffleMusic(){
        musicService.setSongs(CollectionUtils.shuffleMyList(fragmentEvents.invokeOnShuffleTrack()));
        musicService.play();
    }

    private void addNewPlaylist(){
        final AlertDialog.Builder alert = new AlertDialog.Builder(LibraryActivity.this);

        alert.setMessage("Add a new playlist to get started.");
        //final EditText editText = new EditText(LibraryActivity.this);
        final View view = this.getLayoutInflater().inflate(R.layout.add_new_playlist, null);
        alert.setView(view);

        alert.setPositiveButton("CREATE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                //Toast.makeText(LibraryActivity.this, ((EditText)view.findViewById(R.id.newPlaylistEditText)).getText().toString(), Toast.LENGTH_LONG).show();
            }
        });
        alert.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alert.show();
    }

    private void showSnackBar(String message) {
        Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_LONG).show();
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

    @Override
    public void trackChanged() {
        if(mProgressDialog != null){
            try{
                mProgressDialog.dismiss();
            }catch (Exception e){
                //ignore...
            }
        }
    }

    @Override
    public void noInternet() {
        showAlert(LibraryActivity.this);
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
}
