package com.airstem.airflow.ayush.airflow.events.search;

import com.airstem.airflow.ayush.airflow.model.search.SearchAlbum;
import com.airstem.airflow.ayush.airflow.model.search.SearchArtist;
import com.airstem.airflow.ayush.airflow.model.search.SearchTrack;

/**
 * Created by mcd-50 on 10/7/17.
 */

public interface SearchArtistListener {
    void onArtistClick(SearchArtist searchArtist);
    void onArtistTrackClick(SearchTrack searchTrack);
    void onArtistAlbumClick(SearchAlbum searchAlbum);
}
