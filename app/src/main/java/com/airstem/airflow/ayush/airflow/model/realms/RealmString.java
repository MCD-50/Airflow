package com.airstem.airflow.ayush.airflow.model.realms;

import io.realm.RealmObject;

/**
 * Created by mcd-50 on 5/9/17.
 */

public class RealmString extends RealmObject {
    private String mString;

    public RealmString(String mString) {
        this.mString = mString;
    }

    public RealmString() {

    }

    public String getString() {
        return mString;
    }

    public void setString(String mString) {
        this.mString = mString;
    }
}
