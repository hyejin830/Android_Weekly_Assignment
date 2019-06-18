package com.example.week4_project.assignment01;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class RecyclerViewItemDecoration extends RecyclerView.ItemDecoration {

    private int dividerHeight;  // 구분선 높이
    private int interval;   // 간격
    private Paint paint;    // 색칠

    public RecyclerViewItemDecoration(int dividerHeight, int interval) {

        this.dividerHeight = dividerHeight;
        this.interval = interval;
        paint = new Paint();
        paint.setColor(Color.BLACK);
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);

        int count = parent.getChildCount();
        int width = parent.getWidth();
        int height = parent.getHeight();

        for (int i = 0; i < count; i++) {
            View child = parent.getChildAt(i);
            int bottom = child.getBottom()+interval;
            c.drawRect(0, bottom, width, bottom + dividerHeight, paint);
        }

        for (int i = 1; i < count; i+=2) {
            View child = parent.getChildAt(i);
            int left = child.getLeft();
            c.drawRect(left+dividerHeight, height, left, 0, paint);
        }

    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.top = interval;
        outRect.bottom = interval;
    }
}


