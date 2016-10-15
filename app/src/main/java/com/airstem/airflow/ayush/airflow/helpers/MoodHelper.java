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
                new Mood("AIRFLOW 50", R.drawable.top_icon, R.color.top),
                new Mood("SHUFFLE ME", R.drawable.shuffle_icon, R.color.shuffle),
                new Mood("NERVOUS", R.drawable.nervous_icon, R.color.nervous),
                new Mood("ENERGY", R.drawable.energy_icon, R.color.energy),
                new Mood("ANGRY", R.drawable.angry_icon, R.color.angry),
                new Mood("LOVE", R.drawable.love_icon, R.color.love),
                new Mood("GOOD", R.drawable.good_icon, R.color.good),
                new Mood("CALM", R.drawable.calm_icon, R.color.calm),
                new Mood("DEPRESSED", R.drawable.depressed_icon, R.color.depressed),
                new Mood("RELAX", R.drawable.relax_icon, R.color.relax),
                new Mood("CRAZY", R.drawable.crazy_icon, R.color.crazy),
                new Mood("BORED", R.drawable.bored_icon, R.color.bored),
                new Mood("SAD", R.drawable.sad_icon, R.color.sad)
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
}
