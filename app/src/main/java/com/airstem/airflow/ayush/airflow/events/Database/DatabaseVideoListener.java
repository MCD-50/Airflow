package com.airstem.airflow.ayush.airflow.events.Database;

import com.airstem.airflow.ayush.airflow.model.collection.CollectionVideo;

/**
 * Created by mcd-50 on 13/7/17.
 */

public interface DatabaseVideoListener {
    void onVideoCreate(CollectionVideo collectionVideo);
    void onVideoDelete(CollectionVideo collectionVideo);
    void onVideoArtworkUpdated(CollectionVideo collectionVideo);
}
