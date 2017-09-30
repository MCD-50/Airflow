package com.airstem.airflow.ayush.airflow.model.collection;

import com.airstem.airflow.ayush.airflow.model.realms.RealmString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by mcd-50 on 11/7/17.
 */

public class CollectionRadio extends RealmObject implements Serializable {


    private String mDatabaseId;

    @PrimaryKey
    private String mTitle;

    private int mMaxUser;

    private RealmList<RealmString> mStreamUrl;
    private RealmList<RealmString> mTags;
    private String mCountry;
    private String mColor;
    private boolean mIsFav;
    private String mModifiedOn;


    public void init(){
        mDatabaseId = UUID.randomUUID().toString();
        mTitle = "";
        mMaxUser = 0;

        mStreamUrl = new RealmList<RealmString>();
        mTags = new RealmList<RealmString>();
        mCountry = "";
        mColor = "";
        mIsFav = false;
        mModifiedOn = new Date().toString();
    }


    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public void setMaxUser(int mMaxUser) {
        this.mMaxUser = mMaxUser;
    }

    public void setStreamUrl(RealmList<RealmString> mStreamUrl) {
        this.mStreamUrl = mStreamUrl;
    }

    public void setTags(RealmList<RealmString> mTags) {
        this.mTags = mTags;
    }

    public void setCountry(String mCountry) {
        this.mCountry = mCountry;
    }

    public void setColor(String mColor) {
        this.mColor = mColor;
    }

    public String getModifiedOn() {
        return mModifiedOn;
    }

    public void setModifiedOn(String mModifiedOn) {
        this.mModifiedOn = mModifiedOn;
    }

    public String getTitle() {
        return mTitle;
    }

    public int getUser() {
        return mMaxUser;
    }

    public RealmList<RealmString> getStreamUrl() {
        return mStreamUrl;
    }

    public RealmList<RealmString> getTags() {
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

