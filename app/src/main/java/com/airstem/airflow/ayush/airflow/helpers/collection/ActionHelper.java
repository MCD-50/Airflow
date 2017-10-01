package com.airstem.airflow.ayush.airflow.helpers.collection;

import android.content.Context;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.airstem.airflow.ayush.airflow.R;
import com.airstem.airflow.ayush.airflow.helpers.database.DatabaseHelper;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionArtist;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionPlaylist;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionRadio;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionTrack;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionVideo;
import com.airstem.airflow.ayush.airflow.model.realms.RealmString;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by mcd-50 on 30/9/17.
 */

public class ActionHelper {

    private Context mContext;
    private Realm mRealm;

    public ActionHelper(Context context, Realm realm){
        this.mContext = context;
        this.mRealm = realm;
    }

    public void performAction(String action, final CollectionTrack collectionTrack) {
        switch (action){
            case CollectionConstant.ADD_TO_FAV_OPTION :
            case CollectionConstant.REMOVE_FROM_FAV_OPTION:
                mRealm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        collectionTrack.setIsFav(!collectionTrack.getIsFav());
                    }
                });
                break;
            case CollectionConstant.PLAY_TRACK_OPTION:
            case CollectionConstant.STREAM_TRACK_OPTION:
                break;
            case CollectionConstant.DELETE_OPTION:
                mRealm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        collectionTrack.deleteFromRealm();
                    }
                });
                break;
            case CollectionConstant.RE_MATCH_OPTION:
            case CollectionConstant.MANUAL_MATCH_OPTION:
                break;
            case CollectionConstant.ADD_TO_PLAYLIST_OPTION:
                final RealmResults<CollectionPlaylist> collectionPlaylists = mRealm.where(CollectionPlaylist.class).findAllSorted("mTitle");
                if(collectionPlaylists.size() > 0){
                    ArrayList<String> items = new ArrayList<String>();
                    for(CollectionPlaylist x : collectionPlaylists){
                        items.add(x.getTitle());
                    }

                    new MaterialDialog.Builder(mContext)
                            .title("Select Playlist")
                            .items(items)
                            .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                                @Override
                                public boolean onSelection(MaterialDialog dialog, View view, final int which, final CharSequence text) {
                                    mRealm.executeTransaction(new Realm.Transaction() {
                                        @Override
                                        public void execute(Realm realm) {
                                            int index = CollectionHelper.getItemIndex(collectionPlaylists.get(which), collectionTrack.getId());
                                            if(index < 0){
                                                collectionPlaylists.get(which).getPlaylists().add(new RealmString(collectionTrack.getId()));
                                            }
                                        }
                                    });
                                    return true;
                                }
                            })
                            .positiveText("CHOOSE")
                            .show();
                }
                break;
        }
    }

    public void performActionPlaylistTrack(String action, final CollectionTrack collectionTrack, final CollectionPlaylist collectionPlaylist) {
        switch (action){
            case CollectionConstant.ADD_TO_FAV_OPTION :
            case CollectionConstant.REMOVE_FROM_FAV_OPTION:
                mRealm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        collectionTrack.setIsFav(!collectionTrack.getIsFav());
                    }
                });
                break;
            case CollectionConstant.PLAY_TRACK_OPTION:
            case CollectionConstant.STREAM_TRACK_OPTION:
                break;
            case CollectionConstant.DELETE_OPTION:
                mRealm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        collectionTrack.deleteFromRealm();
                    }
                });
                break;
            case CollectionConstant.RE_MATCH_OPTION:
            case CollectionConstant.MANUAL_MATCH_OPTION:
                break;
            case CollectionConstant.REMOVE_FROM_PLAYLIST_OPTION:
                mRealm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        int index = CollectionHelper.getItemIndex(collectionPlaylist, collectionTrack.getId());
                        if(index > -1) {
                            collectionPlaylist.getPlaylists().remove(index);
                        }
                    }
                });
                break;
        }
    }


    public void performAction(String action, final CollectionRadio collectionRadio) {
        switch (action){
            case CollectionConstant.START_RADIO_OPTION:
                break;
            case CollectionConstant.REMOVE_FROM_FAV_OPTION:
                mRealm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        collectionRadio.setIsFav(false);
                    }
                });
                break;
        }
    }

    public void performAction(String action, final CollectionVideo collectionVideo) {
        switch (action){
            case CollectionConstant.ADD_TO_FAV_OPTION :
            case CollectionConstant.REMOVE_FROM_FAV_OPTION:
                mRealm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        collectionVideo.setIsFav(!collectionVideo.getIsFav());
                    }
                });
                break;
            case CollectionConstant.PLAY_VIDEO_OPTION:
            case CollectionConstant.STREAM_VIDEO_OPTION:
                break;
            case CollectionConstant.DELETE_OPTION:
                mRealm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        collectionVideo.deleteFromRealm();
                    }
                });
                break;
        }
    }

    public void performAction(String action, final CollectionArtist collectionArtist) {
        switch (action){
            case CollectionConstant.SHUFFLE_TRACKS_OPTION:
                break;

            case CollectionConstant.DELETE_OPTION:
                mRealm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        //delete all artist tracks
                        realm.where(CollectionTrack.class).equalTo("mArtistId", collectionArtist.getId())
                                .findAll()
                                .deleteAllFromRealm();
                        //delete artists
                        collectionArtist.deleteFromRealm();
                    }
                });
                break;
            case CollectionConstant.ADD_TO_PLAYLIST_OPTION:
                final RealmResults<CollectionPlaylist> collectionPlaylists = mRealm.where(CollectionPlaylist.class).findAllSorted("mTitle");
                if(collectionPlaylists.size() > 0){
                    ArrayList<String> items = new ArrayList<String>();
                    for(CollectionPlaylist x : collectionPlaylists){
                        items.add(x.getTitle());
                    }

                    new MaterialDialog.Builder(mContext)
                            .title("Select Playlist")
                            .items(items)
                            .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                                @Override
                                public boolean onSelection(MaterialDialog dialog, View view, final int which, final CharSequence text) {
                                    mRealm.executeTransaction(new Realm.Transaction() {
                                        @Override
                                        public void execute(Realm realm) {
                                            RealmResults<CollectionTrack> collectionTracks = realm.where(CollectionTrack.class).equalTo("mArtistId", collectionArtist.getId()).findAll();
                                            for(CollectionTrack x : collectionTracks){
                                                int index = CollectionHelper.getItemIndex(collectionPlaylists.get(which), x.getId());
                                                if(index < 0){
                                                    collectionPlaylists.get(which).getPlaylists().add(new RealmString(x.getId()));
                                                }
                                            }
                                        }
                                    });
                                    return true;
                                }
                            })
                            .positiveText("CHOOSE")
                            .show();
                }
                break;
        }
    }

    public void performAction(String action, final CollectionPlaylist collectionPlaylist) {
        switch (action){
            case CollectionConstant.SHUFFLE_TRACKS_OPTION:
                break;

            case CollectionConstant.DELETE_OPTION:
                mRealm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        collectionPlaylist.deleteFromRealm();
                    }
                });
                break;
        }
    }
}
