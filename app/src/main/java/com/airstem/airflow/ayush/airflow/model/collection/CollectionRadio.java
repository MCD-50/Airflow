package com.airstem.airflow.ayush.airflow.model.collection;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by mcd-50 on 11/7/17.
 */

public class CollectionRadio extends RealmObject implements Serializable {


    private String mDatabaseId;
    private String mTitle;
    private int mMaxUser;
    private String[] mStreamUrl;
    private String[] mTags;
    private String mCountry;
    private String mColor;
    private boolean mIsFav;


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

    public boolean getIsFav() {
        return mIsFav;
    }

    public void setIsFav(boolean mIsFav) {
        this.mIsFav = mIsFav;
    }

    public String getDatabaseId() {
        return mDatabaseId;
    }

    public void setDatabaseId(String mDatabaseId) {
        this.mDatabaseId = mDatabaseId;
    }
}

