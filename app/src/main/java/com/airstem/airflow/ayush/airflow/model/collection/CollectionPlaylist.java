package com.airstem.airflow.ayush.airflow.model.collection;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by mcd-50 on 9/7/17.
 */

public class CollectionPlaylist implements Serializable {

    private String mTitle;
    private String mDatabaseId;
    private ArrayList<CollectionTrack> mTracks;
    private String mOwner;

    public CollectionPlaylist(String mTitle, String mOwner) {
        this.mTitle = mTitle;
        this.mOwner = mOwner;

        this.mDatabaseId = "";
        this.mTracks = new ArrayList<>();
    }


    public String getTitle() {
        return mTitle;
    }

    public ArrayList<CollectionTrack> getTracks() {
        return mTracks;
    }

    public int getTrackLength(){
        return mTitle.length();
    }

    public String getArtworkUrl() {
        return mTracks.get(0).getArtworkUrl();
    }


    public String getOwner() {
        return mOwner;
    }

    public void setTracks(ArrayList<CollectionTrack> mTracks) {
        this.mTracks = mTracks;
    }

    public String getDatabaseId() {
        return mDatabaseId;
    }

    public void setDatabaseId(String mDatabaseId) {
        this.mDatabaseId = mDatabaseId;
    }
}
