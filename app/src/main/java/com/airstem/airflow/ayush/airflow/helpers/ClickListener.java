package com.airstem.airflow.ayush.airflow.helpers;

import com.airstem.airflow.ayush.airflow.model.Artist;
import com.airstem.airflow.ayush.airflow.model.Playlist;
import com.airstem.airflow.ayush.airflow.model.Track;

/**
 * Created by ayush on 16-11-16.
 */
public interface ClickListener {
    void OnItemClick(Track track);
    void OnItemClick(Artist artist);
    void OnItemClick(Playlist playlist);
}
