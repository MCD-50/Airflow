package com.airstem.airflow.ayush.airflow.model.home;

import java.util.ArrayList;

/**
 * Created by mcd-50 on 5/9/17.
 */

public class Discover {
    private String mTitle;
    private ArrayList<DiscoverItem> mItems;


    public Discover(String mTitle, ArrayList<DiscoverItem> mItems) {
        this.mTitle = mTitle;
        this.mItems = mItems;
    }


    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public ArrayList<DiscoverItem> getItems() {
        return mItems;
    }

    public void setItems(ArrayList<DiscoverItem> mItems) {
        this.mItems = mItems;
    }
}

