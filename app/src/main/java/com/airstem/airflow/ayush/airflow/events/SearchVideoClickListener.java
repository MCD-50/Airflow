package com.airstem.airflow.ayush.airflow.events;

import com.airstem.airflow.ayush.airflow.model.search.SearchRadio;
import com.airstem.airflow.ayush.airflow.model.search.SearchVideo;

/**
 * Created by mcd-50 on 10/7/17.
 */

public interface SearchVideoClickListener {
    void onItemClick(SearchVideo searchVideo);
}
