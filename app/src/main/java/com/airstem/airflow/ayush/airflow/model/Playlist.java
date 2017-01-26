package com.airstem.airflow.ayush.airflow.model;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by ayush AS on 7/1/17.
 */

public class Playlist implements Serializable{

    private String mName;
    private String mTracksCount;
    private String mImage;
    private ArrayList<Track> mTracks;
    private String mId;


    public Playlist(String mId, String mName, String mTracksCount, String mImage, ArrayList<Track> mTracks) {
        this.mName = mName;
        this.mTracksCount = mTracksCount;
        this.mImage = mImage;
        this.mTracks = mTracks;
        this.mId = mId;
    }

    public ArrayList<Track> getmTracks() {
        return mTracks;
    }

    public void setmTracks(ArrayList<Track> mTracks) {
        this.mTracks = mTracks;
    }

    public String getmImage() {
        return mImage;
    }

    public void setmImage(String mImage) {
        this.mImage = mImage;
    }
    public String getmTracksCount() {
        return mTracksCount;
    }

    public void setmTracksCount(String mTracksCount) {
        this.mTracksCount = mTracksCount;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }
}
