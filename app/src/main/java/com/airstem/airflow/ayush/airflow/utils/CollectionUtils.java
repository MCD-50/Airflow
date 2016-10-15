package com.airstem.airflow.ayush.airflow.utils;

import com.airstem.airflow.ayush.airflow.model.Track;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by ayush on 07-10-16.
 */
public class CollectionUtils {


    //shuffle list
    public static ArrayList<Track> shuffleMyList(ArrayList<Track> tracks){
        long seed = System.nanoTime();
        Collections.shuffle(tracks, new Random(seed));
        return tracks;
    }

    //capitalize string
    public static String capitalizeThisString(String string){
        StringBuffer baf = new StringBuffer();

        //make it lower case for better results

        String[] array = string.toLowerCase().split(" ");
        for (String str : array){
            char[] charArray = str.trim().toCharArray();
            charArray[0] = Character.toUpperCase(charArray[0]);
            str = new String(charArray);

            baf.append(str).append(" ");
        }

        return baf.toString();
    }

    public static int getIndexFromCountryName(String value){
        for(int i = 0; i < countries.length; i++){
            if(countries[i].equalsIgnoreCase(value)){
                return i;
            }
        }
        return 0;
    }

    public static String getCountryFromIndex(int index){
        return countries[index];
    }
    //regional code list
    public static String[] regionCode = new String[]{
            "Worldwide",
            "AU",
            "AT",
            "BE",
            "BR",
            "CA",
            "DK",
            "FI",
            "FR",
            "DE",
            "IN",
            "IL",
            "IT",
            "KW",
            "MY",
            "NP",
            "NO",
            "RU",
            "SG",
            "ZA",
            "CH",
            "GB",
            "US"
    };

    public static String[] countries = new String[]{
        "Worldwide","Australia","Austria","Belgium","Brazil","Canada","Denmark"
            ,"Finland","France","Germany","India","Israel",
            "Italy","Kuwait","Malaysia","Nepal","Norway","Russia","Singapore","South Africa",
            "Switzerland","United Kingdom","United States"
    };


   /*
     <string-array name="list_of_countries">
        <item>Worldwide</item>
        <item>Australia</item>
        <item>Austria</item>
        <item>Belgium</item>
        <item>Brazil</item>
        <item>Canada</item>
        <item>Denmark</item>
        <item>Finland</item>
        <item>France</item>
        <item>Germany</item>
        <item>India</item>
        <item>Israel</item>
        <item>Italy</item>
        <item>Kuwait</item>
        <item>Malaysia</item>
        <item>Nepal</item>
        <item>Norway</item>
        <item>Russia</item>
        <item>Singapore</item>
        <item>South Africa</item>
        <item>Switzerland</item>
        <item>United Kingdom</item>
        <item>United States</item>
    </string-array>

    <string-array name="list_of_iso">
    <item>XA</item>
    <item>AU</item>
    <item>AT</item>
    <item>BE</item>
    <item>BR</item>
    <item>CA</item>
    <item>DK</item>
    <item>FI</item>
    <item>FR</item>
    <item>DE</item>
    <item>IN</item>
    <item>IL</item>
    <item>IT</item>
    <item>KW</item>
    <item>MY</item>
    <item>NP</item>
    <item>NO</item>
    <item>RU</item>
    <item>SG</item>
    <item>ZA</item>
    <item>CH</item>
    <item>GB</item>
    <item>US</item>
    </string-array>





    */

}
