package com.airstem.airflow.ayush.airflow.events.collection;

import com.airstem.airflow.ayush.airflow.model.collection.CollectionArtist;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionPlaylist;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionTrack;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionVideo;

import java.util.ArrayList;

/**
 * Created by mcd-50 on 24/9/17.
 */

public interface CursorListener {
    void onSuccess(ArrayList<CollectionTrack> collectionTracks, ArrayList<CollectionVideo> collectionVideos, ArrayList<CollectionPlaylist> collectionPlaylists, ArrayList<CollectionArtist> collectionArtists);
    void onArtistAndTracksFill(ArrayList<CollectionArtist> collectionArtists, ArrayList<CollectionTrack> collectionTracks);
}
