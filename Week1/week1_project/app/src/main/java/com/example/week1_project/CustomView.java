package com.example.week1_project;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class CustomView extends View {

    private Paint paint;


    public CustomView(Context context) {
        super(context);
        init();
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint(); // Paint 인스턴스 생성
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int x = canvas.getWidth()/2;
        int y = canvas.getHeight()/2;
        int raidus = x;

        // bottom circle
        paint.setColor(Color.BLACK);
        canvas.drawCircle(x, y, raidus, paint);

        // top circle
        paint.setColor(Color.RED);
        canvas.drawCircle(x, y, raidus-30, paint);
    }
}
