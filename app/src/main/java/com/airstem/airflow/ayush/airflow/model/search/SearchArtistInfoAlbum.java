package com.airstem.airflow.ayush.airflow.model.search;

import java.util.ArrayList;

/**
 * Created by mcd-50 on 9/7/17.
 */

public class SearchArtistInfoAlbum {
    private String mTitle;
    private String mArtistName;
    private ArrayList<SearchImage> mArtworkUrl;

    public SearchArtistInfoAlbum(String mTitle, String mArtistName, ArrayList<SearchImage> mArtworkUrl) {
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

    public ArrayList<SearchImage> getArtworkUrl() {
        return mArtworkUrl;
    }
}
