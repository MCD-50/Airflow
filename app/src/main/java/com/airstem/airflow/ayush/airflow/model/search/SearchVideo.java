package com.airstem.airflow.ayush.airflow.model.search;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by mcd-50 on 8/7/17.
 */

public class SearchVideo implements Serializable {
    private String mTitle;
    private String mDescription;
    private String mAuthor;
    private ArrayList<String> mTags;
    private ArrayList<SearchImage> mArtworkUrl;
    private String mProvider;
    private String mId;
    private String mUrl;

    public SearchVideo(String mTitle, String mDescription, String mAuthor, ArrayList<String> mTags, ArrayList<SearchImage> mArtworkUrl, String mProvider, String mId) {
        this.mTitle = mTitle;
        this.mDescription = mDescription;
        this.mAuthor = mAuthor;
        this.mTags = mTags;
        this.mArtworkUrl = mArtworkUrl;
        this.mProvider = mProvider;
        this.mId = mId;
        this.mUrl = "";
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public ArrayList<String> getTags() {
        return mTags;
    }

    public ArrayList<SearchImage> getArtworkUrl() {
        return mArtworkUrl;
    }

    public String getProvider(){
        return mProvider;
    }

    public String getId() {
        return mId;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String mUrl) {
        this.mUrl = mUrl;
    }
}
