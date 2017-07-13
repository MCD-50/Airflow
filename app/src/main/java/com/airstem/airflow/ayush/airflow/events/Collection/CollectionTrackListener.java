package com.airstem.airflow.ayush.airflow.events.Collection;

import com.airstem.airflow.ayush.airflow.model.collection.CollectionTrack;

/**
 * Created by mcd-50 on 10/7/17.
 */

public interface CollectionTrackListener {
    void onTrackClick(CollectionTrack collectionTrack);
}
