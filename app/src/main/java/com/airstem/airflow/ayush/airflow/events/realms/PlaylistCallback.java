package com.airstem.airflow.ayush.airflow.events.realms;

import com.airstem.airflow.ayush.airflow.model.collection.CollectionPlaylist;

import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by mcd-50 on 24/9/17.
 */

public interface PlaylistCallback {
    void onSuccess(RealmResults<CollectionPlaylist> collectionPlaylists);
}
