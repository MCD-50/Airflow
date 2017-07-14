package com.airstem.airflow.ayush.airflow.helpers.Database;

/**
 * Created by mcd-50 on 14/7/17.
 */

public class DatabaseConstant {


    public static String DATABASE_NAME = "airstemDatabase";
    public static int DATABASE_VERSION = 1;
    
    //Database tables
    public static final String TABLE_TRACK = "trackTable";
    public static final String TABLE_ARTIST = "artistTable";
    public static final String TABLE_VIDEO = "videoTable";
    public static final String TABLE_RADIO = "radioTable";
    public static final String TABLE_PLAYLIST= "playlistTable";


    //Database actions
    public static final String SPACE = " ";
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS";
    public static final String CREATE_TABLE = "CREATE TABLE";
    public static final String GET_FROM_TABLE = "SELECT  * FROM";
    public static final String COMMA = ",";


    //Database datatypes
    public static final String TYPE_TEXT = "TEXT";
    public static final String TYPE_BLOB = "BLOB";
    public static final String TYPE_INTEGER = "INTEGER";
    public static final String TYPE_PRIMARY_KEY = "PRIMARY KEY";
    public static final String TYPE_AUTOINCREMENT = "AUTOINCREMENT";

    //Database shorthand
    public static final String __ID__ = TYPE_INTEGER + SPACE + TYPE_PRIMARY_KEY + SPACE + TYPE_AUTOINCREMENT;


    //Database columns
    public static final String KEY_ID = "id";
    public static final String KEY_TITLE = "title";
    public static final String KEY_ARTWORK_URL = "artworkUrl";
    public static final String KEY_HAS_ARTWORK = "hasArtwork";
    public static final String KEY_MODIFIED_ON = "modifiedOn";
    public static final String KEY_ONLINE_URL = "onlineUrl";
    public static final String KEY_OFFLINE_URL = "offlineUrl";
    public static final String KEY_IS_OFFLINE = "isOffline";
    public static final String KEY_IS_FAV = "isFav";


    //for track
    public static final String KEY_ARTIST_NAME = "artistName";
    public static final String KEY_ALBUM_NAME = "albumName";
    public static final String KEY_LAST_PLAYED = "lastPlayed";
    public static final String KEY_PLAY_COUNT = "playCount";
    public static final String KEY_PLAYLIST_ID = "playlistId";
    public static final String KEY_ARTIST_ID = "artistId";
    

    //for artist


    //for video
    public static final String KEY_AUTHOR = "authorName";


    //for radio
    public static final String KEY_MAX_USER = "maxUser";
    public static final String KEY_STREAM_URL = "streamUrl";
    public static final String KEY_TAGS = "tags";
    public static final String KEY_COUNTRY = "country";
    public static final String KEY_COLOR = "color";


    //for playlist
    public static final String KEY_OWNER = "owner";

}
