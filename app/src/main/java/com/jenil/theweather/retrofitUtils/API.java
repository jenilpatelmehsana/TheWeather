package com.jenil.theweather.retrofitUtils;

import com.jenil.theweather.BuildConfig;
import com.jenil.theweather.weather.WeatherData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {

    @GET("/data/2.5/weather")
    Call<WeatherData> getWeatherData(@Query("lat") Double lat,
                                     @Query("lon") Double lon,
                                     @Query("units") String units,
                                     @Query("appid") String appid);
}
