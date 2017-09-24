package com.airstem.airflow.ayush.airflow.helpers.database;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by mcd-50 on 23/9/17.
 */

public class StoreHelper {

    private SharedPreferences sharedPreferences;
    public StoreHelper(Context context){
        sharedPreferences = context.getSharedPreferences("airstem", 0);
    }

    public void saveData(String key, String value){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getData(String key, String defaultValue){
        return sharedPreferences.getString(key, defaultValue);
    }

}
