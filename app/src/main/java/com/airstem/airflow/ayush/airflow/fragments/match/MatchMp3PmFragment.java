package com.airstem.airflow.ayush.airflow.fragments.match;

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

import com.afollestad.materialdialogs.MaterialDialog;
import com.airstem.airflow.ayush.airflow.FavActivity;
import com.airstem.airflow.ayush.airflow.ManualMatchActivity;
import com.airstem.airflow.ayush.airflow.R;
import com.airstem.airflow.ayush.airflow.SearchActivity;
import com.airstem.airflow.ayush.airflow.adapters.collection.RadioAdapter;
import com.airstem.airflow.ayush.airflow.adapters.search.ManualMatchAdapter;
import com.airstem.airflow.ayush.airflow.adapters.search.TrackAdapter;
import com.airstem.airflow.ayush.airflow.decorators.LineDivider;
import com.airstem.airflow.ayush.airflow.enums.collection.Action;
import com.airstem.airflow.ayush.airflow.events.collection.CollectionRadioListener;
import com.airstem.airflow.ayush.airflow.events.search.ManualMatchListener;
import com.airstem.airflow.ayush.airflow.events.search.SearchTrackListener;
import com.airstem.airflow.ayush.airflow.events.volly.Callback;
import com.airstem.airflow.ayush.airflow.helpers.collection.ActionHelper;
import com.airstem.airflow.ayush.airflow.helpers.collection.CollectionConstant;
import com.airstem.airflow.ayush.airflow.helpers.collection.CollectionHelper;
import com.airstem.airflow.ayush.airflow.helpers.collection.MatchHelper;
import com.airstem.airflow.ayush.airflow.helpers.database.DatabaseHelper;
import com.airstem.airflow.ayush.airflow.helpers.internet.InternetHelper;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionArtist;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionRadio;
import com.airstem.airflow.ayush.airflow.model.collection.CollectionTrack;
import com.airstem.airflow.ayush.airflow.model.search.ManualMatch;
import com.airstem.airflow.ayush.airflow.model.search.SearchAlbum;
import com.airstem.airflow.ayush.airflow.model.search.SearchArtist;
import com.airstem.airflow.ayush.airflow.model.search.SearchImage;
import com.airstem.airflow.ayush.airflow.model.search.SearchPaging;
import com.airstem.airflow.ayush.airflow.model.search.SearchRadio;
import com.airstem.airflow.ayush.airflow.model.search.SearchTrack;
import com.airstem.airflow.ayush.airflow.model.search.SearchVideo;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import io.realm.OrderedCollectionChangeSet;
import io.realm.OrderedRealmCollectionChangeListener;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by mcd-50 on 2/10/17.
 */

public class MatchMp3PmFragment extends Fragment implements ManualMatchListener {

    Realm realm;
    ActionHelper actionHelper;

    TextView empty;
    RecyclerView listView;
    LinearLayoutManager linearLayoutManager;

    ArrayList<ManualMatch> mItems;
    ManualMatchAdapter mAdapter;



    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.manual_match_fragment, container, false);

        empty = (TextView) rootView.findViewById(R.id.manual_match_fragment_empty);
        listView = (RecyclerView) rootView.findViewById(R.id.manual_match_fragment_list);
        listView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getContext());
        listView.setLayoutManager(linearLayoutManager);
        listView.addItemDecoration(new LineDivider(getContext()));
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setAdapter();
        realm = ((ManualMatchActivity) getActivity()).getRealm();
        actionHelper = ((ManualMatchActivity) getActivity()).getActionHelper();
    }


    private void setAdapter() {
        mItems = new ArrayList<>();
        mAdapter = new ManualMatchAdapter(getContext(), mItems, this);
        listView.setAdapter(mAdapter);
    }

    public void reloadAdapter(ArrayList<ManualMatch> items){
        mItems.clear();
        mItems.addAll(items);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onTrackClick(final ManualMatch manualMatch) {
        final CollectionTrack collectionTrack = ((ManualMatchActivity)getActivity()).getCollectionTrack();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                collectionTrack.setIsMatched(true);
                collectionTrack.setIsMatchError(false);
                collectionTrack.setTrackOnlineUrl(manualMatch.getUrl());
            }
        });
    }

    @Override
    public void onTrackOption(final ManualMatch manualMatch) {
        final CollectionTrack collectionTrack = ((ManualMatchActivity)getActivity()).getCollectionTrack();
        final ArrayList<String> options = CollectionHelper.prepareOptionFromMatchTrack();

        new MaterialDialog.Builder(getContext())
                .title("Options")
                .items(options)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                        actionHelper.performAction(options.get(which), collectionTrack, manualMatch);
                    }
                })
                .show();
    }
}

