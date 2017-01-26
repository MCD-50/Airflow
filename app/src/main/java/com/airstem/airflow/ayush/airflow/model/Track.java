package com.airstem.airflow.ayush.airflow.model;

import android.graphics.Bitmap;
import android.net.Uri;
import android.net.wifi.ScanResult;

/**
 * Created by ayush on 06-10-16.
 */
public class Track {


    private String mId;
    private String mTitle;
    private String mMoodName;
    private String mTrackUrl;
    private String mArtwork;
    private Bitmap mBitmap;
    private Integer mRadioArtwork;
    private PlayMode mPlayMode;


    public Track(String id, String title, String url, PlayMode playMode){
        mId = id;
        mTitle = title;
        mTrackUrl = url;
        mPlayMode = playMode;
    }

    public void setMoodName(String moodName) {
        mMoodName = moodName;
    }

    public String getMood() {
        return mMoodName;
    }

    public String getArtwork() {
        return mArtwork;
    }

    public void setArtwork(String artwork) {
        mArtwork = artwork;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setMode(PlayMode mode){
        mPlayMode = mode;
    }

    public PlayMode getMode(){
        return mPlayMode;
    }

    public String getTrackUrl() {
        return mTrackUrl;
    }

    public String getId() {
        return mId;
    }

    public Bitmap getBitmap() {
        return mBitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        mBitmap = bitmap;
    }

    public void setRadioArtwork(Integer artwork){
        mRadioArtwork = artwork;
    }

    public Integer getRadioArtwork(){
        return mRadioArtwork;
    }


}
