package com.airstem.airflow.ayush.airflow.model.search;

import java.io.Serializable;
import java.security.Provider;
import java.util.ArrayList;

/**
 * Created by mcd-50 on 8/7/17.
 */

public class SearchAlbum implements Serializable {
    private String mTitle;
    private String mArtistName;
    private ArrayList<SearchImage> mArtworkUrl;
    private Provider mProvider;
    private String mId;


    public SearchAlbum(String mTitle, String mArtistName, ArrayList<SearchImage> mArtworkUrl, Provider mProvider, String mId) {
        this.mTitle = mTitle;
        this.mArtistName = mArtistName;
        this.mArtworkUrl = mArtworkUrl;
        this.mProvider = mProvider;
        this.mId = mId;
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

    public Provider getProvider() {
        return mProvider;
    }

    public String getId() {
        return mId;
    }
}
