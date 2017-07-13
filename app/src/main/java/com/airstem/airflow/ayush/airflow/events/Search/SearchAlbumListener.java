package com.airstem.airflow.ayush.airflow.events.Search;

import com.airstem.airflow.ayush.airflow.model.search.SearchAlbum;
import com.airstem.airflow.ayush.airflow.model.search.SearchAlbumInfoTrack;

/**
 * Created by mcd-50 on 10/7/17.
 */

public interface SearchAlbumListener {
    void onAlbumClick(SearchAlbum searchAlbum);
    void onAlbumTrackClick(SearchAlbumInfoTrack searchAlbumInfoTrack);
}
