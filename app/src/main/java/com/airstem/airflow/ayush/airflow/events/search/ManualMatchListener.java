package com.airstem.airflow.ayush.airflow.events.search;

import com.airstem.airflow.ayush.airflow.model.search.ManualMatch;
import com.airstem.airflow.ayush.airflow.model.search.SearchTrack;

/**
 * Created by mcd-50 on 2/10/17.
 */

public interface ManualMatchListener {
    void onTrackClick(ManualMatch manualMatch);
    void onTrackOption(ManualMatch manualMatch);
}
