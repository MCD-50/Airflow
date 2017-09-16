package com.airstem.airflow.ayush.airflow.events.home;

import com.airstem.airflow.ayush.airflow.model.home.DiscoverItem;
import com.airstem.airflow.ayush.airflow.model.search.SearchAlbum;
import com.airstem.airflow.ayush.airflow.model.search.SearchArtist;
import com.airstem.airflow.ayush.airflow.model.search.SearchTrack;

/**
 * Created by mcd-50 on 5/9/17.
 */

public interface DiscoverListener {
    void onMoreClick();
    void onClick(DiscoverItem discoverItem);
}
