package com.airstem.airflow.ayush.airflow.helpers;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.airstem.airflow.ayush.airflow.utils.AppConstant;

/**
 * Created by ayush on 15-10-16.
 */

public class StorageHelper {

    SharedPreferences sharedPreferences;

    public StorageHelper(Context context){
       sharedPreferences = context.getSharedPreferences("RANDOM", 0);
    }

    //we have single data to be stored..
    public void saveData(String currentCountryISO){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(AppConstant.SHARED_PREFERENCES_KEY, currentCountryISO);
        //ignore the warning
        editor.apply();
    }


    public String getData(){
        return sharedPreferences.getString(AppConstant.SHARED_PREFERENCES_KEY, "Worldwide");
    }

}
