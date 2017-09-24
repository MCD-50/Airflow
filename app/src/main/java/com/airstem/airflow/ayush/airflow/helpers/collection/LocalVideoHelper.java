package com.airstem.airflow.ayush.airflow.helpers.collection;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;

import com.airstem.airflow.ayush.airflow.model.collection.CollectionVideo;

import java.util.ArrayList;

/**
 * Created by mcd-50 on 13/7/17.
 */

public class LocalVideoHelper {


    public static ArrayList<CollectionVideo> getAllVideos(Context context) {
        ArrayList<CollectionVideo> items = new ArrayList<>();
        Cursor cursor = CollectionCursorHelper.getAllVideos(context, CollectionCursorHelper.getVideoProjection());

        if (cursor != null && cursor.moveToFirst()) {
            do {
                long videoId = cursor.getLong(0);
                String artworkUrl = CollectionCursorHelper.getVideoThumbnail(context, videoId);

                String videoName = cursor.getString(1);
                if (!TextUtils.isEmpty(videoName) && !videoName.toLowerCase().contains("unknown")) {
                    CollectionVideo item = new CollectionVideo();

                    item.init();

                    item.setTitle(videoName);
                    item.setAuthor(cursor.getString(2));
                    item.setVideoOnlineUrl("");
                    item.setVideoOfflineUrl(cursor.getString(3));
                    item.setArtworkUrl(artworkUrl);
                    item.setIsOffline(true);
                    item.setLocalId(String.valueOf(videoId));
                    item.setModifiedOn(cursor.getString(4));

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
