package com.airstem.airflow.ayush.airflow.service;


import android.app.NotificationManager;
import android.app.Service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.AudioManager;


import android.media.MediaPlayer;
import android.media.session.MediaSessionManager;
import android.net.wifi.WifiManager;
import android.os.Binder;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.annotation.Nullable;

import android.support.v4.app.TaskStackBuilder;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;

import com.airstem.airflow.ayush.airflow.PlayerActivity;
import com.airstem.airflow.ayush.airflow.R;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionTrack;


import java.io.IOException;
import java.util.ArrayList;

import android.app.PendingIntent;




/**
 * Created by ayush on 05-10-16.
 */


public class MusicService extends Service
        /*implements AudioManager.OnAudioFocusChangeListener ,
        MediaPlayer.OnCompletionListener,
        MediaPlayer.OnErrorListener,
        MediaPlayer.OnPreparedListener,
        MediaPlayer.OnSeekCompleteListener,
        MediaPlayer.OnBufferingUpdateListener*/
        {
            @Nullable
            @Override
            public IBinder onBind(Intent intent) {
                return null;
            }


    /*// The volume we set the media player to when we lose audio focus, but are
    // allowed to reduce the volume instead of stopping playback.
    public static final float VOLUME_DUCK = 0.2f;
    // The volume we set the media player when we have audio focus.
    public static final float VOLUME_NORMAL = 1.0f;
    // we don't have audio focus, and can't duck (play at a low volume)
    private static final int AUDIO_NO_FOCUS_NO_DUCK = 0;
    // we don't have focus, but can duck (play at a low volume)
    private static final int AUDIO_NO_FOCUS_CAN_DUCK = 1;
    // we have full audio focus
    private static final int AUDIO_FOCUSED  = 2;



    private int mAudioFocus = AUDIO_NO_FOCUS_NO_DUCK;
    private AudioManager mAudioManager;
    WifiManager.WifiLock mWifiLock;
    ArrayList<CollectionTrack> mItems;

    //private ExoPlayer exoPlayer;
    private Track radio;
    private PlayMode playMode;
    private MediaPlayer mPlayer;
    private int mSongPos;
    boolean mIsBound = false;
    private final IBinder musicBind = new MusicBinder();


    InternetHelper internetHelper;
    //Media sessions
    private MediaSessionManager mediaSessionManager;
    private MediaSessionCompat mediaSession;
    private MediaControllerCompat.TransportControls transportControls;
    private final int NOTIFICATION_ID = 999;



    private CustomEvent mCustomEvent;

    @Override
    public void onCreate() {
        super.onCreate();

        internetHelper = new InternetHelper(this);
        mWifiLock = ((WifiManager) getSystemService(Context.WIFI_SERVICE))
                     .createWifiLock(WifiManager.WIFI_MODE_FULL, WIFI_CONST);
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        mTracks = new ArrayList<>();

        mSongPos = 0;
        mCurrentPosition = 0;
        createMediaPlayerIfNeeded();
    }

    public void initEvent(CustomEvent customEvent){
        mCustomEvent = customEvent;

        if(mState == PlaybackStateCompat.STATE_PAUSED || mState == PlaybackStateCompat.STATE_BUFFERING ||
                mState == PlaybackStateCompat.STATE_PLAYING || mState == PlaybackStateCompat.STATE_CONNECTING){
            mCustomEvent.trackChanged();
        }

    }


    public enum PlaybackStatus{
        PLAYING,
        PAUSED
    }

    private void initMediaSession(){
        if(mediaSessionManager != null) return;

        mediaSessionManager = (MediaSessionManager) getSystemService(Context.MEDIA_SESSION_SERVICE);
        mediaSession = new MediaSessionCompat(getApplicationContext(), "Music Service");
        transportControls = mediaSession.getController().getTransportControls();

        mediaSession.setActive(true);
        mediaSession.setFlags(MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS);


        //to be implemented...
        if(mState == PlaybackStateCompat.STATE_PAUSED || mState == PlaybackStateCompat.STATE_BUFFERING ||
                mState == PlaybackStateCompat.STATE_PLAYING || mState == PlaybackStateCompat.STATE_CONNECTING)
            updateMetaData();

        mediaSession.setCallback(new MediaSessionCompat.Callback() {
            @Override
            public void onPause() {
                super.onPause();
                pause();
            }

            @Override
            public void onPlay() {
                super.onPlay();
                resume();
            }

            @Override
            public void onSkipToNext() {
                super.onSkipToNext();
                if(playMode != PlayMode.RADIO){
                    nextTrack(false);
                    updateMetaData();
                }
                buildNotification(PlaybackStatus.PLAYING);
            }
        });

    }

    private void updateMetaData(){
        try {
            Track t = getCurrentData();
            Bitmap art = BitmapFactory.decodeResource(getResources(), R.drawable.default_art);
            mediaSession.setMetadata(new MediaMetadataCompat.Builder()
                    .putBitmap(MediaMetadataCompat.METADATA_KEY_ALBUM_ART, art)
                    .putString(MediaMetadataCompat.METADATA_KEY_TITLE, t.getTitle())
                    .putString(MediaMetadataCompat.METADATA_KEY_ALBUM, t.getMood())
                    .build());
        }catch (Exception e){

        }
    }

    private void buildNotification(PlaybackStatus playbackStatus){
        try {
            Track t = getCurrentData();
            PendingIntent playPauseAction = null;
            int state = android.R.drawable.ic_media_pause;

            switch (playbackStatus){

                case PLAYING: state = android.R.drawable.ic_media_pause;
                    playPauseAction = playbackAction(1);
                    break;
                case PAUSED: state = android.R.drawable.ic_media_play;
                    playPauseAction = playbackAction(0);
                    break;
            }

            Intent resultIntent = new Intent(this, PlayerActivity.class);
            TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);

            stackBuilder.addParentStack(PlayerActivity.class);

            stackBuilder.addNextIntent(resultIntent);
            PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

            Bitmap art = BitmapFactory.decodeResource(getResources(), R.drawable.default_art);
            android.support.v7.app.NotificationCompat.Builder notificationBuilder =  new android.support.v7.app.NotificationCompat.Builder(this);

                    notificationBuilder.setVisibility(android.support.v7.app.NotificationCompat.VISIBILITY_PUBLIC)
                    .setStyle(new android.support.v7.app.NotificationCompat.MediaStyle()
                              .setMediaSession(mediaSession.getSessionToken())
                              .setShowActionsInCompactView(0,1))
                    .setLargeIcon(art)
                    .setColor(Color.parseColor("#FF262626"))
                    .setSmallIcon(R.drawable.default_art)
                    .setContentText(t.getMood())
                    .setContentTitle(t.getTitle())
                    .setContentIntent(resultPendingIntent)
                    .addAction(state, "pause", playPauseAction)
                    .addAction(android.R.drawable.ic_media_next, "next", playbackAction(2));


            ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).notify(NOTIFICATION_ID, notificationBuilder.build());

        }catch (Exception e){

        }
    }



    private PendingIntent playbackAction(int id){
        Intent playbackAction = new Intent(this, MusicService.class);
        switch (id){
            case 0 : playbackAction.setAction(ACTION_PLAY);
                     return PendingIntent.getService(this, id, playbackAction, 0);
            case 1 : playbackAction.setAction(ACTION_PAUSE);
                     return PendingIntent.getService(this, id, playbackAction, 0);
            case 2 : playbackAction.setAction(ACTION_NEXT);
                     return PendingIntent.getService(this, id, playbackAction, 0);
            default:
                    break;
        }
        return null;
    }

    private void handleAction(Intent playbackAction){
        if(playbackAction == null || playbackAction.getAction() == null) return;
        String action  = playbackAction.getAction();
        if(action.equalsIgnoreCase(ACTION_PLAY)){
            transportControls.play();
        }else if(action.equalsIgnoreCase(ACTION_PAUSE)){
            transportControls.pause();
        }else if(action.equalsIgnoreCase(ACTION_NEXT)){
            transportControls.skipToNext();
        }
    }

    private  void removeNotification(){
        ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).cancel(NOTIFICATION_ID);
    }


  *//*  private void showNotification(String title, String mood){


        Intent playIntent = new Intent(ACTION_PLAY);
        Intent pauseIntent = new Intent(ACTION_PAUSE);

        PendingIntent playPendingIntent = PendingIntent.getBroadcast(this,PENDING_ID, playIntent, 0);
        PendingIntent pausePendingIntent = PendingIntent.getBroadcast(this, PENDING_ID, pauseIntent, 0);

        NotificationCompat.Builder mBuilder =
        new NotificationCompat.Builder(this)
         .setSmallIcon(R.drawable.default_art)
         .setContentTitle(title)
         .setContentText(mood).addAction().build();




        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }*//*

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(mediaSessionManager == null){
            try {
                 initMediaSession();
            }catch (Exception e){
                e.printStackTrace();
                stopSelf();
            }
            buildNotification(PlaybackStatus.PLAYING);
        }
        handleAction(intent);
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        mIsBound = true;
        return musicBind;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        mIsBound = false;
        return  false;
    }

    @Override
    public void onDestroy ()
    {
        super.onDestroy();
        removeNotification();
    }


    public void setSongs(ArrayList<Track> tracks){
        mTracks = tracks;
        playMode = tracks.get(0).getMode();
    }

    public void setRadio(Track track){
        radio = track;
        playMode = track.getMode();
    }


    public void setSongIndex(int index){
        mSongPos = index;
    }


    public void nextTrack(boolean show){
        if(internetHelper.isNetworkAvailable() || playMode == PlayMode.OFFLINE ){
            mSongPos  = (mSongPos + 1) % mTracks.size();
            //if(show)
               //Toast.makeText(this, "Changing track...",Toast.LENGTH_SHORT).show();
            play();
            mCustomEvent.trackChanged();
        }else {
            mCustomEvent.noInternet();
        }
    }

    public void prevTrack(boolean show){
        if(internetHelper.isNetworkAvailable() || playMode == PlayMode.OFFLINE){
            if(mSongPos > 0)
               mSongPos  = (mSongPos - 1) % mTracks.size();
            else
                mSongPos =  mTracks.size() - 1;
            //if(show)
                //Toast.makeText(this, "Changing track...",Toast.LENGTH_SHORT).show();
            play();
            mCustomEvent.trackChanged();
        }else {
            mCustomEvent.noInternet();
        }
    }


    private int mState;
    private boolean mPlayOnFocusGain;
    private volatile boolean mAudioNoisyReceiverRegistered;
    private volatile int mCurrentPosition;


    private final IntentFilter mAudioNoisyIntentFilter =
            new IntentFilter(AudioManager.ACTION_AUDIO_BECOMING_NOISY);



    private final BroadcastReceiver mAudioNoisyReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (AudioManager.ACTION_AUDIO_BECOMING_NOISY.equals(intent.getAction())) {
                if (mPlayer.isPlaying()) {
                    pause();
                }
            }
        }
    };

    public void stop() {
        mState = PlaybackStateCompat.STATE_STOPPED;
        // Give up Audio focus
        giveUpAudioFocus();
        unregisterAudioNoisyReceiver();
        // Relax all resources
        relaxResources(true);
        if (mWifiLock.isHeld()) mWifiLock.release();
    }


    public void play() {

        runOnThread();

//        mPlayer.reset();
//        mPlayOnFocusGain = true;
//        tryToGetAudioFocus();
//        registerAudioNoisyReceiver();
//
//        if(playMode == PlayMode.RADIO){
//            if (mState == PlaybackStateCompat.STATE_PAUSED && mPlayer != null) {
//                configMediaPlayerState();
//            } else {
//                mState = PlaybackStateCompat.STATE_STOPPED;
//                relaxResources(false);
//                try {
//                    createMediaPlayerIfNeeded();
//                    mState = PlaybackStateCompat.STATE_BUFFERING;
//                    mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//                    mPlayer.setDataSource(radio.getTrackUrl());
//                    mPlayer.prepareAsync();
//                    mWifiLock.acquire();
//                } catch (Exception ex) {
//                    mState = PlaybackStateCompat.STATE_ERROR;
//                }
//            }
//        } else {
//
//            mCurrentPosition = 0;
//            Track track = mTracks.get(mSongPos);
//
//            if (mState == PlaybackStateCompat.STATE_PAUSED && mPlayer != null) {
//                configMediaPlayerState();
//            } else {
//                mState = PlaybackStateCompat.STATE_STOPPED;
//                relaxResources(false); // release everything except MediaPlayer
//
//                try {
//                    createMediaPlayerIfNeeded();
//                    mState = PlaybackStateCompat.STATE_BUFFERING;
//                    mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//
//                    mPlayer.setDataSource(track.getTrackUrl());
//                    mPlayer.prepareAsync();
//
//                    mWifiLock.acquire();
//                } catch (IOException ex) {
//                    mState = PlaybackStateCompat.STATE_ERROR;
//                }
//            }
//        }
    }


            public void runOnThread() {
                mPlayer.reset();
                mPlayOnFocusGain = true;
                tryToGetAudioFocus();
                registerAudioNoisyReceiver();

                if(playMode == PlayMode.RADIO){
                    startInternally(radio);
                }else {
                    mCurrentPosition = 0;
                    startInternally(mTracks.get(mSongPos));
                }
            }

    private void startInternally(Track track){
        if (mState == PlaybackStateCompat.STATE_PAUSED && mPlayer != null) {
            configMediaPlayerState();
        } else {
            mState = PlaybackStateCompat.STATE_STOPPED;
            relaxResources(false); // release everything except MediaPlayer

            try {
                createMediaPlayerIfNeeded();
                mState = PlaybackStateCompat.STATE_BUFFERING;
                mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mPlayer.setDataSource(track.getTrackUrl());
                
                *//*try{
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            mPlayer.prepareAsync();
                        }
                    }).start();
                }catch (Exception e){
                    mPlayer.prepareAsync();
                }
               *//*

                mPlayer.prepareAsync();
                mWifiLock.acquire();
            } catch (IOException ex) {
                mState = PlaybackStateCompat.STATE_ERROR;
            }
        }

    }


    public boolean canPause(){
        return mState == PlaybackStateCompat.STATE_PLAYING;
    }


    public boolean canResume(){
        return mState == PlaybackStateCompat.STATE_PAUSED;
    }

    public void pause() {
        if (mState == PlaybackStateCompat.STATE_PLAYING) {
            // Pause media player and cancel the 'foreground service' state.
            if (mPlayer != null && mPlayer.isPlaying()) {
                mPlayer.pause();
                if(playMode != PlayMode.RADIO)
                   mCurrentPosition = mPlayer.getCurrentPosition();
            }
            relaxResources(false);
            giveUpAudioFocus();
            buildNotification(PlaybackStatus.PAUSED);
            mState = PlaybackStateCompat.STATE_PAUSED;
            unregisterAudioNoisyReceiver();
        }
    }


    public void resume(){
        if (mState == PlaybackStateCompat.STATE_PAUSED) {
            tryToGetAudioFocus();
            if (mPlayer != null && !mPlayer.isPlaying()) {
                if(playMode != PlayMode.RADIO)
                   mPlayer.seekTo(mCurrentPosition);
                mPlayer.start();
            }
            buildNotification(PlaybackStatus.PLAYING);
            mState = PlaybackStateCompat.STATE_PLAYING;
            registerAudioNoisyReceiver();
        }

    }


    public boolean isMediaPlaying(){
        return (mPlayer != null && mState == PlaybackStateCompat.STATE_PLAYING);
    }


    public boolean isMediaPlayerActive(){
        return mPlayer != null && mState != PlaybackStateCompat.STATE_ERROR;
    }

    public Track getCurrentData(){
        if(playMode == PlayMode.RADIO)
            return radio;
        else if(mTracks != null && mTracks.size() > 0)
            return mTracks.get(mSongPos);
        return null;
    }



    public int getCurrentPosition(){
        return (mPlayer != null && mState == PlaybackStateCompat.STATE_PLAYING) ? mPlayer.getCurrentPosition() : 0;
    }

    public int getDuration(){
        return (mPlayer != null && mState == PlaybackStateCompat.STATE_PLAYING) ? mPlayer.getDuration() : 0;
    }

    public void seekTo(int position) {
        if (mPlayer == null) {
            // If we do not have a current media player, simply update the current position
            mCurrentPosition = position;
        } else {
            if (mPlayer.isPlaying()) {
                mState = PlaybackStateCompat.STATE_BUFFERING;
            }
            mCurrentPosition = position;
        }
    }


    private void tryToGetAudioFocus() {
        if (mAudioFocus != AUDIO_FOCUSED) {
            int result = mAudioManager.requestAudioFocus(this, AudioManager.STREAM_MUSIC,
                    AudioManager.AUDIOFOCUS_GAIN);
            if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                mAudioFocus = AUDIO_FOCUSED;
            }
        }
    }


    private void giveUpAudioFocus() {
        if (mAudioFocus == AUDIO_FOCUSED) {
            if (mAudioManager.abandonAudioFocus(this) == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                mAudioFocus = AUDIO_NO_FOCUS_NO_DUCK;
            }
        }

    }

    private void configMediaPlayerState() {
        if (mAudioFocus == AUDIO_NO_FOCUS_NO_DUCK) {
            // If we don't have audio focus and can't duck, we have to pause,
            if (mState == PlaybackStateCompat.STATE_PLAYING) {
                pause();
            }

        } else {  // we have audio focus:

            if (mAudioFocus == AUDIO_NO_FOCUS_CAN_DUCK) {
                mPlayer.setVolume(VOLUME_DUCK, VOLUME_DUCK); // we'll be relatively quiet
            } else {

                if (mPlayer != null) {
                    mPlayer.setVolume(VOLUME_NORMAL, VOLUME_NORMAL); // we can be loud again
                } // else do something for remote client.
            }
            // If we were playing when we lost focus, we need to resume playing.
            if (mPlayOnFocusGain) {
                if (mPlayer != null && !mPlayer.isPlaying()) {
                    if (mCurrentPosition == mPlayer.getCurrentPosition()) {
                        mPlayer.start();
                        mState = PlaybackStateCompat.STATE_PLAYING;
                        mCustomEvent.trackChanged();

                        removeNotification();

                        updateMetaData();
                        buildNotification(PlaybackStatus.PLAYING);

                    } else {
                        if(playMode != PlayMode.RADIO)
                           mPlayer.seekTo(mCurrentPosition);
                        mState = PlaybackStateCompat.STATE_BUFFERING;
                    }
                }
                mPlayOnFocusGain = false;
            }
        }
    }

    @Override
    public void onAudioFocusChange(int focusChange) {
        if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
            // We have gained focus:
            mAudioFocus = AUDIO_FOCUSED;
        } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS ||
                focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
            // We have lost focus. If we can duck (low playback volume), we can keep playing.
            // Otherwise, we need to pause the playback.
            boolean canDuck = focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK;
            mAudioFocus = canDuck ? AUDIO_NO_FOCUS_CAN_DUCK : AUDIO_NO_FOCUS_NO_DUCK;
            // If we are playing, we need to reset media player by calling configMediaPlayerState
            // with mAudioFocus properly set.
            if (mState == PlaybackStateCompat.STATE_PLAYING && !canDuck) {
                // If we don't have audio focus and can't duck, we save the information that
                // we were playing, so that we can resume playback once we get the focus back.
                mPlayOnFocusGain = true;
            }
        }
        configMediaPlayerState();
    }



    @Override
    public void onPrepared(MediaPlayer player) {
        // The media player is done preparing. That means we can start playing if we
        // have audio focus.
        configMediaPlayerState();
    }


    @Override
    public void onCompletion(MediaPlayer mp) {
        if(internetHelper.isNetworkAvailable() || playMode == PlayMode.OFFLINE){
            if(playMode != PlayMode.RADIO)
              mSongPos  = (mSongPos + 1) % mTracks.size();
            play();
        }else{
            mCustomEvent.noInternet();
        }
    }

    int tryCount = 0;
    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
         if(internetHelper.isNetworkAvailable() || playMode == PlayMode.OFFLINE){
            if(playMode != PlayMode.RADIO)
                mSongPos  = (mSongPos + 1) % mTracks.size();
            play();
        }
        else{
            mCustomEvent.noInternet();
        }
        //mCustomEvent.noInternet();
        return false;
    }

    @Override
    public void onSeekComplete(MediaPlayer mp) {
        mCurrentPosition = mp.getCurrentPosition();
        if (mState == PlaybackStateCompat.STATE_BUFFERING) {
            mPlayer.start();
            mState = PlaybackStateCompat.STATE_PLAYING;
        }
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {

        int i = percent;
        int j = i;

    }


    private void createMediaPlayerIfNeeded() {
        if (mPlayer == null) {
            mPlayer = new MediaPlayer();
            mPlayer.setWakeMode(getApplicationContext(),
                    PowerManager.PARTIAL_WAKE_LOCK);
            mPlayer.setOnPreparedListener(this);
            mPlayer.setOnCompletionListener(this);
            mPlayer.setOnErrorListener(this);
            mPlayer.setOnSeekCompleteListener(this);
            mPlayer.setOnBufferingUpdateListener(this);


           *//* mPlayer.setOnCompletionListener(new FFmpegMediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(FFmpegMediaPlayer mp) {
                    if(internetHelper.isNetworkAvailable() || playMode == PlayMode.OFFLINE){
                        if(playMode != PlayMode.RADIO)
                            mSongPos  = (mSongPos + 1) % mTracks.size();
                        play();
                    }else{
                        mCustomEvent.noInternet();
                    }
                }
            });

            mPlayer.setOnSeekCompleteListener(new FFmpegMediaPlayer.OnSeekCompleteListener() {
                @Override
                public void onSeekComplete(FFmpegMediaPlayer mp) {
                    mCurrentPosition = mp.getCurrentPosition();
                    if (mState == PlaybackStateCompat.STATE_BUFFERING) {
                        mPlayer.start();
                        mState = PlaybackStateCompat.STATE_PLAYING;
                    }
                }
            });

            mPlayer.setOnErrorListener(new FFmpegMediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(FFmpegMediaPlayer mp, int what, int extra) {
                    if(internetHelper.isNetworkAvailable() || playMode == PlayMode.OFFLINE){
                        if(playMode != PlayMode.RADIO)
                            mSongPos  = (mSongPos + 1) % mTracks.size();
                        play();
                    }
                    else{
                        mCustomEvent.noInternet();
                    }
                    //mCustomEvent.noInternet();
                    return false;
                }
            });

            mPlayer.setOnPreparedListener(new FFmpegMediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(FFmpegMediaPlayer mp) {
                    configMediaPlayerState();
                }
            });

            mPlayer.setOnBufferingUpdateListener(new FFmpegMediaPlayer.OnBufferingUpdateListener() {
                @Override
                public void onBufferingUpdate(FFmpegMediaPlayer mp, int percent) {
                    int i = percent;
                    int j = i;
                }
            });*//*

        } else {
            mPlayer.reset();
        }
    }

    private void relaxResources(boolean releaseMediaPlayer) {
        // stop and release the Media Player, if it's available
        if (releaseMediaPlayer && mPlayer != null) {
            mPlayer.reset();
            mPlayer.release();
            mPlayer = null;
        }
        // we can also release the Wifi lock, if we're holding it
        if (mWifiLock.isHeld()) {
            mWifiLock.release();
        }
    }
    private void registerAudioNoisyReceiver() {
        if (!mAudioNoisyReceiverRegistered) {
            registerReceiver(mAudioNoisyReceiver, mAudioNoisyIntentFilter);
            mAudioNoisyReceiverRegistered = true;
        }
    }


    private void unregisterAudioNoisyReceiver() {
        if (mAudioNoisyReceiverRegistered) {
            unregisterReceiver(mAudioNoisyReceiver);
            mAudioNoisyReceiverRegistered = false;
        }
    }


    public class MusicBinder extends Binder{
        public MusicService getService(){
            return MusicService.this;
        }
    }*/
}
