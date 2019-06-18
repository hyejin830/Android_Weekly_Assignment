package com.example.week4_project.assignment02;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.week4_project.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestApiActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText inputIdEditText;
    private Button searchButton;
    private Button allSearchButton;
    private Button postButton;
    private TextView showSearchedTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_api);

        initView();
    }

    private void initView() {
        inputIdEditText = findViewById(R.id.et_input_id);

        searchButton = findViewById(R.id.btn_search);
        allSearchButton = findViewById(R.id.btn_all_search);
        postButton = findViewById(R.id.btn_post);

        showSearchedTextView = findViewById(R.id.tv_show_data);

        searchButton.setOnClickListener(this);
        allSearchButton.setOnClickListener(this);
        postButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_search:
                String id = inputIdEditText.getText().toString();

                if (!id.isEmpty()) {
                    Call<Restaurant> res = NetRetrofit.getInstance().getService().getRestaurantInfo(Integer.valueOf(id));
                    res.enqueue(new Callback<Restaurant>() {
                        @Override
                        public void onResponse(Call<Restaurant> call, Response<Restaurant> response) {
                            Log.d("Retrofit", response.toString());
                            if (response.body() != null) {
                                String content = "name : " + response.body().component1()
                                        + "\n address : " + response.body().component2()
                                        + "\n isOpen : " + response.body().component3();

                                showSearchedTextView.setText(content);
                            } else {
                                Log.d("Retrofit", "response body null");
                            }
                        }

                        @Override
                        public void onFailure(Call<Restaurant> call, Throwable t) {
                            Log.e("Err", t.getMessage());
                        }
                    });
                } else
                    Toast.makeText(this, "아이디를 입력하세요.", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btn_all_search:

                Call<List<Restaurant>> res = NetRetrofit.getInstance().getService().getAllRestaurant();
                res.enqueue(new Callback<List<Restaurant>>() {
                    @Override
                    public void onResponse(Call<List<Restaurant>> call, Response<List<Restaurant>> response) {
                        Log.d("Retrofit", response.toString());
                        if (response.body() != null) {
                            int all_size = response.body().size();

                            String all_content = "";
                            for (int i = 0; i < all_size; i++) {
                                all_content = all_content + "name : " + response.body().get(i).component1()
                                        + "\n address : " + response.body().get(i).component2()
                                        + "\n isOpen : " + response.body().get(i).component3()
                                        + "\n\n";
                            }

                            showSearchedTextView.setText(all_content);
                        } else {
                            Log.d("Retrofit", "response body null");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Restaurant>> call, Throwable t) {
                        Log.e("Err", t.getMessage());
                    }
                });
                break;

            case R.id.btn_post:
                Intent startInsertResaurantActivityIntent = new Intent(this, InsertRestaurantActivity.class);
                startActivity(startInsertResaurantActivityIntent);
                break;
        }

    }
}
