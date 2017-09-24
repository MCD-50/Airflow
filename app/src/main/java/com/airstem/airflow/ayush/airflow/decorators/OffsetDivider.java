package com.airstem.airflow.ayush.airflow.decorators;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.DimenRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by mcd-50 on 23/9/17.
 */

public class OffsetDivider extends RecyclerView.ItemDecoration {

    private int mItemOffset;

    public OffsetDivider(int itemOffset) {
        mItemOffset = itemOffset;
    }

    public OffsetDivider(@NonNull Context context, @DimenRes int itemOffsetId) {
        this(context.getResources().getDimensionPixelSize(itemOffsetId));
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(mItemOffset, mItemOffset, mItemOffset, mItemOffset);
    }
}
