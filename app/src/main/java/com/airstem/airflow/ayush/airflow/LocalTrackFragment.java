package com.airstem.airflow.ayush.airflow;


import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.airstem.airflow.ayush.airflow.adapters.CategoryAdapter;
import com.airstem.airflow.ayush.airflow.adapters.DiscoverAdapter;
import com.airstem.airflow.ayush.airflow.adapters.LocalTrackAdapter;
import com.airstem.airflow.ayush.airflow.helpers.ClickListener;
import com.airstem.airflow.ayush.airflow.helpers.LocalMusicHelper;
import com.airstem.airflow.ayush.airflow.model.Track;

import java.util.ArrayList;

/**
 * Created by ayush on 22-11-16.
 */
public class LocalTrackFragment extends ListFragment {

    //RecyclerView mRecyclerView;
    //TextView mTextView;
    LocalTrackAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.local_track_fragment, container, false);
        askPermission();

       /* mRecyclerView = (RecyclerView) rootView.findViewById(R.id.local_track_fragment_tracksRecyclerView);
        mTextView  = (TextView) rootView.findViewById(R.id.local_track_fragment_emptyTextView);*/
        return rootView;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

       /* final LocalMusicHelper localMusicHelper = new LocalMusicHelper(getContext());
        final ArrayList<Track> tracks = localMusicHelper.getTracks();

        if(askPermission()){
            mRecyclerView.setAdapter(new DiscoverAdapter(getActivity(), new ClickListener() {
                @Override
                public void OnItemClick(Track track) {
                    ((MainActivity) getActivity()).executeLocalFragmentListViewOnItemSelected(track, tracks);
                }
            }, tracks));
        }else {
            mRecyclerView.setAdapter(new DiscoverAdapter(getActivity(), new ClickListener() {
                @Override
                public void OnItemClick(Track track) {
                    ((MainActivity) getActivity()).executeLocalFragmentListViewOnItemSelected(track, new ArrayList<Track>());
                }
            }, new ArrayList<Track>()));
        }
       */


        if(getListView().getCount() <= 0){
            ProgressDialog mProgressDialog = new ProgressDialog(getContext());
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();
            setListAdapter(adapter);
            mProgressDialog.dismiss();
        }
        onClick();
    }

    private void askPermission() {
        int permissionCheck = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 109);
            adapter = new LocalTrackAdapter(getActivity(), new ArrayList<Track>());
        } else {
            LocalMusicHelper localMusicHelper = new LocalMusicHelper(getContext());
            adapter = new LocalTrackAdapter(getActivity(), localMusicHelper.getTracks());
        }

    }

    private void onClick(){
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((MainActivity) getActivity()).executeLocalFragmentListViewOnItemSelected(position, adapter);
            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 109) {
            for (int i = 0; i < permissions.length; i++) {
                String permission = permissions[i];
                int grantResult = grantResults[i];

                if (permission.equals(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    if (grantResult != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 109);
                    }
                }
            }
        }
    }
}
