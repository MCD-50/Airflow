package com.airstem.airflow.ayush.airflow.helpers;

import com.airstem.airflow.ayush.airflow.model.Track;

/**
 * Created by ayush on 13-10-16.
 */
public interface DatabaseEvent {
    void resetAdapter(Track track, boolean addData);
}
