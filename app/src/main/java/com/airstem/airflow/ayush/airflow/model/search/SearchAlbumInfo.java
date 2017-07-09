package com.airstem.airflow.ayush.airflow.model.search;

import java.io.Serializable;
import java.security.Provider;

/**
 * Created by mcd-50 on 9/7/17.
 */

public class SearchAlbumInfo {
    private String mTitle;
    private String mArtistName;
    private SearchImage[] mArtworkUrl;
    private SearchAlbumInfo[] mTracks;
    private Provider mProvider;

    public SearchAlbumInfo(String mTitle, String mArtistName, Provider mProvider, SearchImage[] mArtworkUrl, SearchAlbumInfo[] mTracks) {
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

    public SearchImage[] getArtworkUrl() {
        return mArtworkUrl;
    }

    public SearchAlbumInfo[] getTracks() {
        return mTracks;
    }

    public Provider getProvider() {
        return mProvider;
    }
}
