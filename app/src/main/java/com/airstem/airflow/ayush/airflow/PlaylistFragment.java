package com.airstem.airflow.ayush.airflow;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.airstem.airflow.ayush.airflow.adapters.PlaylistAdapter;
import com.airstem.airflow.ayush.airflow.helpers.ClickListener;
import com.airstem.airflow.ayush.airflow.helpers.LocalMusicHelper;
import com.airstem.airflow.ayush.airflow.model.Artist;
import com.airstem.airflow.ayush.airflow.model.Playlist;
import com.airstem.airflow.ayush.airflow.model.Track;

import java.util.ArrayList;

/**
 * Created by ayush AS on 7/1/17.
 */

public class PlaylistFragment extends Fragment {

    PlaylistAdapter adapter;
    RecyclerView recyclerView;
    TextView message;
    ProgressDialog dialog;
    LocalMusicHelper localMusicHelper;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.library_playlist_fragment, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.dataList);
        message = (TextView) rootView.findViewById(R.id.emptyMessage);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        dialog = new ProgressDialog(getContext());
        localMusicHelper = new LocalMusicHelper(getContext());
        new getTracks().execute();
    }

    class getTracks extends AsyncTask<Void, Void, ArrayList<Playlist>> {

        @Override
        protected ArrayList<Playlist> doInBackground(Void... voids) {
            return localMusicHelper.getPlaylist();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setMessage("Please wait...");
            dialog.setIndeterminate(true);
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected void onPostExecute(final ArrayList<Playlist> playlists) {
            super.onPostExecute(playlists);

            dialog.dismiss();

            if(playlists == null || playlists.size() == 0){
                message.setVisibility(View.VISIBLE);
            }else {
                message.setVisibility(View.GONE);
                recyclerView.setAdapter(new PlaylistAdapter(getActivity(), new ClickListener() {
                    @Override
                    public void OnItemClick(Track track) {

                    }

                    @Override
                    public void OnItemClick(Artist artist) {

                    }

                    @Override
                    public void OnItemClick(Playlist playlist) {
                        onPlaylistClicked(playlist);
                    }
                }, playlists));
            }
        }
    }


    private void onPlaylistClicked(Playlist playlist){
        ((CollectionActivity) getActivity()).navigateToPlaylistPage(playlist);
    }

}
