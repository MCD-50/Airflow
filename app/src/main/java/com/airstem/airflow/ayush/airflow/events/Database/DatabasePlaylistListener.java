package com.airstem.airflow.ayush.airflow.events.Database;

import com.airstem.airflow.ayush.airflow.model.collection.CollectionPlaylist;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionTrack;

/**
 * Created by mcd-50 on 13/7/17.
 */

public interface DatabasePlaylistListener {
    void onPlaylistCreate(CollectionPlaylist collectionPlaylist);
    void onPlaylistDelete(CollectionPlaylist collectionPlaylist);
    void onPlaylistAddTrack(CollectionTrack collectionTrack);
    void onPlaylistRemoveTrack(CollectionTrack collectionTrack);
}
