package com.airstem.airflow.ayush.airflow.helpers.collection;

import com.airstem.airflow.ayush.airflow.model.collection.CollectionArtist;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionPlaylist;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionRadio;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionTrack;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionVideo;

import java.util.ArrayList;

/**
 * Created by mcd-50 on 14/7/17.
 */

public class CollectionProvider {
    public static ArrayList<CollectionTrack> mTracks = new ArrayList<>();
    public static ArrayList<CollectionArtist> mArtists = new ArrayList<>();
    public static ArrayList<CollectionPlaylist> mPlaylists = new ArrayList<>();
    public static ArrayList<CollectionVideo> mVideos = new ArrayList<>();
    public static ArrayList<CollectionRadio> mRadios = new ArrayList<>();
}
