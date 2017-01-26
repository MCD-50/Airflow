package com.airstem.airflow.ayush.airflow.helpers;

import com.airstem.airflow.ayush.airflow.model.Track;

/**
 * Created by ayush on 13-10-16.
 */
public class EventHelper {

    private static DatabaseEvent databaseEvent;
    public static void initDatabaseEvent(DatabaseEvent myEvent){
        databaseEvent = myEvent;
    }

    public static void Invoke(Track track, boolean addData){
        databaseEvent.resetAdapter(track, addData);
    }
}
