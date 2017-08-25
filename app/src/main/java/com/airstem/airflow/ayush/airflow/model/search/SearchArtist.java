package com.airstem.airflow.ayush.airflow.model.search;

import java.io.Serializable;
import java.security.Provider;
import java.util.ArrayList;

/**
 * Created by mcd-50 on 8/7/17.
 */

public class SearchArtist implements Serializable {
    private String mTitle;
    private ArrayList<SearchImage> mArtworkUrl;
    private String mProvider;
    private String mId;

    public SearchArtist(String mTitle, ArrayList<SearchImage> mArtworkUrl, String mProvider, String mId) {
        this.mTitle = mTitle;
        this.mArtworkUrl = mArtworkUrl;
        this.mProvider = mProvider;
        this.mId = mId;
    }

    public String getTitle() {
        return mTitle;
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
