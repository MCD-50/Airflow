package com.airstem.airflow.ayush.airflow.helpers;

import android.app.Activity;

import java.lang.ref.WeakReference;

/**
 * Created by ayush on 10-10-16.
 */

//Not using cause found better solution
public class ActivityReference {
    private static WeakReference<Activity> mActivityRef;
    public static void setRef(Activity activity){
        mActivityRef = new WeakReference<Activity>(activity);
    }

    //getting ref of registered activity and activity.get()
    public static WeakReference<Activity> getRef(){
        return mActivityRef;
    }

}
