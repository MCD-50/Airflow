package com.airstem.airflow.ayush.airflow.model;

/**
 * Created by ayush on 10-10-16.
 */
public class Params {
    private String Id;
    private String Artwork;

    public Params(String id, String artwork){
        Id = id;
        Artwork = artwork;
    }

    public String getArtwork() {
        return Artwork;
    }

    public String getId() {
        return Id;
    }

}
