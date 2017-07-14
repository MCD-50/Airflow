package com.airstem.airflow.ayush.airflow.helpers.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.airstem.airflow.ayush.airflow.events.Collection.CollectionProviderListener;
import com.airstem.airflow.ayush.airflow.events.Database.DatabaseListener;
import com.airstem.airflow.ayush.airflow.helpers.Collection.CollectionProvider;

/**
 * Created by mcd-50 on 13/7/17.
 */

public class Database extends SQLiteOpenHelper {
    private Context mContext;
    private static Database mDatabase = null;
    private CollectionProviderListener mListener;

    Database(Context context){
        super(context, DatabaseConstant.DATABASE_NAME, null, DatabaseConstant.DATABASE_VERSION);
        this.mContext = context;
    }

    public static Database getDatabaseHelper(Context context, CollectionProviderListener listener){
        if(mDatabase == null){
            mDatabase = new Database(context);
        }
        mDatabase.mListener= listener;
        return mDatabase;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
