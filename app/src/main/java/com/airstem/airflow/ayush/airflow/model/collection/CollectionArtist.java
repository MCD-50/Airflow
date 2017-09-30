package com.airstem.airflow.ayush.airflow.model.collection;

import android.graphics.Bitmap;
import android.text.TextUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import io.realm.RealmObject;
import io.realm.RealmList;
import io.realm.annotations.PrimaryKey;

/**
 * Created by mcd-50 on 9/7/17.
 */

public class CollectionArtist extends RealmObject implements Serializable {



    @PrimaryKey
    private String mLocalId;
    private String mDatabaseId;
    private String mTitle;
    private String mArtworkUrl;
    private String mModifiedOn;


    public void init(){
        mLocalId = "";
        mDatabaseId = UUID.randomUUID().toString();
        mTitle = "";
        mArtworkUrl = "";
        mModifiedOn = new Date().toString();
    }


    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getArtworkUrl() {
        return mArtworkUrl;
    }

    public void setArtworkUrl(String mArtworkUrl) {
        this.mArtworkUrl = mArtworkUrl;
    }

    public String getModifiedOn() {
        return mModifiedOn;
    }

    public void setModifiedOn(String mModifiedOn) {
        this.mModifiedOn = mModifiedOn;
    }


    public String getLocalId() {
        return mLocalId;
    }

    public void setLocalId(String mLocalId) {
        this.mLocalId = mLocalId;
    }

    public String getDatabaseId() {
        return mDatabaseId;
    }

    public void setDatabaseId(String mDatabaseId) {
        this.mDatabaseId = mDatabaseId;
    }
}
