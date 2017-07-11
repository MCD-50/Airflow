package com.airstem.airflow.ayush.airflow.model.search;

import java.io.Serializable;
import java.security.Provider;
import java.util.ArrayList;

/**
 * Created by mcd-50 on 9/7/17.
 */

public class SearchAlbumInfo {
    private String mTitle;
    private String mArtistName;
    private ArrayList<SearchImage> mArtworkUrl;
    private ArrayList<SearchAlbumInfo> mTracks;
    private Provider mProvider;

    public SearchAlbumInfo(String mTitle, String mArtistName, Provider mProvider, ArrayList<SearchImage> mArtworkUrl, ArrayList<SearchAlbumInfo> mTracks) {
        this.mTitle = mTitle;
        this.mArtistName = mArtistName;
        this.mProvider = mProvider;
        this.mArtworkUrl = mArtworkUrl;
        this.mTracks = mTracks;
    }


    public String getTitle() {
        return mTitle;
    }

    public String getArtistName() {
        return mArtistName;
    }

    public ArrayList<SearchImage> getArtworkUrl() {
        return mArtworkUrl;
    }

    public ArrayList<SearchAlbumInfo> getTracks() {
        return mTracks;
    }

    public Provider getProvider() {
        return mProvider;
    }
}
