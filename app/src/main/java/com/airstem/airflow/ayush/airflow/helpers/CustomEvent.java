package com.airstem.airflow.ayush.airflow.helpers;

import android.content.Context;
import android.media.MediaPlayer;

import com.airstem.airflow.ayush.airflow.model.Track;

/**
 * Created by ayush on 13-10-16.
 */

public interface CustomEvent {
    //create a method to be executed when it is invoked by sender.
    void trackChanged();
    void noInternet();
}
