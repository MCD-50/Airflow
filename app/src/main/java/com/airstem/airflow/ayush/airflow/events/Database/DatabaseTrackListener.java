package com.airstem.airflow.ayush.airflow.events.Database;

import com.airstem.airflow.ayush.airflow.model.collection.CollectionTrack;

/**
 * Created by mcd-50 on 13/7/17.
 */

public interface DatabaseTrackListener {
    void onTrackCreate(CollectionTrack collectionTrack);
    void onTrackDelete(CollectionTrack collectionTrack);
    void onTrackArtworkUpdated(CollectionTrack collectionTrack);
}
