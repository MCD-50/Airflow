package com.airstem.airflow.ayush.airflow.model.search;

import com.airstem.airflow.ayush.airflow.model.home.DiscoverItem;

import java.io.Serializable;
import java.util.ArrayList;

import io.realm.annotations.PrimaryKey;

/**
 * Created by mcd-50 on 8/7/17.
 */

public class SearchTrack extends DiscoverItem implements Serializable {
    private String mTitle;
    private String mArtistName;
    private String mAlbumName;
    private ArrayList<SearchImage> mArtworkUrl;
    private String mProvider;
    private String mId;
    private String mUrl;

    public SearchTrack(String mTitle, String mArtistName, String mAlbumName, ArrayList<SearchImage> mArtworkUrl, String mProvider, String mId) {
        this.mTitle = mTitle;
        this.mArtistName = mArtistName;
        this.mAlbumName = mAlbumName;
        this.mArtworkUrl = mArtworkUrl;
        this.mProvider = mProvider;
        this.mId = mId;
        this.mUrl = "";
    }
    
    public SearchTrack(){
        
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

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public void setArtistName(String mArtistName) {
        this.mArtistName = mArtistName;
    }

    public void setAlbumName(String mAlbumName) {
        this.mAlbumName = mAlbumName;
    }

    public void setArtworkUrl(ArrayList<SearchImage> mArtworkUrl) {
        this.mArtworkUrl = mArtworkUrl;
    }

    public void setProvider(String mProvider) {
        this.mProvider = mProvider;
    }

    public void setId(String mId) {
        this.mId = mId;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String mUrl) {
        this.mUrl = mUrl;
    }
}
