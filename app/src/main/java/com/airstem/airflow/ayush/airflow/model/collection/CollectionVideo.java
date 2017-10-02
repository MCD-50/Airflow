package com.airstem.airflow.ayush.airflow.model.collection;

import android.graphics.Bitmap;

import com.airstem.airflow.ayush.airflow.enums.search.Type;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by mcd-50 on 9/7/17.
 */

public class CollectionVideo extends RealmObject implements Serializable {

    private String mId = UUID.randomUUID().toString();

    @PrimaryKey
    private String mLocalId;
    private String mTitle;
    private String mAuthor;
    private String mVideoOnlineUrl;
    private String mVideoOfflineUrl;
    private String mArtworkUrl;
    private boolean mIsOffline;
    private boolean mIsFav;
    private boolean mIsMatched;

    private String mModifiedOn;
    private String mBookmark;
    private boolean mMatchError;

    private String mInternetId;
    private String mProvider;

    private CollectionDownload mDownload;
    private RealmList<CollectionVideoSize> mVideoSize;


    public void init(){
        mLocalId = "";
        mTitle = "";
        mAuthor = "";
        mVideoOnlineUrl = "";
        mVideoOfflineUrl = "";
        mArtworkUrl = "";
        mIsOffline = true;
        mIsMatched = true;
        mMatchError = false;
        mIsFav = false;
        mDownload = new CollectionDownload();
        mVideoSize = new RealmList<CollectionVideoSize>();
        mModifiedOn = new Date().toString();
        mInternetId = "";
        mBookmark = "";
        mProvider = String.valueOf(Type.COLLECTION);
    }

    public String getTitle() {
        return mTitle;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public String getVideoOnlineUrl() {
        return mVideoOnlineUrl;
    }

    public String getVideoOfflineUrl() {
        return mVideoOfflineUrl;
    }

    public String getArtworkUrl() {
        return mArtworkUrl;
    }

    public boolean getIsOffline() {
        return mIsOffline;
    }

    public void setIsOffline(boolean mIsOffline) {
        this.mIsOffline = mIsOffline;
    }

    public boolean getIsFav() {
        return mIsFav;
    }

    public void setIsFav(boolean mIsFav) {
        this.mIsFav = mIsFav;
    }

    public String getLocalId() {
        return mLocalId;
    }

    public void setLocalId(String mLocalId) {
        this.mLocalId = mLocalId;
    }

    public String getModifiedOn() {
        return mModifiedOn;
    }

    public void setModifiedOn(String mModifiedOn) {
        this.mModifiedOn = mModifiedOn;
    }

    public String getId() {
        return mId;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public void setAuthor(String mAuthor) {
        this.mAuthor = mAuthor;
    }

    public void setVideoOnlineUrl(String mVideoOnlineUrl) {
        this.mVideoOnlineUrl = mVideoOnlineUrl;
    }

    public void setVideoOfflineUrl(String mVideoOfflineUrl) {
        this.mVideoOfflineUrl = mVideoOfflineUrl;
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


    public RealmList<CollectionVideoSize> getVideoSize() {
        return mVideoSize;
    }

    public void setVideoSize(RealmList<CollectionVideoSize> mVideoSize) {
        this.mVideoSize = mVideoSize;
    }
}
