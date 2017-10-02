
package com.airstem.airflow.ayush.airflow.helpers.collection;
import java.util.ArrayList;

/**
 * Created by mcd-50 on 13/7/17.
 */

public class CollectionConstant {


    //activity constant
    public static final long FIND_SUGGESTION_SIMULATED_DELAY = 250;

    //internet constants
    //https://airstem.herokuapp.com
    public static final String SERVER_BASE = "https://airstem.herokuapp.com";
    public static final String MATCH_BASE = "http://192.168.3.100:2000";
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
    public static final String COLLECTION_LOCAL_VIDEO_ARTWORK_BASE = "content://media/external/images/media";

    //collection constants
    public static final ArrayList<String> COLLECTION_DEFAULT_PLAYLISTS = new ArrayList<String>() {{
            add("Recent");
            add("Most Played");
            add("Last Added");
    }};

    //intent constants
    public static final String SHARED_PASSING_COLLECTION_TRACK = "sharedPassingCollectionTrack";
    public static final String SHARED_PASSING_COLLECTION_TRACK_LOCAL_ID = "sharedPassingCollectionTrackLocalId";
    public static final String SHARED_PASSING_COLLECTION_ARTIST_LOCAL_ID = "sharedPassingCollectionArtistLocalId";
    public static final String SHARED_PASSING_COLLECTION_VIDEO = "sharedPassingCollectionVideo";
    public static final String SHARED_PASSING_COLLECTION_PLAYLIST_TITLE = "sharedPassingCollectionPlaylistLocalId";
    public static final String SHARED_PASSING_SEARCH_ALBUM = "sharedPassingSearchAlbum";
    public static final String SHARED_PASSING_SEARCH_ARTIST = "sharedPassingSearchArtist";
    public static final String SHARED_PASSING_SEARCH_TEXT = "sharedPassingSearchText";

    //store constants
    public static final String STORE_FIRST_RUN = "storeFirstRun";
    public static final String STORE_EMAIL = "storeEmail";
    public static final String STORE_IS_LOGGED = "storeIsLogged";

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




    //option contant
    public static final String PLAY_TRACK_OPTION = "Play track";
    public static final String PLAY_VIDEO_OPTION = "Play video";
    public static final String START_RADIO_OPTION = "Start radio";
    public static final String STREAM_TRACK_OPTION = "Stream track";
    public static final String STREAM_VIDEO_OPTION = "Stream video";
    public static final String SAVE_TRACK_OPTION = "Save track";
    public static final String SHUFFLE_TRACKS_OPTION = "Shuffle tracks";
    public static final String ADD_TO_PLAYLIST_OPTION = "Add to playlist";
    public static final String ADD_TO_FAV_OPTION = "Add to fav";
    public static final String REMOVE_FROM_FAV_OPTION = "Remove from fav";
    public static final String REMOVE_FROM_PLAYLIST_OPTION = "Remove from playlist";
    public static final String REMOVE_FROM_COLLECTION_OPTION = "Remove from collection";
    public static final String DELETE_OPTION = "Delete";
    public static final String RE_MATCH_OPTION = "Re match track";
    public static final String MANUAL_MATCH_OPTION = "Manual match";

    //speech text
    public static final String SPEECH_TEXT = "You say we play" + "\n" + "Try saying like play taylor swift...";
    public static final int SPEECH_CODE = 111;
    public static final int NETWORK_CODE = 112;


    public static final ArrayList<String> COLOR_ARRAY = new ArrayList<String>(){{
        add("#3f869b");
        add("#9f2be3");
        add("#52655b");
        add("#ee9a00");
        add("#ff4500");
        add("#3f969b");
        add("#00868b");
    }};

    public static final String[] COLLECTION_TRACK_OPTIONS = {"Play track", "Delete"};
    public static final String[] PLAYLIST_TRACK_OPTIONS = {"Play track", "Remove from playlist"};
    public static final String[] COLLECTION_ARTIST_OPTIONS = {"Shuffle artist", "Add to playlist", "Delete"};
    public static final String[] COLLECTION_VIDEO_OPTIONS = {"Play video", "Delete"};
    public static final String[] COLLECTION_PLAYLIST_OPTIONS = {"Shuffle playlist", "Delete"};
    public static final String[] COLLECTION_FAV_TRACK_OPTIONS = {"Play track", "Remove from fav", "Delete"};
    public static final String[] COLLECTION_FAV_VIDEO_OPTIONS =  {"Play video", "Remove from fav", "Delete"};
    public static final String[] COLLECTION_FAV_RADIO_OPTIONS = {"Play radio", "Remove from fav"};
}

