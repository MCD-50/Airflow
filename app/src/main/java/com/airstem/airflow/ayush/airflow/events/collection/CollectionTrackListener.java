package com.airstem.airflow.ayush.airflow.events.collection;

import com.airstem.airflow.ayush.airflow.enums.collection.Action;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionTrack;

/**
 * Created by mcd-50 on 10/7/17.
 */

public interface CollectionTrackListener {
    void onTrackClick(CollectionTrack collectionTrack);
    void onTrackOptions(CollectionTrack collectionTrack, Action action);
}
