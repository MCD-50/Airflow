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

import com.airstem.airflow.ayush.airflow.adapters.ArtistAdapter;
import com.airstem.airflow.ayush.airflow.helpers.ClickListener;
import com.airstem.airflow.ayush.airflow.helpers.LocalMusicHelper;
import com.airstem.airflow.ayush.airflow.model.Artist;
import com.airstem.airflow.ayush.airflow.model.Playlist;
import com.airstem.airflow.ayush.airflow.model.Track;

import java.util.ArrayList;

/**
 * Created by ayush AS on 7/1/17.
 */

public class ArtistFragment extends Fragment {


    ArtistAdapter adapter;
    RecyclerView recyclerView;
    TextView message;
    ProgressDialog dialog;
    LocalMusicHelper localMusicHelper;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.library_artists_fragment, container, false);
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

    class getTracks extends AsyncTask<Void, Void, ArrayList<Artist>> {



        @Override
        protected ArrayList<Artist> doInBackground(Void... voids) {
            return localMusicHelper.getArtists();
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
        protected void onPostExecute(final ArrayList<Artist> artists) {
            super.onPostExecute(artists);

            dialog.dismiss();

            if(artists == null || artists.size() == 0){
                message.setVisibility(View.VISIBLE);
            }else {
                message.setVisibility(View.GONE);
                recyclerView.setAdapter(new ArtistAdapter(getActivity(), new ClickListener() {
                    @Override
                    public void OnItemClick(Track track) {

                    }

                    @Override
                    public void OnItemClick(Artist artist) {
                        onArtistClicked(artist);
                    }

                    @Override
                    public void OnItemClick(Playlist playlist) {

                    }
                }, artists));
            }
        }
    }


    private void onArtistClicked(Artist artist){
        ((CollectionActivity) getActivity()).navigateToArtistPage(artist);
    }




}
