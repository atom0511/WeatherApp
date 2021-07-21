package trungatom.tqt.weather_app.api;

import android.annotation.SuppressLint;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import trungatom.tqt.weather_app.models.data_model.GetWeatherRespond;

@SuppressLint("StaticFieldLeak")
public interface APIService {

    @GET("weather")

    Call<GetWeatherRespond> getWeather(
            @Query("q") String nameCity,
            @Query("units") String temperatureType,
            @Query("appid") String apiKey
    );

}
