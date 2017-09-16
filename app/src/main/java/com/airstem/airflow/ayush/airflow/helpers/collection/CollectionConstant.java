package com.airstem.airflow.ayush.airflow.helpers.collection;

import java.util.ArrayList;

/**
 * Created by mcd-50 on 13/7/17.
 */

public class CollectionConstant {


    //activity constant
    public static final long FIND_SUGGESTION_SIMULATED_DELAY = 250;


    //internet constants
    public static final String SERVER_BASE = "http://192.168.43.44:2000";
    public static final String ENDPOINT_SEARCH = "/search";
    public static final String ENDPOINT_SEARCH_ALBUMS = "/searchAlbums";
    public static final String ENDPOINT_SEARCH_ARTISTS = "/searchArtists";
    public static final String ENDPOINT_SEARCH_ALBUM_INFO_DEEZER = "/albumInfoDeezer";
    public static final String ENDPOINT_SEARCH_ARTIST_INFO_DEEZER = "/artistInfoDeezer";
    public static final String ENDPOINT_SEARCH_ALBUM_INFO_LAST = "/albumInfoLastFm";
    public static final String ENDPOINT_SEARCH_ARTIST_INFO_LAST = "/artistInfoLastFm";
    public static final String ENDPOINT_SEARCH_ARTIST_ALBUMS = "/searchArtistAlbums";
    public static final String ENDPOINT_SEARCH_ARTIST_TRACKS = "/searchArtistTracks";
    public static final String ENDPOINT_SEARCH_TOP_DATA = "/searchTopData";
    public static final String ENDPOINT_SEARCH_TRENDING_DATA = "/searchTrendingData";
    public static final String ENDPOINT_SEARCH_SIMILAR_DATA = "/searchSimilarData";
    public static final String ENDPOINT_SEARCH_NEW_DATA = "/searchNewData";
    public static final String ENDPOINT_SEARCH_ALBUM_ARTWORK = "/albumArtwork";
    public static final String ENDPOINT_SEARCH_ARTIST_ARTWORK = "/artistArtwork";
    public static final String ENDPOINT_SEARCH_TRACK_ARTWORK = "/trackArtwork";
    public static final String ENDPOINT_SEARCH_LYRICS = "/searchLyrics";
    public static final String ENDPOINT_MATCH = "/match";

    //database constants
    public static final String COLLECTION_LOCAL_TRACK_ARTWORK_BASE = "content://media/external/audio/albumart";
    public static final String COLLECTION_LOCAL_VIDEO_ARTWORK_BASE = "content://media/external/video/media";

    //collection constants
    public static final ArrayList<String> COLLECTION_DEFAULT_PLAYLISTS = new ArrayList<String>() {{
            add("Recent");
            add("Most Played");
            add("Last Added");
    }};

    //intent constants
    public static final String SHARED_PASSING_COLLECTION_TRACK = "sharedPassingCollectionTrack";
    public static final String SHARED_PASSING_COLLECTION_ARTIST = "sharedPassingCollectionArtist";
    public static final String SHARED_PASSING_COLLECTION_VIDEO = "sharedPassingCollectionVideo";
    public static final String SHARED_PASSING_COLLECTION_PLAYLIST = "sharedPassingCollectionPlaylist";
    public static final String SHARED_PASSING_SEARCH_ALBUM = "sharedPassingSearchAlbum";
    public static final String SHARED_PASSING_SEARCH_ARTIST = "sharedPassingSearchArtist";


    //player constant
    public static final String PLAYER_PAUSE = "playerPause";
    public static final String PLAYER_PLAY = "playerPlay";
    public static final String PLAYER_NEXT = "playerNext";
    public static final String PLAYER_PREV = "playerPrev";
    public static final String PLAYER_WIFI="playerWifi";
    public static final String PLAYER_TRACK_CHANGE = "playerTrackChange";
    public static final String PLAYER_CURRENT_TRACK_ID = "playerCurrentTrackId";
    public static final String PLAYER_CURRENT_INFO = "playerCurrentInfo";
    public static final String PLAYER_IS_PLAYER_ACTIVE = "playerActive";
    public static final String PLAYER_ACTION_INTENT_FILTER = "android.intent.action.PLAYER";



    public static String SPEECH_TEXT = "You say we play" + "\n" + "Try saying like play taylor swift...";
    public static int SPEECH_CODE = 111;
    public static int NETWORK_CODE = 112;

    //request permissions
    public static int PERMISSION_REQUEST = 113;
}

