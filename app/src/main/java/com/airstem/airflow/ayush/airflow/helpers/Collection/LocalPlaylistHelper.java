package com.airstem.airflow.ayush.airflow.helpers.Collection;

import android.content.Context;

import com.airstem.airflow.ayush.airflow.constants.CollectionConstant;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionPlaylist;

import java.util.ArrayList;

/**
 * Created by mcd-50 on 13/7/17.
 */

public class LocalPlaylistHelper {

    public static ArrayList<CollectionPlaylist> getAllPlaylists(Context context){
        ArrayList<CollectionPlaylist> items = new ArrayList<>();
        for(String name : CollectionConstant.COLLECTION_DEFAULT_PLAYLISTS){
            items.add(new CollectionPlaylist(name, "Airstem"));
        }
        return items;
    }
}

