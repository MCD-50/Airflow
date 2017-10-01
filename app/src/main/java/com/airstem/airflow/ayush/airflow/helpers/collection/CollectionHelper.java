package com.airstem.airflow.ayush.airflow.helpers.collection;

import android.text.TextUtils;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionArtist;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionPlaylist;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionRadio;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionTrack;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionVideo;
import com.airstem.airflow.ayush.airflow.model.realms.RealmString;
import com.airstem.airflow.ayush.airflow.model.search.SearchRadio;
import com.airstem.airflow.ayush.airflow.model.search.SearchTrack;
import com.airstem.airflow.ayush.airflow.model.search.SearchVideo;

import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by mcd-50 on 14/7/17.
 */

public class CollectionHelper {

    public static String getSweetString(String modifiedOn){
        if(!TextUtils.isEmpty(modifiedOn)){
            String[] parts = modifiedOn.split(" ");
            if(parts.length > 2){
                return parts[0] + " " + parts[1] + " " + parts[2];
            }
            return "Unknown date";
        }
        return "Unknown date";
    }

    public static int getItemIndex(CollectionPlaylist collectionPlaylist, String id){
        int index = -1;
        for(int i = 0; i<collectionPlaylist.getPlaylists().size(); i++){
            if(String.valueOf(collectionPlaylist.getPlaylists().get(i).getString()).equals(id)){
                index = i;
                break;
            }
        }
        return index;
    }

    public static int getItemIndex(RealmResults<CollectionTrack> collectionTracks, String id){
        int index = -1;
        for(int i = 0; i< collectionTracks.size(); i++){
            if(String.valueOf(collectionTracks.get(i).getLocalId()).equals(id)){
                index = i;
                break;
            }
        }
        return index;
    }


    public static ArrayList<String> prepareOptionFromTrack(CollectionTrack collectionTrack){
        ArrayList<String> options = new ArrayList<String>();
        if(!collectionTrack.getIsOffline() && !collectionTrack.getIsMatched()){
            options.add(CollectionConstant.MANUAL_MATCH_OPTION);
            options.add(CollectionConstant.DELETE_OPTION);
            return options;
        };

        if(collectionTrack.getIsOffline()){
            options.add(CollectionConstant.PLAY_TRACK_OPTION);
            options.add(CollectionConstant.ADD_TO_PLAYLIST_OPTION);
            if(collectionTrack.getIsFav()){
                options.add(CollectionConstant.REMOVE_FROM_FAV_OPTION);
            }else{
                options.add(CollectionConstant.ADD_TO_FAV_OPTION);
            }
            options.add(CollectionConstant.RE_MATCH_OPTION);
            options.add(CollectionConstant.DELETE_OPTION);
        }else if(collectionTrack.getIsMatched()){
            options.add(CollectionConstant.STREAM_TRACK_OPTION);
            options.add(CollectionConstant.RE_MATCH_OPTION);
            options.add(CollectionConstant.DELETE_OPTION);
        }
        return options;
    }

    public static ArrayList<String> prepareOptionFromVideo(CollectionVideo collectionVideo){
        ArrayList<String> options = new ArrayList<String>();
        if(!collectionVideo.getIsOffline() && !collectionVideo.getIsMatched()){
            options.add(CollectionConstant.DELETE_OPTION);
            return options;
        }

        if(collectionVideo.getIsOffline()){
            options.add(CollectionConstant.PLAY_VIDEO_OPTION);
            if(collectionVideo.getIsFav()){
                options.add(CollectionConstant.REMOVE_FROM_FAV_OPTION);
            }else{
                options.add(CollectionConstant.ADD_TO_FAV_OPTION);
            }
            options.add(CollectionConstant.DELETE_OPTION);
        }else if(collectionVideo.getIsMatched()){
            options.add(CollectionConstant.STREAM_VIDEO_OPTION);
            options.add(CollectionConstant.DELETE_OPTION);
        }
        return options;
    }

    public static ArrayList<String> prepareOptionFromRadio(CollectionRadio collectionRadio){
        ArrayList<String> options = new ArrayList<String>();
        options.add(CollectionConstant.START_RADIO_OPTION);
        options.add(CollectionConstant.REMOVE_FROM_FAV_OPTION);
        return options;
    }

    public static ArrayList<String> prepareOptionFromArtist(CollectionArtist collectionArtist){
        ArrayList<String> options = new ArrayList<String>();

        options.add(CollectionConstant.SHUFFLE_TRACKS_OPTION);
        if(collectionArtist.getIsOffline()){
            options.add(CollectionConstant.ADD_TO_PLAYLIST_OPTION);
            options.add(CollectionConstant.DELETE_OPTION);
        }else{
            options.add(CollectionConstant.DELETE_OPTION);
        }
        return options;
    }

    public static ArrayList<String> prepareOptionFromPlaylist(CollectionPlaylist collectionPlaylist){
        ArrayList<String> options = new ArrayList<String>();
        options.add(CollectionConstant.SHUFFLE_TRACKS_OPTION);
        options.add(CollectionConstant.DELETE_OPTION);
        return options;
    }

    public static ArrayList<String> prepareOptionFromPlaylistTrack(CollectionTrack collectionTrack){
        ArrayList<String> options = new ArrayList<String>();
        options.add(CollectionConstant.PLAY_TRACK_OPTION);
        if(collectionTrack.getIsFav()){
            options.add(CollectionConstant.REMOVE_FROM_FAV_OPTION);
        }else{
            options.add(CollectionConstant.ADD_TO_FAV_OPTION);
        }
        options.add(CollectionConstant.REMOVE_FROM_PLAYLIST_OPTION);
        return options;
    }


    public static String getLocalIdForTrack(SearchTrack searchTrack){
        return searchTrack.getAlbumName() + '-' + searchTrack.getArtistName() + '-' + searchTrack.getTitle();
    }

    public static String getLocalIdForVideo(SearchVideo searchVideo){
        return searchVideo.getAuthor() + '-' + searchVideo.getTitle();
    }

    public static String getLocalIdForArtist(SearchTrack searchTrack){
        return searchTrack.getArtistName();
    }

    public static CollectionTrack getCollectionTrack(SearchTrack searchTrack, CollectionArtist collectionArtist){
        CollectionTrack collectionTrack = new CollectionTrack();
        collectionTrack.init();
        collectionTrack.setTitle(searchTrack.getTitle());
        collectionTrack.setAlbumName(searchTrack.getAlbumName());
        collectionTrack.setArtistName(searchTrack.getArtistName());
        collectionTrack.setIsOffline(false);
        collectionTrack.setIsMatched(false);
        collectionTrack.setLocalId(getLocalIdForTrack(searchTrack));
        collectionTrack.setArtistId(collectionArtist.getId());
        if(searchTrack.getArtworkUrl().size() > 0){
            int lastIndex = searchTrack.getArtworkUrl().size() - 1;
            collectionTrack.setArtworkUrl(searchTrack.getArtworkUrl().get(lastIndex).getUri());
        }
        return  collectionTrack;
    }

    public static CollectionArtist getCollectionArtistFromTrack(SearchTrack searchTrack){
        CollectionArtist collectionArtist = new CollectionArtist();
        collectionArtist.init();
        collectionArtist.setTitle(searchTrack.getArtistName());
        collectionArtist.setIsOffline(false);
        collectionArtist.setLocalId(getLocalIdForArtist(searchTrack));
        if(searchTrack.getArtworkUrl().size() > 0){
            int lastIndex = searchTrack.getArtworkUrl().size() - 1;
            collectionArtist.setArtworkUrl(searchTrack.getArtworkUrl().get(lastIndex).getUri());
        }
        return collectionArtist;
    }

    public static CollectionRadio getCollectionRadio(SearchRadio searchRadio){
        CollectionRadio collectionRadio = new CollectionRadio();
        collectionRadio.init();
        collectionRadio.setTitle(searchRadio.getTitle());
        collectionRadio.setMaxUser(Long.parseLong(searchRadio.getUser()));
        collectionRadio.setCountry(searchRadio.getCountry());
        collectionRadio.setIsFav(true);
        RealmList<RealmString> streamUrl = new RealmList<>();
        for(String x : searchRadio.getStreamUrl()){
            streamUrl.add(new RealmString(x));
        }
        collectionRadio.setStreamUrl(streamUrl);
        return collectionRadio;
    }

    public static CollectionVideo getCollectionVideo(SearchVideo searchVideo){
        CollectionVideo collectionVideo = new CollectionVideo();
        collectionVideo.init();
        collectionVideo.setTitle(searchVideo.getTitle());
        collectionVideo.setAuthor(searchVideo.getAuthor());
        collectionVideo.setLocalId(getLocalIdForVideo(searchVideo));
        collectionVideo.setIsOffline(false);
        collectionVideo.setIsMatched(false);
        if(searchVideo.getArtworkUrl().size() > 0){
            int lastIndex = searchVideo.getArtworkUrl().size() - 1;
            collectionVideo.setArtworkUrl(searchVideo.getArtworkUrl().get(lastIndex).getUri());
        }
        return collectionVideo;
    }
}
