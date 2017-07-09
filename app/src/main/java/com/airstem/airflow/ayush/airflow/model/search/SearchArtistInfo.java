package com.airstem.airflow.ayush.airflow.model.search;

import java.io.Serializable;
import java.security.Provider;

/**
 * Created by mcd-50 on 9/7/17.
 */

public class SearchArtistInfo {
    private String mTitle;
    private SearchArtistInfoAlbum[] mAlbums;
    private SearchArtistInfoTrack[] mTracks;
    private SearchImage[] mArtworkUrl;
    private Provider mProvider;

    public SearchArtistInfo(String mTitle, Provider mProvider, SearchArtistInfoAlbum[] mAlbums, SearchArtistInfoTrack[] mTracks, SearchImage[] mArtworkUrl) {
        this.mTitle = mTitle;
        this.mProvider = mProvider;
        this.mAlbums = mAlbums;
        this.mTracks = mTracks;
        this.mArtworkUrl = mArtworkUrl;
    }

    public String getTitle() {
        return mTitle;
    }

    public Provider getProvider() {
        return mProvider;
    }

    public SearchArtistInfoAlbum[] getAlbums() {
        return mAlbums;
    }

    public SearchArtistInfoTrack[] getTracks() {
        return mTracks;
    }

    public SearchImage[] getArtworkUrl() {
        return mArtworkUrl;
    }
}
