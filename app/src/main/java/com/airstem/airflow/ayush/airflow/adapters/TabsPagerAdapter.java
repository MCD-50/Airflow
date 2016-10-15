package com.airstem.airflow.ayush.airflow.adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.airstem.airflow.ayush.airflow.IFeelFragment;
import com.airstem.airflow.ayush.airflow.MyFavFragment;


/**
 * Created by ayush on 09-10-16.
 */

public class TabsPagerAdapter extends FragmentPagerAdapter {

    private String[] tabs = { "I FEEL", "MY FAVS" };
    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {

        switch (index) {
            case 0:
                return new IFeelFragment();
            case 1:
                return new MyFavFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }

}
