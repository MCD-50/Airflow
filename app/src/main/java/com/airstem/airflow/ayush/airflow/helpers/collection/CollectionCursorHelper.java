package com.airstem.airflow.ayush.airflow.helpers.collection;


import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

/**
 * Created by mcd-50 on 13/7/17.
 */

public class CollectionCursorHelper {


    public static Cursor getAllTracksWithSelection(Context context, String selection, String[] projection) {
        ContentResolver contentResolver = context.getContentResolver();
        return contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection,
                selection,
                null,
                MediaStore.Audio.Artists.DEFAULT_SORT_ORDER);
    }

    public static Cursor getAllTracks(Context context, String[] projection) {
        ContentResolver contentResolver = context.getContentResolver();
        return contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection,
                null,
                null,
                MediaStore.Audio.Artists.DEFAULT_SORT_ORDER);
    }


    public static Cursor getAllVideos(Context context, String[] projection) {
        ContentResolver contentResolver = context.getContentResolver();
        return contentResolver.query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                projection,
                null,
                null,
                MediaStore.Video.Media.DEFAULT_SORT_ORDER);
    }


    public static Cursor getAllArtists(Context context) {
        ContentResolver contentResolver = context.getContentResolver();
        String[] projection = {MediaStore.Audio.Artists._ID,
                MediaStore.Audio.Artists.ARTIST,
                MediaStore.Audio.Artists.NUMBER_OF_TRACKS,
                MediaStore.Audio.Artists.NUMBER_OF_ALBUMS};

        return contentResolver.query(MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI,
                projection,
                null,
                null,
                MediaStore.Audio.Artists.DEFAULT_SORT_ORDER);

    }


    public static Cursor getAllAlbums(Context context) {
        ContentResolver contentResolver = context.getContentResolver();
        String[] projection = {MediaStore.Audio.Albums._ID,
                MediaStore.Audio.Albums.ALBUM,
                MediaStore.Audio.Albums.NUMBER_OF_SONGS};

        return contentResolver.query(MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI,
                projection,
                null,
                null,
                MediaStore.Audio.Albums.DEFAULT_SORT_ORDER);

    }


    public static Cursor getAllGenres(Context context) {
        ContentResolver contentResolver = context.getContentResolver();
        String[] projection = {MediaStore.Audio.Genres._ID,
                MediaStore.Audio.Genres.NAME};

        return contentResolver.query(MediaStore.Audio.Genres.EXTERNAL_CONTENT_URI,
                projection,
                null,
                null,
                MediaStore.Audio.Genres.DEFAULT_SORT_ORDER);

    }


    //helpers
    public static long getAlbumId(Context context, long id) {
        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursor;
        String[] projection = {
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.ALBUM_ID
        };

        String[] arguments = {
                Long.toString(id)
        };

        cursor = contentResolver.query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection,
                MediaStore.Audio.Media._ID + " = ?",
                arguments,
                null);

        if (cursor != null && cursor.moveToFirst()) {
            long albumId = cursor.getLong(1);
            cursor.close();
            return albumId;
        }
        return -1;
    }

    public static String[] getTrackProjection() {
        return new String[]{
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ALBUM,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.DATE_MODIFIED
        };
    }

    public static String[] getVideoProjection() {
        return new String[]{MediaStore.Video.Media._ID,
                MediaStore.Video.Media.TITLE,
                MediaStore.Audio.Media.ALBUM,
                MediaStore.Audio.Media.DATA,
                MediaStore.Video.Media.DATE_MODIFIED,

        };
    }

}
