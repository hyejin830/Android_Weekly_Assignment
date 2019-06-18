package com.example.week1_project;

import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CustomView customCircleView;
    private Button changeCircleColorButton;
    private Button changeCircleSizeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        customCircleView = findViewById(R.id.custom_circle);
        changeCircleColorButton = findViewById(R.id.btn_change_color);
        changeCircleSizeButton = findViewById(R.id.btn_change_size);
        changeCircleColorButton.setOnClickListener(this);
        changeCircleSizeButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_change_color:
                customCircleView.setCircleColor(Color.BLUE);
                customCircleView.setStrokeColor(Color.GREEN);
                break;
            case R.id.btn_change_size:
                customCircleView.setCircleRadius(250);
                break;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("CIRCLE_COLOR", customCircleView.getCircleColor());
        outState.putInt("STROKE_COLOR", customCircleView.getStrokeColor());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        customCircleView.setCircleColor(savedInstanceState.getInt("CIRCLE_COLOR"));
        customCircleView.setStrokeColor(savedInstanceState.getInt("STROKE_COLOR"));
        customCircleView.invalidate();
    }
}
