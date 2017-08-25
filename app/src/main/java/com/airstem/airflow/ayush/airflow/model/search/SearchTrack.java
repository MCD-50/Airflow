package com.airstem.airflow.ayush.airflow.model.search;

import java.io.Serializable;
import java.security.Provider;
import java.util.ArrayList;

/**
 * Created by mcd-50 on 8/7/17.
 */

public class SearchTrack implements Serializable {
    private String mTitle;
    private String mArtistName;
    private String mAlbumName;
    private ArrayList<SearchImage> mArtworkUrl;
    private String mProvider;
    private String mId;


    public SearchTrack(String mTitle, String mArtistName, String mAlbumName, ArrayList<SearchImage> mArtworkUrl, String mProvider, String mId) {
        this.mTitle = mTitle;
        this.mArtistName = mArtistName;
        this.mAlbumName = mAlbumName;
        this.mArtworkUrl = mArtworkUrl;
        this.mProvider = mProvider;
        this.mId = mId;
    }


    public String getTitle() {
        return mTitle;
    }

    public String getArtistName() {
        return mArtistName;
    }

    public String getAlbumName() {
        return mAlbumName;
    }

    public ArrayList<SearchImage> getArtworkUrl() {
        return mArtworkUrl;
    }

    public String getProvider() {
        return mProvider;
    }

    public String getId() {
        return mId;
    }
}
