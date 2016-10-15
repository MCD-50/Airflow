package com.airstem.airflow.ayush.airflow;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.airstem.airflow.ayush.airflow.adapters.FavAdapter;
import com.airstem.airflow.ayush.airflow.helpers.DatabaseEvent;
import com.airstem.airflow.ayush.airflow.helpers.EventHelper;
import com.airstem.airflow.ayush.airflow.model.Track;
import java.util.ArrayList;

/**
 * Created by ayush on 09-10-16.
 */

public class MyFavFragment extends ListFragment implements DatabaseEvent {

    FavAdapter adapter;
    //TextView textView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.my_favs_fragment, container, false);
        //textView = (TextView)rootView.findViewById(R.id.empty);
        return rootView;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayList<Track> tracks = ((MainActivity)getActivity()).getTracksFromDatabase();
        adapter = new FavAdapter(getActivity(), tracks);
        //registering event
        EventHelper.initDatabaseEvent(this);
        //loading data for first time only the updating
        loadData();
    }



    public void loadData(){
        //setEmptyText(getString(R.string.empty_message));
        setListAdapter(adapter);
        //getListView().setEmptyView(textView);
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((MainActivity) getActivity()).executeMyFavFragmentListViewOnItemSelected(position, adapter);
            }
        });
    }

    @Override
    public void resetAdapter(Track track, boolean addData) {
        if(addData){
            adapter.addData(track);
            adapter.notifyDataSetChanged();
        }else {
            adapter.removeData(((MainActivity)getActivity()).getTracksFromDatabase());
        }


    }
}

