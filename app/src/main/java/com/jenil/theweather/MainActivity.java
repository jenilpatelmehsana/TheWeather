package com.jenil.theweather;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.jenil.theweather.retrofitUtils.API;
import com.jenil.theweather.weather.WeatherData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    WeatherData currentWeather = null;
    TextView temp, maxTemp, minTemp, description, city;
    Double lat = 0.0, lon = 0.0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getUserGeoLocation();

        initialize();

        getWeather();

    }

    private void getUserGeoLocation() {

        GPSTracker gpsTracker = new GPSTracker(this);
        if(gpsTracker.isGPSEnabled) {
            lat = gpsTracker.latitude;
            lon = gpsTracker.longitude;
        } else {
            gpsTracker.showSettingsAlert();
        }

    }

    private void initialize() {
        temp = findViewById(R.id.temp);
        minTemp = findViewById(R.id.minTemp);
        maxTemp = findViewById(R.id.maxTemp);
        description = findViewById(R.id.description);
        city = findViewById(R.id.city);
    }

    private void getWeather() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.openweathermap.org").addConverterFactory(GsonConverterFactory.create()).build();
        API api = retrofit.create(API.class);
        Call<WeatherData> call = api.getWeatherData(23.690592, 72.536558, "metric", BuildConfig.API_KEY);
        call.enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
                setWeather(response.body());
                updateWeather();
            }

            @Override
            public void onFailure(Call<WeatherData> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });

    }

    private void updateWeather() {
        if (currentWeather != null && currentWeather.getCod() != 200) {
            return;
        }
        temp.setText(String.valueOf((int) Math.ceil(currentWeather.getMain().getTemp())) + " \u2103");
        minTemp.setText(String.valueOf((int) Math.ceil(currentWeather.getMain().getTempMin())) + " \u2103");
        maxTemp.setText(String.valueOf((int) Math.ceil(currentWeather.getMain().getTempMax())) + " \u2103");
        description.setText(currentWeather.getWeather().get(0).getDescription());
        city.setText(currentWeather.getName());
    }

    private void setWeather(WeatherData body) {
        currentWeather = body;
    }
}