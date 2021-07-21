package trungatom.tqt.weather_app.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Response;
import trungatom.tqt.weather_app.R;
import trungatom.tqt.weather_app.Utils;
import trungatom.tqt.weather_app.WeatherContract;
import trungatom.tqt.weather_app.api.RetrofitConfiguration;
import trungatom.tqt.weather_app.models.data_model.GetWeatherRespond;
import trungatom.tqt.weather_app.presenter.WeatherPresenter;


public class MainActivity extends AppCompatActivity implements WeatherContract.View {


    @BindView(R.id.et_input_city_name)
    EditText etInputCityName;
    @BindView(R.id.bt_get_data)
    Button btGetData;
    @BindView(R.id.tv_show_date)
    TextView tvShowDate;

    String apiKey = "751b5e44a578a890dc6470abc99faa11";
    String temperatureType = "metric";
    @BindView(R.id.tv_show_country)
    TextView tvShowCountry;
    @BindView(R.id.tv_show_wind)
    TextView tvShowWind;
    @BindView(R.id.tv_show_temperature)
    TextView tvShowTemperature;
    @BindView(R.id.tv_show_description)
    TextView tvShowDescription;
    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.tv_status)
    TextView tvStatus;

    private WeatherPresenter weatherPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        weatherPresenter = new WeatherPresenter(this);
    }

    @Override
    public void showWeatherDetail(GetWeatherRespond weather) {
        String country = weather.getSys().getCountry();
        tvShowCountry.setText("Country: " + country);
        int date1 = weather.getDt();
        Date date2 = new Date(date1 * 1000L);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE yyyy-MM-dd");
        String day = simpleDateFormat.format(date2);
        tvShowDate.setText(day);

        GetWeatherRespond.WeatherBean quickWeather = weather.getWeather().get(0);
        String status = quickWeather.getMain();
        Log.d("wtf", "onResponse: " + status);
        tvStatus.setText(status);

        Glide.with(this)
                //example: http://openweathermap.org/img/w/50d.png
                .load(RetrofitConfiguration.getImageBaseUrlOriginal() + quickWeather.getIcon() + ".png")
                .into(ivIcon);

        String description = quickWeather.getDescription();
        tvShowDescription.setText("Description: " + description);
        double temperature = weather.getMain().getTemp();
        tvShowTemperature.setText("Temperature: " + temperature);
        double wind = weather.getWind().getSpeed();
        tvShowWind.setText("SpeedWind: " + wind);

    }

    @Override
    public void showErrorFromServer(Response error) {
        Utils.showErrorFromServer(error, this);
    }

    @Override
    public void showErrorWhenFailure(String message) {
        Toast.makeText(MainActivity.this, "City not found: " + message, Toast.LENGTH_LONG).show();
    }


    @OnClick(R.id.bt_get_data)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_get_data:
                String cityname = etInputCityName.getText().toString().trim();
                weatherPresenter.getWeatherDetail(cityname);
                break;
        }
    }

}
