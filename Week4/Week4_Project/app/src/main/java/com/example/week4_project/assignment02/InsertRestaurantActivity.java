package com.example.week4_project.assignment02;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.week4_project.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertRestaurantActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText restaurantNameEditText;
    private EditText restaurantAddrEditText;
    private Button restaurantInsertButton;

    private RadioButton restaurantOpenTrueRadioButton;
    private RadioButton restaurantOpenFalseRadioButton;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_restauant);

        initView();

    }

    private void initView() {
        restaurantNameEditText = findViewById(R.id.et_restaurant_name);
        restaurantAddrEditText = findViewById(R.id.et_restaurant_address);
        restaurantInsertButton = findViewById(R.id.btn_insert_restaurant);
        restaurantOpenFalseRadioButton = findViewById(R.id.rb_open_false);
        restaurantOpenTrueRadioButton = findViewById(R.id.rb_open_true);

        restaurantInsertButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_insert_restaurant:
                boolean isOpenValue;
                isOpenValue = restaurantOpenTrueRadioButton.isChecked();

                Restaurant restaurant = new Restaurant(
                        restaurantNameEditText.getText().toString(),
                        restaurantAddrEditText.getText().toString(),
                        isOpenValue);


                NetRetrofit.getInstance().getService().postRestaurant(restaurant)
                        .enqueue(new Callback<Restaurant>() {
                            @Override
                            public void onResponse(Call<Restaurant> call, Response<Restaurant> response) {
                                Log.d("Retrofit", response.toString());
                                Toast.makeText(getApplicationContext(), "등록이 완료되었습니다", Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onFailure(Call<Restaurant> call, Throwable t) {
                                Log.d("Retrofit", "fail");
                                Toast.makeText(getApplicationContext(), "등록이 실패했어요ㅠㅠ", Toast.LENGTH_SHORT).show();

                            }
                        });
                break;
        }

    }
}
