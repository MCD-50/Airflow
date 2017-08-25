package com.airstem.airflow.ayush.airflow.events.volly;

import com.airstem.airflow.ayush.airflow.model.search.SearchAlbum;
import com.airstem.airflow.ayush.airflow.model.search.SearchArtist;
import com.airstem.airflow.ayush.airflow.model.search.SearchImage;
import com.airstem.airflow.ayush.airflow.model.search.SearchRadio;
import com.airstem.airflow.ayush.airflow.model.search.SearchTrack;
import com.airstem.airflow.ayush.airflow.model.search.SearchVideo;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by mcd-50 on 19/8/17.
 */

public interface Callback {
    void OnSuccess(ArrayList<Object> items);
    void onSearch(ArrayList<SearchTrack> tracks, ArrayList<SearchAlbum> albums, ArrayList<SearchArtist> artists, ArrayList<SearchVideo> videos, ArrayList<SearchRadio> radios);
    void onArtistImages(ArrayList<SearchImage> searchImages);
    void onAlbumImages(ArrayList<SearchImage> searchImages);
    void onLyrics(String text);
    void onRadios(ArrayList<SearchRadio> searchRadios);
    void OnFailure(String message);
}
