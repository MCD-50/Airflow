package com.airstem.airflow.ayush.airflow.model.collection;

import io.realm.RealmModel;
import io.realm.RealmObject;

/**
 * Created by mcd-50 on 2/10/17.
 */

public class CollectionVideoSize extends RealmObject {
    private String mUrl = "";
    private int mWidth = 0;
    private int mHeight = 0;

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public int getWidth() {
        return mWidth;
    }

    public void setWidth(int mWidth) {
        this.mWidth = mWidth;
    }

    public int getHeight() {
        return mHeight;
    }

    public void setHeight(int mHeight) {
        this.mHeight = mHeight;
    }
}
