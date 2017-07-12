package com.airstem.airflow.ayush.airflow.model.collection;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by mcd-50 on 9/7/17.
 */

public class CollectionVideo implements Serializable {
    private String mTitle;
    private String mAuthor;
    private String mVideoOnlineUrl;
    private String mVideoOfflineUrl;
    private String mArtworkUrl;
    private boolean mIsOffline;
    private int mIsFav;

    public CollectionVideo(String mTitle, String mAuthor, String mVideoOnlineUrl, String mVideoOfflineUrl, String mArtworkUrl, boolean mIsOffline) {
        this.mTitle = mTitle;
        this.mAuthor = mAuthor;
        this.mVideoOnlineUrl = mVideoOnlineUrl;
        this.mVideoOfflineUrl = mVideoOfflineUrl;
        this.mArtworkUrl = mArtworkUrl;
        this.mIsOffline = mIsOffline;
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

    public int getIsFav() {
        return mIsFav;
    }

    public void setIsFav(int mIsFav) {
        this.mIsFav = mIsFav;
    }
}
