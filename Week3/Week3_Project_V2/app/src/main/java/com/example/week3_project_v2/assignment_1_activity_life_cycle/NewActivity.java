package com.example.week3_project_v2.assignment_1_activity_life_cycle;

import android.os.Bundle;

import com.example.week3_project_v2.R;

import androidx.appcompat.app.AppCompatActivity;

public class NewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
    }
}
