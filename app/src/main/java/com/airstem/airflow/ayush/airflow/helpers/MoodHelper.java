package com.airstem.airflow.ayush.airflow.helpers;

import android.app.Activity;
import android.content.Context;

import com.airstem.airflow.ayush.airflow.R;
import com.airstem.airflow.ayush.airflow.model.Mood;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ayush on 06-10-16.
 */
public class MoodHelper {

    Mood[] moods;
    public MoodHelper(){
        moods = new Mood[] {
                new Mood("Airflow 50", R.drawable.top_icon, R.color.top),
                new Mood("Shuffle Me", R.drawable.shuffle_icon, R.color.shuffle),
                new Mood("Nervous", R.drawable.nervous_icon, R.color.nervous),
                new Mood("Energy", R.drawable.energy_icon, R.color.energy),
                new Mood("Angry", R.drawable.angry_icon, R.color.angry),
                new Mood("Love", R.drawable.love_icon, R.color.love),
                new Mood("Good", R.drawable.good_icon, R.color.good),
                new Mood("Calm", R.drawable.calm_icon, R.color.calm),
                new Mood("Depressed", R.drawable.depressed_icon, R.color.depressed),
                new Mood("Relax", R.drawable.relax_icon, R.color.relax),
                new Mood("Crazy", R.drawable.crazy_icon, R.color.crazy),
                new Mood("Bored", R.drawable.bored_icon, R.color.bored),
                new Mood("Sad", R.drawable.sad_icon, R.color.sad)
        };
    }


    public Mood[] getMoods(){
        return moods;
    }

    public Mood getMoodFromString(String name){
        for(Mood m : moods){
            if(m.Name.equalsIgnoreCase(name))
                return m;
        }
        return moods[1];
    }

    public Mood createMoodFromString(String name){
        return new Mood(name, R.drawable.top_icon, R.color.top);
    }
}
