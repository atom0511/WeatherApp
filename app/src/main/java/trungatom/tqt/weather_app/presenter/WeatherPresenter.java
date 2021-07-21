package trungatom.tqt.weather_app.presenter;

import retrofit2.Response;
import trungatom.tqt.weather_app.WeatherContract;
import trungatom.tqt.weather_app.models.WeatherDetailModel;
import trungatom.tqt.weather_app.models.data_model.GetWeatherRespond;

public class WeatherPresenter implements WeatherContract.Presenter,
        WeatherContract.Model.OnFinishGetWeatherDetail {

    WeatherContract.View view;
    WeatherContract.Model model;

    public WeatherPresenter(WeatherContract.View view) {
        this.view = view;
        this.model = new WeatherDetailModel();
    }

    @Override
    public void getWeatherDetail(String cityName) {
        model.getWeatherDetail(this, cityName);
    }

    @Override
    public void onResponseGetWeatherDetail(Response<GetWeatherRespond> response) {
        if (response.code() == 200) {
            view.showWeatherDetail(response.body());
        } else {
            view.showErrorFromServer(response);
        }
    }

    @Override
    public void onFailure(String error) {
        view.showErrorWhenFailure(error);
    }
}
