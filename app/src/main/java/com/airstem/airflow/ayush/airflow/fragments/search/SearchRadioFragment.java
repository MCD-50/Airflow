package com.airstem.airflow.ayush.airflow.fragments.search;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.airstem.airflow.ayush.airflow.R;
import com.airstem.airflow.ayush.airflow.adapters.search.RadioAdapter;
import com.airstem.airflow.ayush.airflow.events.search.SearchRadioListener;
import com.airstem.airflow.ayush.airflow.helpers.internet.InternetHelper;
import com.airstem.airflow.ayush.airflow.model.search.SearchRadio;

import java.util.ArrayList;


/**
 * Created by mcd-50 on 8/7/17.
 */

public class SearchRadioFragment extends Fragment implements SearchRadioListener {


    boolean isLoading;
    int nextPage = 1;
    ProgressDialog progressDialog;
    InternetHelper internetHelper;


    TextView empty;
    RecyclerView listView;
    SwipeRefreshLayout swipeRefreshLayout;
    LinearLayoutManager linearLayoutManager;

    ArrayList<SearchRadio> mItems;
    RadioAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.search_radio_fragment, container, false);

        progressDialog = new ProgressDialog(getContext());
        internetHelper = new InternetHelper(getContext());


        empty = (TextView) rootView.findViewById(R.id.search_radio_fragment_empty);
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.search_radio_fragment_refresh);
        listView = (RecyclerView) rootView.findViewById(R.id.search_radio_fragment_list);
        listView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getContext());
        listView.setLayoutManager(linearLayoutManager);


        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setAdapter();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                nextPage = 1;
                makeRequest(true);
            }
        });
    }

    private void setAdapter() {
        mItems = new ArrayList<>();
        mAdapter = new RadioAdapter(getContext(), mItems, this);
        listView.setAdapter(mAdapter);
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
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    private void onNetworkAvailable(final boolean showDialog){
        loadData(showDialog);
        empty.setVisibility(View.GONE);
        listView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int totalItemCount = linearLayoutManager.getItemCount();
                int lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if (nextPage != -1 && !isLoading && totalItemCount <= lastVisibleItem) {
                    nextPage = nextPage + 1;
                    loadData(showDialog);
                }
            }
        });
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




    @Override
    public void onRadioClick(SearchRadio searchRadio) {

    }
}

