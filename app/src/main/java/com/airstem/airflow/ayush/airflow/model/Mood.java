package com.airstem.airflow.ayush.airflow.model;

/**
 * Created by ayush on 06-10-16.
 */
public class Mood extends Base {

    public String Name;
    public Integer Image;
    public Integer Color;

    public Mood(String name, Integer image, Integer color){
        Name = name;
        Image = image;
        Color = color;
    }
}
