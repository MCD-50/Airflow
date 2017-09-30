package com.airstem.airflow.ayush.airflow.model.collection;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import io.realm.RealmObject;
import io.realm.RealmList;
import io.realm.annotations.PrimaryKey;

/**
 * Created by mcd-50 on 9/7/17.
 */

public class CollectionPlaylist extends RealmObject implements Serializable {


    @PrimaryKey
    private String mTitle;

    private String mDatabaseId;
    private String mOwner;
    private String mModifiedOn;


    public void init(){
        mDatabaseId = UUID.randomUUID().toString();
        mTitle = "";
        mOwner = "";
        mModifiedOn = new Date().toString();
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public void setOwner(String mOwner) {
        this.mOwner = mOwner;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getModifiedOn() {
        return mModifiedOn;
    }

    public void setModifiedOn(String mModifiedOn) {
        this.mModifiedOn = mModifiedOn;
    }


    public String getOwner() {
        return mOwner;
    }

    public String getDatabaseId() {
        return mDatabaseId;
    }

    public void setDatabaseId(String mDatabaseId) {
        this.mDatabaseId = mDatabaseId;
    }
}
