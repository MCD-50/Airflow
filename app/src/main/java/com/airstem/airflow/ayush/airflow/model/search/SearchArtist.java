package com.airstem.airflow.ayush.airflow.model.search;

import java.io.Serializable;
import java.security.Provider;

/**
 * Created by mcd-50 on 8/7/17.
 */

public class SearchArtist implements Serializable {
    private String mTitle;
    private SearchImage[] mArtworkUrl;
    private Provider mProvider;
    private String mId;

    public SearchArtist(String mTitle, SearchImage[] mArtworkUrl, Provider mProvider, String mId) {
        this.mTitle = mTitle;
        this.mArtworkUrl = mArtworkUrl;
        this.mProvider = mProvider;
        this.mId = mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public SearchImage[] getArtworkUrl() {
        return mArtworkUrl;
    }

    public Provider getProvider() {
        return mProvider;
    }

    public String getId() {
        return mId;
    }
}
