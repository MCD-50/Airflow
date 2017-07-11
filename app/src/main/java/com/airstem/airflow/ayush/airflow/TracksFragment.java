package com.airstem.airflow.ayush.airflow;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.airstem.airflow.ayush.airflow.adapters.TracksAdapter;
import com.airstem.airflow.ayush.airflow.helpers.FragmentEvents;
import com.airstem.airflow.ayush.airflow.helpers.LocalMusicHelper;
import com.airstem.airflow.ayush.airflow.model.Track;

import java.util.ArrayList;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.view.CardListView;

/**
 * Created by ayush AS on 7/1/17.
 */

public class TracksFragment extends Fragment implements FragmentEvents {

    TracksAdapter adapter;
    CardArrayAdapter cardArrayAdapter;
    RecyclerView recyclerView;
    TextView message;
    ProgressDialog dialog;
    LocalMusicHelper localMusicHelper;

    CardListView cardListView;
    ArrayList<Card> cards;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.library_tracks_fragment, container, false);
        //recyclerView = (RecyclerView) rootView.findViewById(R.id.dataList);
        cardListView = (CardListView) rootView.findViewById(R.id.dataList);
        message = (TextView) rootView.findViewById(R.id.emptyMessage);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        localMusicHelper = new LocalMusicHelper(getContext());
        dialog = new ProgressDialog(getContext());
        ((CollectionActivity)getActivity()).setFragmentsEvent(this);
        new getTracks().execute();
    }

    @Override
    public ArrayList<Track> invokeOnShuffleTrack() {
        return adapter.getmList();
    }

    class getTracks extends AsyncTask<Void, Void, ArrayList<Track>>{

        @Override
        protected ArrayList<Track> doInBackground(Void... voids) {
            return localMusicHelper.getTracks();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            cards = new ArrayList<Card>();
            dialog.setMessage("Please wait...");
            dialog.setIndeterminate(true);
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected void onPostExecute(final ArrayList<Track> tracks) {
            super.onPostExecute(tracks);
            dialog.dismiss();
            if(tracks == null || tracks.size() == 0){
                message.setVisibility(View.VISIBLE);
            }else {
                message.setVisibility(View.GONE);
                /*adapter = new TracksAdapter(getActivity(), new ClickListener() {
                    @Override
                    public void OnItemClick(Track track) {
                        onTrackClicked(track, tracks);
                    }

                    @Override
                    public void OnItemClick(Artist artist) {

                    }

                    @Override
                    public void OnItemClick(Playlist playlist) {

                    }
                }, tracks);

                recyclerView.setAdapter(adapter);*/

                setCards(tracks);

                CardArrayAdapter cardArrayAdapter = new CardArrayAdapter(getActivity(), cards);
                cardListView.setAdapter(cardArrayAdapter);

            }
        }
    }

    private void setCards(ArrayList<Track> tracks){
        for (Track track: tracks){
            Card card = new Card(getContext(), R.layout.card_layout);
            card.setTitle(track.getTitle());
            card.setClickable(true);
            card.setOnClickListener(new Card.OnCardClickListener() {
                @Override
                public void onClick(Card card, View view) {
                    Card x = card;
                }
            });
            cards.add(card);
        }
    }


    private void onTrackClicked(Track track, ArrayList<Track> tracks){
        ((CollectionActivity) getActivity()).playTrack(track, tracks);
    }
}
