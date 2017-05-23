package com.airstem.airflow.ayush.airflow.helpers;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.Toast;

import com.airstem.airflow.ayush.airflow.R;
import com.airstem.airflow.ayush.airflow.utils.AppConstant;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ayush AS on 12/3/17.
 */

public class ArtworkHelper {

    Context mContext;
    public ArtworkHelper(Context context){
        mContext = context;
    }

    public void getArtwork(String artistName){
        String url = AppConstant.getArtistArtwork(artistName);
        String finalResult;
        try {
            URL _obj = new URL(url);
            HttpURLConnection _connection = (HttpURLConnection) _obj.openConnection();
            _connection.connect();
            BufferedReader _reader = new BufferedReader(new InputStreamReader(_connection.getInputStream()));
            String result;
            StringBuilder _sBuilder = new StringBuilder();

            while((result = _reader.readLine()) != null){
                _sBuilder.append(result);
            }
            finalResult = _sBuilder.toString();
            _reader.close();

            JSONObject obj = new JSONObject(finalResult);
            JSONArray array = obj.getJSONArray("image");

            for (int i = array.length() - 1; i >= 0; i--)
            {
                String artistUrl = array.getJSONObject(i).getString("#text");
                String artistSize = array.getJSONObject(i).getString("size");
                if(artistSize.equals("extralarge") || artistSize.equals("large") || artistSize.equals("medium")){
                    if(artistUrl != null && !artistUrl.isEmpty()){
                        finalResult = artistUrl;
                        break;
                    }
                }
            }

            new DownloadImage().execute(finalResult);

        } catch (Exception any){
            any.printStackTrace();

        }
    }

    class DownloadImage extends AsyncTask<String, Void, Bitmap> {
        String id;
        @Override
        protected Bitmap doInBackground(String... params) {
            String url = params[0];
            Bitmap bitmap = null;
            try {
                InputStream iStream = new URL(url).openStream();
                bitmap = BitmapFactory.decodeStream(iStream);
            }catch(Exception ex){
                try{
                    bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.default_art);
                }catch (Exception e){

                }
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);

        }
    }
}
