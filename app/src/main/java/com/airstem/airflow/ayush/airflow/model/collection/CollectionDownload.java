package com.airstem.airflow.ayush.airflow.model.collection;

import io.realm.RealmObject;

/**
 * Created by mcd-50 on 30/9/17.
 */

public class CollectionDownload extends RealmObject {
    private long mDownloadId = -1;
    private int mProgress = 0;
    private int mStatus = 0;
    private long mDownloadByte = -1;
    private long mFileSize = -1;
    private int mError = 0;

    public long getDownloadId() {
        return mDownloadId;
    }

    public void setDownloadId(long mDownloadId) {
        this.mDownloadId = mDownloadId;
    }

    public int getProgress() {
        return mProgress;
    }

    public void setProgress(int mProgress) {
        this.mProgress = mProgress;
    }

    public int getStatus() {
        return mStatus;
    }

    public void setStatus(int mStatus) {
        this.mStatus = mStatus;
    }

    public long getDownloadByte() {
        return mDownloadByte;
    }

    public void setDownloadByte(long mDownloadByte) {
        this.mDownloadByte = mDownloadByte;
    }

    public long getFileSize() {
        return mFileSize;
    }

    public void setFileSize(long mFileSize) {
        this.mFileSize = mFileSize;
    }

    public int getError() {
        return mError;
    }

    public void setError(int mError) {
        this.mError = mError;
    }
}
