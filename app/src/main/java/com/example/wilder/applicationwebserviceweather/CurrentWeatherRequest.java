package com.example.wilder.applicationwebserviceweather;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.json.gson.GsonFactory;
import com.octo.android.robospice.request.googlehttpclient.GoogleHttpClientSpiceRequest;

import java.util.Locale;

import roboguice.util.temp.Ln;

/**
 * Created by wilder on 11/05/17.
 */

public class CurrentWeatherRequest extends GoogleHttpClientSpiceRequest<CurrentWeatherModel> {
    private String baseUrl;
    public CurrentWeatherRequest( double lat, double lon, String key) {
        super(CurrentWeatherModel.class);
        this.baseUrl = String.format(Locale.FRANCE,"http://api.openweathermap.org/data/2.5/weather?lat=%1$.6f&lon=%2$.6f&units=Metric&lang=fr&APPID=%3$s",lat,lon,key);
    }

    @Override
    public CurrentWeatherModel loadDataFromNetwork() throws Exception {
        Ln.d( "Call web service " + baseUrl );
        HttpRequest request = getHttpRequestFactory()//
                .buildGetRequest( new GenericUrl( baseUrl ) );
        request.setParser( new GsonFactory().createJsonObjectParser() );
        return request.execute().parseAs( getResultType() );
    }

    public String createCacheKey() {
        return "Weather." + baseUrl;
    }

    /**
    public CacheManager createCacheManager (Application application){
        CacheManager cacheManager = new CacheManager();
        JacksonObjectPersisterFactory jacksonObjectPersisterFactory = null;
        try {
            jacksonObjectPersisterFactory = new JacksonObjectPersisterFactory(application);
        } catch (CacheCreationException e) {
            e.printStackTrace();
        }
        cacheManager.addPersister(jacksonObjectPersisterFactory);
        return cacheManager;
    }*/

}
