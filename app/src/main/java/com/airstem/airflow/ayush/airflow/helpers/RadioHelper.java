package com.airstem.airflow.ayush.airflow.helpers;

import com.airstem.airflow.ayush.airflow.R;
import com.airstem.airflow.ayush.airflow.model.PlayMode;
import com.airstem.airflow.ayush.airflow.model.Track;

import java.util.ArrayList;

/**
 * Created by ayush on 16-11-16.
 */
public class RadioHelper {

    private String linkPre = "http://prclive1.listenon.in:";
    private String linkPost = "/listen.pls";


    String[] names = {"Airflow India Mix", "Radio City Hindi", "Radio City International", "Radio City Dance"
                      ,"Radio City Fun", "Radio City Freedom", "Radio City Pop", "Radio City Fusion", "Radio City Classic"
                      ,"Radio City Love", "Hindi Hits", "Hindi Evergreen Hits"};

    String[] link = {"http://radio.dj-gaurav.com:8035/" ,
                     linkPre + "9960/" ,
                     linkPre + "9918/" ,
                     linkPre + "8994/" ,
                     linkPre + "9998/" ,
                     linkPre + "9978/" ,
                     linkPre + "9910/" ,
                     linkPre + "8992/" ,
                     linkPre + "8996/" ,
                     linkPre + "8808/" ,
                     "http://123.176.41.8:8056/" ,
                     "http://50.7.77.114:8296/"};


    /*Integer[] link = {R.raw.radio_city_hindi,
            R.raw.radio_city_international,
            R.raw.radio_city_dance,
            R.raw.radio_city_fun,
            R.raw.radio_city_freedom,
            R.raw.radio_city_pop,
            R.raw.radio_city_fusion,
            R.raw.radio_city_classic,
            R.raw.radio_city_love,
            R.raw.hindi_hits,
            R.raw.airflow_india_mix,
            R.raw.hindi_evergreen_hits};
*/

    Integer[] artworks = {R.drawable.top_icon,
            R.drawable.radio_city_hindi,
            R.drawable.radio_city_international,
            R.drawable.radio_city_dance,
            R.drawable.radio_city_fun,
            R.drawable.radio_city_freedom,
            R.drawable.radio_city_pop,
            R.drawable.radio_city_fusion,
            R.drawable.radio_city_classic,
            R.drawable.love_icon,
            R.drawable.hindi_hits,
            R.drawable.radio_city_evergreen};


    public ArrayList<Track> getRadioArrayList(){
        ArrayList<Track> radioArrayList = new ArrayList<>();
        for(int i = 0 ;i < link.length; i++){
            Track t = new Track(names[i] ,names[i], link[i].trim(), PlayMode.RADIO);
            t.setMoodName("Radio");
            t.setRadioArtwork(artworks[i]);
            radioArrayList.add(i, t);
        }

        return radioArrayList;
    }

}
