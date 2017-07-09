package com.airstem.airflow.ayush.airflow.model.search;

import java.io.Serializable;

/**
 * Created by mcd-50 on 9/7/17.
 */

public class SearchArtistInfoTrack{
    private String mTitle;
    private String mArtistName;
    private String mAlbumName;
    private SearchImage[] mArtworkUrl;

    public SearchArtistInfoTrack(String mTitle, String mArtistName, String mAlbumName, SearchImage[] mArtworkUrl) {
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

    public SearchImage[] getArtworkUrl() {
        return mArtworkUrl;
    }
}
