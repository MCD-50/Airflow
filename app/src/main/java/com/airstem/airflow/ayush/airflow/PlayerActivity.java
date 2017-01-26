package com.airstem.airflow.ayush.airflow;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import com.airstem.airflow.ayush.airflow.helpers.CustomEvent;
import com.airstem.airflow.ayush.airflow.helpers.EventHelper;
import com.airstem.airflow.ayush.airflow.helpers.InternetHelper;
import com.airstem.airflow.ayush.airflow.helpers.MoodHelper;
import com.airstem.airflow.ayush.airflow.helpers.YouTubeApiHelper;
import com.airstem.airflow.ayush.airflow.model.Track;
import com.airstem.airflow.ayush.airflow.service.MusicService;
import com.airstem.airflow.ayush.airflow.utils.CollectionUtils;
import com.airstem.airflow.ayush.airflow.utils.DatabaseUtils;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Created by ayush on 08-10-16.
 */


public class PlayerActivity extends  AppCompatActivity implements CustomEvent {

    ImageView bgImageView, playPauseImageView, nextImageView, favImageView, prevImageView;
    //View bgView;
    TextView titleTextView, artistTextView, positionTextView, durationTextView;
    Button similarButton;

    SeekBar progressSeekBar;
    YouTubeApiHelper youTubeApiHelper;
    boolean isPaused = false;

    ProgressDialog mProgressDialog;

    InternetHelper internetHelper;
    DatabaseUtils databaseUtils;
    MoodHelper moodHelper;

    MusicService musicService;

    private Intent playIntent;
    private boolean musicBound = false;


    private AdView mAdView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_activity);

/*
        mAdView = (AdView) findViewById(R.id.player_activity_adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

*/

       /* //preventing memory leaks memory
        WeakReference<Activity> weakReference =  ActivityReference.getRef();
        // getting ref to main activity
        MainActivity mActivity = (MainActivity) weakReference.get();

        //getting ref to musicService
        musicService = mActivity.musicService;
        */


        databaseUtils = new DatabaseUtils(PlayerActivity.this);
        moodHelper = new MoodHelper();
        internetHelper = new InternetHelper(PlayerActivity.this);
        youTubeApiHelper = new YouTubeApiHelper(PlayerActivity.this, getApplicationContext());
        mProgressDialog = new ProgressDialog(PlayerActivity.this);
       /*  if(savedInstanceState != null){
           moodName = moodHelper.getMoodFromString(savedInstanceState.getString("Mood"));
        }

        String _sValue = getPreferences(Context.MODE_PRIVATE).getString("Mood","Empty");
        if(!_sValue.equals("Empty")) {
            moodName = moodHelper.getMoodFromString(_sValue); ;
        }*/


        bgImageView = (ImageView) findViewById(R.id.player_activity_artworkImageView);
        playPauseImageView = (ImageView) findViewById(R.id.player_activity_playPauseImageView);
        nextImageView = (ImageView) findViewById(R.id.player_activity_nextImageView);
        favImageView = (ImageView) findViewById(R.id.player_activity_favSetImageView);
        prevImageView = (ImageView) findViewById(R.id.player_activity_prevImageView);
        //bgView = findViewById(R.id.player_activity_colorView);
        similarButton = (Button) findViewById(R.id.player_activity_similarTrackButton);

        titleTextView = (TextView) findViewById(R.id.player_activity_titleTextView);
        artistTextView = (TextView) findViewById(R.id.player_activity_artistTextView);


        positionTextView = (TextView) findViewById(R.id.player_activity_currentPositionTextView);
        durationTextView = (TextView) findViewById(R.id.player_activity_durationTextView);

        progressSeekBar = (SeekBar) findViewById(R.id.player_activity_progressSeekBar);

        //set opacity

        //bgView.getBackground().setAlpha(128);
        //bgImageView.getBackground().setAlpha(200);

    }


    private ArrayList<Track> getSimilarTracks(Track track){
        return youTubeApiHelper.getYoutubeSimilarTracks(13, track.getId());
    }


    private String getTimeString(long millis){
        StringBuffer baf = new StringBuffer();

        long calculationSimplifier = 1000 * 60 * 60;
        long calculationSimplifier2 = 1000 * 60;

        int hours = (int) (millis / calculationSimplifier);
        int minutes = (int) ((millis % calculationSimplifier) / calculationSimplifier2);
        int seconds = (int) (((millis % calculationSimplifier) % calculationSimplifier2) / 1000);

        baf.append(String.format("%02d", hours))
           .append(":")
           .append(String.format("%02d", minutes))
           .append(":")
           .append(String.format("%02d", seconds));

        return baf.toString();
    }


    private void updateProgress(){

        Track cTrack = musicService.getCurrentData();
        if(cTrack.getMood().equalsIgnoreCase("Radio")){
            hideEverything();
            nextImageView.setVisibility(View.GONE);
            prevImageView.setVisibility(View.GONE);
            favImageView.setVisibility(View.GONE);

            boolean isPlaying = musicService.isMediaPlaying();
            if(isPlaying){
                playPauseImageView.setImageResource(R.drawable.pause_icon);
            }else {
                playPauseImageView.setImageResource(R.drawable.play_icon);
            }

            titleTextView.setText(cTrack.getTitle());
            artistTextView.setText(cTrack.getMood());
            bgImageView.setImageResource(R.drawable.default_art);

        }
        else if(cTrack.getMood().equalsIgnoreCase("AIRFLOW 50") || cTrack.getMood().equalsIgnoreCase("SEARCH")){
            getAndSetData(cTrack, false);
        }else {
            getAndSetData(cTrack, true);
            setMaxAndDuration(musicService.getDuration());
        }

        /*if(cTrack.getMood().equalsIgnoreCase("Search")){
            similarButton.setVisibility(View.VISIBLE);
        }else{
            similarButton.setVisibility(View.GONE);
        }*/
    }


    public void startSimilarTracks(View view){
        if (internetHelper.isNetworkAvailable()) {
            mProgressDialog.setMessage("Starting playback...");
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();
            Track cTrack = musicService.getCurrentData();

            if(cTrack.getMood().equalsIgnoreCase("Search")){
                playSong(0, getSimilarTracks(cTrack));
            }else {
                mProgressDialog.dismiss();
                similarButton.setVisibility(View.GONE);
            }

        } else {
            showAlert(PlayerActivity.this);
        }
    }

    public void playSong(int position, ArrayList<Track> myTracks) {
        if(musicBound){
            musicService.setSongs(myTracks);
            musicService.setSongIndex(position);
            musicService.play();
        }else {
            Toast.makeText (this, "Unable to bind music service.", Toast.LENGTH_SHORT).show();
        }
    }

    private void getAndSetData(Track t, boolean showBar){

        setPlayPause(t, showBar);

        if(t == null){
            t = musicService.getCurrentData();
        }

        if(t != null){
            titleTextView.setText(t.getTitle());
            artistTextView.setText(t.getMood());

            //Mood getMood = moodHelper.getMoodFromString(t.getMood());
            //bgView.setBackgroundResource(getMood.Color);

            try{
                if(t.getBitmap() != null){
                    bgImageView.setImageBitmap(t.getBitmap());
                }else {
                    if(t.getArtwork() != null){
                        Picasso.with(PlayerActivity.this).load(t.getArtwork()).placeholder(R.drawable.default_art).into(bgImageView);
                    }
                    else {
                        bgImageView.setImageResource(R.drawable.default_art);
                    }
                }
            }
            catch(Exception e){
                bgImageView.setImageResource(R.drawable.default_art);
            }


        }
    }

    private void setPlayPause(Track t, boolean showBar){

        if(showBar){
            showEverything();
        }else {
            hideEverything();
        }

        boolean isPlaying = musicService.isMediaPlaying();
        if(isPlaying){
            playPauseImageView.setImageResource(R.drawable.pause_icon);
        }else {
             playPauseImageView.setImageResource(R.drawable.play_icon);
        }

        if(databaseUtils.isInFavList(t.getId())){
            favImageView.setImageResource(R.drawable.fav_icon);
        }else {
            favImageView.setImageResource(R.drawable.no_fav_icon);
        }

    }

    private void hideEverything() {
        positionTextView.setVisibility(View.GONE);
        durationTextView.setVisibility(View.GONE);
        progressSeekBar.setVisibility(View.GONE);
    }

    private void showEverything(){
        positionTextView.setVisibility(View.VISIBLE);
        durationTextView.setVisibility(View.VISIBLE);
        progressSeekBar.setVisibility(View.VISIBLE);
    }


    private void setMaxAndDuration(int duration){
        durationTextView.setText(getTimeString(duration));
        progressSeekBar.setMax(duration);
        updateProgressAndCurrentText();
    }

    void updateProgressAndCurrentText(){

         Thread thread =new Thread(){
          public void run(){

              final int duration = musicService.getDuration();
              try{
                  do{
                      if(!isPaused){
                          runOnUiThread(new Runnable() {
                              @Override
                              public void run() {
                                  progressSeekBar.setProgress(musicService.getCurrentPosition());
                                  positionTextView.setText(getTimeString(musicService.getCurrentPosition()));
                              }
                          });
                      }
                      sleep(1000);
                  }while(musicService.getCurrentPosition() < duration);

              }catch(Exception e){
                  e.printStackTrace();
              }
          }};

        thread.start();
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
            playIntent = new Intent(PlayerActivity.this, MusicService.class);
            bindService(playIntent, musicConnection, Context.BIND_AUTO_CREATE);
            startService(playIntent);
        }
    }

    private void initListeners() {

        playPauseImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(musicService.isMediaPlaying()){
                    if(musicService.canPause()){
                        musicService.pause();
                        playPauseImageView.setImageResource(R.drawable.play_icon);
                        isPaused = true;
                    }else {
                        Toast.makeText(PlayerActivity.this, "Please wait...", Toast.LENGTH_SHORT).show();
                        isPaused = false;
                    }
                }else {
                    if(musicService.canResume()){
                        musicService.resume();
                        playPauseImageView.setImageResource(R.drawable.pause_icon);
                        isPaused = false;
                    }else {
                        Toast.makeText(PlayerActivity.this, "Please wait...", Toast.LENGTH_SHORT).show();
                        isPaused = true;
                    }
                }
            }
        });

        nextImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                musicService.nextTrack(true);
            }
        });

        prevImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                musicService.prevTrack(true);
            }
        });

        favImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addRemoveFromToFav();
            }
        });


        progressSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(musicService.isMediaPlaying() && fromUser){
                    musicService.pause();
                    musicService.seekTo(progress);
                    musicService.resume();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void addRemoveFromToFav() {
        Track currentTrack = musicService.getCurrentData();
        if(databaseUtils.isInFavList(currentTrack.getId())){
            showRemoveAlert(currentTrack);
        }else {
            databaseUtils.addTrack(currentTrack);
            favImageView.setImageResource(R.drawable.fav_icon);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 999){
            if(internetHelper.isNetworkAvailable()){
                Toast.makeText(PlayerActivity.this, "You are connected.", Toast.LENGTH_SHORT).show();
            }else {
                showAlert(PlayerActivity.this);
                try{
                    musicService.play();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    private void showRemoveAlert(final Track currentTrack) {
        AlertDialog.Builder alert = new AlertDialog.Builder(PlayerActivity.this);
        alert.setTitle("Remove track");
        alert.setMessage("Are you sure you want to remove this track from your fav list?");
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                 databaseUtils.deleteTrack(currentTrack.getId(), true);
                 favImageView.setImageResource(R.drawable.no_fav_icon);
            }
        });
        alert.setNegativeButton("CANCEL", null);
        alert.show();
    }



    private ServiceConnection musicConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MusicService.MusicBinder binder = (MusicService.MusicBinder) service;
            musicService = binder.getService();
            //init our callback
            musicService.initEvent(PlayerActivity.this);
            musicBound = true;
            initListeners();
            updateProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            musicBound = false;
        }


    };

    @Override
    public void trackChanged() {
        if(mProgressDialog != null){
            try{
                mProgressDialog.dismiss();
            }catch (Exception e){
                //ignore...
            }
        }
        updateProgress();

    }

    @Override
    public void noInternet() {
        showAlert(PlayerActivity.this);
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



   /* @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("Mood", moodName.Name);
        super.onSaveInstanceState(outState);
    }

    private void saveData(){
        SharedPreferences.Editor _spEditor = getPreferences(Context.MODE_PRIVATE).edit();
        _spEditor.putString("Mood", moodName.Name);
        _spEditor.commit();
    }
*/


}
