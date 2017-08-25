package com.airstem.airflow.ayush.airflow.helpers.internet;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.airstem.airflow.ayush.airflow.enums.search.Type;
import com.airstem.airflow.ayush.airflow.events.volly.Callback;
import com.airstem.airflow.ayush.airflow.helpers.collection.CollectionConstant;
import com.airstem.airflow.ayush.airflow.model.search.SearchAlbum;
import com.airstem.airflow.ayush.airflow.model.search.SearchArtist;
import com.airstem.airflow.ayush.airflow.model.search.SearchImage;
import com.airstem.airflow.ayush.airflow.model.search.SearchRadio;
import com.airstem.airflow.ayush.airflow.model.search.SearchTrack;
import com.airstem.airflow.ayush.airflow.model.search.SearchVideo;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by mcd-50 on 15/8/17.
 */

public class InternetHelper {

    Context mContext;

    public InternetHelper(Context context) {
        mContext = context;
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    public void search(String query, final Callback callback) throws IOException, JSONException {
        if (!isNetworkAvailable()) {
            callback.OnFailure("Network Error.");
        } else {
            String url = CollectionConstant.SERVER_BASE + CollectionConstant.ENDPOINT_SEARCH;
            final JSONObject jsonBody = new JSONObject();
            jsonBody.put("query", String.valueOf(query));

            final ArrayList<SearchTrack> searchTracks = new ArrayList<>();
            final ArrayList<SearchAlbum> searchAlbums = new ArrayList<>();
            final ArrayList<SearchArtist> searchArtists = new ArrayList<>();
            final ArrayList<SearchRadio> searchRadios = new ArrayList<>();
            final ArrayList<SearchVideo> searchVideos = new ArrayList<>();


            RequestQueue queue = Volley.newRequestQueue(mContext);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    if (response != null) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("message");
                            if (jsonArray != null && jsonArray.length() > 0) {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    JSONObject meta = jsonObject.getJSONObject("meta");
                                    JSONObject result = jsonObject.getJSONObject("result");

                                    //leave meta for now
                                    String type = result.getString("type");

                                    if(type.equals(Type.AIRSTEM_RADIO.toString())){

                                        JSONObject radios = result.getJSONObject("radios");
                                        JSONObject radio_meta = radios.getJSONObject("meta");
                                        JSONArray radios_result = radios.getJSONArray("result");

                                        for (int j = 0; j < radios_result.length(); j++) {
                                            JSONObject _item = radios_result.getJSONObject(j);
                                            String fullName = _item.getString("full_name");
                                            String title = _item.getString("title");
                                            String country = _item.getString("country");
                                            String maxUser = _item.getString("max_user");

                                            JSONArray url = _item.getJSONArray("url");
                                            String[] urlArray = new String[url.length()];
                                            for (int k = 0; k < url.length(); k++) {
                                                urlArray[k] = url.getString(k);
                                            }

                                            JSONArray genre = _item.getJSONArray("genre");
                                            String[] genreArray = new String[genre.length()];
                                            for (int k = 0; k < genre.length(); k++) {
                                                genreArray[k] = genre.getString(k);
                                            }

                                            SearchRadio searchRadio = new SearchRadio(title, maxUser, urlArray, genreArray, country, null);
                                            searchRadios.add(searchRadio);
                                        }

                                    }else if (type.equals(Type.YOUTUBE_SEARCH.toString())){
                                        JSONObject tracks = result.getJSONObject("tracks");
                                        JSONObject tracks_meta = tracks.getJSONObject("meta");
                                        JSONArray tracks_result = tracks.getJSONArray("result");


                                        for (int j = 0; j < tracks_result.length(); j++) {
                                            JSONObject _item = tracks_result.getJSONObject(j);
                                            String title = _item.getString("name");
                                            String description = _item.getString("description");
                                            String author = _item.getString("channel_title");
                                            String id = _item.getString("id");
                                            JSONArray images = _item.getJSONArray("images");
                                            ArrayList<SearchImage> searchImages = new ArrayList<>();

                                            for (int k = 0; k < images.length(); k++) {
                                                JSONObject __item = images.getJSONObject(k);
                                                String size = __item.getString("size");
                                                String url = __item.getString("url");
                                                SearchImage image = new SearchImage(size, url, "YOUTUBE");
                                                searchImages.add(image);
                                            }

                                            JSONArray tags = _item.getJSONArray("tags");
                                            ArrayList<String> searchTags = new ArrayList<>();

                                            for (int k = 0; k < tags.length(); k++) {
                                                searchTags.add(tags.getString(k));
                                            }

                                            SearchVideo searchVideo = new SearchVideo(title, description, author, searchTags, searchImages, "YOUTUBE", id);
                                            searchVideos.add(searchVideo);
                                        }

                                    }else if(type.equals(Type.DEEZER_SEARCH.toString())){
                                        JSONObject tracks = result.getJSONObject("tracks");
                                        JSONObject artists = result.getJSONObject("artists");
                                        JSONObject albums = result.getJSONObject("albums");

                                        JSONObject tracks_meta = tracks.getJSONObject("meta");
                                        JSONObject artists_meta = artists.getJSONObject("meta");
                                        JSONObject albums_meta = albums.getJSONObject("meta");

                                        JSONArray tracks_result = tracks.getJSONArray("result");
                                        JSONArray artists_result = artists.getJSONArray("result");
                                        JSONArray albums_result = albums.getJSONArray("result");


                                        for (int j = 0; j < tracks_result.length(); j++) {
                                            JSONObject _item = tracks_result.getJSONObject(j);
                                            String title = _item.getString("name");
                                            String artist_name = _item.getString("artist_name");
                                            String album_name = _item.getString("album_name");
                                            String id = _item.getString("id");
                                            JSONArray images = _item.getJSONArray("images");
                                            ArrayList<SearchImage> searchImages = new ArrayList<>();
                                            for (int k = 0; k < images.length(); k++) {
                                                JSONObject __item = images.getJSONObject(k);
                                                String size = __item.getString("size");
                                                String url = __item.getString("url");
                                                SearchImage image = new SearchImage(size, url, "DEEZER");
                                                searchImages.add(image);
                                            }

                                            SearchTrack searchTrack = new SearchTrack(title, artist_name, album_name, searchImages, "DEEZER", id);
                                            searchTracks.add(searchTrack);
                                        }

                                        for (int j = 0; j < artists_result.length(); j++) {
                                            JSONObject _item = artists_result.getJSONObject(j);
                                            String title = _item.getString("name");
                                            String id = _item.getString("id");
                                            JSONArray images = _item.getJSONArray("images");
                                            ArrayList<SearchImage> searchImages = new ArrayList<>();
                                            for (int k = 0; k < images.length(); k++) {
                                                JSONObject __item = images.getJSONObject(k);
                                                String size = __item.getString("size");
                                                String url = __item.getString("url");
                                                SearchImage image = new SearchImage(size, url, "DEEZER");
                                                searchImages.add(image);
                                            }

                                            SearchArtist searchArtist = new SearchArtist(title, searchImages, "DEEZER", id);
                                            searchArtists.add(searchArtist);
                                        }

                                        for (int j = 0; j < albums_result.length(); j++) {
                                            JSONObject _item = albums_result.getJSONObject(j);
                                            String title = _item.getString("name");
                                            String artist_name = _item.getString("artist_name");
                                            String id = _item.getString("id");
                                            JSONArray images = _item.getJSONArray("images");
                                            ArrayList<SearchImage> searchImages = new ArrayList<>();
                                            for (int k = 0; k < images.length(); k++) {
                                                JSONObject __item = images.getJSONObject(k);
                                                String size = __item.getString("size");
                                                String url = __item.getString("url");
                                                SearchImage image = new SearchImage(size, url, "DEEZER");
                                                searchImages.add(image);
                                            }

                                            SearchAlbum searchAlbum = new SearchAlbum(title, artist_name, searchImages, "DEEZER", id);
                                            searchAlbums.add(searchAlbum);
                                        }
                                    }
                                }
                                callback.onSearch(searchTracks, searchAlbums, searchArtists, searchVideos, searchRadios);
                            }else{
                                callback.OnFailure("Array is null or empty");
                            }
                        } catch (JSONException error) {
                            callback.OnFailure(error.getMessage());
                        }
                    }else{
                        callback.OnFailure("Response is null or empty");
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //VolleyError e = error;
                    callback.OnFailure(error.getMessage());
                }
            });
            queue.add(jsonObjectRequest);
        }
    }

    public void searchArtists(String artist_name, final Callback callback) throws JSONException {
        if (!isNetworkAvailable()) {
            callback.OnFailure("Network Error.");
        } else {
            String url = CollectionConstant.SERVER_BASE + CollectionConstant.ENDPOINT_SEARCH_ARTISTS;
            final JSONObject jsonBody = new JSONObject();
            jsonBody.put("artist_name", String.valueOf(artist_name));

            final ArrayList<SearchArtist> searchArtists = new ArrayList<>();

            RequestQueue queue = Volley.newRequestQueue(mContext);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    if (response != null) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("message");
                            if (jsonArray != null && jsonArray.length() > 0) {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    JSONObject artists_meta = jsonObject.getJSONObject("meta");
                                    JSONArray artists_result = jsonObject.getJSONArray("result");

                                    //leave meta for now
                                    for (int j = 0; j < artists_result.length(); j++) {
                                        JSONObject _item = artists_result.getJSONObject(j);
                                        String title = _item.getString("name");
                                        String id = _item.getString("id");
                                        JSONArray images = _item.getJSONArray("images");
                                        ArrayList<SearchImage> searchImages = new ArrayList<>();
                                        for (int k = 0; k < images.length(); k++) {
                                            JSONObject __item = images.getJSONObject(k);
                                            String size = __item.getString("size");
                                            String url = __item.getString("url");
                                            SearchImage image = new SearchImage(size, url, "DEEZER");
                                            searchImages.add(image);
                                        }

                                        SearchArtist searchArtist = new SearchArtist(title, searchImages, "DEEZER", id);
                                        searchArtists.add(searchArtist);
                                    }
                                }
                                callback.onSearch(null, null, searchArtists, null, null);
                            }else{
                                callback.OnFailure("Array is null or empty");
                            }
                        } catch (JSONException error) {
                            callback.OnFailure(error.getMessage());
                        }
                    }else{
                        callback.OnFailure("Response is null or empty");
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //VolleyError e = error;
                    callback.OnFailure(error.getMessage());
                }
            });
            queue.add(jsonObjectRequest);
        }
    }


    public void searchAlbums(String artist_name, final Callback callback) throws JSONException {
        if (!isNetworkAvailable()) {
            callback.OnFailure("Network Error.");
        } else {
            String url = CollectionConstant.SERVER_BASE + CollectionConstant.ENDPOINT_SEARCH_ALBUMS;
            final JSONObject jsonBody = new JSONObject();
            jsonBody.put("artist_name", String.valueOf(artist_name));

            final ArrayList<SearchAlbum> searchAlbums = new ArrayList<>();
            RequestQueue queue = Volley.newRequestQueue(mContext);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    if (response != null) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("message");
                            if (jsonArray != null && jsonArray.length() > 0) {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    JSONObject albums_meta = jsonObject.getJSONObject("meta");
                                    JSONArray albums_result = jsonObject.getJSONArray("result");
                                    //leave meta for now

                                    for (int j = 0; j < albums_result.length(); j++) {
                                        JSONObject _item = albums_result.getJSONObject(j);
                                        String title = _item.getString("name");
                                        String artist_name = _item.getString("artist_name");
                                        String id = _item.getString("id");
                                        JSONArray images = _item.getJSONArray("images");
                                        ArrayList<SearchImage> searchImages = new ArrayList<>();
                                        for (int k = 0; k < images.length(); k++) {
                                            JSONObject __item = images.getJSONObject(k);
                                            String size = __item.getString("size");
                                            String url = __item.getString("url");
                                            SearchImage image = new SearchImage(size, url, "DEEZER");
                                            searchImages.add(image);
                                        }

                                        SearchAlbum searchAlbum = new SearchAlbum(title, artist_name, searchImages, "DEEZER", id);
                                        searchAlbums.add(searchAlbum);
                                    }
                                }
                                callback.onSearch(null, searchAlbums, null, null, null);
                            }else{
                                callback.OnFailure("Array is null or empty");
                            }
                        } catch (JSONException error) {
                            callback.OnFailure(error.getMessage());
                        }
                    }else{
                        callback.OnFailure("Response is null or empty");
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //VolleyError e = error;
                    callback.OnFailure(error.getMessage());
                }
            });
            queue.add(jsonObjectRequest);
        }
    }


    public void searchAlbumTracks(String artist_name, String album_name, final Callback callback) throws JSONException {
        if (!isNetworkAvailable()) {
            callback.OnFailure("Network Error.");
        } else {
            String url = CollectionConstant.SERVER_BASE + CollectionConstant.ENDPOINT_SEARCH_ALBUM_INFO_LAST;
            final JSONObject jsonBody = new JSONObject();
            jsonBody.put("artist_name", String.valueOf(artist_name));
            jsonBody.put("album_name", String.valueOf(album_name));

            final ArrayList<SearchTrack> searchTracks = new ArrayList<>();
            RequestQueue queue = Volley.newRequestQueue(mContext);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    if (response != null) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("message");
                            if (jsonArray != null && jsonArray.length() > 0) {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    JSONObject tracks_meta = jsonObject.getJSONObject("meta");
                                    JSONObject result = jsonObject.getJSONObject("result");

                                    JSONArray tracks_result = result.getJSONArray("tracks");
                                    //leave meta for now

                                    for (int j = 0; j < tracks_result.length(); j++) {
                                        JSONObject _item = tracks_result.getJSONObject(j);
                                        String title = _item.getString("name");
                                        String artist_name = _item.getString("artist_name");
                                        String id = _item.getString("mbid");
                                        JSONArray images = _item.getJSONArray("images");
                                        ArrayList<SearchImage> searchImages = new ArrayList<>();
                                        for (int k = 0; k < images.length(); k++) {
                                            JSONObject __item = images.getJSONObject(k);
                                            String size = __item.getString("size");
                                            String url = __item.getString("url");
                                            SearchImage image = new SearchImage(size, url, "LASTFM");
                                            searchImages.add(image);
                                        }

                                        SearchTrack searchTrack = new SearchTrack(title, artist_name, null, searchImages, "LASTFM", id);
                                        searchTracks.add(searchTrack);
                                    }

                                }
                                callback.onSearch(searchTracks, null, null, null, null);
                            }else{
                                callback.OnFailure("Array is null or empty");
                            }
                        } catch (JSONException error) {
                            callback.OnFailure(error.getMessage());
                        }
                    }else{
                        callback.OnFailure("Response is null or empty");
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //VolleyError e = error;
                    callback.OnFailure(error.getMessage());
                }
            });
            queue.add(jsonObjectRequest);
        }
    }


    public void searchArtistAlbums(String artist_name, final Callback callback) throws JSONException {
        if (!isNetworkAvailable()) {
            callback.OnFailure("Network Error.");
        } else {
            String url = CollectionConstant.SERVER_BASE + CollectionConstant.ENDPOINT_SEARCH_ARTIST_ALBUMS;
            final JSONObject jsonBody = new JSONObject();
            jsonBody.put("artist_name", String.valueOf(artist_name));

            final ArrayList<SearchAlbum> searchAlbums = new ArrayList<>();
            RequestQueue queue = Volley.newRequestQueue(mContext);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    if (response != null) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("message");
                            if (jsonArray != null && jsonArray.length() > 0) {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    JSONObject albums_meta = jsonObject.getJSONObject("meta");
                                    JSONArray albums_result = jsonObject.getJSONArray("result");
                                    //leave meta for now

                                    for (int j = 0; j < albums_result.length(); j++) {
                                        JSONObject _item = albums_result.getJSONObject(j);
                                        String title = _item.getString("name");
                                        String artist_name = _item.getString("artist_name");
                                        String id = _item.getString("mbid");
                                        JSONArray images = _item.getJSONArray("images");
                                        ArrayList<SearchImage> searchImages = new ArrayList<>();
                                        for (int k = 0; k < images.length(); k++) {
                                            JSONObject __item = images.getJSONObject(k);
                                            String size = __item.getString("size");
                                            String url = __item.getString("url");
                                            SearchImage image = new SearchImage(size, url, "LASTFM");
                                            searchImages.add(image);
                                        }

                                        SearchAlbum searchAlbum = new SearchAlbum(title, artist_name, searchImages, "LASTFM", id);
                                        searchAlbums.add(searchAlbum);
                                    }
                                }
                                callback.onSearch(null, searchAlbums, null, null, null);
                            }else{
                                callback.OnFailure("Array is null or empty");
                            }
                        } catch (JSONException error) {
                            callback.OnFailure(error.getMessage());
                        }
                    }else{
                        callback.OnFailure("Response is null or empty");
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //VolleyError e = error;
                    callback.OnFailure(error.getMessage());
                }
            });
            queue.add(jsonObjectRequest);
        }
    }


    public void searchArtistTracks(String artist_name, final Callback callback) throws JSONException {
        if (!isNetworkAvailable()) {
            callback.OnFailure("Network Error.");
        } else {
            String url = CollectionConstant.SERVER_BASE + CollectionConstant.ENDPOINT_SEARCH_ARTIST_TRACKS;
            final JSONObject jsonBody = new JSONObject();
            jsonBody.put("artist_name", String.valueOf(artist_name));

            final ArrayList<SearchTrack> searchTracks = new ArrayList<>();
            RequestQueue queue = Volley.newRequestQueue(mContext);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    if (response != null) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("message");
                            if (jsonArray != null && jsonArray.length() > 0) {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    JSONObject tracks_meta = jsonObject.getJSONObject("meta");
                                    JSONArray tracks_result = jsonObject.getJSONArray("result");
                                    //leave meta for now

                                    for (int j = 0; j < tracks_result.length(); j++) {
                                        JSONObject _item = tracks_result.getJSONObject(j);
                                        String title = _item.getString("name");
                                        String artist_name = _item.getString("artist_name");
                                        String id = _item.getString("mbid");
                                        JSONArray images = _item.getJSONArray("images");
                                        ArrayList<SearchImage> searchImages = new ArrayList<>();
                                        for (int k = 0; k < images.length(); k++) {
                                            JSONObject __item = images.getJSONObject(k);
                                            String size = __item.getString("size");
                                            String url = __item.getString("url");
                                            SearchImage image = new SearchImage(size, url, "LASTFM");
                                            searchImages.add(image);
                                        }

                                        SearchTrack searchTrack = new SearchTrack(title, artist_name, null, searchImages, "LASTFM", id);
                                        searchTracks.add(searchTrack);
                                    }

                                }
                                callback.onSearch(searchTracks, null, null, null, null);
                            }else{
                                callback.OnFailure("Array is null or empty");
                            }
                        } catch (JSONException error) {
                            callback.OnFailure(error.getMessage());
                        }
                    }else{
                        callback.OnFailure("Response is null or empty");
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //VolleyError e = error;
                    callback.OnFailure(error.getMessage());
                }
            });
            queue.add(jsonObjectRequest);
        }
    }


    public void searchTopData(final Callback callback) throws IOException, JSONException {
        if (!isNetworkAvailable()) {
            callback.OnFailure("Network Error.");
        } else {
            String url = CollectionConstant.SERVER_BASE + CollectionConstant.ENDPOINT_SEARCH_TOP_DATA;
            final JSONObject jsonBody = new JSONObject();

            final ArrayList<SearchTrack> searchTracks = new ArrayList<>();
            final ArrayList<SearchAlbum> searchAlbums = new ArrayList<>();
            final ArrayList<SearchArtist> searchArtists = new ArrayList<>();


            RequestQueue queue = Volley.newRequestQueue(mContext);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    if (response != null) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("message");
                            if (jsonArray != null && jsonArray.length() > 0) {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    JSONObject meta = jsonObject.getJSONObject("meta");
                                    JSONObject result = jsonObject.getJSONObject("result");

                                    //leave meta for now
                                    String type = result.getString("type");

                                    JSONArray artists_result = result.getJSONArray("artists");
                                    JSONArray albums_result = result.getJSONArray("albums");
                                    JSONArray tracks_result = result.getJSONArray("tracks");


                                    for (int j = 0; j < tracks_result.length(); j++) {
                                        JSONObject _item = tracks_result.getJSONObject(j);
                                        String title = _item.getString("name");
                                        String artist_name = _item.getString("artist_name");
                                        String album_name = _item.getString("album_name");
                                        String id = _item.getString("id");
                                        JSONArray images = _item.getJSONArray("images");
                                        ArrayList<SearchImage> searchImages = new ArrayList<>();
                                        for (int k = 0; k < images.length(); k++) {
                                            JSONObject __item = images.getJSONObject(k);
                                            String size = __item.getString("size");
                                            String url = __item.getString("url");
                                            SearchImage image = new SearchImage(size, url, "DEEZER");
                                            searchImages.add(image);
                                        }

                                        SearchTrack searchTrack = new SearchTrack(title, artist_name, album_name, searchImages, "DEEZER", id);
                                        searchTracks.add(searchTrack);
                                    }

                                    for (int j = 0; j < artists_result.length(); j++) {
                                        JSONObject _item = artists_result.getJSONObject(j);
                                        String title = _item.getString("name");
                                        String id = _item.getString("id");
                                        JSONArray images = _item.getJSONArray("images");
                                        ArrayList<SearchImage> searchImages = new ArrayList<>();
                                        for (int k = 0; k < images.length(); k++) {
                                            JSONObject __item = images.getJSONObject(k);
                                            String size = __item.getString("size");
                                            String url = __item.getString("url");
                                            SearchImage image = new SearchImage(size, url, "DEEZER");
                                            searchImages.add(image);
                                        }

                                        SearchArtist searchArtist = new SearchArtist(title, searchImages, "DEEZER", id);
                                        searchArtists.add(searchArtist);
                                    }

                                    for (int j = 0; j < albums_result.length(); j++) {
                                        JSONObject _item = albums_result.getJSONObject(j);
                                        String title = _item.getString("name");
                                        String artist_name = _item.getString("artist_name");
                                        String id = _item.getString("id");
                                        JSONArray images = _item.getJSONArray("images");
                                        ArrayList<SearchImage> searchImages = new ArrayList<>();
                                        for (int k = 0; k < images.length(); k++) {
                                            JSONObject __item = images.getJSONObject(k);
                                            String size = __item.getString("size");
                                            String url = __item.getString("url");
                                            SearchImage image = new SearchImage(size, url, "DEEZER");
                                            searchImages.add(image);
                                        }

                                        SearchAlbum searchAlbum = new SearchAlbum(title, artist_name, searchImages, "DEEZER", id);
                                        searchAlbums.add(searchAlbum);
                                    }

                                }
                                callback.onSearch(searchTracks, searchAlbums, searchArtists, null, null);
                            }else{
                                callback.OnFailure("Array is null or empty");
                            }
                        } catch (JSONException error) {
                            callback.OnFailure(error.getMessage());
                        }
                    }else{
                        callback.OnFailure("Response is null or empty");
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //VolleyError e = error;
                    callback.OnFailure(error.getMessage());
                }
            });
            queue.add(jsonObjectRequest);
        }
    }

    public void searchSimilarData(String id, String track_name, String artist_name, final Callback callback) throws IOException, JSONException {
        if (!isNetworkAvailable()) {
            callback.OnFailure("Network Error.");
        } else {
            String url = CollectionConstant.SERVER_BASE + CollectionConstant.ENDPOINT_SEARCH_SIMILAR_DATA;
            final JSONObject jsonBody = new JSONObject();
            jsonBody.put("track_name", track_name);
            jsonBody.put("artist_name", artist_name);
            jsonBody.put("related_video_id", id);

            final ArrayList<SearchTrack> searchTracks = new ArrayList<>();

            RequestQueue queue = Volley.newRequestQueue(mContext);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    if (response != null) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("message");
                            if (jsonArray != null && jsonArray.length() > 0) {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    JSONObject meta = jsonObject.getJSONObject("meta");
                                    JSONObject result = jsonObject.getJSONObject("result");

                                    //leave meta for now
                                    String type = result.getString("type");
                                    JSONArray tracks_result = result.getJSONArray("tracks");

                                    for (int j = 0; j < tracks_result.length(); j++) {
                                        JSONObject _item = tracks_result.getJSONObject(j);
                                        String title = _item.getString("name");
                                        String description = _item.getString("description");
                                        String author = _item.getString("channel_title");
                                        String id = _item.getString("id");
                                        JSONArray images = _item.getJSONArray("images");
                                        ArrayList<SearchImage> searchImages = new ArrayList<>();
                                        for (int k = 0; k < images.length(); k++) {
                                            JSONObject __item = images.getJSONObject(k);
                                            String size = __item.getString("size");
                                            String url = __item.getString("url");
                                            SearchImage image = new SearchImage(size, url, "YOUTUBE");
                                            searchImages.add(image);
                                        }

                                        SearchTrack searchTrack = new SearchTrack(title, author, null, searchImages, "YOUTUBE", id);
                                        searchTracks.add(searchTrack);
                                    }
                                }
                                callback.onSearch(searchTracks, null, null, null, null);
                            }else{
                                callback.OnFailure("Array is null or empty");
                            }
                        } catch (JSONException error) {
                            callback.OnFailure(error.getMessage());
                        }
                    }else{
                        callback.OnFailure("Response is null or empty");
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //VolleyError e = error;
                    callback.OnFailure(error.getMessage());
                }
            });
            queue.add(jsonObjectRequest);
        }
    }


    public void searchNewData(final Callback callback) throws IOException, JSONException {
        if (!isNetworkAvailable()) {
            callback.OnFailure("Network Error.");
        } else {
            String url = CollectionConstant.SERVER_BASE + CollectionConstant.ENDPOINT_SEARCH_NEW_DATA;
            final JSONObject jsonBody = new JSONObject();

            final ArrayList<SearchTrack> searchTracks = new ArrayList<>();
            RequestQueue queue = Volley.newRequestQueue(mContext);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    if (response != null) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("message");
                            if (jsonArray != null && jsonArray.length() > 0) {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    JSONObject meta = jsonObject.getJSONObject("meta");
                                    JSONObject result = jsonObject.getJSONObject("result");

                                    //leave meta for now
                                    String type = result.getString("type");
                                    JSONArray tracks_result = result.getJSONArray("tracks");

                                    for (int j = 0; j < tracks_result.length(); j++) {
                                        JSONObject _item = tracks_result.getJSONObject(j);
                                        String title = _item.getString("name");
                                        String description = _item.getString("description");
                                        String author = _item.getString("channel_title");
                                        String id = _item.getString("id");
                                        JSONArray images = _item.getJSONArray("images");
                                        ArrayList<SearchImage> searchImages = new ArrayList<>();
                                        for (int k = 0; k < images.length(); k++) {
                                            JSONObject __item = images.getJSONObject(k);
                                            String size = __item.getString("size");
                                            String url = __item.getString("url");
                                            SearchImage image = new SearchImage(size, url, "YOUTUBE");
                                            searchImages.add(image);
                                        }

                                        SearchTrack searchTrack = new SearchTrack(title, author, null, searchImages, "YOUTUBE", id);
                                        searchTracks.add(searchTrack);
                                    }
                                }
                                callback.onSearch(searchTracks, null, null, null, null);
                            }else{
                                callback.OnFailure("Array is null or empty");
                            }
                        } catch (JSONException error) {
                            callback.OnFailure(error.getMessage());
                        }
                    }else{
                        callback.OnFailure("Response is null or empty");
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //VolleyError e = error;
                    callback.OnFailure(error.getMessage());
                }
            });
            queue.add(jsonObjectRequest);
        }
    }


    public void searchArtistArtwork(String artist_name, final Callback callback) throws IOException, JSONException {
        if (!isNetworkAvailable()) {
            callback.OnFailure("Network Error.");
        } else {
            String url = CollectionConstant.SERVER_BASE + CollectionConstant.ENDPOINT_SEARCH_ARTIST_ARTWORK;
            final JSONObject jsonBody = new JSONObject();
            jsonBody.put("artist_name", artist_name);

            final ArrayList<SearchImage> searchImages = new ArrayList<>();

            RequestQueue queue = Volley.newRequestQueue(mContext);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    if (response != null) {
                        try {

                            JSONArray jsonArray = response.getJSONArray("message");
                            if (jsonArray != null && jsonArray.length() > 0) {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    JSONObject meta = jsonObject.getJSONObject("meta");
                                    JSONObject result = jsonObject.getJSONObject("result");

                                    //leave meta for now
                                    String type = jsonObject.getString("type");
                                    JSONArray images = result.getJSONArray("images");

                                    for (int k = 0; k < images.length(); k++) {
                                        JSONObject __item = images.getJSONObject(k);
                                        String size = __item.getString("size");
                                        String url = __item.getString("url");
                                        SearchImage image = new SearchImage(size, url, type);
                                        searchImages.add(image);
                                    }

                                }
                                callback.onArtistImages(searchImages);
                            }else{
                                callback.OnFailure("Array is null or empty");
                            }
                        } catch (JSONException error) {
                            callback.OnFailure(error.getMessage());
                        }
                    }else{
                        callback.OnFailure("Response is null or empty");
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //VolleyError e = error;
                    callback.OnFailure(error.getMessage());
                }
            });
            queue.add(jsonObjectRequest);
        }
    }

    public void searchAlbumArtwork(String artist_name, String album_name, final Callback callback) throws IOException, JSONException {
        if (!isNetworkAvailable()) {
            callback.OnFailure("Network Error.");
        } else {
            String url = CollectionConstant.SERVER_BASE + CollectionConstant.ENDPOINT_SEARCH_ALBUM_ARTWORK;
            final JSONObject jsonBody = new JSONObject();
            jsonBody.put("artist_name", artist_name);
            jsonBody.put("album_name", album_name);

            final ArrayList<SearchImage> searchImages = new ArrayList<>();

            RequestQueue queue = Volley.newRequestQueue(mContext);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    if (response != null) {
                        try {


                            JSONArray jsonArray = response.getJSONArray("message");
                            if (jsonArray != null && jsonArray.length() > 0) {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    JSONObject meta = jsonObject.getJSONObject("meta");
                                    JSONObject result = jsonObject.getJSONObject("result");

                                    //leave meta for now
                                    String type = jsonObject.getString("type");
                                    JSONArray images = result.getJSONArray("images");

                                    for (int k = 0; k < images.length(); k++) {
                                        JSONObject __item = images.getJSONObject(k);
                                        String size = __item.getString("size");
                                        String url = __item.getString("url");
                                        SearchImage image = new SearchImage(size, url, type);
                                        searchImages.add(image);
                                    }

                                }
                                callback.onArtistImages(searchImages);
                            }else{
                                callback.OnFailure("Array is null or empty");
                            }
                        } catch (JSONException error) {
                            callback.OnFailure(error.getMessage());
                        }
                    }else{
                        callback.OnFailure("Response is null or empty");
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //VolleyError e = error;
                    callback.OnFailure(error.getMessage());
                }
            });
            queue.add(jsonObjectRequest);
        }
    }

    public void searchLyrics(String artist_name, String album_name, final Callback callback) throws IOException, JSONException {
        if (!isNetworkAvailable()) {
            callback.OnFailure("Network Error.");
        } else {
            String url = CollectionConstant.SERVER_BASE + CollectionConstant.ENDPOINT_SEARCH_LYRICS;
            final JSONObject jsonBody = new JSONObject();
            jsonBody.put("artist_name", artist_name);
            jsonBody.put("album_name", album_name);

            RequestQueue queue = Volley.newRequestQueue(mContext);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    if (response != null) {
                        try {

                            JSONArray jsonArray = response.getJSONArray("message");
                            if (jsonArray != null && jsonArray.length() > 0) {
                                String text = null;
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    JSONObject meta = jsonObject.getJSONObject("meta");
                                    JSONObject result = jsonObject.getJSONObject("result");

                                    //leave meta for now
                                    String type = jsonObject.getString("type");
                                    JSONObject lyricsObject = result.getJSONObject("lyrics");
                                    text = lyricsObject.getString("text");
                                }
                                callback.onLyrics(text);
                            }else{
                                callback.OnFailure("Array is null or empty");
                            }
                        } catch (JSONException error) {
                            callback.OnFailure(error.getMessage());
                        }
                    }else{
                        callback.OnFailure("Response is null or empty");
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //VolleyError e = error;
                    callback.OnFailure(error.getMessage());
                }
            });
            queue.add(jsonObjectRequest);
        }
    }

}
