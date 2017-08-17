package com.airstem.airflow.ayush.airflow.helpers.collection;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;

import com.airstem.airflow.ayush.airflow.model.collection.CollectionTrack;

import java.util.ArrayList;

/**
 * Created by mcd-50 on 13/7/17.
 */

public class LocalTrackHelper {

    public static ArrayList<CollectionTrack> getAllTracks(Context context) {
        ArrayList<CollectionTrack> items = new ArrayList<>();
        Cursor cursor = CollectionCursorHelper.getAllTracks(context, CollectionCursorHelper.getTrackProjection());

        if (cursor != null && cursor.moveToFirst()) {
            do {
                long songId = cursor.getLong(0);
                Uri artworkUrl = ContentUris.withAppendedId(Uri.parse(CollectionConstant.COLLECTION_LOCAL_TRACK_ARTWORK_BASE), CollectionCursorHelper.getAlbumId(context, songId));
                String trackName = cursor.getString(1);
                if (!TextUtils.isEmpty(trackName) && !trackName.toLowerCase().contains("Unknown")) {
                    CollectionTrack item = new CollectionTrack(trackName, cursor.getString(2), cursor.getString(3), "", cursor.getString(4), artworkUrl.toString(), 1);
                    item.setLocalId(String.valueOf(songId));
                    item.setModifiedOn(cursor.getString(5));
                    items.add(item);
                }
            } while (cursor.moveToNext());
        }
        if (cursor != null) {
            cursor.close();
        }
        return items;
    }
}
