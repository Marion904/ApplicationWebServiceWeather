package com.example.wilder.applicationwebserviceweather;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.octo.android.robospice.GsonGoogleHttpClientSpiceService;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private String key, currentRequestCacheKey, forecastRequestCacheKey;
    private TextView city, date, current_temp, current_temp2, current_weather, forecast_weather,forecast_weather1;
    private CurrentWeatherRequest requestCurrent;
    private ForecastWeatherRequest requestForecast;
    private ProgressDialog progressDialog;
    protected SpiceManager spiceManager;
    LocationManager locationManager = null;
    String locationProvider = LocationManager.NETWORK_PROVIDER;
    final int MY_PERMISSION_ACCESS_LOCATION = 1234;
    LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            //makeUseOfLocation(location);
            downloadWeatherData(location);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onProviderDisabled(String provider) {
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressDialog = new ProgressDialog(MainActivity.this);
        spiceManager = new SpiceManager(GsonGoogleHttpClientSpiceService.class);
        locationManager = (LocationManager) this.getSystemService(this.LOCATION_SERVICE);

        key =getString(R.string.api_key);
        city = (TextView) findViewById(R.id.city);
        date=(TextView) findViewById(R.id.date);
        current_temp = (TextView) findViewById(R.id.current_temp);
        current_temp2 = (TextView) findViewById(R.id.current_temp2);
        current_weather=(TextView) findViewById(R.id.current_weather);
        forecast_weather =(TextView) findViewById(R.id.forecast_weather);
        forecast_weather1 =(TextView) findViewById(R.id.forecast_weather1);




       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{
                                    Manifest.permission.ACCESS_FINE_LOCATION,
                                    Manifest.permission.ACCESS_COARSE_LOCATION
                            }, MY_PERMISSION_ACCESS_LOCATION);
                    Toast.makeText(MainActivity.this, R.string.need_permission_toast, Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(MainActivity.this, R.string.app_start_toast, Toast.LENGTH_SHORT).show();
                locationManager.requestLocationUpdates(locationProvider, 0, 0, locationListener);
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        spiceManager.start(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                    }, MY_PERMISSION_ACCESS_LOCATION);
            Toast.makeText(MainActivity.this, R.string.need_permission_toast, Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(MainActivity.this, R.string.app_start_toast, Toast.LENGTH_SHORT).show();
        locationManager.requestLocationUpdates(locationProvider, 0, 0, locationListener);

    }

    @Override
    protected void onStop() {
        spiceManager.shouldStop();
        super.onStop();
        locationManager.removeUpdates(locationListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void downloadWeatherData(Location location){
        progressDialog.setTitle("Please wait...");
        progressDialog.show();
        requestCurrent = new CurrentWeatherRequest(location.getLatitude(), location.getLongitude(), key);
        requestForecast = new ForecastWeatherRequest(location.getLatitude(),location.getLongitude(), key);
        currentRequestCacheKey = requestCurrent.createCacheKey();
        forecastRequestCacheKey = requestForecast.createCacheKey();
        spiceManager.execute( requestCurrent, new currentWeatherRequestListener());
        spiceManager.execute( requestForecast, new forecastWeatherRequestListener());
    }
    private class currentWeatherRequestListener implements RequestListener<CurrentWeatherModel> {

        @Override
        public void onRequestFailure(SpiceException e) {
            Toast.makeText( MainActivity.this, R.string.echec, Toast.LENGTH_SHORT ).show();
        }

        @Override
        public void onRequestSuccess(CurrentWeatherModel currentWeather) {
            progressDialog.dismiss();
            String name = currentWeather.getName();
            Double temp = currentWeather.getMain().getTemp();
            Wind curWind = currentWeather.getWind();
            String desc = currentWeather.getWeather().get(0).getDescription();
            long day = currentWeather.getDt();
            Date today = new Date(day * 1000);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.FRANCE);
            long windy = Math.round(curWind.getSpeed()*3600/1000);
            Double pressure = currentWeather.getMain().getPressure();
            Toast.makeText( MainActivity.this, name, Toast.LENGTH_SHORT ).show();
            city.setText(getString(R.string.intro)+name);
            date.setText(sdf.format(today).toString());
            current_temp.setText(getString(R.string.Temp)+temp.toString()+getString(R.string.metricUnit)
                    +getString(R.string.windSpeed)+windy+getString(R.string.windUnit));
            current_temp2.setText(getString(R.string.hPa)+pressure.toString()+getString(R.string.pressureUnit));
            current_weather.setText(desc);


        }
    }

    private class forecastWeatherRequestListener implements RequestListener<ForecastWeatherModel> {

        @Override
        public void onRequestFailure(SpiceException e) {
            Toast.makeText( MainActivity.this, R.string.echec, Toast.LENGTH_SHORT ).show();
        }

        @Override
        public void onRequestSuccess(ForecastWeatherModel forecastWeather) {
            progressDialog.dismiss();
            forecast_weather.setText(getString(R.string.forecast));
            java.util.List<com.example.wilder.applicationwebserviceweather.List> list = forecastWeather.getList();
            StringBuilder sb = new StringBuilder();
            for(int i = 1; i<list.size();i++) {
                if (i % 8 == 0) {
                    Double windy = forecastWeather.getList().get(i).getWind().getSpeed();
                    Double temp = list.get(i).getMain().getTemp();
                    Double pressure = list.get(i).getMain().getPressure();
                    switch (i){
                        case 8:
                            sb.append(getString(R.string.forecast_1_day));
                            break;
                        case 16:
                            sb.append(getString(R.string.forecast_2_day));
                            break;
                        case 24:
                            sb.append(getString(R.string.forecast_3_day));
                            break;
                        case 32:
                            sb.append(getString(R.string.forecast_4_day));
                            break;
                    }
                    sb.append(System.getProperty("line.separator"));
                    sb.append(getString(R.string.wind_speed));
                    sb.append(windy);
                    sb.append(getString(R.string.windUnit));
                    sb.append(getString(R.string.Temp));
                    sb.append(temp);
                    sb.append(getString(R.string.metricUnit));
                    sb.append(System.getProperty("line.separator"));
                    sb.append(getString(R.string.hPa));
                    sb.append(pressure);
                    sb.append(getString(R.string.pressureUnit));
                    sb.append(System.getProperty("line.separator"));
                    sb.append(System.getProperty("line.separator"));
                }
            }

                forecast_weather1.setText(sb.toString());
            }

        }

    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSION_ACCESS_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {
                    Toast.makeText(MainActivity.this, R.string.no_permission_toast, Toast.LENGTH_SHORT).show();
                }
                return;
            }

        }
    }

}
