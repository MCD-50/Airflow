package com.airstem.airflow.ayush.airflow.helpers.collection;

import com.airstem.airflow.ayush.airflow.events.collection.CollectionProviderListener;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionArtist;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionPlaylist;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionRadio;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionTrack;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionVideo;

import java.util.ArrayList;

/**
 * Created by mcd-50 on 14/7/17.
 */

public class CollectionProvider implements DatabaseListener {

    private static CollectionProvider collectionProvider = null;
    private CollectionProviderListener collectionProviderListener;

    public static CollectionProvider getInstance(CollectionProviderListener listener){
        if(collectionProvider == null){
            collectionProvider = new CollectionProvider();
        }
        collectionProvider.collectionProviderListener = listener;
        return collectionProvider;
    }


    public final ArrayList<CollectionTrack> mTracks = new ArrayList<>();
    public final ArrayList<CollectionArtist> mArtists = new ArrayList<>();
    public final ArrayList<CollectionPlaylist> mPlaylists = new ArrayList<>();
    public final ArrayList<CollectionVideo> mVideos = new ArrayList<>();
    public final ArrayList<CollectionRadio> mRadios = new ArrayList<>();

    @Override
    public synchronized void onRadioCreate(CollectionRadio collectionRadio) {
        mRadios.add(collectionRadio);
        collectionProviderListener.onRadioUpdated(mRadios);
    }

    @Override
    public synchronized void onRadioDelete(final CollectionRadio collectionRadio) {
        int index = CollectionHelper.getRadioIndex(mRadios, collectionRadio.getDatabaseId());
        if(index > -1){
            mRadios.remove(index);
            collectionProviderListener.onRadioUpdated(mRadios);
        }
    }

    @Override
    public synchronized void onTrackCreate(CollectionTrack collectionTrack) {
        mTracks.add(collectionTrack);
        collectionProviderListener.onTracksUpdated(mTracks);
    }

    @Override
    public synchronized void onTrackDelete(CollectionTrack collectionTrack) {
        int index = CollectionHelper.getTrackIndex(mTracks, collectionTrack.getDatabaseId());
        if(index > -1){
            mTracks.remove(index);
            collectionProviderListener.onTracksUpdated(mTracks);
        }
    }

    @Override
    public synchronized void onTrackArtworkUpdated(CollectionTrack collectionTrack) {
        int index = CollectionHelper.getTrackIndex(mTracks, collectionTrack.getDatabaseId());
        if(index > -1){
            mTracks.remove(index);
            mTracks.add(index, collectionTrack);
            collectionProviderListener.onTracksUpdated(mTracks);
        }
    }

    @Override
    public synchronized void onVideoCreate(CollectionVideo collectionVideo) {
        mVideos.add(collectionVideo);
        collectionProviderListener.onVideosUpdated(mVideos);
    }

    @Override
    public synchronized void onVideoDelete(CollectionVideo collectionVideo) {
        int index = CollectionHelper.getVideoIndex(mVideos, collectionVideo.getDatabaseId());
        if(index > -1){
            mVideos.remove(index);
            collectionProviderListener.onVideosUpdated(mVideos);
        }
    }

    @Override
    public synchronized void onVideoArtworkUpdated(CollectionVideo collectionVideo) {
        int index = CollectionHelper.getVideoIndex(mVideos, collectionVideo.getDatabaseId());
        if(index > -1){
            mVideos.remove(index);
            mVideos.add(index, collectionVideo);
            collectionProviderListener.onVideosUpdated(mVideos);
        }
    }

    @Override
    public synchronized void onArtistCreate(CollectionArtist collectionArtist) {
        mArtists.add(collectionArtist);
        collectionProviderListener.onArtistsUpdated(mArtists);
    }

    @Override
    public synchronized void onArtistDelete(CollectionArtist collectionArtist) {
        int index = CollectionHelper.getArtistIndex(mArtists, collectionArtist.getDatabaseId());
        if(index > -1){
            mArtists.remove(index);
            collectionProviderListener.onArtistsUpdated(mArtists);
        }
    }

    @Override
    public synchronized void onArtistArtworkUpdated(CollectionArtist collectionArtist) {
        int index = CollectionHelper.getArtistIndex(mArtists, collectionArtist.getDatabaseId());
        if(index > -1){
            mArtists.remove(index);
            mArtists.add(index, collectionArtist);
            collectionProviderListener.onArtistsUpdated(mArtists);
        }
    }

    @Override
    public synchronized void onPlaylistCreate(CollectionPlaylist collectionPlaylist) {
        mPlaylists.add(collectionPlaylist);
        collectionProviderListener.onPlaylistsUpdated(mPlaylists);
    }

    @Override
    public synchronized void onPlaylistDelete(CollectionPlaylist collectionPlaylist) {
        int index = CollectionHelper.getPlaylistIndex(mPlaylists, collectionPlaylist.getDatabaseId());
        if(index > -1){
            mPlaylists.remove(index);
            collectionProviderListener.onPlaylistsUpdated(mPlaylists);
        }
    }

    @Override
    public synchronized void onPlaylistAddTrack(CollectionTrack collectionTrack) {
        int index = CollectionHelper.getTrackIndex(mTracks, collectionTrack.getDatabaseId());
        if(index > -1){
            mTracks.remove(index);
            mTracks.add(index, collectionTrack);
            collectionProviderListener.onTracksUpdated(mTracks);
        }
    }

    @Override
    public synchronized void onPlaylistRemoveTrack(CollectionTrack collectionTrack) {
        int index = CollectionHelper.getTrackIndex(mTracks, collectionTrack.getDatabaseId());
        if(index > -1){
            mTracks.remove(index);
            mTracks.add(index, collectionTrack);
            collectionProviderListener.onTracksUpdated(mTracks);
        }
    }
}
