package com.airstem.airflow.ayush.airflow.helpers.Collection;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;

import com.airstem.airflow.ayush.airflow.constants.CollectionConstant;
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
                Uri artworkUrl = ContentUris.withAppendedId(Uri.parse(CollectionConstant.COLLECTION_LOCAL_VIDEO_ARTWORK_BASE), videoId);
                String videoName = cursor.getString(1);
                if (!TextUtils.isEmpty(videoName) && !videoName.toLowerCase().contains("Unknown")) {
                    CollectionVideo item = new CollectionVideo(videoName, cursor.getString(2), "", cursor.getString(3), artworkUrl.toString(), 1);
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
