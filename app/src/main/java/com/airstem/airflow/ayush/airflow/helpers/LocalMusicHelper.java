package com.airstem.airflow.ayush.airflow.helpers;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;
import android.provider.MediaStore;
import android.text.TextUtils;

import com.airstem.airflow.ayush.airflow.R;
import com.airstem.airflow.ayush.airflow.model.Artist;
import com.airstem.airflow.ayush.airflow.model.PlayMode;
import com.airstem.airflow.ayush.airflow.model.Playlist;
import com.airstem.airflow.ayush.airflow.model.Track;
import com.airstem.airflow.ayush.airflow.utils.AppConstant;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by ayush on 22-11-16.
 */

public class LocalMusicHelper {

    ContentResolver contentResolver;
    public LocalMusicHelper(Context context){
        contentResolver = context.getContentResolver();
    }



    private final Cursor makeArtistCursor() {
        return contentResolver.query(MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI,
                new String[] {
          /* 0 */ BaseColumns._ID,
          /* 1 */ MediaStore.Audio.ArtistColumns.ARTIST,
          /* 2 */ MediaStore.Audio.ArtistColumns.NUMBER_OF_ALBUMS,
          /* 3 */ MediaStore.Audio.ArtistColumns.NUMBER_OF_TRACKS,
                }, null, null, MediaStore.Audio.Artists.DEFAULT_SORT_ORDER);
    }

    public ArrayList<Artist> getArtists() {
        ArrayList<Artist> mArtistsList = new ArrayList<Artist>();
        Cursor mCursor = makeArtistCursor();
        if (mCursor != null && mCursor.moveToFirst()) {
            do {

                final long id = mCursor.getLong(0);
                final String artistName = mCursor.getString(1);
                //final int albumCount = mCursor.getInt(2);
                final int songCount = mCursor.getInt(3);
                String uri = "";
                if(!TextUtils.isEmpty(artistName) && !artistName.toLowerCase().contains("Unknown")){
                    uri = AppConstant.getArtistArtwork(artistName);
                }

                if(!artistName.contains("unknown")){
                    final Artist artist = new Artist(String.valueOf(id), artistName, getCountMessage(songCount),getArtistSongs(id), uri);
                    mArtistsList.add(artist);
                }

            } while (mCursor.moveToNext());
        }
        if (mCursor != null) {
            mCursor.close();
            mCursor = null;
        }
        return mArtistsList;
    }


    public ArrayList<Playlist> getPlaylist(){
        ArrayList<Playlist> playlists = new ArrayList<Playlist>();
        playlists.add(new Playlist(String.valueOf(1), "Most Played", getCountMessage(1), " ", new ArrayList<Track>()));
        playlists.add(new Playlist(String.valueOf(2), "Top Played", getCountMessage(2), " ", new ArrayList<Track>()));
        playlists.add(new Playlist(String.valueOf(3), "Most Played", getCountMessage(3), " ", new ArrayList<Track>()));
        playlists.add(new Playlist(String.valueOf(4), "Top Played", getCountMessage(1), " ", new ArrayList<Track>()));
        playlists.add(new Playlist(String.valueOf(5), "Most Played", getCountMessage(4), " ", new ArrayList<Track>()));
        playlists.add(new Playlist(String.valueOf(6), "Top Played", getCountMessage(3), " ", new ArrayList<Track>()));
        playlists.add(new Playlist(String.valueOf(7), "Top Played", getCountMessage(1), " ", new ArrayList<Track>()));
        playlists.add(new Playlist(String.valueOf(8), "Most Played", getCountMessage(4), " ", new ArrayList<Track>()));
        playlists.add(new Playlist(String.valueOf(9), "Top Played", getCountMessage(3), " ", new ArrayList<Track>()));
        return playlists;
    }


    private String getCountMessage(int count){
        if(count == 0 || count ==1)
            return count + " Track";
        return count + " Tracks";
    }

    private final Cursor makeArtistSongCursor(final Long artistId) {
        final StringBuilder selection = new StringBuilder();
        selection.append(MediaStore.Audio.AudioColumns.IS_MUSIC + "=1");
        selection.append(" AND " + MediaStore.Audio.AudioColumns.TITLE + " != ''");
        selection.append(" AND " + MediaStore.Audio.AudioColumns.ARTIST_ID + "=" + artistId);
        return contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                new String[] {
          /* 0 */       BaseColumns._ID,
                        MediaStore.Audio.Media.TITLE,
          /* 1 */       MediaStore.Audio.Media.ARTIST,
                        MediaStore.Audio.Media.DATA
                }, selection.toString(), null, MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
    }


    public ArrayList<Track> getArtistSongs(final Long artistId) {
        ArrayList<Track> mSongList = new ArrayList<Track>();
        Cursor songCursor = makeArtistSongCursor(artistId);
        if (songCursor != null && songCursor.moveToFirst()) {
            int songId = songCursor.getColumnIndex(MediaStore.Audio.Media._ID);
            int songTitle = songCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int songArtist = songCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
            int songUrl = songCursor.getColumnIndex(MediaStore.Audio.Media.DATA);


            do {

                long currentId = songCursor.getLong(songId);

                long albumId = getAlbumId(contentResolver, currentId);
                Uri sArtworkUri = Uri.parse("content://media/external/audio/albumart");
                Uri songArtwork = ContentUris.withAppendedId(sArtworkUri, albumId);

                String currentTitle, currentArtist, currentFileUrl, currentArtwork;
                try{
                    currentTitle = songCursor.getString(songTitle);
                }catch (Exception e){
                    currentTitle = "Unknown Track";
                }

                try{
                    currentArtist = songCursor.getString(songArtist);
                }catch (Exception e){
                    currentArtist = "Unknown Artist";
                }

                currentArtwork = songArtwork.toString();
                currentFileUrl = songCursor.getString(songUrl);


                Track t = new Track(Long.toString(currentId), currentTitle, currentFileUrl, PlayMode.OFFLINE);
                t.setMoodName(currentArtist);
                t.setArtwork(currentArtwork);

                if(!currentArtist.contains("unknown")){
                    mSongList.add(t);
                }

            } while(songCursor.moveToNext());
        }
        if (songCursor!= null) {
            songCursor.close();
            songCursor = null;
        }
        return mSongList;
    }


    public ArrayList<Track> getTracks(){
        ArrayList<Track> localTracks = new ArrayList<>();
        String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";
        String[] projection = {
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DATA
        };

        final String sortOrder = MediaStore.Audio.AudioColumns.TITLE + " COLLATE LOCALIZED ASC";

        Cursor songCursor = contentResolver.query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection,
                selection,
                null,
                MediaStore.Audio.Media.DEFAULT_SORT_ORDER);

        if(songCursor != null && songCursor.moveToFirst())
        {
            int songId = songCursor.getColumnIndex(MediaStore.Audio.Media._ID);
            int songTitle = songCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int songArtist = songCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
            int songUrl = songCursor.getColumnIndex(MediaStore.Audio.Media.DATA);


            do {

                long currentId = songCursor.getLong(songId);

                long albumId = getAlbumId(contentResolver, currentId);
                Uri sArtworkUri = Uri.parse("content://media/external/audio/albumart");
                Uri songArtwork = ContentUris.withAppendedId(sArtworkUri, albumId);

                String currentTitle, currentArtist, currentFileUrl, currentArtwork;
                try{
                    currentTitle = songCursor.getString(songTitle);
                }catch (Exception e){
                    currentTitle = "Unknown Track";
                }

                try{
                     currentArtist = songCursor.getString(songArtist);
                }catch (Exception e){
                    currentArtist = "Unknown Artist";
                }

                currentArtwork = songArtwork.toString();
                currentFileUrl = songCursor.getString(songUrl);


                Track t = new Track(Long.toString(currentId), currentTitle, currentFileUrl, PlayMode.OFFLINE);
                t.setMoodName(currentArtist);
                t.setArtwork(currentArtwork);

                if(!currentArtist.contains("unknown")){
                    localTracks.add(t);
                }

            } while(songCursor.moveToNext());
        }

        if (songCursor != null) {
            songCursor.close();
        }

        return localTracks;

    }

    private long getAlbumId(ContentResolver resolver, long id){
        Cursor cursor;
        String[] projection = {
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.ALBUM_ID
        };

        String[] arguments = {
                Long.toString(id)
        };

        cursor = resolver.query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection,
                MediaStore.Audio.Media._ID + " = ?",
                arguments,
                null);

        if(cursor != null && cursor.moveToFirst()){
            long albumId = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID));
            cursor.close();
            return albumId;
        }
       return -1;
    }


}
