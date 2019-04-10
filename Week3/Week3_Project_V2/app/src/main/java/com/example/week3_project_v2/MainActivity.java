package com.example.week3_project_v2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.week3_project_v2.assignment_1_activity_fragment.FragmentActivity;
import com.example.week3_project_v2.assignment_1_activity_life_cycle.LifeCycleActivity;
import com.example.week3_project_v2.assignment_2_frag_nav.BottomNavigationActivity;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button doStartActivityFrgLifeCycleButton;
    private Button doStartFragLifeCycleButton;
    private Button doStartFragmentNavButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    void initView() {

        doStartActivityFrgLifeCycleButton = findViewById(R.id.btn_do_start_activity_frg_lifecycle);
        doStartFragLifeCycleButton = findViewById(R.id.btn_do_start_frag_lifecycle);
        doStartFragmentNavButton = findViewById(R.id.btn_do_start_frag_nav);

        doStartActivityFrgLifeCycleButton.setOnClickListener(this);
        doStartFragLifeCycleButton.setOnClickListener(this);
        doStartFragmentNavButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_do_start_activity_frg_lifecycle:
                Intent doStartActivityFragLifeCycleIntent = new Intent(MainActivity.this, LifeCycleActivity.class);
                startActivity(doStartActivityFragLifeCycleIntent);
                break;
            case R.id.btn_do_start_frag_lifecycle:
                Intent doStartFragLifeCycleIntent = new Intent(MainActivity.this, FragmentActivity.class);
                startActivity(doStartFragLifeCycleIntent);
                break;
            case R.id.btn_do_start_frag_nav:
                Intent doStartFragNavIntent = new Intent(MainActivity.this, BottomNavigationActivity.class);
                startActivity(doStartFragNavIntent);
                break;
        }
    }
}
