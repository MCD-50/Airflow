package com.airstem.airflow.ayush.airflow.events.collection;

import com.airstem.airflow.ayush.airflow.model.collection.CollectionRadio;

/**
 * Created by mcd-50 on 11/7/17.
 */

public interface CollectionRadioListener {
    void onRadioClick(CollectionRadio collectionRadio);
    void onRadioFav(CollectionRadio collectionRadio, boolean addToFav);
}
