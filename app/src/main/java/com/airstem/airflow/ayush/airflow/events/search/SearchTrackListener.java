package com.airstem.airflow.ayush.airflow.events.search;

import com.airstem.airflow.ayush.airflow.model.search.SearchTrack;

/**
 * Created by mcd-50 on 10/7/17.
 */

public interface SearchTrackListener {
    void onTrackClick(SearchTrack searchTrack);
}
