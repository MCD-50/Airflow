package com.airstem.airflow.ayush.airflow.events.Search;

import com.airstem.airflow.ayush.airflow.model.search.SearchRadio;
import com.airstem.airflow.ayush.airflow.model.search.SearchVideo;

/**
 * Created by mcd-50 on 10/7/17.
 */

public interface SearchVideoListener {
    void onVideoClick(SearchVideo searchVideo);
}
