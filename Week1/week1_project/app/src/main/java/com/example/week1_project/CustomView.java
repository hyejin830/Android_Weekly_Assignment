package com.example.week1_project;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class CustomView extends View {

    Paint paint;
    int circleColor;
    int strokeWidth;


    public CustomView(Context context) {
        super(context);
        init(null);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(@Nullable AttributeSet set) {

        paint = new Paint();

        if(set == null){
            return;
        }

        TypedArray typedArray = getContext().obtainStyledAttributes(set, R.styleable.CustomView);

        circleColor = typedArray.getColor(R.styleable.CustomView_circleColor, Color.RED);
        strokeWidth = typedArray.getDimensionPixelSize(R.styleable.CustomView_strokeWidth, getContext().getResources().getDimensionPixelSize(R.dimen._5dp));

        paint.setColor(circleColor);

        //paint.setStrokeWidth();
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
        canvas.drawCircle(x, y, raidus, paint);

        /*// top circle
        paint.setColor(Color.RED);
        canvas.drawCircle(x, y, raidus - 30, paint);*/
    }
}
