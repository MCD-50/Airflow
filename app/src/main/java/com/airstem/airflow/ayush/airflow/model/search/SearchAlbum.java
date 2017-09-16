package com.airstem.airflow.ayush.airflow.model.search;

import com.airstem.airflow.ayush.airflow.model.home.DiscoverItem;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by mcd-50 on 8/7/17.
 */

public class SearchAlbum extends DiscoverItem implements Serializable {
    private String mTitle;
    private String mArtistName;
    private ArrayList<SearchImage> mArtworkUrl;
    private String mProvider;
    private String mId;


    public SearchAlbum(String mTitle, String mArtistName, ArrayList<SearchImage> mArtworkUrl, String mProvider, String mId) {
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

    public String getProvider() {
        return mProvider;
    }

    public String getId() {
        return mId;
    }
}
