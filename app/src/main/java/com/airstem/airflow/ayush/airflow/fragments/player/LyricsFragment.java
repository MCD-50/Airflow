package com.airstem.airflow.ayush.airflow.fragments.player;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.airstem.airflow.ayush.airflow.R;
import com.airstem.airflow.ayush.airflow.helpers.internet.InternetHelper;

import java.util.ArrayList;

/**
 * Created by mcd-50 on 11/7/17.
 */

public class LyricsFragment extends Fragment {


    boolean isLoading = false;
    InternetHelper internetHelper;
    ProgressDialog progressDialog;

    TextView empty, lyrics;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.player_lyrics_fragment, container, false);

        progressDialog = new ProgressDialog(getContext());
        internetHelper = new InternetHelper(getContext());

        lyrics = (TextView) rootView.findViewById(R.id.player_lyrics_fragment_lyrics);

        return rootView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public boolean hasLoaded = false;
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(this.isVisible()){
            if(isVisibleToUser && !hasLoaded){
                makeRequest(true);
            }
            hasLoaded = true;
        }
    }

    public void makeRequest(boolean showDialog){
        if (internetHelper.isNetworkAvailable()) {
            onNetworkAvailable(showDialog);
        } else {
            empty.setVisibility(View.VISIBLE);
        }
    }

    private void onNetworkAvailable(final boolean showDialog){
        loadData(showDialog);
        empty.setVisibility(View.GONE);
    }


    private void loadData(boolean showDialog) {
        try {
            isLoading = true;
            if(showDialog){
                progressDialog.setMessage("Please wait...");
                progressDialog.setCancelable(false);
                progressDialog.show();
            }

        } catch (Exception e) {
            isLoading = false;
            empty.setVisibility(View.VISIBLE);

            e.printStackTrace();
        }
    }
}
