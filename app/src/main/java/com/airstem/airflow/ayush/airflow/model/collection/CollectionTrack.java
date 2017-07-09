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
    private String mArtworkOnlineUrl;
    private String mArtworkOfflineUrl;
    private Bitmap mArtworkBitmap;


    public CollectionTrack(String mTitle, String mAlbumName, String mArtistName, String mTrackOnlineUrl, String mTrackOfflineUrl, String mArtworkOnlineUrl, String mArtworkOfflineUrl, Bitmap mArtworkBitmap) {
        this.mTitle = mTitle;
        this.mAlbumName = mAlbumName;
        this.mArtistName = mArtistName;
        this.mTrackOnlineUrl = mTrackOnlineUrl;
        this.mTrackOfflineUrl = mTrackOfflineUrl;
        this.mArtworkOnlineUrl = mArtworkOnlineUrl;
        this.mArtworkOfflineUrl = mArtworkOfflineUrl;
        this.mArtworkBitmap = mArtworkBitmap;
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

    public String getArtworkOnlineUrl() {
        return mArtworkOnlineUrl;
    }

    public String getArtworkOfflineUrl() {
        return mArtworkOfflineUrl;
    }

    public Bitmap getArtworkBitmap() {
        return mArtworkBitmap;
    }
}
