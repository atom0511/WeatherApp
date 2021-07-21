package trungatom.tqt.weather_app.models;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import trungatom.tqt.weather_app.WeatherContract;
import trungatom.tqt.weather_app.api.APIService;
import trungatom.tqt.weather_app.api.RetrofitConfiguration;
import trungatom.tqt.weather_app.models.data_model.GetWeatherRespond;

public class WeatherDetailModel implements WeatherContract.Model {
    APIService apiService;
    String apiKey = "751b5e44a578a890dc6470abc99faa11";
    String temperatureType = "metric";

    public WeatherDetailModel() {
        this.apiService = RetrofitConfiguration.getInstance().create(APIService.class);
    }

    @Override
    public void getWeatherDetail(OnFinishGetWeatherDetail onFinishGetWeatherDetail, String cityName) {
        Call<GetWeatherRespond> call = apiService.getWeather(cityName, temperatureType, apiKey);
        call.enqueue(new Callback<GetWeatherRespond>() {
            @Override
            public void onResponse(Call<GetWeatherRespond> call, Response<GetWeatherRespond> response) {
                onFinishGetWeatherDetail.onResponseGetWeatherDetail(response);
            }

            @Override
            public void onFailure(Call<GetWeatherRespond> call, Throwable t) {
                onFinishGetWeatherDetail.onFailure(t.toString());
            }
        });
    }
}
