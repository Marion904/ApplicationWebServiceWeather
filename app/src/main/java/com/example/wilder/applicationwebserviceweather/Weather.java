
package com.example.wilder.applicationwebserviceweather;

import com.google.api.client.util.Key;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Weather {

    @SerializedName("id")
    @Expose
    @Key
    private Integer id;
    @SerializedName("main")
    @Expose
    @Key
    private String main;
    @SerializedName("description")
    @Expose
    @Key
    private String description;
    @SerializedName("icon")
    @Expose
    @Key
    private String icon;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

}
