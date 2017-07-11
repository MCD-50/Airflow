package com.airstem.airflow.ayush.airflow.events;

import com.airstem.airflow.ayush.airflow.model.collection.CollectionTrack;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionVideo;
import com.airstem.airflow.ayush.airflow.model.search.SearchRadio;

/**
 * Created by mcd-50 on 10/7/17.
 */

public interface CollectionArtistInfoTrackClickListener {
    void onItemClick(CollectionTrack collectionTrack);
}
