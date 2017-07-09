package com.airstem.airflow.ayush.airflow.model.search;

/**
 * Created by mcd-50 on 9/7/17.
 */

public class SearchArtistInfoAlbum {
    private String mTitle;
    private String mArtistName;
    private SearchImage[] mArtworkUrl;

    public SearchArtistInfoAlbum(String mTitle, String mArtistName, SearchImage[] mArtworkUrl) {
        this.mTitle = mTitle;
        this.mArtistName = mArtistName;
        this.mArtworkUrl = mArtworkUrl;
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
}
