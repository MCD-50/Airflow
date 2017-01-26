package com.airstem.airflow.ayush.airflow.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.airstem.airflow.ayush.airflow.ArtistFragment;
import com.airstem.airflow.ayush.airflow.DiscoverFragment;
import com.airstem.airflow.ayush.airflow.IFeelFragment;
import com.airstem.airflow.ayush.airflow.LocalTrackFragment;
import com.airstem.airflow.ayush.airflow.MyFavFragment;
import com.airstem.airflow.ayush.airflow.PlaylistFragment;
import com.airstem.airflow.ayush.airflow.TracksFragment;

/**
 * Created by ayush AS on 7/1/17.
 */

public class LibraryTabsAdapter extends FragmentPagerAdapter {

    private String[] tabs = { "TRACKS", "ARTISTS", "PLAYLIST" };
    public LibraryTabsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {

        switch (index) {
            case 0:
                return new TracksFragment();
            case 1:
                return new ArtistFragment();
            case 2:
                return new PlaylistFragment();

        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }

}
