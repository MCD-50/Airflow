package com.airstem.airflow.ayush.airflow.model.collection;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by mcd-50 on 9/7/17.
 */

public class CollectionArtist implements Serializable {
    private String mTitle;
    private ArrayList<CollectionTrack> mTracks;
    private String mArtworkUrl;

    public CollectionArtist(String mTitle, String mArtworkUrl) {
        this.mTitle = mTitle;
        this.mArtworkUrl = mArtworkUrl;
    }

    public String getTitle() {
        return mTitle;
    }


    public String getArtworkUrl() {
        return mArtworkUrl;
    }

    public ArrayList<CollectionTrack> getTracks() {
        return mTracks;
    }

    public int getTracksLength(){
        return mTitle.length();
    }

    public void setTracks(ArrayList<CollectionTrack> mTracks) {
        this.mTracks = mTracks;
    }
}
