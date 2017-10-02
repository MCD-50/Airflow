package com.airstem.airflow.ayush.airflow.model.collection;

import android.graphics.Bitmap;
import android.text.TextUtils;

import com.airstem.airflow.ayush.airflow.enums.search.Type;
import com.airstem.airflow.ayush.airflow.model.realms.RealmString;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by mcd-50 on 9/7/17.
 */

public class CollectionTrack extends RealmObject implements Serializable {

    private String mId = UUID.randomUUID().toString();

    @PrimaryKey
    private String mLocalId;

    private String mTitle;
    private String mAlbumName;
    private String mArtistName;
    private String mTrackOnlineUrl;
    private String mTrackOfflineUrl;
    private String mArtworkUrl;
    private boolean mIsFav;
    private boolean mIsOffline;
    private boolean mIsMatched;

    private int mPlayCount;

    private String mLastPlayed;
    private String mModifiedOn;

    private String mArtistId;
    private String mBookmark;
    private boolean mMatchError;
    private CollectionDownload mDownload;

    private String mInternetId;
    private String mProvider;

    public void init(){
        mLocalId = "";
        mTitle = "";
        mAlbumName = "";
        mArtistName = "";
        mTrackOfflineUrl = "";
        mTrackOnlineUrl = "";
        mArtworkUrl = "";
        mIsOffline = true;
        mIsFav = false;
        mIsMatched = true;
        mMatchError = false;

        mPlayCount = 0;

        mLastPlayed = "";
        mModifiedOn = new Date().toString();
        mDownload = new CollectionDownload();

        mInternetId = "";
        mProvider = String.valueOf(Type.COLLECTION);
        mBookmark = "";
        mArtistId = "";
    }


    public String getTitle() {
        return mTitle;
    }

    public String getAlbumName() {
        return mAlbumName;
    }

    public String getArtistName() {
        return mArtistName;
    }

    public String getTrackOnlineUrl() {
        return mTrackOnlineUrl;
    }

    public String getTrackOfflineUrl() {
        return mTrackOfflineUrl;
    }

    public String getArtworkUrl() {
        return mArtworkUrl;
    }

    public boolean getIsOffline() {
        return mIsOffline;
    }

    public boolean getIsFav() {
        return mIsFav;
    }

    public void setIsFav(boolean mIsFav) {
        this.mIsFav = mIsFav;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getLastPlayed() {
        return mLastPlayed;
    }

    public void setLastPlayed(String mLastPlayed) {
        this.mLastPlayed = mLastPlayed;
    }

    public int getPlayCount() {
        return mPlayCount;
    }

    public void setPlayCount(int mPlayCount) {
        this.mPlayCount = mPlayCount;
    }


    public void setIsOffline(boolean mIsOffline) {
        this.mIsOffline = mIsOffline;
    }

    public String getLocalId() {
        return mLocalId;
    }


    public String getModifiedOn() {
        return mModifiedOn;
    }

    public void setModifiedOn(String mModifiedOn) {
        this.mModifiedOn = mModifiedOn;
    }

    public void setLocalId(String mLocalId) {
        this.mLocalId = mLocalId;
    }



    public String getArtistId() {
        return mArtistId;
    }

    public void setArtistId(String mArtistId) {
        this.mArtistId = mArtistId;
    }


    public String getId() {
        return mId;
    }

    public void setAlbumName(String mAlbumName) {
        this.mAlbumName = mAlbumName;
    }

    public void setArtistName(String mArtistName) {
        this.mArtistName = mArtistName;
    }

    public void setTrackOnlineUrl(String mTrackOnlineUrl) {
        this.mTrackOnlineUrl = mTrackOnlineUrl;
    }

    public void setTrackOfflineUrl(String mTrackOfflineUrl) {
        this.mTrackOfflineUrl = mTrackOfflineUrl;
    }


    public void setArtworkUrl(String mArtworkUrl) {
        this.mArtworkUrl = mArtworkUrl;
    }

    public CollectionDownload getDownload() {
        return mDownload;
    }

    public boolean getIsMatched() {
        return mIsMatched;
    }

    public void setIsMatched(boolean mIsMatched) {
        this.mIsMatched = mIsMatched;
    }

    public String getBookmark() {
        return mBookmark;
    }

    public void setBookmark(String mBookmark) {
        this.mBookmark = mBookmark;
    }

    public boolean getIsMatchError() {
        return mMatchError;
    }

    public void setIsMatchError(boolean mMatchError) {
        this.mMatchError = mMatchError;
    }

    public String getInternetId() {
        return mInternetId;
    }

    public void setInternetId(String mInternetId) {
        this.mInternetId = mInternetId;
    }


    public String getProvider() {
        return mProvider;
    }

    public void setProvider(String mProvider) {
        this.mProvider = mProvider;
    }
}
