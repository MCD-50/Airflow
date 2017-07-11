package com.airstem.airflow.ayush.airflow.events;

import com.airstem.airflow.ayush.airflow.model.collection.CollectionRadio;
import com.airstem.airflow.ayush.airflow.model.search.SearchRadio;

/**
 * Created by mcd-50 on 11/7/17.
 */

public interface CollectionRadioClickListener {
    void onItemClick(CollectionRadio collectionRadio);
}
