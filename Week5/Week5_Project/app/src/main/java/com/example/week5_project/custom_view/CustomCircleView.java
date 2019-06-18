package com.example.week5_project.custom_view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;

import com.example.week5_project.R;

import androidx.annotation.Nullable;

public class CustomCircleView extends View {

    Paint paint;
    Paint strokePaint;
    int circleColor;
    int strokeWidth;

    public CustomCircleView(Context context) {

        super(context);
        init(null);
    }

    public CustomCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CustomCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(@Nullable AttributeSet set) {

        paint = new Paint();
        strokePaint = new Paint();

        if(set == null){
            return;
        }

        TypedArray typedArray = getContext().obtainStyledAttributes(set, R.styleable.CustomView);
        circleColor = typedArray.getColor(R.styleable.CustomView_circleColor, Color.RED);

        paint.setColor(circleColor);

        strokeWidth = typedArray.getDimensionPixelSize(R.styleable.CustomView_strokeWidth, getContext().getResources().getDimensionPixelSize(R.dimen._5dp));
        strokePaint.setColor(Color.BLACK);
        strokePaint.setStrokeWidth(strokeWidth);

        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int width = getMeasuredWidth();
        int height = getMeasuredHeight();

        switch (widthMode) {
            case MeasureSpec.UNSPECIFIED: // unspecified
                width = widthMeasureSpec;
                break;
            case MeasureSpec.AT_MOST:  // wrap_content
                break;
            case MeasureSpec.EXACTLY:  // match_parent
                width = MeasureSpec.getSize(widthMeasureSpec);
                break;
        }

        switch (heightMode) {
            case MeasureSpec.UNSPECIFIED: // unspecified
                height = heightMeasureSpec;
                break;
            case MeasureSpec.AT_MOST:  // wrap_content
                break;
            case MeasureSpec.EXACTLY:  // match_parent
                height = MeasureSpec.getSize(heightMeasureSpec);
                break;
        }

        setMeasuredDimension(width,height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 색상 클래스
        int x = canvas.getWidth() / 2;
        int y = canvas.getHeight() / 2;


        int raidus = x;

        // bottom circle
        canvas.drawCircle(x, y, raidus-strokeWidth, paint);

        /*// top circle
        paint.setColor(Color.RED);
        canvas.drawCircle(x, y, raidus - 30, paint);*/
    }

    @Nullable
    @Override
    protected Parcelable onSaveInstanceState() {
        return super.onSaveInstanceState();
    }
}
