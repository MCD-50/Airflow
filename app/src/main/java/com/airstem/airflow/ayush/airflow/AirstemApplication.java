package com.airstem.airflow.ayush.airflow;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by mcd-50 on 15/8/17.
 */

public class AirstemApplication  extends Application {

    public void onCreate() {
        super.onCreate();
        // Initialize Realm. Should only be done once when the application starts.
        Realm.init(this);

        initDatabase();
    }

    private void initDatabase() {

    }



}
