package com.example.week2_project.assignment03_custom_dialog;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.week2_project.R;

public class CustomDialogPasswordChangeTestActivity extends AppCompatActivity {

    public static boolean isDontWatchCheck;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_custom_dialog_password_change);

        // 상태 저장을 위한 SharedPreferences
        SharedPreferences sharedPreferences = getPreferences(getApplicationContext().MODE_PRIVATE);
        boolean defaultValue = getResources().getBoolean(R.bool.saved_bool);
        isDontWatchCheck = sharedPreferences.getBoolean(getString(R.string.preference_watch_key), defaultValue);

        if (!isDontWatchCheck) { // 보지 않기가 false 값이라면 다이얼로그를 띄운다
            FragmentManager fragmentManager = getSupportFragmentManager();
            CustomDialogPasswordChange passwordChange = new CustomDialogPasswordChange();
            passwordChange.show(fragmentManager, "dialog");

        } else { // 보지 않기 true인 경우
            Toast.makeText(getApplicationContext(), "놉", Toast.LENGTH_SHORT).show();
        }

    }

}
