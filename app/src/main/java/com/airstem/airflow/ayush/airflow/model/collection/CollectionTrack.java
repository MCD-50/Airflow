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


    public CollectionTrack(String mTitle, String mAlbumName, String mArtistName, String mTrackOnlineUrl, String mTrackOfflineUrl, String mArtworkUrl, boolean mIsOffline) {
        this.mTitle = mTitle;
        this.mAlbumName = mAlbumName;
        this.mArtistName = mArtistName;
        this.mTrackOnlineUrl = mTrackOnlineUrl;
        this.mTrackOfflineUrl = mTrackOfflineUrl;
        this.mArtworkUrl = mArtworkUrl;
        this.mIsOffline = mIsOffline;
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

}
