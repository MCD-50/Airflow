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
                String artistName = cursor.getString(3);
                if (!TextUtils.isEmpty(trackName) && !trackName.toLowerCase().contains("unknown") && !artistName.toLowerCase().contains("unknown")) {
                    CollectionTrack item = new CollectionTrack();

                    item.init();

                    item.setTitle(trackName);
                    item.setAlbumName(cursor.getString(2));
                    item.setArtistName(artistName);
                    item.setTrackOnlineUrl("");
                    item.setTrackOfflineUrl(cursor.getString(4));
                    item.setArtworkUrl(String.valueOf(artworkUrl));
                    item.setIsOffline(true);
                    item.setLocalId(String.valueOf(songId));
                    item.setModifiedOn(cursor.getString(5));
                    item.setBookmark(cursor.getString(6));
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
