package com.example.week1_project;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class CustomView extends View {

    private Paint paint;
    private int strokeWidth;
    private int strokeColor;
    private int circleColor;

    private int circleWidth;
    private int circleHeight;
    private int circleRadius;

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


    public void setStrokeColor(int strokeColor) {
        this.strokeColor = strokeColor;
        invalidate();
    }

    public void setCircleColor(int circleColor) {
        this.circleColor = circleColor;
        invalidate();
    }

    public void setCircleRadius(int circleRadius) {
        this.circleRadius = circleRadius;
        invalidate();
    }

    public int getStrokeColor() {
        return strokeColor;
    }

    public int getCircleColor() {
        return circleColor;
    }

    public int getCircleRadius() {
        return circleRadius;
    }


    private void init(@Nullable AttributeSet set) {

        paint = new Paint();

        if (set == null) {
            return;
        }

        TypedArray typedArray = getContext().obtainStyledAttributes(set, R.styleable.CustomView);

        // 설정된 값 가져오고 없으면 default로
        circleRadius = typedArray.getInteger(R.styleable.CustomView_circleRadius,300);
        strokeWidth = typedArray.getDimensionPixelSize(R.styleable.CustomView_strokeWidth, getContext().getResources().getDimensionPixelSize(R.dimen._10dp));
        strokeColor = typedArray.getColor(R.styleable.CustomView_strokeColor, Color.BLACK);
        circleColor = typedArray.getColor(R.styleable.CustomView_circleColor, Color.RED);

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


        circleWidth = width;
        circleHeight = height;

        Log.d("HJ_TEST", "width = " + circleWidth + " height = " + circleHeight);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 색상 클래스
        int x = circleWidth / 2;
        int y = circleHeight / 2;

        int radius = circleRadius;

        /*// 가로,세로 원 반지름
        if (circleWidth > circleHeight) {
            radius = y / 2;
        } else {
            radius = x / 2;
        }*/

        canvas.drawCircle(x, y, radius, getStroke());

        paint.setColor(circleColor);
        canvas.drawCircle(x, y, radius, paint);
    }

    private Paint getStroke() {
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setStrokeWidth(strokeWidth);
        p.setColor(strokeColor);
        p.setStyle(Paint.Style.STROKE);
        return p;
    }

}
