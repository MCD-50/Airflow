package com.airstem.airflow.ayush.airflow.model.collection;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by mcd-50 on 9/7/17.
 */

public class CollectionTrack implements Serializable {
    private String mTitle;
    private String mAlbumName;
    private String mArtistName;
    private String mTrackOnlineUrl;
    private String mTrackOfflineUrl;
    private boolean mIsOffline;
    private String mArtworkUrl;
    private int mIsFav;
    private String mLastPlayed;
    private int mPlayCount;

    public CollectionTrack(String mTitle, String mAlbumName, String mArtistName, String mTrackOnlineUrl, String mTrackOfflineUrl, String mArtworkUrl, boolean mIsOffline) {
        this.mTitle = mTitle;
        this.mAlbumName = mAlbumName;
        this.mArtistName = mArtistName;
        this.mTrackOnlineUrl = mTrackOnlineUrl;
        this.mTrackOfflineUrl = mTrackOfflineUrl;
        this.mArtworkUrl = mArtworkUrl;
        this.mIsOffline = mIsOffline;

        mLastPlayed = null;
        mPlayCount = 0;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getAlbumName() {
        return mAlbumName;
    }

    public String getArtistName() {
        return mArtistName;
    }

    public String getTrackOnlineUrl() {
        return mTrackOnlineUrl;
    }

    public String getTrackOfflineUrl() {
        return mTrackOfflineUrl;
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

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getLastPlayed() {
        return mLastPlayed;
    }

    public void setLastPlayed(String mLastPlayed) {
        this.mLastPlayed = mLastPlayed;
    }

    public int getPlayCount() {
        return mPlayCount;
    }

    public void setPlayCount(int mPlayCount) {
        this.mPlayCount = mPlayCount;
    }
}
