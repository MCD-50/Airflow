package com.airstem.airflow.ayush.airflow.model.collection;

import android.graphics.Bitmap;
import android.text.TextUtils;

import java.io.Serializable;
import java.util.ArrayList;

import io.realm.RealmObject;
import io.realm.RealmList;

/**
 * Created by mcd-50 on 9/7/17.
 */

public class CollectionArtist extends RealmObject implements Serializable {



    private String mLocalId;
    private String mDatabaseId;
    private String mTitle;
    private RealmList<CollectionTrack> mTracks;
    private String mArtworkUrl;
    private boolean mHasArtwork;

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getTitle() {
        return mTitle;
    }
    

    public RealmList<CollectionTrack> getTracks() {
        return mTracks;
    }

    public int getTracksLength(){
        return mTitle.length();
    }

    public void setTracks(RealmList<CollectionTrack> mTracks) {
        this.mTracks = mTracks;
    }

    public String getArtworkUrl() {
        return mArtworkUrl;
    }

    public void setArtworkUrl(String mArtworkUrl) {
        this.mArtworkUrl = mArtworkUrl;
    }

    public boolean getHasArtwork() {
        return mHasArtwork;
    }

    public void setHasArtwork(boolean mHasArtwork) {
        this.mHasArtwork = mHasArtwork;
    }

    public String getLocalId() {
        return mLocalId;
    }

    public void setLocalId(String mLocalId) {
        this.mLocalId = mLocalId;
    }

    public String getDatabaseId() {
        return mDatabaseId;
    }

    public void setDatabaseId(String mDatabaseId) {
        this.mDatabaseId = mDatabaseId;
    }
}
