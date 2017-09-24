package com.airstem.airflow.ayush.airflow.model.collection;

import android.graphics.Bitmap;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.RealmList;
import io.realm.annotations.PrimaryKey;

/**
 * Created by mcd-50 on 9/7/17.
 */

public class CollectionPlaylist extends RealmObject implements Serializable {


    @PrimaryKey
    private String mTitle;

    private String mDatabaseId;
    private RealmList<CollectionTrack> mTracks;
    private String mOwner;


    public void init(){
        mDatabaseId = "";
        mTitle = "";
        mTracks = new RealmList<CollectionTrack>();
        mOwner = "";
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public void setOwner(String mOwner) {
        this.mOwner = mOwner;
    }

    public String getTitle() {
        return mTitle;
    }

    public RealmList<CollectionTrack> getTracks() {
        return mTracks;
    }

    public String getTracksLength(){
        return mTracks.size() > 1 ? mTracks.size() + " Tracks" :  mTracks.size() + " Track" ;
    }

    public String getArtworkUrl() {
        if(mTracks.size() > 0){
            return mTracks.get(0).getArtworkUrl();
        }else{
            return "";
        }
    }


    public String getOwner() {
        return mOwner;
    }

    public void setTracks(RealmList<CollectionTrack> mTracks) {
        this.mTracks = mTracks;
    }

    public String getDatabaseId() {
        return mDatabaseId;
    }

    public void setDatabaseId(String mDatabaseId) {
        this.mDatabaseId = mDatabaseId;
    }
}
