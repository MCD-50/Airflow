package com.airstem.airflow.ayush.airflow.events.Database;

import com.airstem.airflow.ayush.airflow.model.collection.CollectionArtist;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionPlaylist;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionRadio;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionTrack;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionVideo;

/**
 * Created by mcd-50 on 13/7/17.
 */

public interface DatabaseListener {
    void onArtistCreate(CollectionArtist collectionArtist);
    void onArtistDelete(CollectionArtist collectionArtist);
    void onArtistArtworkUpdated(CollectionArtist collectionArtist);

    void onPlaylistCreate(CollectionPlaylist collectionPlaylist);
    void onPlaylistDelete(CollectionPlaylist collectionPlaylist);
    void onPlaylistAddTrack(CollectionTrack collectionTrack);
    void onPlaylistRemoveTrack(CollectionTrack collectionTrack);

    void onRadioCreate(CollectionRadio collectionRadio);
    void onRadioDelete(CollectionRadio collectionRadio);

    void onTrackCreate(CollectionTrack collectionTrack);
    void onTrackDelete(CollectionTrack collectionTrack);
    void onTrackArtworkUpdated(CollectionTrack collectionTrack);

    void onVideoCreate(CollectionVideo collectionVideo);
    void onVideoDelete(CollectionVideo collectionVideo);
    void onVideoArtworkUpdated(CollectionVideo collectionVideo);
}
