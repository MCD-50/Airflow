package com.airstem.airflow.ayush.airflow.events.Search;

import com.airstem.airflow.ayush.airflow.model.search.SearchArtist;
import com.airstem.airflow.ayush.airflow.model.search.SearchArtistInfoAlbum;
import com.airstem.airflow.ayush.airflow.model.search.SearchArtistInfoTrack;

/**
 * Created by mcd-50 on 10/7/17.
 */

public interface SearchArtistListener {
    void onArtistClick(SearchArtist searchArtist);
    void onArtistTrackClick(SearchArtistInfoTrack searchArtistInfoTrack);
    void onArtistAlbumClick(SearchArtistInfoAlbum searchArtistInfoAlbum);
}
