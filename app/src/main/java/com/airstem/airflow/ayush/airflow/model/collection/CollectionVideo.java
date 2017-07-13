package com.airstem.airflow.ayush.airflow.model.collection;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by mcd-50 on 9/7/17.
 */

public class CollectionVideo implements Serializable {

    private String mLocalId;
    private String mTitle;
    private String mAuthor;
    private String mVideoOnlineUrl;
    private String mVideoOfflineUrl;
    private String mArtworkUrl;
    private int mIsOffline;
    private int mIsFav;

    private String mModifiedOn;

    public CollectionVideo(String mTitle, String mAuthor, String mVideoOnlineUrl, String mVideoOfflineUrl, String mArtworkUrl, int mIsOffline) {
        this.mTitle = mTitle;
        this.mAuthor = mAuthor;
        this.mVideoOnlineUrl = mVideoOnlineUrl;
        this.mVideoOfflineUrl = mVideoOfflineUrl;
        this.mArtworkUrl = mArtworkUrl;
        this.mIsOffline = mIsOffline;

        this.mIsFav = 0;
        this.mModifiedOn = "";
        this.mLocalId = "";
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

    public int getIsOffline() {
        return mIsOffline;
    }

    public void setIsOffline(int mIsOffline) {
        this.mIsOffline = mIsOffline;
    }

    public int getIsFav() {
        return mIsFav;
    }

    public void setIsFav(int mIsFav) {
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


}
