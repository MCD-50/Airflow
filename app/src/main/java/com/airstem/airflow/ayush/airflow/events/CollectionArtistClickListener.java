package com.airstem.airflow.ayush.airflow.events;

import com.airstem.airflow.ayush.airflow.model.collection.CollectionArtist;

/**
 * Created by mcd-50 on 10/7/17.
 */

public interface CollectionArtistClickListener {
    void onItemClick(CollectionArtist collectionArtist);
}
