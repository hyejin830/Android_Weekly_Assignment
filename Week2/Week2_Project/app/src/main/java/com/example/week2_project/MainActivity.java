package com.example.week2_project;

import android.content.Intent;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.week2_project.assignment01_scrollview_nestedscrollview.NestedScrollViewActivity;
import com.example.week2_project.assignment01_scrollview_nestedscrollview.ScrollViewActivity;
import com.example.week2_project.assignment02_communicate_app_webview.CommunicateAppWithWebpage;
import com.example.week2_project.assignment03_custom_dialog.CustomDialogPasswordChangeTestActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button startScrollViewActivityButton;
    private Button startNestedScrollViewActivityButton;
    private Button startCommunicateAppWithWebViewButton;
    private Button startCustomDialogPasswordChangeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    void initView() {

        startScrollViewActivityButton = findViewById(R.id.btn_start_scroll_view);
        startNestedScrollViewActivityButton = findViewById(R.id.btn_start_nested_scroll_view);
        startCommunicateAppWithWebViewButton = findViewById(R.id.btn_start_communicate_app_with_webpage);
        startCustomDialogPasswordChangeButton = findViewById(R.id.btn_start_custom_dialog_password_change);

        startScrollViewActivityButton.setOnClickListener(this);
        startNestedScrollViewActivityButton.setOnClickListener(this);
        startCommunicateAppWithWebViewButton.setOnClickListener(this);
        startCustomDialogPasswordChangeButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start_scroll_view:
                Intent startScrollViewIntent = new Intent(MainActivity.this, ScrollViewActivity.class);
                startActivity(startScrollViewIntent);
                break;
            case R.id.btn_start_nested_scroll_view:
                Intent startNestedScrollViewIntent = new Intent(MainActivity.this, NestedScrollViewActivity.class);
                startActivity(startNestedScrollViewIntent);
                break;
            case R.id.btn_start_communicate_app_with_webpage:
                Intent StartCommunicateAppWithWebpageIntent = new Intent(MainActivity.this, CommunicateAppWithWebpage.class);
                startActivity(StartCommunicateAppWithWebpageIntent);
                break;
            case R.id.btn_start_custom_dialog_password_change:
                Intent StartCustomDialogPasswordChangeIntent = new Intent(MainActivity.this, CustomDialogPasswordChangeTestActivity.class);
                startActivity(StartCustomDialogPasswordChangeIntent);
                break;


        }
    }
}
