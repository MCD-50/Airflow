package com.airstem.airflow.ayush.airflow.helpers.collection;

import com.airstem.airflow.ayush.airflow.model.collection.CollectionArtist;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionPlaylist;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionRadio;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionTrack;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionVideo;

import java.util.ArrayList;

/**
 * Created by mcd-50 on 14/7/17.
 */

public class CollectionHelper {

    public static int getTrackIndex(ArrayList<CollectionTrack> items, String databaseId){
        int index = -1;
        for(int i = 0; i<items.size(); i++){
            if(items.get(i).getDatabaseId() == databaseId){
                index = i;
                break;
            }

        }
        return index;
    }

    public static int getArtistIndex(ArrayList<CollectionArtist> items, String databaseId){
        int index = -1;
        for(int i = 0; i<items.size(); i++){
            if(items.get(i).getDatabaseId() == databaseId){
                index = i;
                break;
            }

        }
        return index;
    }


    public static int getPlaylistIndex(ArrayList<CollectionPlaylist> items, String databaseId){
        int index = -1;
        for(int i = 0; i<items.size(); i++){
            if(items.get(i).getDatabaseId() == databaseId){
                index = i;
                break;
            }

        }
        return index;
    }

    public static int getRadioIndex(ArrayList<CollectionRadio> items, String databaseId){
        int index = -1;
        for(int i = 0; i<items.size(); i++){
            if(items.get(i).getDatabaseId() == databaseId){
                index = i;
                break;
            }

        }
        return index;
    }

    public static int getVideoIndex(ArrayList<CollectionVideo> items, String databaseId){
        int index = -1;
        for(int i = 0; i<items.size(); i++){
            if(items.get(i).getDatabaseId() == databaseId){
                index = i;
                break;
            }

        }
        return index;
    }



}
