package com.example.week4_project.assignment01;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.week4_project.R;

import java.util.ArrayList;

public class RecyclerViewActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private RecyclerViewLayoutManager recyclerViewLayoutManager;

    private Button addViewButton;
    private Button removeViewButton;

    private ArrayList<String> textSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        initView();
    }

    private void initView() {
        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        recyclerViewLayoutManager = new RecyclerViewLayoutManager(this, 2);
        recyclerView.setLayoutManager(recyclerViewLayoutManager);

        textSet = new ArrayList<>();
        doAddTextSet();

        adapter = new RecyclerViewAdapter(textSet);
        recyclerView.setAdapter(adapter);

        // 데코레이션
        recyclerView.addItemDecoration(new RecyclerViewItemDecoration(5, 50));
        //recyclerView.setItemAnimator(new RecyclerViewItemAnimator());

        // 액티비티 뷰 참조 및 액션
        addViewButton = findViewById(R.id.btn_add_view);
        removeViewButton = findViewById(R.id.btn_remove_view);

        addViewButton.setOnClickListener(this);
        removeViewButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_view:
                adapter.addAtPosition(2, getString(R.string.text_add_test));
                break;
            case R.id.btn_remove_view:
                adapter.removeAtPosition(2);
                break;
        }

    }

    private void doAddTextSet() {
        for (int i = 1; i <= 31; i++) {
            textSet.add("test" + i);
        }
    }
}
