package com.airstem.airflow.ayush.airflow.model.collection;

import android.graphics.Bitmap;
import android.text.TextUtils;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by mcd-50 on 9/7/17.
 */

public class CollectionArtist implements Serializable {



    private String mLocalId;
    private String mTitle;
    private ArrayList<CollectionTrack> mTracks;
    private String mArtworkUrl;
    private int mHasArtwork;


    public CollectionArtist(String mTitle, String mArtworkUrl) {
        this.mTitle = mTitle;
        this.mArtworkUrl = mArtworkUrl;
        this.mHasArtwork = TextUtils.isEmpty(mArtworkUrl) ? 0 : 1;

        this.mLocalId = "";
        this.mTracks = new ArrayList<>();
    }

    public String getTitle() {
        return mTitle;
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

    public String getArtworkUrl() {
        return mArtworkUrl;
    }

    public void setArtworkUrl(String mArtworkUrl) {
        this.mArtworkUrl = mArtworkUrl;
    }

    public int getHasArtwork() {
        return mHasArtwork;
    }

    public void setHasArtwork(int mHasArtwork) {
        this.mHasArtwork = mHasArtwork;
    }

    public String getLocalId() {
        return mLocalId;
    }

    public void setLocalId(String mLocalId) {
        this.mLocalId = mLocalId;
    }
}
