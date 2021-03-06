package com.airstem.airflow.ayush.airflow.events.collection;

import com.airstem.airflow.ayush.airflow.enums.collection.Action;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionArtist;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionTrack;

/**
 * Created by mcd-50 on 10/7/17.
 */

public interface CollectionArtistListener {
    void onArtistClick(CollectionArtist collectionArtist);
    void onArtistTrackClick(CollectionTrack collectionTrack);
    void onArtistTrackOptions(CollectionTrack collectionTrack, Action action);
    void onArtistOptions(CollectionArtist collectionArtist, Action action);
}
