package com.airstem.airflow.ayush.airflow.behaviors;

import android.content.res.Resources;
import android.support.v4.view.ViewCompat;
import android.view.View;

import com.airstem.airflow.ayush.airflow.R;

import jp.satorufujiwara.scrolling.Behavior;

/**
 * Created by mcd-50 on 9/7/17.
 */

public class TitleBehavior extends Behavior {

    private final int scrollLimitHeight;

    public TitleBehavior(Resources r) {
        scrollLimitHeight = r.getDimensionPixelOffset(R.dimen.title_scroll_height);
    }

    @Override
    protected void onScrolled(View target, int scrollY, int dy) {
        ViewCompat.setTranslationY(target, -Math.min(scrollY, scrollLimitHeight));
    }
}