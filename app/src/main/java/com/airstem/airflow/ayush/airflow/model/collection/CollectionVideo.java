package com.airstem.airflow.ayush.airflow.model.collection;

import android.graphics.Bitmap;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by mcd-50 on 9/7/17.
 */

public class CollectionVideo extends RealmObject implements Serializable {


    private String mDatabaseId;
    private String mLocalId;
    private String mTitle;
    private String mAuthor;
    private String mVideoOnlineUrl;
    private String mVideoOfflineUrl;
    private String mArtworkUrl;

    private boolean mIsOffline;
    private boolean mIsFav;

    private String mModifiedOn;


    public String getTitle() {
        return mTitle;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public String getVideoOnlineUrl() {
        return mVideoOnlineUrl;
    }

    public String getVideoOfflineUrl() {
        return mVideoOfflineUrl;
    }

    public String getArtworkUrl() {
        return mArtworkUrl;
    }

    public boolean getIsOffline() {
        return mIsOffline;
    }

    public void setIsOffline(boolean mIsOffline) {
        this.mIsOffline = mIsOffline;
    }

    public boolean getIsFav() {
        return mIsFav;
    }

    public void setIsFav(boolean mIsFav) {
        this.mIsFav = mIsFav;
    }

    public String getLocalId() {
        return mLocalId;
    }

    public void setLocalId(String mLocalId) {
        this.mLocalId = mLocalId;
    }

    public String getModifiedOn() {
        return mModifiedOn;
    }

    public void setModifiedOn(String mModifiedOn) {
        this.mModifiedOn = mModifiedOn;
    }

    public String getDatabaseId() {
        return mDatabaseId;
    }

    public void setDatabaseId(String mDatabaseId) {
        this.mDatabaseId = mDatabaseId;
    }


}
