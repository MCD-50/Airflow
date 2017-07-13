package com.airstem.airflow.ayush.airflow.events.Collection;

import com.airstem.airflow.ayush.airflow.model.collection.CollectionRadio;
import com.airstem.airflow.ayush.airflow.model.search.SearchRadio;

/**
 * Created by mcd-50 on 11/7/17.
 */

public interface CollectionRadioListener {
    void onRadioClick(CollectionRadio collectionRadio);
}
