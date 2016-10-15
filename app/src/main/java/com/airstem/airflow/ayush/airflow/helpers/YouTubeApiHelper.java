package com.airstem.airflow.ayush.airflow.helpers;

import android.app.Activity;
import android.content.Context;
import android.provider.MediaStore;

import com.airstem.airflow.ayush.airflow.R;
import com.airstem.airflow.ayush.airflow.model.Track;
import com.airstem.airflow.ayush.airflow.utils.AppConstant;
import com.airstem.airflow.ayush.airflow.utils.CollectionUtils;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.Thumbnail;
import com.google.api.services.youtube.model.ThumbnailDetails;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoListResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by ayush on 14-10-16.
 */
public class YouTubeApiHelper {


    Context mContext;
    StorageHelper storageHelper;

    public YouTubeApiHelper(Context context, Context appContext){
        mContext = context;
        storageHelper = new StorageHelper(appContext);
    }

    private YouTube youTube;


    //request
    private YouTube.Search.List  searchListRequest;
    private YouTube.Videos.List  videoListRequest;

    //response;
    private SearchListResponse searchListResponse;
    private VideoListResponse videoListResponse;



    private void createClientIfNeeded(){
        youTube = new YouTube.Builder(new NetHttpTransport(), new JacksonFactory(), new HttpRequestInitializer() {
            @Override
            public void initialize(HttpRequest request) throws IOException {
                //nothing imp here
            }
        }).setApplicationName(mContext.getString(R.string.app_name)).build();
    }

    private void createClientIfNeeded2(){
        youTube = new YouTube.Builder(new NetHttpTransport(), new JacksonFactory(), new HttpRequestInitializer() {
            @Override
            public void initialize(HttpRequest request) throws IOException {
                //nothing imp here
            }
        }).setApplicationName(mContext.getString(R.string.musicus)).build();
    }

    private void initYoutubeClient(long count){

           createClientIfNeeded();

        try {
            videoListRequest = youTube.videos().list("id,snippet");
            videoListRequest.setChart("mostPopular");
            videoListRequest.setVideoCategoryId("10");
            videoListRequest.setMaxResults(count);

            if(!storageHelper.getData().equalsIgnoreCase("Worldwide"))
                videoListRequest.setRegionCode(getRegionalCode());

            videoListRequest.setKey(AppConstant.YOUTUBE_API_ID_1);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    private String getRegionalCode(){
        int index = CollectionUtils.getIndexFromCountryName(storageHelper.getData());
        return CollectionUtils.regionCode[index];
    }

    private String getFinalString(String youtubeVidId){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("http://youtubeinmp3.com/fetch/?video=").append("https://www.youtube.com/watch?v=").append(youtubeVidId);
        return stringBuilder.toString();
    }



    //return null once;;;; and then set flag
    public ArrayList<Track> getYoutubeTopTracks(long count){

        initYoutubeClient(count);

        try{
            videoListResponse = videoListRequest.execute();
            List<Video> results = videoListResponse.getItems();

            ArrayList<Track> tracks = new ArrayList<>();
            for(Video result : results){
                String id = result.getId();
                Track t = new Track(result.getId(), result.getSnippet().getTitle(), getFinalString(id), true);
                t.setMoodName("Airflow 50");
                t.setArtwork(getArtwork(result.getSnippet().getThumbnails()));
                tracks.add(t);
            }

            return tracks;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private String getArtwork(ThumbnailDetails thumbnails) {
        if(thumbnails != null){
            if(thumbnails.getStandard()!= null)
                return thumbnails.getStandard().getUrl();
            else if(thumbnails.getHigh() != null){
                return thumbnails.getHigh().getUrl();
            }else if(thumbnails.getMedium() != null){
                return thumbnails.getMedium().getUrl();
            }else if(thumbnails.getDefault() != null){
                return thumbnails.getDefault().getUrl();
            }else
                return " ";
        }else {
            return " ";
        }
    }
}
