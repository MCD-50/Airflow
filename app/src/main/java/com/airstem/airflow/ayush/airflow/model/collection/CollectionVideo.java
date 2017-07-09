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
    private String mArtworkOnlineUrl;
    private String mArtworkOfflineUrl;
    private Bitmap mArtworkBitmap;

    public CollectionVideo(String mTitle, String mAuthor, String mVideoOnlineUrl, String mVideoOfflineUrl, String mArtworkOnlineUrl, String mArtworkOfflineUrl, Bitmap mArtworkBitmap) {
        this.mTitle = mTitle;
        this.mAuthor = mAuthor;
        this.mVideoOnlineUrl = mVideoOnlineUrl;
        this.mVideoOfflineUrl = mVideoOfflineUrl;
        this.mArtworkOnlineUrl = mArtworkOnlineUrl;
        this.mArtworkOfflineUrl = mArtworkOfflineUrl;
        this.mArtworkBitmap = mArtworkBitmap;
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
