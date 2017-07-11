package com.airstem.airflow.ayush.airflow.model.search;

import java.io.Serializable;
import java.security.Provider;
import java.util.ArrayList;

/**
 * Created by mcd-50 on 8/7/17.
 */

public class SearchTrack implements Serializable {
    private String mTitle;
    private String mArtistName;
    private String mAlbumName;
    private ArrayList<SearchImage> mArtworkUrl;
    private Provider mProvider;


    public SearchTrack(String mTitle, String mArtistName, String mAlbumName, ArrayList<SearchImage> mArtworkUrl, Provider mProvider) {
        this.mTitle = mTitle;
        this.mArtistName = mArtistName;
        this.mAlbumName = mAlbumName;
        this.mArtworkUrl = mArtworkUrl;
        this.mProvider = mProvider;
    }


    public String getTitle() {
        return mTitle;
    }

    public String getArtistName() {
        return mArtistName;
    }

    public String getAlbumName() {
        return mAlbumName;
    }

    public ArrayList<SearchImage> getArtworkUrl() {
        return mArtworkUrl;
    }

    public Provider getProvider() {
        return mProvider;
    }
}
