
package com.example.wilder.applicationwebserviceweather;

import com.google.api.client.util.Key;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class List {

    @SerializedName("dt")
    @Expose
    @Key
    private Integer dt;
    @SerializedName("main")
    @Expose
    @Key
    private Main main;
    @SerializedName("weather")
    @Expose
    @Key
    private java.util.List<Weather> weather = new ArrayList<Weather>();
    @SerializedName("clouds")
    @Expose
    @Key
    private Clouds clouds;
    @SerializedName("wind")
    @Expose
    @Key
    private Wind wind;
    @SerializedName("rain")
    @Expose
    @Key
    private Rain rain;
    @SerializedName("sys")
    @Expose
    @Key
    private Sys sys;
    @SerializedName("dt_txt")
    @Expose
    @Key
    private String dtTxt;

    public Integer getDt() {
        return dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public java.util.List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(java.util.List<Weather> weather) {
        this.weather = weather;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Rain getRain() {
        return rain;
    }

    public void setRain(Rain rain) {
        this.rain = rain;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public String getDtTxt() {
        return dtTxt;
    }

    public void setDtTxt(String dtTxt) {
        this.dtTxt = dtTxt;
    }

}
