package com.airstem.airflow.ayush.airflow.events;

import com.airstem.airflow.ayush.airflow.model.collection.CollectionPlaylist;

/**
 * Created by mcd-50 on 10/7/17.
 */

public interface CollectionPlaylistClickListener {
    void onItemClick(CollectionPlaylist collectionPlaylist);
}
