package com.airstem.airflow.ayush.airflow.model.search;

import java.util.ArrayList;

/**
 * Created by mcd-50 on 9/7/17.
 */

public class SearchAlbumInfoTrack {
    private String mTitle;
    private String mArtistName;
    private String mAlbumName;
    private ArrayList<SearchImage> mArtworkUrl;


    public SearchAlbumInfoTrack(String mTitle, String mArtistName, String mAlbumName, ArrayList<SearchImage> mArtworkUrl) {
        this.mTitle = mTitle;
        this.mArtistName = mArtistName;
        this.mAlbumName = mAlbumName;
        this.mArtworkUrl = mArtworkUrl;
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
}
