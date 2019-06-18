package com.example.week4_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.week4_project.assignment01.RecyclerViewActivity;
import com.example.week4_project.assignment02.RestApiActivity;
import com.example.week4_project.assignment03.CustomNotificationActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button startRecyclerViewActivityButton;
    private Button startRestApiActivityButton;
    private Button executeCustomNotificationButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        executeCustomNotificationButton = findViewById(R.id.execute_custom_notification);
        startRecyclerViewActivityButton = findViewById(R.id.btn_start_recycler_view);
        startRestApiActivityButton = findViewById(R.id.btn_start_rest_api);


        executeCustomNotificationButton.setOnClickListener(this);
        startRecyclerViewActivityButton.setOnClickListener(this);
        startRestApiActivityButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.execute_custom_notification:
                Intent executeCustomNotificationIntent = new Intent(this, CustomNotificationActivity.class);
                startActivity(executeCustomNotificationIntent);
                break;
            case R.id.btn_start_recycler_view:
                Intent startRecyclerViewActivityIntent = new Intent(this, RecyclerViewActivity.class);
                startActivity(startRecyclerViewActivityIntent);
                break;
            case R.id.btn_start_rest_api:
                Intent startRestApiActivityIntent = new Intent(this, RestApiActivity.class);
                startActivity(startRestApiActivityIntent);
                break;

        }
    }
}
