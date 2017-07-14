package com.airstem.airflow.ayush.airflow.model.collection;

/**
 * Created by mcd-50 on 11/7/17.
 */

public class CollectionRadio {


    private String mDatabaseId;
    private String mTitle;
    private int mMaxUser;
    private String[] mStreamUrl;
    private String[] mTags;
    private String mCountry;
    private String mColor;
    private int mIsFav;

    public CollectionRadio(String mTitle, int mMaxUser, String[] mStreamUrl, String[] mTags, String mCountry, String mColor) {
        this.mTitle = mTitle;
        this.mMaxUser = mMaxUser;
        this.mStreamUrl = mStreamUrl;
        this.mTags = mTags;
        this.mCountry = mCountry;
        this.mColor = mColor;

        this.mDatabaseId = "";
        this.mIsFav = 0;
    }



    public String getTitle() {
        return mTitle;
    }

    public int getUser() {
        return mMaxUser;
    }

    public String[] getStreamUrl() {
        return mStreamUrl;
    }

    public String[] getTags() {
        return mTags;
    }

    public String getCountry() {
        return mCountry;
    }

    public String getColor() {
        return mColor;
    }

    public int getIsFav() {
        return mIsFav;
    }

    public void setIsFav(int mIsFav) {
        this.mIsFav = mIsFav;
    }

    public String getDatabaseId() {
        return mDatabaseId;
    }

    public void setDatabaseId(String mDatabaseId) {
        this.mDatabaseId = mDatabaseId;
    }
}

