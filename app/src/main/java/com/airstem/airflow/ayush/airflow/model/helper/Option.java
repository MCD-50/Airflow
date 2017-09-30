package com.airstem.airflow.ayush.airflow.model.helper;

/**
 * Created by mcd-50 on 24/9/17.
 */

public class Option {
    private String mText;
    private int mImage;


    public Option(String mText, int mImage) {
        this.mText = mText;
        this.mImage = mImage;
    }

    public String getText() {
        return mText;
    }

    public void setText(String mText) {
        this.mText = mText;
    }

    public int getImage() {
        return mImage;
    }

    public void setImage(int mImage) {
        this.mImage = mImage;
    }
}
