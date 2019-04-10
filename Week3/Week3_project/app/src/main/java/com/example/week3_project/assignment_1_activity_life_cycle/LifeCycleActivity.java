package com.example.week3_project.assignment_1_activity_life_cycle;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.week3_project.R;

public class LifeCycleActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "ActivityLifeCycle";

    private Button doStartDialogShowButton;
    private Button doStartNewActivityButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle);

        Log.d(TAG, "onCreate()");

        initView();
    }

    void initView() {
        doStartDialogShowButton = findViewById(R.id.do_start_dialog_show);
        doStartNewActivityButton = findViewById(R.id.do_start_new_activity);

        doStartDialogShowButton.setOnClickListener(this);
        doStartNewActivityButton.setOnClickListener(this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Log.d(TAG, "onRestart()");
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d(TAG, "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(TAG, "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d(TAG, "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d(TAG, "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d(TAG, "onDestroy()");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.do_start_dialog_show:
                show();
                break;
            case R.id.do_start_new_activity:
                Intent startNewActivityIntent = new Intent(LifeCycleActivity.this, NewActivity.class);
                startActivity(startNewActivityIntent);
                break;
        }
    }

    void show() {
        // 1. Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

// 2. Chain together various setter methods to set the dialog characteristics
        builder.setMessage("내용")
                .setTitle("제목")
                .setPositiveButton("넴", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "넴",Toast.LENGTH_LONG).show();
                    }
                });


        builder.show();
    }
}
