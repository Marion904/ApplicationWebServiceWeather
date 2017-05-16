
package com.example.wilder.applicationwebserviceweather;

import com.google.api.client.util.Key;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ForecastWeatherModel {

    @SerializedName("cod")
    @Expose
    @Key
    private String cod;
    @SerializedName("message")
    @Expose
    @Key
    private Double message;
    @SerializedName("cnt")
    @Expose
    @Key
    private Integer cnt;
    @SerializedName("list")
    @Expose
    @Key
    private java.util.List<com.example.wilder.applicationwebserviceweather.List> list = new ArrayList<com.example.wilder.applicationwebserviceweather.List>();
    @SerializedName("city")
    @Expose
    @Key
    private City city;

    protected String baseUrl;


    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public Double getMessage() {
        return message;
    }

    public void setMessage(Double message) {
        this.message = message;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public java.util.List<com.example.wilder.applicationwebserviceweather.List> getList() {
        return list;
    }

    public void setList(java.util.List<com.example.wilder.applicationwebserviceweather.List> list) {
        this.list = list;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

}
