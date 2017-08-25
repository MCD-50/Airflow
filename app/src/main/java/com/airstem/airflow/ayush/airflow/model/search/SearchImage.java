package com.airstem.airflow.ayush.airflow.model.search;

import java.io.Serializable;
import java.security.Provider;

/**
 * Created by mcd-50 on 9/7/17.
 */

public class SearchImage implements Serializable {
    private String mSize;
    private String mUri;
    private String mProvider;


    public SearchImage(String mSize, String mUri, String mProvider) {
        this.mSize = mSize;
        this.mUri = mUri;
        this.mProvider = mProvider;
    }

    public String getSize() {
        return mSize;
    }

    public String getUri() {
        return mUri;
    }

    public String getProvider() {
        return mProvider;
    }
}
