package com.airstem.airflow.ayush.airflow.model.search;

/**
 * Created by mcd-50 on 2/10/17.
 */

public class ManualMatch {

    private String mTitle;
    private String mArtistName;
    private String mUrl;
    private String mProvider;

    public ManualMatch(String mTitle, String mArtistName, String mUrl, String mProvider) {
        this.mTitle = mTitle;
        this.mArtistName = mArtistName;
        this.mUrl = mUrl;
        this.mProvider = mProvider;
    }


    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getArtistName() {
        return mArtistName;
    }

    public void setArtistName(String mArtistName) {
        this.mArtistName = mArtistName;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public String getProvider() {
        return mProvider;
    }

    public void setProvider(String mProvider) {
        this.mProvider = mProvider;
    }
}
