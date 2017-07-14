package com.airstem.airflow.ayush.airflow.events.Collection;

import com.airstem.airflow.ayush.airflow.model.collection.CollectionArtist;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionPlaylist;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionRadio;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionTrack;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionVideo;

import java.util.ArrayList;

/**
 * Created by mcd-50 on 14/7/17.
 */


public interface CollectionProviderListener {
    void onTracksUpdated(ArrayList<CollectionTrack> collectionTracks);
    void onPlaylistsUpdated(ArrayList<CollectionPlaylist> collectionPlaylists);
    void onArtistsUpdated(ArrayList<CollectionArtist> collectionArtists);
    void onVideosUpdated(ArrayList<CollectionVideo> collectionVideos);
    void onRadioUpdated(ArrayList<CollectionRadio> collectionRadios);
}
