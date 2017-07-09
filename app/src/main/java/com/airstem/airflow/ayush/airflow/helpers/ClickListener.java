package com.airstem.airflow.ayush.airflow.helpers;

import com.airstem.airflow.ayush.airflow.model.Artist;
import com.airstem.airflow.ayush.airflow.model.Playlist;
import com.airstem.airflow.ayush.airflow.model.Track;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionArtist;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionPlaylist;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionTrack;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionVideo;
import com.airstem.airflow.ayush.airflow.model.search.SearchAlbum;
import com.airstem.airflow.ayush.airflow.model.search.SearchAlbumInfoTrack;
import com.airstem.airflow.ayush.airflow.model.search.SearchArtist;
import com.airstem.airflow.ayush.airflow.model.search.SearchArtistInfoAlbum;
import com.airstem.airflow.ayush.airflow.model.search.SearchArtistInfoTrack;
import com.airstem.airflow.ayush.airflow.model.search.SearchRadio;
import com.airstem.airflow.ayush.airflow.model.search.SearchTrack;
import com.airstem.airflow.ayush.airflow.model.search.SearchVideo;

/**
 * Created by ayush on 16-11-16.
 */
public interface ClickListener {
    void OnItemClick(Track track);
    void OnItemClick(Artist artist);
    void OnItemClick(Playlist playlist);


    void OnItemClick(CollectionArtist collectionArtist);
    void OnItemClick(CollectionPlaylist collectionPlaylist);
    void OnItemClick(CollectionTrack collectionTrack);
    void OnItemClick(CollectionVideo collectionVideo);


    void OnItemClick(SearchAlbum searchAlbum);
    void OnItemClick(SearchArtist searchArtist);
    void OnItemClick(SearchRadio searchRadio);
    void OnItemClick(SearchVideo searchVideo);
    void OnItemClick(SearchTrack searchTrack);

    void OnItemClick(SearchAlbumInfoTrack searchAlbumInfoTrack);
    void OnItemClick(SearchArtistInfoTrack searchArtistInfoTrack);
    void OnItemClick(SearchArtistInfoAlbum searchArtistInfoAlbum);
}
