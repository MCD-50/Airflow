package com.airstem.airflow.ayush.airflow.model.collection;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by mcd-50 on 9/7/17.
 */

public class CollectionVideo extends RealmObject implements Serializable {


    private String mDatabaseId;

    @PrimaryKey
    private String mLocalId;

    private String mTitle;
    private String mAuthor;
    private String mVideoOnlineUrl;
    private String mVideoOfflineUrl;
    private String mArtworkUrl;

    private boolean mIsOffline;
    private boolean mIsFav;

    private String mModifiedOn;


    public void init(){
        mDatabaseId = UUID.randomUUID().toString();
        mLocalId = "";
        mTitle = "";
        mAuthor = "";
        mVideoOnlineUrl = "";
        mVideoOfflineUrl = "";
        mArtworkUrl = "";
        mIsOffline = true;
        mIsFav = false;
        mModifiedOn = new Date().toString();
    }

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


    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public void setAuthor(String mAuthor) {
        this.mAuthor = mAuthor;
    }

    public void setVideoOnlineUrl(String mVideoOnlineUrl) {
        this.mVideoOnlineUrl = mVideoOnlineUrl;
    }

    public void setVideoOfflineUrl(String mVideoOfflineUrl) {
        this.mVideoOfflineUrl = mVideoOfflineUrl;
    }

    public void setArtworkUrl(String mArtworkUrl) {
        this.mArtworkUrl = mArtworkUrl;
    }
}
