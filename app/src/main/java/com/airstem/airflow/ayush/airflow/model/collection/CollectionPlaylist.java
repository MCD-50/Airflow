package com.airstem.airflow.ayush.airflow.model.collection;

import android.graphics.Bitmap;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.RealmResults;
/**
 * Created by mcd-50 on 9/7/17.
 */

public class CollectionPlaylist extends RealmObject implements Serializable {

    private String mTitle;
    private String mDatabaseId;
    private RealmResults<CollectionTrack> mTracks;
    private String mOwner;



    public String getTitle() {
        return mTitle;
    }

    public RealmResults<CollectionTrack> getTracks() {
        return mTracks;
    }

    public int getTrackLength(){
        return mTitle.length();
    }

    public String getArtworkUrl() {
        return mTracks.get(0).getArtworkUrl();
    }


    public String getOwner() {
        return mOwner;
    }

    public void setTracks(RealmResults<CollectionTrack> mTracks) {
        this.mTracks = mTracks;
    }

    public String getDatabaseId() {
        return mDatabaseId;
    }

    public void setDatabaseId(String mDatabaseId) {
        this.mDatabaseId = mDatabaseId;
    }
}
