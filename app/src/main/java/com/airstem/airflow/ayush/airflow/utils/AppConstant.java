package com.airstem.airflow.ayush.airflow.utils;
/**
 * Created by ayush on 07-10-16.
 */
public class AppConstant {
    public static String CLIENT_ID_1 = "ab8a436c433184c8d74f58e7a0d83178";
    public static String CLIENT_ID_2 = "e73b9ca3aefe78a9ae55560b223b682f";
    public static String BASE_SOUND_CLOUD_URL="https://api-v2.soundcloud.com/charts?kind=";
    public static String KIND_TRENDING="trending";
    public static String KIND_TOP="top";
    public static String YOUTUBE_API_ID_1 = "AIzaSyAcqlxfGVKYkgSJoW0DiW8-AQZAj7jud8w";
    public static String PLAYBACK_LAST_URL= "/stream?client_id=" + CLIENT_ID_1;
    public static String SHARED_PREFERENCES_KEY = "com.ayush.airflow.KEY";
    public static String LAST_FM_KEY="a2be27fc57d01bb6c2f982cf639df152";
    public static String HALFKEY="http://ws.audioscrobbler.com/2.0/?method=artist.getinfo&artist=";
    public static String ENDKEY="&api_key=a2be27fc57d01bb6c2f982cf639df152&format=json";
    //t500x500 crop(400*400) t300x300 large


    public static String getArtistArtwork(String artistName){
        return HALFKEY + artistName + ENDKEY;
    }

    public static String getQ(String kind, String genres,String id ,int count, int offset){

         StringBuilder sBuider = new StringBuilder();
         sBuider.append(BASE_SOUND_CLOUD_URL).append(kind).append("&genre=").append(genres).
                 append("&client_id=").append(id).append("&limit=").append(count).append("&offset=").append(offset);
         return sBuider.toString();
    }

    public static String getCropUrl(String url){
        return url.replace("large", "crop");
    }

    public static String getBigUrl(String url){
        return url.replace("large", "t500x500");
    }
}
