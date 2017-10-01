package com.airstem.airflow.ayush.airflow.model.collection;

import android.graphics.Bitmap;

import com.airstem.airflow.ayush.airflow.model.realms.RealmString;

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

    private String mId = UUID.randomUUID().toString();

    @PrimaryKey
    private String mTitle;
    private String mOwner;
    private String mModifiedOn;

    RealmList<RealmString> mPlaylists;

    public void init(){
        mTitle = "";
        mOwner = "";
        mModifiedOn = new Date().toString();

        mPlaylists = new RealmList<RealmString>();
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

    public String getId() {
        return mId;
    }

    public RealmList<RealmString> getPlaylists() {
        return mPlaylists;
    }

    public void setPlaylists(RealmList<RealmString> mPlaylists) {
        this.mPlaylists = mPlaylists;
    }
}
