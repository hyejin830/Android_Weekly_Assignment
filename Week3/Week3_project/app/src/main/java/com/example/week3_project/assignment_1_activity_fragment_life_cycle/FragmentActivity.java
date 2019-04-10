package com.example.week3_project.assignment_1_activity_fragment_life_cycle;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.week3_project.R;

public class FragmentActivity extends AppCompatActivity {

    private static final String TAG = "ActivityLifeCycle";

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        initView();
    }

    void initView(){

        fragmentManager  = getSupportFragmentManager();
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

}
