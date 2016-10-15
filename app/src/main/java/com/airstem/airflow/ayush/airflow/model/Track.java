package com.airstem.airflow.ayush.airflow.model;

import android.graphics.Bitmap;
import android.net.Uri;
import android.net.wifi.ScanResult;

/**
 * Created by ayush on 06-10-16.
 */
public class Track {


    private String Title;
    private String MoodName;
    private String Artwork;
    private String Url;
    private String Id;
    private boolean Streamable;
    private Bitmap bitmap;

    public Track(String id, String title, String url, boolean streamable){
        Id = id;
        Title = title;
        Url = url;
        Streamable = streamable;
    }

    public void setMoodName(String moodName) {
        MoodName = moodName;
    }

    public String getMood() {
        return MoodName;
    }

    public String getArtwork() {
        return Artwork;
    }

    public void setArtwork(String artwork) {
        Artwork = artwork;
    }

    public String getTitle() {
        return Title;
    }

    public boolean isStreamable() {
        return Streamable;
    }

    public String getUrl() {
        return Url;
    }

    public String getId() {
        return Id;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

}
