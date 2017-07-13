package com.airstem.airflow.ayush.airflow.events.Database;

import com.airstem.airflow.ayush.airflow.model.collection.CollectionArtist;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionTrack;

/**
 * Created by mcd-50 on 13/7/17.
 */

public interface DatabaseArtistListener {
    void onArtistCreate(CollectionArtist collectionArtist);
    void onArtistDelete(CollectionArtist collectionArtist);
    void onArtistArtworkUpdated(CollectionArtist collectionArtist);
    void onArtistAddTrack(CollectionTrack collectionTrack);
    void onArtistDeleteTrack(CollectionTrack collectionTrack);
}
