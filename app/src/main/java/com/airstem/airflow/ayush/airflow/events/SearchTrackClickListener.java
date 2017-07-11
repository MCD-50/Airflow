package com.airstem.airflow.ayush.airflow.events;

import com.airstem.airflow.ayush.airflow.model.search.SearchRadio;
import com.airstem.airflow.ayush.airflow.model.search.SearchTrack;

/**
 * Created by mcd-50 on 10/7/17.
 */

public interface SearchTrackClickListener {
    void onItemClick(SearchTrack searchTrack);
}
