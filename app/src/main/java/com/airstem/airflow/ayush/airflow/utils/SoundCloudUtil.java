package com.airstem.airflow.ayush.airflow.utils;

import android.os.AsyncTask;

import com.airstem.airflow.ayush.airflow.helpers.JSONParseHelper;
import com.airstem.airflow.ayush.airflow.model.Mood;
import com.airstem.airflow.ayush.airflow.model.Track;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by ayush on 07-10-16.
 */
public class SoundCloudUtil {

    JSONParseHelper jsonParseHelper;
    String kind;
    String id;
    int offset;
    public SoundCloudUtil(){
        jsonParseHelper = new JSONParseHelper();
    }

    public ArrayList<Track> getSoundCloudData(String genres, int count,String mood){

        Random rand = new Random();

        int n = rand.nextInt(50) + 1;

        if(n % 2 == 0){
            kind = AppConstant.KIND_TOP;
            offset = 0;
        }
        else {
            kind = AppConstant.KIND_TRENDING;
            offset = 0;
        }

        id = AppConstant.CLIENT_ID_2;

        String url = AppConstant.getQ(kind, genres, id, count, offset);
        String finalResult;

        try {

            URL _obj = new URL(url);
            HttpURLConnection _connection = (HttpURLConnection) _obj.openConnection();

            //_connection.setRequestProperty("User_Agent", USER_AGENT);
            //_connection.setRequestMethod("GET");

            _connection.connect();

            BufferedReader _reader = new BufferedReader(new InputStreamReader(_connection.getInputStream()));

            String result;
            StringBuilder _sBuilder = new StringBuilder();

            while((result = _reader.readLine()) != null){
                _sBuilder.append(result);
            }

            finalResult = _sBuilder.toString();
            _reader.close();

            jsonParseHelper.setJSONString(finalResult);

            return jsonParseHelper.getTrack(mood);

        } catch (Exception any){
            any.printStackTrace();

        }

        return null;
    }

}
