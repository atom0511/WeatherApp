package trungatom.tqt.weather_app;

import retrofit2.Response;
import trungatom.tqt.weather_app.models.data_model.GetWeatherRespond;

public interface WeatherContract {
    interface View {
        void showWeatherDetail(GetWeatherRespond weather);
        void showErrorFromServer(Response error);
        void showErrorWhenFailure(String message);
    }

    interface Presenter {
        void getWeatherDetail(String cityName);
    }

    interface Model {
        void getWeatherDetail(OnFinishGetWeatherDetail onFinishGetWeatherDetail, String cityName);

        interface OnFinishGetWeatherDetail{
            void onResponseGetWeatherDetail(Response<GetWeatherRespond> response);
            void onFailure(String error);
        }


    }
}
