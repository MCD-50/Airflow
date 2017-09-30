package com.airstem.airflow.ayush.airflow.events.collection;

import com.airstem.airflow.ayush.airflow.enums.collection.Action;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionPlaylist;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionTrack;

/**
 * Created by mcd-50 on 10/7/17.
 */

public interface CollectionPlaylistListener {
    void onPlaylistClick(CollectionPlaylist collectionPlaylist);
    void onPlaylistTrackClick(CollectionTrack collectionTrack);
    void onPlaylistTrackOptions(CollectionTrack collectionTrack, Action action);
    void onPlaylistOptions(CollectionPlaylist collectionPlaylist, Action action);
}
