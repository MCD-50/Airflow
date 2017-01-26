package com.airstem.airflow.ayush.airflow;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.airstem.airflow.ayush.airflow.adapters.CategoryAdapter;
import com.airstem.airflow.ayush.airflow.adapters.DiscoverAdapter;
import com.airstem.airflow.ayush.airflow.adapters.ListDiscoverAdapter;
import com.airstem.airflow.ayush.airflow.helpers.ClickListener;
import com.airstem.airflow.ayush.airflow.helpers.RadioHelper;
import com.airstem.airflow.ayush.airflow.model.Track;

/**
 * Created by ayush on 16-11-16.
 */
public class DiscoverFragment extends ListFragment {

    //RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.discover_fragment, container, false);
        //recyclerView = (RecyclerView) rootView.findViewById(R.id.discover_fragment_discoverRecycleView);
        //recyclerView.setHasFixedSize(true);
        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        //linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //recyclerView.setLayoutManager(linearLayoutManager);
        return rootView;

     /*   recyclerView.setAdapter(new DiscoverAdapter(getActivity(), new ClickListener() {
            @Override
            public void OnItemClick(Track track) {
                ((MainActivity) getActivity()).playRadio(track);
            }
        }, radioHelper.getRadioArrayList()));*/
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        RadioHelper radioHelper = new RadioHelper();
        final ListDiscoverAdapter adapter = new ListDiscoverAdapter(getActivity(), radioHelper.getRadioArrayList());
        setListAdapter(adapter);
        getListView().setDividerHeight(0);
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((MainActivity) getActivity()).playRadio(position, adapter);
            }
        });

    }

}
