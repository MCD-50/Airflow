package com.airstem.airflow.ayush.airflow.events.collection;

import com.airstem.airflow.ayush.airflow.enums.collection.Action;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionRadio;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionTrack;

/**
 * Created by mcd-50 on 11/7/17.
 */

public interface CollectionRadioListener {
    void onRadioClick(CollectionRadio collectionRadio);
    void onRadioOptions(CollectionRadio collectionRadio, Action action);
}
