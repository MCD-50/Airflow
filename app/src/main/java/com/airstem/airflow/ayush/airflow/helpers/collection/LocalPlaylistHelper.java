package com.airstem.airflow.ayush.airflow.helpers.collection;

import android.content.Context;

import com.airstem.airflow.ayush.airflow.model.collection.CollectionPlaylist;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionTrack;

import java.util.ArrayList;

import io.realm.RealmList;

/**
 * Created by mcd-50 on 13/7/17.
 */

public class LocalPlaylistHelper {

    public static ArrayList<CollectionPlaylist> getAllPlaylists(Context context){
        ArrayList<CollectionPlaylist> items = new ArrayList<>();
        for(String name : CollectionConstant.COLLECTION_DEFAULT_PLAYLISTS){
            CollectionPlaylist item = new CollectionPlaylist();

            item.init();


            item.setTitle(name);
            item.setTracks(new RealmList<CollectionTrack>());
            items.add(item);
        }
        return items;
    }
}

