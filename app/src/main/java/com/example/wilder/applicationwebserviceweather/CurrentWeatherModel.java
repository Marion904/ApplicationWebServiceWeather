
package com.example.wilder.applicationwebserviceweather;

import com.google.api.client.util.Key;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class CurrentWeatherModel {

    @SerializedName("coord")
    @Expose
    @Key
    private Coord coord;
    @SerializedName("weather")
    @Expose
    @Key
    private List<Weather> weather = new ArrayList<Weather>();
    @SerializedName("base")
    @Expose
    @Key
    private String base;
    @SerializedName("main")
    @Expose
    @Key
    private Main main;
    @SerializedName("wind")
    @Expose
    @Key
    private Wind wind;
    @SerializedName("clouds")
    @Expose
    @Key
    private Clouds clouds;
    @SerializedName("dt")
    @Expose
    @Key
    private Integer dt;
    @SerializedName("sys")
    @Expose
    @Key
    private Sys sys;
    @SerializedName("id")
    @Expose
    @Key
    private Integer id;
    @SerializedName("name")
    @Expose
    @Key
    private String name;
    @SerializedName("cod")
    @Expose
    @Key
    private Integer cod;

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Integer getDt() {
        return dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

}
