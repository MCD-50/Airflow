package com.airstem.airflow.ayush.airflow.helpers;

import android.app.Activity;
import android.content.Context;
import android.provider.MediaStore;

import com.airstem.airflow.ayush.airflow.R;
import com.airstem.airflow.ayush.airflow.model.PlayMode;
import com.airstem.airflow.ayush.airflow.model.Track;
import com.airstem.airflow.ayush.airflow.utils.AppConstant;
import com.airstem.airflow.ayush.airflow.utils.CollectionUtils;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
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

    private void initYoutubeClient(long count, String query){

           createClientIfNeeded();
        try {
            if(!query.isEmpty()){

                searchListRequest = youTube.search().list("id,snippet");
                searchListRequest.setType("video");
                searchListRequest.setVideoCategoryId("10");
                searchListRequest.setQ(query);
                searchListRequest.setMaxResults(count);
                //searchListRequest.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails");
                searchListRequest.setKey(AppConstant.YOUTUBE_API_ID_1);

            }else {
                videoListRequest = youTube.videos().list("id,snippet");
                videoListRequest.setChart("mostPopular");
                videoListRequest.setVideoCategoryId("10");
                videoListRequest.setMaxResults(count);

                if(!storageHelper.getData().equalsIgnoreCase("Worldwide"))
                    videoListRequest.setRegionCode(getRegionalCode());

                videoListRequest.setKey(AppConstant.YOUTUBE_API_ID_1);

            }



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
    public ArrayList<Track> getYoutubeTopTracks(long count, String query){

        initYoutubeClient(count, query);

        try{
            videoListResponse = videoListRequest.execute();
            List<Video> results = videoListResponse.getItems();

            ArrayList<Track> tracks = new ArrayList<>();
            for(Video result : results){
                String id = result.getId();
                Track t = new Track(result.getId(), result.getSnippet().getTitle(), getFinalString(id), PlayMode.ONLINE);
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

    public ArrayList<Track> getYoutubeSearchResults(long count, String query){
        initYoutubeClient(count, query);

        try{
            searchListResponse = searchListRequest.execute();
            List<SearchResult> results = searchListResponse.getItems();

            ArrayList<Track> tracks = new ArrayList<>();
            for(SearchResult result : results){
                ResourceId vId = result.getId();
                if(vId.getKind().equals("youtube#video")){
                    String id = vId.getVideoId();
                    Track t = new Track(vId.getVideoId(), result.getSnippet().getTitle(), getFinalString(id), PlayMode.ONLINE);
                    t.setMoodName("Search");
                    t.setArtwork(getArtwork(result.getSnippet().getThumbnails()));
                    tracks.add(t);
                }
            }

            return tracks;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Track> getYoutubeSimilarTracks(long count, String query){
        try {
            searchListRequest = youTube.search().list("id,snippet");
        } catch (IOException e) {
            e.printStackTrace();
        }
        searchListRequest.setType("video");
        searchListRequest.setVideoCategoryId("10");
        searchListRequest.setRelatedToVideoId(query);
        searchListRequest.setMaxResults(count);
        //searchListRequest.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails");
        searchListRequest.setKey(AppConstant.YOUTUBE_API_ID_1);


        try{
            searchListResponse = searchListRequest.execute();
            List<SearchResult> results = searchListResponse.getItems();

            ArrayList<Track> tracks = new ArrayList<>();
            for(SearchResult result : results){
                ResourceId vId = result.getId();
                if(vId.getKind().equals("youtube#video")){
                    String id = vId.getVideoId();
                    Track t = new Track(vId.getVideoId(), result.getSnippet().getTitle(), getFinalString(id), PlayMode.RADIO);
                    t.setMoodName("Search");
                    t.setArtwork(getArtwork(result.getSnippet().getThumbnails()));
                    tracks.add(t);
                }
            }

            return tracks;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public String setAndCleanString(String query){
        query = query.toLowerCase();
        if(query.contains("play")){
            query = query.replace("play","");
        }
        if(query.contains("shuffle")){
            query = query.replace("shuffle","");
        }

        return query;
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
