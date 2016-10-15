package com.airstem.airflow.ayush.airflow.helpers;

import com.airstem.airflow.ayush.airflow.model.Mood;
import com.airstem.airflow.ayush.airflow.model.Track;
import com.airstem.airflow.ayush.airflow.utils.AppConstant;
import com.airstem.airflow.ayush.airflow.utils.CollectionUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ayush on 07-10-16.
 */
public class JSONParseHelper {

    private String mJsonString;

    public void setJSONString(String jsonString){
        mJsonString = jsonString;
    }

    public ArrayList<Track> getTrack(String mood){

        ArrayList<Track> tracks = new ArrayList<Track>();
        try {
            String id, title, url, artwork, streamable;
            JSONObject obj = new JSONObject(mJsonString);
            JSONArray array = obj.getJSONArray("collection");
            for (int i = 0; i < array.length(); i++)
            {

                JSONObject newObj = array.getJSONObject(i).getJSONObject("track");

                id = newObj.getString("id");
                try{
                    artwork = newObj.getString("artwork_url");
                    artwork = AppConstant.getBigUrl(artwork);
                }
                catch(Exception myex){
                    artwork = "";
                }

                try{
                    title = newObj.getString("title");
                }
                catch(Exception myex){
                    title = "Unknown Track";
                }

                try{
                    url = newObj.getString("uri");
                }
                catch(Exception myex){
                    url = "";
                }

                try{
                    streamable = newObj.getString("streamable");
                    if(streamable != null && (streamable.contains("true") || streamable.contains("false")))
                        streamable = streamable;
                    else
                        streamable = "false";
                }
                catch(Exception myex){
                    streamable = "false";
                }

                try{
                    title = CollectionUtils.capitalizeThisString(title);
                    mood = CollectionUtils.capitalizeThisString(mood);
                }catch(Exception exception){

                }

                //String artist = array.getJSONObject(i).getString("label_name");
                Track t = new Track(id, title, url, Boolean.valueOf(streamable));
                t.setMoodName(mood);
                t.setArtwork(artwork);
                tracks.add(t);


            }

        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        return tracks;
    }
}
