package com.airstem.airflow.ayush.airflow.model.search;

import java.io.Serializable;

/**
 * Created by mcd-50 on 8/7/17.
 */

public class SearchVideo implements Serializable {
    private String mTitle;
    private String mAuthor;
    private String[] mTags;
    private SearchImage[] mArtworkUrl;
    private String mId;

    public SearchVideo(String mTitle, String mAuthor, String[] mTags, SearchImage[] mArtworkUrl, String mId) {
        this.mTitle = mTitle;
        this.mAuthor = mAuthor;
        this.mTags = mTags;
        this.mArtworkUrl = mArtworkUrl;
        this.mId = mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public String[] getTags() {
        return mTags;
    }

    public SearchImage[] getArtworkUrl() {
        return mArtworkUrl;
    }

    public String getId() {
        return mId;
    }
}
