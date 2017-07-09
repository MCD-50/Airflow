package com.airstem.airflow.ayush.airflow.model.collection;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by mcd-50 on 9/7/17.
 */

public class CollectionArtist implements Serializable {
    private String mTitle;
    private CollectionTrack[] mTracks;
    private String mArtworkOnlineUrl;
    private String mArtworkOfflineUrl;
    private Bitmap mArtworkBitmap;

    public CollectionArtist(String mTitle, String mArtworkOnlineUrl, String mArtworkOfflineUrl, Bitmap mArtworkBitmap) {
        this.mTitle = mTitle;
        this.mArtworkOnlineUrl = mArtworkOnlineUrl;
        this.mArtworkOfflineUrl = mArtworkOfflineUrl;
        this.mArtworkBitmap = mArtworkBitmap;
    }

    public String getTitle() {
        return mTitle;
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

    public CollectionTrack[] getTracks() {
        return mTracks;
    }

    public void setTracks(CollectionTrack[] mTracks) {
        this.mTracks = mTracks;
    }
}
