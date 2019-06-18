package com.example.week3_project_v2.assignment_1_additional;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.week3_project_v2.R;

import org.w3c.dom.Text;

public class ViewModelTestActivity extends AppCompatActivity implements View.OnClickListener {

    private SharedViewModel sharedViewModel;
    private TextView activityTextView;

    private EditText activityEditText;
    private Button activityOkButton;
    private FirstFragment firstFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_model_test);


        firstFragment = new FirstFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.first_container, firstFragment)
                .commit();

        initView();

        // ViewModel 사용
        sharedViewModel = ViewModelProviders.of(this).get(SharedViewModel.class);

        doObserveFragmentText();
    }

    private void doObserveFragmentText() {
        // fragment -> activity
        sharedViewModel.getFromFragmentText().observe(this, new Observer<CharSequence>() {
            @Override
            public void onChanged(CharSequence charSequence) {
                activityTextView.setText(charSequence);
            }
        });
    }

    private void initView() {
        activityTextView = findViewById(R.id.tv_from_fragment_text);
        activityEditText = findViewById(R.id.et_activity_text);
        activityOkButton = findViewById(R.id.btn_activity_ok);
        activityOkButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_activity_ok:
                // Activity -> Fragment
                Log.d("hj_test","ViewModelTestActivity - onClick");
                sharedViewModel.setFromActivityText(activityEditText.getText());
                break;
        }
    }
}
