package com.airstem.airflow.ayush.airflow.helpers.collection;

/**
 * Created by mcd-50 on 13/7/17.
 */

public class CollectionConstant {

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
    public static final String[] COLLECTION_DEFAULT_PLAYLISTS = {
            "Recent",
            "Most Played",
            "Last Added"
    };

    //intent constants
    public static final String SHARED_PASSING_COLLECTION_TRACK = "sharedPassingCollectionTrack";
    public static final String SHARED_PASSING_COLLECTION_ARTIST = "sharedPassingCollectionArtist";
    public static final String SHARED_PASSING_COLLECTION_VIDEO = "sharedPassingCollectionVideo";
    public static final String SHARED_PASSING_COLLECTION_PLAYLIST = "sharedPassingCollectionPlaylist";
    public static final String SHARED_PASSING_SEARCH_ALBUM = "sharedPassingSearchAlbum";
    public static final String SHARED_PASSING_SEARCH_ARTIST = "sharedPassingSearchArtist";

}
