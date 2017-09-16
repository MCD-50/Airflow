package com.airstem.airflow.ayush.airflow.helpers.database;

import com.airstem.airflow.ayush.airflow.enums.collection.Type;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionArtist;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionPlaylist;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionRadio;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionTrack;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionVideo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by mcd-50 on 14/7/17.
 */

public class DatabaseHelper{

    public static void createOrUpdateTracks(Realm realm, ArrayList<CollectionTrack> collectionTracks){
        for(CollectionTrack item : collectionTracks){
           createOrUpdateTrack(realm, item);
        }
    }

    public static void createOrUpdateArtists(Realm realm, ArrayList<CollectionArtist> collectionArtists){
        for(CollectionArtist item : collectionArtists){
            createOrUpdateArtist(realm, item);
        }
    }

    public static void createOrUpdateRadios(Realm realm, ArrayList<CollectionRadio> collectionRadios){
        for(CollectionRadio item : collectionRadios){
            createOrUpdateRadio(realm, item);
        }
    }

    public static void createOrUpdatePlaylists(Realm realm, ArrayList<CollectionPlaylist> collectionPlaylists){
        for(CollectionPlaylist item : collectionPlaylists){
            createOrUpdatePlaylist(realm, item);
        }
    }

    public static void createOrUpdateVideos(Realm realm, ArrayList<CollectionVideo> collectionVideos){
        for(CollectionVideo item : collectionVideos){
            createOrUpdateVideo(realm, item);
        }
    }

    public static void deleteTracks(Realm realm, ArrayList<CollectionTrack> collectionTracks){
        for(CollectionTrack item : collectionTracks){
            deleteTrack(realm, item);
        }
    }

    public static void deleteArtists(Realm realm, ArrayList<CollectionArtist> collectionArtists){
        for(CollectionArtist item : collectionArtists){
            deleteArtist(realm, item);
        }
    }

    public static void deletePlaylists(Realm realm, ArrayList<CollectionPlaylist> collectionPlaylists){
        for(CollectionPlaylist item : collectionPlaylists){
            deletePlaylist(realm, item);
        }
    }

    public static void deleteRadios(Realm realm, ArrayList<CollectionRadio> collectionRadios){
        for(CollectionRadio item : collectionRadios){
            deleteRadio(realm, item);
        }
    }

    public static void deleteVideos(Realm realm, ArrayList<CollectionVideo> collectionVideos){
        for(CollectionVideo item : collectionVideos){
            deleteVideo(realm, item);
        }
    }

    public static void createOrUpdateTrack(Realm realm, CollectionTrack item){
        CollectionTrack collectionTrack =  checkIfTrackExists(realm, item.getDatabaseId());
        if(collectionTrack == null){
            collectionTrack = realm.createObject(CollectionTrack.class);
        }

        //setter
        if(collectionTrack.getIsOffline()){
            collectionTrack.setDatabaseId(item.getLocalId());
        }else{
            collectionTrack.setDatabaseId(item.getDatabaseId());
        }

        collectionTrack.setLocalId(item.getLocalId());
        collectionTrack.setTitle(item.getTitle());
        collectionTrack.setAlbumName(item.getAlbumName());
        collectionTrack.setArtistName(item.getArtistName());
        collectionTrack.setTrackOnlineUrl(item.getTrackOnlineUrl());
        collectionTrack.setTrackOfflineUrl(item.getTrackOfflineUrl());
        collectionTrack.setArtworkUrl(item.getArtworkUrl());
        collectionTrack.setIsFav(item.getIsFav());
        collectionTrack.setIsOffline(item.getIsOffline());


        //utils
        collectionTrack.setPlayCount(item.getPlayCount());
        collectionTrack.setLastPlayed(item.getLastPlayed());
        collectionTrack.setModifiedOn(item.getModifiedOn());


        //for linking playlist and artist
        collectionTrack.setPlaylistId(item.getPlaylistId());
        collectionTrack.setArtistId(item.getArtistId());
    }

    public static void createOrUpdateArtist(Realm realm, CollectionArtist item){
        CollectionArtist collectionArtist =  checkIfArtistExists(realm, item.getDatabaseId());
        if(collectionArtist == null){
            collectionArtist = realm.createObject(CollectionArtist.class);
        }

        //setter
        collectionArtist.setDatabaseId(item.getDatabaseId());
        collectionArtist.setLocalId(item.getLocalId());
        collectionArtist.setArtworkUrl(item.getArtworkUrl());
        collectionArtist.setTitle(item.getTitle());
        collectionArtist.setTracks(item.getTracks());
    }


    public static void createOrUpdateRadio(Realm realm, CollectionRadio item){
        CollectionRadio collectionRadio = checkIfRadioExists(realm, item.getDatabaseId());
        if(collectionRadio == null){
            collectionRadio = realm.createObject(CollectionRadio.class);
        }

        //setter
        collectionRadio.setDatabaseId(item.getDatabaseId());
        collectionRadio.setColor(item.getColor());
        collectionRadio.setCountry(item.getCountry());
        collectionRadio.setIsFav(item.getIsFav());
        collectionRadio.setMaxUser(item.getUser());
        collectionRadio.setStreamUrl(item.getStreamUrl());
        collectionRadio.setTags(item.getTags());
        collectionRadio.setTitle(item.getTitle());
    }

    public static void createOrUpdatePlaylist(Realm realm, CollectionPlaylist item){
        CollectionPlaylist collectionPlaylist = checkIfPlaylistExists(realm, item.getDatabaseId());
        if(collectionPlaylist == null){
            collectionPlaylist = realm.createObject(CollectionPlaylist.class);
        }

        //setter
        collectionPlaylist.setDatabaseId(item.getDatabaseId());
        collectionPlaylist.setOwner(item.getOwner());
        collectionPlaylist.setTitle(item.getTitle());
        collectionPlaylist.setTracks(item.getTracks());
    }

    public static void createOrUpdateVideo(Realm realm, CollectionVideo item){
        CollectionVideo collectionVideo = checkIfVideoExists(realm, item.getDatabaseId());
        if(collectionVideo == null){
            collectionVideo = realm.createObject(CollectionVideo.class);
        }

        //setter
        if(collectionVideo.getIsOffline()){
            collectionVideo.setDatabaseId(item.getLocalId());
        }else{
            collectionVideo.setDatabaseId(item.getDatabaseId());
        }

        collectionVideo.setLocalId(item.getLocalId());
        collectionVideo.setModifiedOn(item.getModifiedOn());
        collectionVideo.setIsOffline(item.getIsOffline());
        collectionVideo.setTitle(item.getTitle());
        collectionVideo.setArtworkUrl(item.getArtworkUrl());
        collectionVideo.setAuthor(item.getAuthor());
        collectionVideo.setIsFav(item.getIsFav());
        collectionVideo.setVideoOfflineUrl(item.getVideoOfflineUrl());
        collectionVideo.setVideoOnlineUrl(item.getVideoOnlineUrl());
    }

    public static void deleteTrack(Realm realm, CollectionTrack item){
        CollectionTrack collectionTrack =  checkIfTrackExists(realm, item.getDatabaseId());
        if(collectionTrack != null){
            collectionTrack.deleteFromRealm();
        }
    }

    public static void deleteArtist(Realm realm, CollectionArtist item){
        CollectionArtist collectionArtist =  checkIfArtistExists(realm, item.getDatabaseId());
        if(collectionArtist != null){
            collectionArtist.deleteFromRealm();
        }
    }

    public static void deleteRadio(Realm realm, CollectionRadio item){
        CollectionRadio collectionRadio = checkIfRadioExists(realm, item.getDatabaseId());
        if(collectionRadio != null){
            collectionRadio.deleteFromRealm();
        }
    }

    public static void deletePlaylist(Realm realm, CollectionPlaylist item){
        CollectionPlaylist collectionPlaylist = checkIfPlaylistExists(realm, item.getDatabaseId());
        if(collectionPlaylist != null){
            collectionPlaylist.deleteFromRealm();
        }
    }

    public static void deleteVideo(Realm realm, CollectionVideo item){
        CollectionVideo collectionVideo = checkIfVideoExists(realm, item.getDatabaseId());
        if(collectionVideo != null){
            collectionVideo.deleteFromRealm();
        }
    }

    private static CollectionTrack checkIfTrackExists(Realm realm, String mId){
        if(mId != null){
            return realm.where(CollectionTrack.class).equalTo("mDatabaseId", mId).findFirst();
        }
        return null;
    }

    private static CollectionArtist checkIfArtistExists(Realm realm, String mId){
        if(mId != null){
            return realm.where(CollectionArtist.class).equalTo("mDatabaseId", mId).findFirst();
        }
        return null;
    }

    private static CollectionRadio checkIfRadioExists(Realm realm, String mId){
        if(mId != null){
            return realm.where(CollectionRadio.class).equalTo("mDatabaseId", mId).findFirst();
        }
        return null;
    }

    private static CollectionVideo checkIfVideoExists(Realm realm, String mId){
        if(mId != null){
            return realm.where(CollectionVideo.class).equalTo("mDatabaseId", mId).findFirst();
        }
        return null;
    }

    private static CollectionPlaylist checkIfPlaylistExists(Realm realm, String mId){
        if(mId != null){
            return realm.where(CollectionPlaylist.class).equalTo("mDatabaseId", mId).findFirst();
        }
        return null;
    }

}
