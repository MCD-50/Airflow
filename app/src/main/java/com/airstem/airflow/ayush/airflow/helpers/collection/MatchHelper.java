package com.airstem.airflow.ayush.airflow.helpers.collection;

import android.content.Context;

import com.airstem.airflow.ayush.airflow.events.volly.Callback;
import com.airstem.airflow.ayush.airflow.helpers.database.DatabaseHelper;
import com.airstem.airflow.ayush.airflow.helpers.internet.InternetHelper;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionTrack;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionVideo;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionVideoSize;
import com.airstem.airflow.ayush.airflow.model.search.SearchAlbum;
import com.airstem.airflow.ayush.airflow.model.search.SearchArtist;
import com.airstem.airflow.ayush.airflow.model.search.SearchImage;
import com.airstem.airflow.ayush.airflow.model.search.SearchPaging;
import com.airstem.airflow.ayush.airflow.model.search.SearchRadio;
import com.airstem.airflow.ayush.airflow.model.search.SearchTrack;
import com.airstem.airflow.ayush.airflow.model.search.SearchVideo;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmList;

/**
 * Created by mcd-50 on 2/10/17.
 */

public class MatchHelper {

    private Realm mRealm;
    private InternetHelper internetHelper;

    public MatchHelper(Context context, Realm realm){
        this.mRealm = realm;
        internetHelper = new InternetHelper(context);
    }

    public void matchTrack(final CollectionTrack collectionTrack) throws JSONException {
        internetHelper.matchTrack(collectionTrack.getTitle(), collectionTrack.getArtistName(), collectionTrack.getAlbumName(), new Callback() {
            @Override
            public void OnSuccess(ArrayList<Object> items) {

            }

            @Override
            public void onSearch(ArrayList<SearchTrack> tracks, ArrayList<SearchAlbum> albums, ArrayList<SearchArtist> artists, ArrayList<SearchVideo> videos, ArrayList<SearchRadio> radios) {

            }

            @Override
            public void onArtistImages(ArrayList<SearchImage> searchImages) {

            }

            @Override
            public void onAlbumImages(ArrayList<SearchImage> searchImages) {

            }

            @Override
            public void onLyrics(String text) {

            }

            @Override
            public void onMatch(final String downloadUrl) {
                collectionTrack.setTrackOnlineUrl(downloadUrl);
                collectionTrack.setIsMatched(true);
                collectionTrack.setIsMatchError(false);
                DatabaseHelper.createOrUpdateTracks(mRealm, new ArrayList<CollectionTrack>(){{add(collectionTrack);}});
            }

            @Override
            public void onVideoMatch(ArrayList<JSONObject> videoMatchUrl) {

            }

            @Override
            public void onSuccess(ArrayList<SearchTrack> searchTracks, ArrayList<SearchArtist> searchArtists, ArrayList<SearchAlbum> searchAlbums, SearchPaging searchPaging) {

            }

            @Override
            public void onTracks(ArrayList<SearchTrack> searchTracks, String nextPage) {

            }

            @Override
            public void onVideos(ArrayList<SearchVideo> searchVideos, String nextPage) {

            }

            @Override
            public void onRadios(ArrayList<SearchRadio> searchRadios) {

            }

            @Override
            public void OnFailure(String message) {
                collectionTrack.setIsMatched(false);
                collectionTrack.setIsMatchError(true);
                DatabaseHelper.createOrUpdateTracks(mRealm, new ArrayList<CollectionTrack>(){{add(collectionTrack);}});

            }
        });
    }

    public void matchVideo(final CollectionVideo collectionVideo) throws JSONException {
        String[] itemIds = {collectionVideo.getInternetId()};
        internetHelper.matchVideo(collectionVideo.getTitle(), itemIds, new Callback() {
            @Override
            public void OnSuccess(ArrayList<Object> items) {

            }

            @Override
            public void onSearch(ArrayList<SearchTrack> tracks, ArrayList<SearchAlbum> albums, ArrayList<SearchArtist> artists, ArrayList<SearchVideo> videos, ArrayList<SearchRadio> radios) {

            }

            @Override
            public void onArtistImages(ArrayList<SearchImage> searchImages) {

            }

            @Override
            public void onAlbumImages(ArrayList<SearchImage> searchImages) {

            }

            @Override
            public void onLyrics(String text) {

            }

            @Override
            public void onMatch(final String downloadUrl) {

            }

            @Override
            public void onVideoMatch(final ArrayList<JSONObject> videoMatchUrl) {
                for(JSONObject x : videoMatchUrl){
                    CollectionVideoSize collectionVideoSize = new CollectionVideoSize();
                    try {
                        collectionVideoSize.setUrl(x.getString("download_url"));
                        collectionVideoSize.setHeight( Integer.parseInt(x.getString("height")));
                        collectionVideoSize.setWidth( Integer.parseInt(x.getString("width")));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    collectionVideo.getVideoSize().add(collectionVideoSize);
                }
                if(collectionVideo.getVideoSize().size() > 0){
                    collectionVideo.setIsMatchError(false);
                    collectionVideo.setIsMatched(true);
                }
                DatabaseHelper.createOrUpdateVideos(mRealm, new ArrayList<CollectionVideo>(){{add(collectionVideo);}});
            }

            @Override
            public void onSuccess(ArrayList<SearchTrack> searchTracks, ArrayList<SearchArtist> searchArtists, ArrayList<SearchAlbum> searchAlbums, SearchPaging searchPaging) {

            }

            @Override
            public void onTracks(ArrayList<SearchTrack> searchTracks, String nextPage) {

            }

            @Override
            public void onVideos(ArrayList<SearchVideo> searchVideos, String nextPage) {

            }

            @Override
            public void onRadios(ArrayList<SearchRadio> searchRadios) {

            }

            @Override
            public void OnFailure(String message) {
                collectionVideo.setIsMatched(false);
                collectionVideo.setIsMatchError(true);
                DatabaseHelper.createOrUpdateVideos(mRealm, new ArrayList<CollectionVideo>(){{add(collectionVideo);}});
            }
        });
    }
}
