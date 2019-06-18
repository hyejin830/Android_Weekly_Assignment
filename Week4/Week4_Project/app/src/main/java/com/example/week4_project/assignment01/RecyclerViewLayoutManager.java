package com.example.week4_project.assignment01;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.util.AttributeSet;

public class RecyclerViewLayoutManager extends GridLayoutManager {

    public RecyclerViewLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public RecyclerViewLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }

    public RecyclerViewLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
    }

}
