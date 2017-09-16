package com.airstem.airflow.ayush.airflow.events.service;

import com.airstem.airflow.ayush.airflow.model.collection.CollectionTrack;

/**
 * Created by mcd-50 on 26/8/17.
 */

public interface PlayerListener {
    void onNext(String databaseId);
    void onPrev(String databaseId);
    void onTrackChange(String databaseId);
    void onPlayerActive();
    void onPlayPause(boolean isPlay);
}
