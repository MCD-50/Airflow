package com.airstem.airflow.ayush.airflow.model.collection;

import android.graphics.Bitmap;
import android.text.TextUtils;

import java.io.Serializable;

/**
 * Created by mcd-50 on 9/7/17.
 */

public class CollectionTrack implements Serializable {



    private String mDatabaseId;
    private String mLocalId;
    private String mTitle;
    private String mAlbumName;
    private String mArtistName;
    private String mTrackOnlineUrl;
    private String mTrackOfflineUrl;
    private int mIsOffline;
    private String mArtworkUrl;
    private int mIsFav;
    private String mLastPlayed;
    private int mPlayCount;
    private int mHasArtwork;

    private String mModifiedOn;
    private String mPlaylistId;
    private String mArtistId;


    public CollectionTrack(String mTitle, String mAlbumName, String mArtistName, String mTrackOnlineUrl, String mTrackOfflineUrl, String mArtworkUrl, int mIsOffline) {
        this.mTitle = mTitle;
        this.mAlbumName = mAlbumName;
        this.mArtistName = mArtistName;
        this.mTrackOnlineUrl = mTrackOnlineUrl;
        this.mTrackOfflineUrl = mTrackOfflineUrl;
        this.mArtworkUrl = mArtworkUrl;
        this.mIsOffline = mIsOffline;

        this.mLastPlayed = "";
        this.mPlayCount = 0;
        this.mHasArtwork = TextUtils.isEmpty(mArtworkUrl) ? 0 : 1;

        this.mDatabaseId = "";
        this.mIsFav = 0;
        this.mModifiedOn = "";
        this.mLocalId = "";
        this.mPlaylistId = "";
        this. mArtistId = "";
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

    public int getIsOffline() {
        return mIsOffline;
    }

    public int getIsFav() {
        return mIsFav;
    }

    public void setIsFav(int mIsFav) {
        this.mIsFav = mIsFav;
    }

    public void setTitle(String mTitle) {
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

    public int getHasArtwork() {
        return mHasArtwork;
    }

    public void setHasArtwork(int mHasArtwork) {
        this.mHasArtwork = mHasArtwork;
    }

    public void setIsOffline(int mIsOffline) {
        this.mIsOffline = mIsOffline;
    }

    public String getLocalId() {
        return mLocalId;
    }


    public String getModifiedOn() {
        return mModifiedOn;
    }

    public void setModifiedOn(String mModifiedOn) {
        this.mModifiedOn = mModifiedOn;
    }

    public void setLocalId(String mLocalId) {
        this.mLocalId = mLocalId;
    }

    public String getPlaylistId() {
        return mPlaylistId;
    }

    public void setPlaylistId(String mPlaylistId) {
        this.mPlaylistId = mPlaylistId;
    }

    public String getArtistId() {
        return mArtistId;
    }

    public void setArtistId(String mArtistId) {
        this.mArtistId = mArtistId;
    }


    public String getDatabaseId() {
        return mDatabaseId;
    }

    public void setDatabaseId(String mDatabaseId) {
        this.mDatabaseId = mDatabaseId;
    }
}
