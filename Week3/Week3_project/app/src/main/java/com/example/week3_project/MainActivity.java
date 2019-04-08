package com.example.week3_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button doStartActivityFrgLifeCycleButton;
    private Button doStartFragmentNavButton;
    private Button doStartCommunicateActivityWithFragButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    void initView() {

        doStartActivityFrgLifeCycleButton = findViewById(R.id.btn_do_start_activity_frg_lifecycle);
        doStartFragmentNavButton = findViewById(R.id.btn_do_start_frag_nav);
        doStartCommunicateActivityWithFragButton = findViewById(R.id.btn_do_start_communicate_activity_with_frag);

        doStartActivityFrgLifeCycleButton.setOnClickListener(this);
        doStartFragmentNavButton.setOnClickListener(this);
        doStartCommunicateActivityWithFragButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_do_start_activity_frg_lifecycle:
                Intent doStartActivityFragLifeCycleIntent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(doStartActivityFragLifeCycleIntent);
                break;
            case R.id.btn_do_start_frag_nav:
                Intent doStartFragNavIntent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(doStartFragNavIntent);
                break;
            case R.id.btn_do_start_communicate_activity_with_frag:
                Intent doStartCommunicateActivityWithFragIntent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(doStartCommunicateActivityWithFragIntent);
                break;
        }
    }
}
