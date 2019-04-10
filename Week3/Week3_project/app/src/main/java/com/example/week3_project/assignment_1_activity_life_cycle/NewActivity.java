package com.example.week3_project.assignment_1_activity_life_cycle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.week3_project.R;

public class NewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
    }
}
