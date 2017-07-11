package com.airstem.airflow.ayush.airflow.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.StringTokenizer;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.airstem.airflow.ayush.airflow.R;
import com.airstem.airflow.ayush.airflow.helpers.EventHelper;
import com.airstem.airflow.ayush.airflow.model.Params;
import com.airstem.airflow.ayush.airflow.model.PlayMode;
import com.airstem.airflow.ayush.airflow.model.Track;

/**
 * Created by ayush on 10-10-16.
 */
public class DatabaseUtils extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "airflowDatabaseFavTracks";

    public static final String TABLE_FAV_TRACKS = "favTracksData";
    public static final String TABLE_LOCAL_TRACKS = "localTracksData";

    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_MOOD = "mood";
    private static final String KEY_LOCAL_ARTWORK = "localArtwork";
    private static final String KEY_ARTWORK = "artwork";
    private static final String KEY_URL = "url";


    Context mContext;
    public DatabaseUtils(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_FAV_TRACK__TABLE = "CREATE TABLE " + TABLE_FAV_TRACKS + "("
                + KEY_ID + " TEXT PRIMARY KEY," + KEY_TITLE + " TEXT,"
                + KEY_URL + " TEXT," + KEY_MOOD + " TEXT," + KEY_ARTWORK + " BLOB," + KEY_LOCAL_ARTWORK + " TEXT" + ")";

       /* String CREATE_LOCAL_TRACK_TABLE = "CREATE TABLE " + TABLE_LOCAL_TRACKS + "("
                + KEY_ID + " TEXT PRIMARY KEY," + KEY_TITLE + " TEXT,"
                + KEY_URL + " TEXT," + KEY_MOOD + " TEXT," + KEY_ARTWORK + " BLOB" + ")";*/

        db.execSQL(CREATE_FAV_TRACK__TABLE);
        //db.execSQL(CREATE_LOCAL_TRACK_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAV_TRACKS);
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOCAL_TRACKS);
        // Create tables again
        onCreate(db);
    }



    public void addTrack(Track track) {

        if(getTracksCount() > 50){
            showAlert("Couldn't add track", "You can add only 50 tracks to your fav list. Try removing few of them and then add.");
        }else {
            SQLiteDatabase db = this.getWritableDatabase();
            //insert only once.
            if(getTrack(track.getId()) == null){
                ContentValues values = new ContentValues();
                values.put(KEY_ID, track.getId());
                values.put(KEY_TITLE, track.getTitle());
                values.put(KEY_URL, track.getTrackUrl());
                values.put(KEY_MOOD, track.getMood());
                if(track.getMode() == PlayMode.OFFLINE){
                    values.put(KEY_LOCAL_ARTWORK, track.getArtwork());
                    db.insert(TABLE_FAV_TRACKS, null, values);
                    //EventHelper.Invoke(track, true);
                }else {
                    db.insert(TABLE_FAV_TRACKS, null, values);
                    //EventHelper.Invoke(track, true);
                    new DownloadImage().execute(new Params(track.getId(), track.getArtwork()));
                }

                //lets downloadImage
                //we are not adding the image right now
            }

            db.close();
        }
    }


    public ArrayList<Track> getAllTracks() {
        ArrayList<Track> trackList = new ArrayList<Track>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_FAV_TRACKS;

        try{
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);

            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    Track track = new Track(cursor.getString(0), cursor.getString(1), cursor.getString(2)
                            , PlayMode.ONLINE);
                    track.setMoodName(cursor.getString(3));
                    try{
                        if(cursor.getString(5) != null){
                            track.setMode(PlayMode.OFFLINE);
                            track.setArtwork(cursor.getString(5));
                        }else{
                            byte[] blob = cursor.getBlob(4);
                            track.setBitmap(BitmapFactory.decodeByteArray(blob, 0,blob.length));
                        }
                    }catch (Exception e){

                    }
                    trackList.add(track);
                } while (cursor.moveToNext());
            }

            cursor.close();
        }catch (Exception e){

        }


        // return contact list
        return trackList;
    }

    // Getting single contact
    Track getTrack(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Track track = null;
        Cursor cursor = db.query(TABLE_FAV_TRACKS, new String[] { KEY_ID,
                        KEY_TITLE, KEY_URL, KEY_MOOD }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            track = new Track(cursor.getString(0),
                    cursor.getString(1), cursor.getString(2), PlayMode.ONLINE);
            track.setMoodName(cursor.getString(3));
        }
        if(cursor != null)
            cursor.close();

        return track;
    }

    public boolean isInFavList(String id){
        try{
            return getTrack(id) != null;
        }catch(Exception e){
            return false;
        }
    }

    boolean updateTrack(Bitmap bitmap, String id) {

        try{
            Track track = getTrack(id);
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();

            Bitmap p = bitmap;
            ByteArrayOutputStream bAOS = new ByteArrayOutputStream();
            p.compress(Bitmap.CompressFormat.PNG, 100, bAOS);
            byte[] bArray = bAOS.toByteArray();
            values.put(KEY_ARTWORK, bArray);
            // updating row
            db.update(TABLE_FAV_TRACKS, values, KEY_ID + " = ?",
                    new String[] { String.valueOf(track.getId()) });


            return true;

        }catch(Exception ex){
            return false;
        }

    }


    public void deleteTrack(String id , boolean showResult) {

        try{
            Track track = getTrack(id);
            SQLiteDatabase db = this.getWritableDatabase();

            db.delete(TABLE_FAV_TRACKS, KEY_ID + " = ?",
                    new String[] { String.valueOf(id) });

            db.close();
            if(showResult)
                Toast.makeText(mContext, "Track removed", Toast.LENGTH_SHORT).show();

            EventHelper.Invoke(track, false);
        }catch (Exception e){
            Toast.makeText(mContext, "Cannot delete.", Toast.LENGTH_SHORT).show();
        }

    }


    public int getTracksCount() {
        try{
            SQLiteDatabase db = this.getReadableDatabase();
            long cnt = android.database.DatabaseUtils.queryNumEntries(db, TABLE_FAV_TRACKS);
            db.close();
            return (int) cnt;

        }catch (Exception e){
           try{
               String countQuery = "SELECT * FROM " + TABLE_FAV_TRACKS;
               SQLiteDatabase db = this.getReadableDatabase();
               Cursor cursor = db.rawQuery(countQuery, null);
               int count = cursor.getCount();
               cursor.close();
               return count;
           }catch (Exception ex){
               return 0;
           }
        }
    }


     ProgressDialog mProgressDialog;
     class DownloadImage extends AsyncTask<Params, Void, Bitmap>{
         String id;
         @Override
         protected Bitmap doInBackground(Params... params) {

             Params mParam = params[0];
             String url = mParam.getArtwork();
             id = mParam.getId();

             Bitmap bitmap = null;
             try {
                 InputStream iStream = new URL(url).openStream();
                 bitmap = BitmapFactory.decodeStream(iStream);
             }catch(Exception ex){
                 try{
                     bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.default_art);
                 }catch (Exception e){

                 }
             }

             return bitmap;
         }

         @Override
         protected void onPreExecute() {
             super.onPreExecute();
             mProgressDialog = new ProgressDialog(mContext);
             mProgressDialog.setMessage("Downloading image...");
             mProgressDialog.setIndeterminate(false);
             mProgressDialog.show();
         }

         @Override
         protected void onPostExecute(Bitmap bitmap) {
             super.onPostExecute(bitmap);
             mProgressDialog.dismiss();
             if(saveBitmap(bitmap, id)){
                 Toast.makeText(mContext, "Track added.", Toast.LENGTH_SHORT).show();
                 EventHelper.Invoke(getTrack(id), true);
             }
         }
     }


    private boolean saveBitmap(Bitmap bitmap, String id) {
        if(bitmap != null){
            return updateTrack(bitmap, id);
        }else {
            showAlert("Couldn't Complete, Try again", "Something went wrong at our end. Please try in a little bit.");
            deleteTrack(id, false);
            return false;
        }
    }

    private void showAlert(String title, String message){
        AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
        alert.setTitle(title);
        alert.setMessage(message);
        alert.setPositiveButton("OK",null);
        alert.setNegativeButton("CANCEL", null);
        alert.show();
    }


}
