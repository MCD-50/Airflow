package com.airstem.airflow.ayush.airflow.model.search;

/**
 * Created by mcd-50 on 23/9/17.
 */

public class SearchPaging {
    private String mTrackNextPage;
    private String mArtistNextPage;
    private String mAlbumNextPage;
    private String mRadioNextPage;
    private String mVideoNextPage;

    public String getTrackNextPage() {
        return mTrackNextPage;
    }

    public void setTrackNextPage(String mTrackNextPage) {
        this.mTrackNextPage = mTrackNextPage;
    }

    public String getArtistNextPage() {
        return mArtistNextPage;
    }

    public void setArtistNextPage(String mArtistNextPage) {
        this.mArtistNextPage = mArtistNextPage;
    }

    public String getAlbumNextPage() {
        return mAlbumNextPage;
    }

    public void setAlbumNextPage(String mAlbumNextPage) {
        this.mAlbumNextPage = mAlbumNextPage;
    }

    public String getRadioNextPage() {
        return mRadioNextPage;
    }

    public void setRadioNextPage(String mRadioNextPage) {
        this.mRadioNextPage = mRadioNextPage;
    }

    public String getVideoNextPage() {
        return mVideoNextPage;
    }

    public void setVideoNextPage(String mVideoNextPage) {
        this.mVideoNextPage = mVideoNextPage;
    }
}
