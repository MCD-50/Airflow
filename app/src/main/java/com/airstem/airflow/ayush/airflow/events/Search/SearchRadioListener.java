package com.airstem.airflow.ayush.airflow.events.Search;

import com.airstem.airflow.ayush.airflow.model.search.SearchRadio;

/**
 * Created by mcd-50 on 10/7/17.
 */

public interface SearchRadioListener {
    void onRadioClick(SearchRadio searchRadio);
}
