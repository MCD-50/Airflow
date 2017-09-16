package com.airstem.airflow.ayush.airflow.helpers.collection;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;

import com.airstem.airflow.ayush.airflow.model.collection.CollectionArtist;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionTrack;

import java.util.ArrayList;

import io.realm.RealmList;

/**
 * Created by mcd-50 on 13/7/17.
 */

public class LocalArtistHelper {


    public static ArrayList<CollectionArtist> getAllArtists(Context context) {
        ArrayList<CollectionArtist> items = new ArrayList<>();

        Cursor cursor = CollectionCursorHelper.getAllArtists(context);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                long artistId = cursor.getLong(0);
                String artistName = cursor.getString(1);
                if (!TextUtils.isEmpty(artistName) && !artistName.toLowerCase().contains("Unknown")) {
                    ArrayList<CollectionTrack> tracks = getArtistTracks(context, artistId);
                    CollectionArtist item = new CollectionArtist();

                    item.init();


                    item.setTitle(artistName);
                    item.setArtworkUrl("");
                    item.setLocalId(String.valueOf(artistId));

                    RealmList<CollectionTrack> collectionTracks = new RealmList<CollectionTrack>();
                    collectionTracks.addAll(tracks);

                    item.setTracks(collectionTracks);
                    items.add(item);
                }
            } while (cursor.moveToNext());
        }
        if (cursor != null) {
            cursor.close();
        }
        return items;
    }

    private static ArrayList<CollectionTrack> getArtistTracks(Context context, long artistId) {
        ArrayList<CollectionTrack> items = new ArrayList<>();
        Cursor cursor = CollectionCursorHelper.getAllTracksWithSelection(context, getArtistTracksSelector(artistId), CollectionCursorHelper.getTrackProjection());

        if (cursor != null && cursor.moveToFirst()) {
            do {
                long songId = cursor.getLong(0);
                Uri artworkUrl = ContentUris.withAppendedId(Uri.parse(CollectionConstant.COLLECTION_LOCAL_TRACK_ARTWORK_BASE), CollectionCursorHelper.getAlbumId(context, songId));
                String trackName = cursor.getString(1);
                if (!TextUtils.isEmpty(trackName) && !trackName.toLowerCase().contains("Unknown")) {
                    CollectionTrack item = new CollectionTrack();

                    item.init();


                    item.setTitle(trackName);
                    item.setAlbumName(cursor.getString(2));
                    item.setArtistName(cursor.getString(3));
                    item.setTrackOnlineUrl("");
                    item.setTrackOfflineUrl(cursor.getString(4));
                    item.setArtworkUrl(String.valueOf(artworkUrl));
                    item.setIsOffline(true);
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


    private static String getArtistTracksSelector(long artistId) {
        return MediaStore.Audio.AudioColumns.IS_MUSIC + "=1"
                + " AND " + MediaStore.Audio.AudioColumns.TITLE + " != ''"
                + " AND " + MediaStore.Audio.AudioColumns.ARTIST_ID + "=" + artistId;

    }
}
