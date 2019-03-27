# 다음과 같은 모양을 갖는 나만의 Custom View Class를 작성하시오. (원과 원을 감싸는 선으로 이루어진 모양일 것, View.class를 상속할 것)

## 이미지

 <img src="https://github.com/hyejin830/Android_Weekly_Assignment/blob/master/Week1/images/custom_view.png" width="20%"></img>

## 특징

 - View 클래스 상속
 - 생성자 
 - init() 함수에서 Paint 인스턴스 생성
 - onDraw() 오버라이딩
   - 캔버스의 너비와 높이를 활용
   - paint로 색 지정
   - drawCircle(float cx, float cy, float radius, Paint paint) 활용

## 코드

```
package com.example.week1_project;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class CustomView extends View {

    private Paint paint; // 색상 클래스


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
```