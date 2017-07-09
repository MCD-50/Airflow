package com.airstem.airflow.ayush.airflow.model.collection;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by mcd-50 on 9/7/17.
 */

public class CollectionPlaylist implements Serializable {
    private String mTitle;
    private CollectionTrack[] mTracks;
    private Bitmap mArtworkBitmap;
    private String mOwner;


    public CollectionPlaylist(String mTitle, String mOwner) {
        this.mTitle = mTitle;
        this.mOwner = mOwner;
    }


    public String getTitle() {
        return mTitle;
    }

    public CollectionTrack[] getTracks() {
        return mTracks;
    }

    public Bitmap getArtworkBitmap() {
        return mArtworkBitmap;
    }

    public String getOwner() {
        return mOwner;
    }

    public void setTracks(CollectionTrack[] mTracks) {
        this.mTracks = mTracks;
    }

    public void setArtworkBitmap(Bitmap mArtworkBitmap) {
        this.mArtworkBitmap = mArtworkBitmap;
    }
}
