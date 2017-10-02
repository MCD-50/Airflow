package com.airstem.airflow.ayush.airflow;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.airstem.airflow.ayush.airflow.events.collection.CursorListener;
import com.airstem.airflow.ayush.airflow.helpers.collection.LocalArtistHelper;
import com.airstem.airflow.ayush.airflow.helpers.collection.LocalVideoHelper;
import com.airstem.airflow.ayush.airflow.helpers.database.DatabaseHelper;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionArtist;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionPlaylist;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionTrack;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionVideo;
import com.anthonycr.grant.PermissionsManager;
import com.anthonycr.grant.PermissionsResultAction;

import java.util.ArrayList;

import io.realm.Realm;


/**
 * Created by ayush on 11-10-16.
 */
public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_page);

        PermissionsManager.getInstance().requestAllManifestPermissionsIfNecessary(this,
                new PermissionsResultAction() {
                    @Override
                    public void onGranted() {
                        // Proceed with initialization
                        initDatabase(Realm.getDefaultInstance());
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent nIntent = new Intent(SplashActivity.this, MainActivity.class);
                                startActivity(nIntent);
                                finish();
                            }
                        }, 1000);
                    }

                    @Override
                    public void onDenied(String permission) {
                        // Notify the user that you need all of the permissions
                        int x = 1;
                    }
                });
    }

    private void initDatabase(final Realm realm){
        //now get all cursors and save to realm
        LocalArtistHelper.getAllArtists(getApplicationContext(), realm, new CursorListener() {
            @Override
            public void onSuccess(ArrayList<CollectionTrack> collectionTracks, ArrayList<CollectionVideo> collectionVideos, ArrayList<CollectionPlaylist> collectionPlaylists, ArrayList<CollectionArtist> collectionArtists) {

            }

            @Override
            public void onArtistAndTracksFill(ArrayList<CollectionArtist> collectionArtists, ArrayList<CollectionTrack> collectionTracks) {
                //ArrayList<CollectionPlaylist> collectionPlaylists = LocalPlaylistHelper.getAllPlaylists(airstemApplication);
                ArrayList<CollectionVideo> collectionVideos = LocalVideoHelper.getAllVideos(getApplicationContext());

                DatabaseHelper.createOrUpdateTracks(realm, collectionTracks);
                DatabaseHelper.createOrUpdateArtists(realm, collectionArtists);
                DatabaseHelper.createOrUpdateVideos(realm, collectionVideos);
                //DatabaseHelper.createOrUpdatePlaylists(realm, collectionPlaylists);
            }
        });
    }
}
