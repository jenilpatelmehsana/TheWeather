package com.jenil.theweather;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChooseCity extends AppCompatActivity {

    ConstraintLayout chooseCityBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_city);
        setTitle("Choose City");

        initialize();

    }


    private void initialize() {
        chooseCityBackground = findViewById(R.id.chooseCityBackground);
    }
}