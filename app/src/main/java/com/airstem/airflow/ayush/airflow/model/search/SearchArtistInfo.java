package com.airstem.airflow.ayush.airflow.model.search;

import java.io.Serializable;
import java.security.Provider;
import java.util.ArrayList;

/**
 * Created by mcd-50 on 9/7/17.
 */

public class SearchArtistInfo {
    private String mTitle;
    private ArrayList<SearchArtistInfoAlbum> mAlbums;
    private ArrayList<SearchArtistInfoTrack> mTracks;
    private ArrayList<SearchImage> mArtworkUrl;
    private Provider mProvider;

    public SearchArtistInfo(String mTitle, Provider mProvider, ArrayList<SearchArtistInfoAlbum> mAlbums, ArrayList<SearchArtistInfoTrack> mTracks, ArrayList<SearchImage> mArtworkUrl) {
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

    public ArrayList<SearchArtistInfoAlbum> getAlbums() {
        return mAlbums;
    }

    public ArrayList<SearchArtistInfoTrack> getTracks() {
        return mTracks;
    }

    public ArrayList<SearchImage> getArtworkUrl() {
        return mArtworkUrl;
    }
}
