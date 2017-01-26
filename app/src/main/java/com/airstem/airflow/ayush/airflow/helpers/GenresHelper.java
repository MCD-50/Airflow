package com.airstem.airflow.ayush.airflow.helpers;

import java.util.Random;

/**
 * Created by ayush on 07-10-16.
 */
public class GenresHelper {

    String[] genres;
    public GenresHelper(){
        genres = new String[]{
                "soundcloud%3Agenres%3Aall-music",//0
                "soundcloud%3Agenres%3Aall-audio",//1
                "soundcloud%3Agenres%3Aalternativerock",//2
                "soundcloud%3Agenres%3Aambient",//3
                "soundcloud%3Agenres%3Aclassical",//4
                "soundcloud%3Agenres%3Acountry",//5
                "soundcloud%3Agenres%3Adanceedm",//6
                "soundcloud%3Agenres%3Adancehall",//7
                "soundcloud%3Agenres%3Adeephouse",//8
                "soundcloud%3Agenres%3Adisco",//9
                "soundcloud%3Agenres%3Adrumbass",//10
                "soundcloud%3Agenres%3Adubstep",//11
                "soundcloud%3Agenres%3Aelectronic",//12
                "soundcloud%3Agenres%3Afolksingersongwriter",//13
                "soundcloud%3Agenres%3Ahiphoprap",//14
                "soundcloud%3Agenres%3Ahouse",//15
                "soundcloud%3Agenres%3Aindie",//16
                "soundcloud%3Agenres%3Ajazzblues",//17
                "soundcloud%3Agenres%3Alatin",//18
                "soundcloud%3Agenres%3Ametal",//19
                "soundcloud%3Agenres%3Apiano",//20
                "soundcloud%3Agenres%3Apop",//21
                "soundcloud%3Agenres%3Arbsoul",//22
                "soundcloud%3Agenres%3Areggae",//23
                "soundcloud%3Agenres%3Areggaeton",//24
                "soundcloud%3Agenres%3Atechno",//25
                "soundcloud%3Agenres%3Atrance",//26
                "soundcloud%3Agenres%3Atrap",//27
                "soundcloud%3Agenres%3Atriphop",//28
                "soundcloud%3Agenres%3Aworld",//29
        };
    }

    //public String[] getGenres(){
    //    return genres;
    //}

    public String getMoodFromGenre(String mood){

        Random rand = new Random();

        if(mood.equalsIgnoreCase("SHUFFLE ME")){
            return genres[0];
        }

        else if(mood.equalsIgnoreCase("NERVOUS")){
            return genres[17];
        }

        else if(mood.equalsIgnoreCase("ENERGY")){
            int n = rand.nextInt(10) + 1;
            if(n == 2 || n == 7 || n == 5)
                return genres[2];
            else if(n == 1 || n == 3 || n==8)
                return genres[18];
            else
                return genres[21];
        }

        else if(mood.equalsIgnoreCase("ANGRY")){
            int n = rand.nextInt(10) + 1;
            if(n == 2 || n == 7 || n == 5)
                return genres[14];
            else if(n == 1 || n == 3 || n==8)
                return genres[23];
            else
                return genres[19];
        }

        else if(mood.equalsIgnoreCase("LOVE")){
            int n = rand.nextInt(10) + 1;
            if(n == 2 || n == 3 || n == 7 || n == 5)
                return genres[13];
            else
                return genres[22];
        }

        else if(mood.equalsIgnoreCase("GOOD")){
            int n = rand.nextInt(10) + 1;
            if(n == 2 || n == 7 || n == 5)
                return genres[7];
            else if(n == 1 || n == 3 || n==8)
                return genres[8];
            else
                return genres[9];
        }

        else if(mood.equalsIgnoreCase("CALM")){
            int n = rand.nextInt(10) + 1;
            if(n == 2 || n == 7 || n == 5)
                return genres[15];
            else if(n == 1 || n == 3 || n==8)
                return genres[20];
            else
                return genres[28];
        }

        else if(mood.equalsIgnoreCase("DEPRESSED")){
            int n = rand.nextInt(10) + 1;
            if(n == 2 || n == 7 || n == 5)
                return genres[3];
            else if(n == 1 || n == 3 || n==8)
                return genres[4];
            else
                return genres[5];
        }

        else if(mood.equalsIgnoreCase("RELAX")){
            int n = rand.nextInt(10) + 1;
            if(n == 2 || n == 7 || n == 5)
                return genres[26];
            else if(n == 1 || n == 3 || n==8)
                return genres[27];
            else
                return genres[28];
        }

        else if(mood.equalsIgnoreCase("CRAZY")){
            int n = rand.nextInt(10) + 1;
            if(n == 2 || n == 7 || n == 5)
                return genres[6];
            else if(n == 1 || n == 3 || n==8)
                return genres[10];
            else
                return genres[25];
        }

        else if(mood.equalsIgnoreCase("BORED")){
            int n = rand.nextInt(10) + 1;
            if(n == 2 || n == 7 || n == 5)
                return genres[11];
            else if(n == 1 || n == 3 || n==8)
                return genres[12];
            else
                return genres[16];
        }

        else {
            int n = rand.nextInt(10) + 1;
            if(n == 2 || n == 3 || n == 7 || n == 5)
                return genres[24];
            else
               return genres[29];
        }
    }
}
