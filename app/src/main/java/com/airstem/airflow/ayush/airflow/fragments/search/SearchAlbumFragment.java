package com.airstem.airflow.ayush.airflow.fragments.search;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.airstem.airflow.ayush.airflow.R;
import com.airstem.airflow.ayush.airflow.SearchActivity;
import com.airstem.airflow.ayush.airflow.SearchAlbumInfoActivity;
import com.airstem.airflow.ayush.airflow.adapters.search.AlbumAdapter;
import com.airstem.airflow.ayush.airflow.decorators.OffsetDivider;
import com.airstem.airflow.ayush.airflow.events.search.SearchAlbumListener;
import com.airstem.airflow.ayush.airflow.events.volly.Callback;
import com.airstem.airflow.ayush.airflow.helpers.collection.CollectionConstant;
import com.airstem.airflow.ayush.airflow.helpers.internet.InternetHelper;
import com.airstem.airflow.ayush.airflow.model.search.SearchAlbum;
import com.airstem.airflow.ayush.airflow.model.search.SearchArtist;
import com.airstem.airflow.ayush.airflow.model.search.SearchImage;
import com.airstem.airflow.ayush.airflow.model.search.SearchPaging;
import com.airstem.airflow.ayush.airflow.model.search.SearchRadio;
import com.airstem.airflow.ayush.airflow.model.search.SearchTrack;
import com.airstem.airflow.ayush.airflow.model.search.SearchVideo;

import org.json.JSONObject;

import java.util.ArrayList;

import io.realm.Realm;

/**
 * Created by mcd-50 on 8/7/17.
 */

public class SearchAlbumFragment extends Fragment implements SearchAlbumListener {

    Realm realm;

    boolean isLoading;
    int nextPage = 0;
    ProgressDialog progressDialog;
    InternetHelper internetHelper;

    TextView empty;
    RecyclerView listView;
    SwipeRefreshLayout swipeRefreshLayout;
    GridLayoutManager gridLayoutManager;

    ArrayList<SearchAlbum> mItems;
    AlbumAdapter mAdapter;

    int numberOfColumns = 2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.search_album_fragment, container, false);

        progressDialog = new ProgressDialog(getContext());
        internetHelper = new InternetHelper(getContext());


        empty = (TextView) rootView.findViewById(R.id.search_album_fragment_empty);
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.search_album_fragment_refresh);
        listView = (RecyclerView) rootView.findViewById(R.id.search_album_fragment_list);
        listView.setHasFixedSize(true);
        gridLayoutManager = new GridLayoutManager(getActivity(), numberOfColumns);
        listView.setLayoutManager(gridLayoutManager);
        listView.addItemDecoration(new OffsetDivider(getContext(), R.dimen.five_dp_margin));

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setAdapter();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                nextPage = 0;
                mItems.clear();
                makeRequest(true);
            }
        });
    }

    private void setAdapter() {
        mItems = new ArrayList<>();
        mAdapter = new AlbumAdapter(getContext(), mItems, this);
        listView.setAdapter(mAdapter);
    }


    public boolean hasLoaded = false;
   /* @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(this.isVisible()){
            if(isVisibleToUser && !hasLoaded){
                makeRequest(true);
            }
            hasLoaded = true;
        }
    }*/

    public void makeRequest(boolean showDialog){
        realm = ((SearchActivity) getActivity()).getRealm();

        if (internetHelper.isNetworkAvailable()) {
            onNetworkAvailable(showDialog);
            hasLoaded = true;
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
                int totalItemCount = gridLayoutManager.getItemCount();
                int lastVisibleItem = gridLayoutManager.findLastVisibleItemPosition();
                if (nextPage != -1 && !isLoading && totalItemCount <= (lastVisibleItem + 1)) {
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

            internetHelper.searchDeezer(((SearchActivity) getActivity()).getSearchQuery(), nextPage, new Callback() {
                @Override
                public void OnSuccess(ArrayList<Object> items) {
                    int x = 1;
                }

                @Override
                public void onSearch(ArrayList<SearchTrack> tracks, ArrayList<SearchAlbum> albums, ArrayList<SearchArtist> artists, ArrayList<SearchVideo> videos, ArrayList<SearchRadio> radios) {
                    int x = 1;
                }

                @Override
                public void onArtistImages(ArrayList<SearchImage> searchImages) {
                    int x = 1;
                }

                @Override
                public void onAlbumImages(ArrayList<SearchImage> searchImages) {
                    int x = 1;
                }

                @Override
                public void onLyrics(String text) {
                    int x = 1;
                }

                @Override
                public void onMatch(String downloadUrl) {

                }

                @Override
                public void onVideoMatch(ArrayList<JSONObject> videoMatchUrl) {

                }

                @Override
                public void onSuccess(ArrayList<SearchTrack> searchTracks, ArrayList<SearchArtist> searchArtists, ArrayList<SearchAlbum> searchAlbums, SearchPaging searchPaging) {
                    mItems.addAll(searchAlbums);
                    mAdapter.notifyDataSetChanged();
                    progressDialog.dismiss();
                    nextPage = Integer.parseInt(searchPaging.getAlbumNextPage());
                    swipeRefreshLayout.setRefreshing(false);
                    isLoading = false;
                }

                @Override
                public void onTracks(ArrayList<SearchTrack> searchTracks, String next) {
                    int x = 1;
                }

                @Override
                public void onVideos(ArrayList<SearchVideo> searchVideos, String next) {
                    int x = 1;
                }

                @Override
                public void onRadios(ArrayList<SearchRadio> searchRadios) {
                    int x = 1;
                }

                @Override
                public void OnFailure(String message) {
                    progressDialog.dismiss();
                    swipeRefreshLayout.setRefreshing(false);
                    isLoading = false;
                }
            });

        } catch (Exception e) {
            isLoading = false;
            progressDialog.dismiss();
            swipeRefreshLayout.setRefreshing(false);
            empty.setVisibility(View.VISIBLE);

            e.printStackTrace();
        }
    }




    @Override
    public void onAlbumClick(SearchAlbum searchAlbum) {
        Intent searchIntent = new Intent(getActivity(), SearchAlbumInfoActivity.class);
        searchIntent.putExtra(CollectionConstant.SHARED_PASSING_SEARCH_ALBUM, searchAlbum);
        startActivity(searchIntent);
    }

    @Override
    public void onAlbumTrackClick(SearchTrack searchTrack) {
        
    }

}
