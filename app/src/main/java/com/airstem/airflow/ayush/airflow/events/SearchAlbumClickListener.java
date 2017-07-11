package com.airstem.airflow.ayush.airflow.events;

import com.airstem.airflow.ayush.airflow.model.search.SearchAlbum;

/**
 * Created by mcd-50 on 10/7/17.
 */

public interface SearchAlbumClickListener {
    void onItemClick(SearchAlbum searchAlbum);
}
