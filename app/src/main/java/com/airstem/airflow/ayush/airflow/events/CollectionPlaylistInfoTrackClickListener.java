package com.airstem.airflow.ayush.airflow.events;

import com.airstem.airflow.ayush.airflow.model.collection.CollectionTrack;

/**
 * Created by mcd-50 on 10/7/17.
 */

public interface CollectionPlaylistInfoTrackClickListener {
    void onItemClick(CollectionTrack collectionTrack);
}
