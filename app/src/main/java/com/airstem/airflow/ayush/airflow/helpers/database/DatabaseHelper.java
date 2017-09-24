package com.airstem.airflow.ayush.airflow.helpers.database;

import com.airstem.airflow.ayush.airflow.enums.collection.Type;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionArtist;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionPlaylist;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionRadio;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionTrack;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionVideo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by mcd-50 on 14/7/17.
 */

public class DatabaseHelper{

    public static void createOrUpdateTracks(Realm realm, final ArrayList<CollectionTrack> collectionTracks){
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                bgRealm.insertOrUpdate(collectionTracks);
            }
        });
    }

    public static void createOrUpdateArtists(Realm realm, final ArrayList<CollectionArtist> collectionArtists){
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                bgRealm.insertOrUpdate(collectionArtists);
            }
        });
    }

    public static void createOrUpdateRadios(Realm realm, final ArrayList<CollectionRadio> collectionRadios){
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                bgRealm.insertOrUpdate(collectionRadios);
            }
        });
    }

    public static void createOrUpdatePlaylists(Realm realm, final ArrayList<CollectionPlaylist> collectionPlaylists){
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                bgRealm.insertOrUpdate(collectionPlaylists);
            }
        });
    }

    public static void createOrUpdateVideos(Realm realm, final ArrayList<CollectionVideo> collectionVideos){
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                bgRealm.insertOrUpdate(collectionVideos);
            }
        });
    }

    public static void deleteTracks(Realm realm, final RealmResults<CollectionTrack> collectionTracks){
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                collectionTracks.deleteAllFromRealm();
            }
        });
    }

    public static void deleteArtists(Realm realm, final RealmResults<CollectionArtist> collectionArtists){
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                collectionArtists.deleteAllFromRealm();
            }
        });
    }

    public static void deletePlaylists(Realm realm, final RealmResults<CollectionPlaylist> collectionPlaylists){
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                collectionPlaylists.deleteAllFromRealm();
            }
        });
    }

    public static void deleteRadios(Realm realm, final RealmResults<CollectionRadio> collectionRadios){
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                collectionRadios.deleteAllFromRealm();
            }
        });
    }

    public static void deleteVideos(Realm realm, final RealmResults<CollectionVideo> collectionVideos){
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                collectionVideos.deleteAllFromRealm();
            }
        });
    }
}
