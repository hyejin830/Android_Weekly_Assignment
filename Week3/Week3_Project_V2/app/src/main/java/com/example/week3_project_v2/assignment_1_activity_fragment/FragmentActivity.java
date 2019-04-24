package com.example.week3_project_v2.assignment_1_activity_fragment;


import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.week3_project_v2.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class FragmentActivity extends AppCompatActivity implements LifeCycleFstFragment.OnTextChangedListener {

    private static final String TAG = "ActivityLifeCycle";

    private FragmentTransaction fragmentTransaction;

    private TextView communicateWithFragmentTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        initView();
    }

    void initView() {

        communicateWithFragmentTextView = findViewById(R.id.tv_communicate_with_fragment);

        FragmentManager fragmentManager = getSupportFragmentManager();
        // 프래그먼트 추가, 제거 또는 교체
        fragmentTransaction = fragmentManager.beginTransaction();

        LifeCycleFstFragment lifeCycleFstFragment = new LifeCycleFstFragment();
        fragmentTransaction.add(R.id.fragment_container, lifeCycleFstFragment);
        fragmentTransaction.commit();
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
    public void onTextChanged(String text) {
        communicateWithFragmentTextView.setText(text);
    }
}