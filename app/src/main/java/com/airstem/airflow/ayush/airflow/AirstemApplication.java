package com.airstem.airflow.ayush.airflow;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.airstem.airflow.ayush.airflow.events.collection.CursorListener;
import com.airstem.airflow.ayush.airflow.events.service.PlayerListener;
import com.airstem.airflow.ayush.airflow.helpers.collection.CollectionConstant;
import com.airstem.airflow.ayush.airflow.helpers.collection.LocalArtistHelper;
import com.airstem.airflow.ayush.airflow.helpers.collection.LocalPlaylistHelper;
import com.airstem.airflow.ayush.airflow.helpers.collection.LocalTrackHelper;
import com.airstem.airflow.ayush.airflow.helpers.collection.LocalVideoHelper;
import com.airstem.airflow.ayush.airflow.helpers.database.DatabaseHelper;
import com.airstem.airflow.ayush.airflow.helpers.database.StoreHelper;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionArtist;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionPlaylist;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionTrack;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionVideo;
import com.airstem.airflow.ayush.airflow.service.MusicService;
import com.anthonycr.grant.PermissionsManager;
import com.anthonycr.grant.PermissionsResultAction;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by mcd-50 on 15/8/17.
 */

public class AirstemApplication extends Application implements PlayerListener {

    Intent intent;
    MusicService musicService;
    boolean musicBound = false;

    //helpers
    AirstemApplication airstemApplication = null;
    Realm realm;


    public void onCreate() {
        super.onCreate();

        // Initialize realm.
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .build();

        Realm.setDefaultConfiguration(config);
        airstemApplication = this;
    }

   /* private ServiceConnection musicConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MusicService.MusicBinder binder = (MusicService.MusicBinder) service;
            musicService = binder.getService();
            musicBound = true;
            musicService.initEvent(airstemApplication);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            musicBound = false;
            stopService(airstemApplication);
        }
    };

    private void initService(AirstemApplication airstemApplication){
        intent = new Intent(airstemApplication, MusicService.class);
        airstemApplication.bindService(intent, musicConnection ,Context.BIND_AUTO_CREATE);
        airstemApplication.startService(intent);
    }

    private void stopService(AirstemApplication airstemApplication){
        airstemApplication.unbindService(musicConnection);
        airstemApplication.stopService(intent);
    }

    @Override
    public ComponentName startService(Intent service) {
        return super.startService(service);

    }

    @Override
    public boolean stopService(Intent name) {
        return super.stopService(name);
    }

    @Override
    public boolean bindService(Intent service, ServiceConnection conn, int flags) {
        return super.bindService(service, conn, flags);
    }

    @Override
    public void unbindService(ServiceConnection conn) {
        super.unbindService(conn);
    }
    */


    @Override
    public void onNext(String databaseId) {
        Intent i = new Intent(CollectionConstant.PLAYER_ACTION_INTENT_FILTER)
                .putExtra(CollectionConstant.PLAYER_NEXT, databaseId);
        this.sendBroadcast(i);
    }

    @Override
    public void onPrev(String databaseId) {
        Intent i = new Intent(CollectionConstant.PLAYER_ACTION_INTENT_FILTER)
                .putExtra(CollectionConstant.PLAYER_PREV, databaseId);
        this.sendBroadcast(i);
    }

    @Override
    public void onTrackChange(String databaseId) {
        Intent i = new Intent(CollectionConstant.PLAYER_ACTION_INTENT_FILTER)
                .putExtra(CollectionConstant.PLAYER_CURRENT_TRACK_ID, databaseId);
        this.sendBroadcast(i);
    }

    @Override
    public void onPlayerActive() {
        Intent i = new Intent(CollectionConstant.PLAYER_ACTION_INTENT_FILTER)
                .putExtra(CollectionConstant.PLAYER_IS_PLAYER_ACTIVE, true);
        this.sendBroadcast(i);
    }

    @Override
    public void onPlayPause(boolean isPlay) {
        Intent i = new Intent(CollectionConstant.PLAYER_ACTION_INTENT_FILTER)
                .putExtra(CollectionConstant.PLAYER_PLAY, isPlay);
        this.sendBroadcast(i);
    }
}
