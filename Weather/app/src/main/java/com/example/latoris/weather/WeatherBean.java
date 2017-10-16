package com.example.latoris.weather;


import android.graphics.Bitmap;

import java.util.LinkedList;
import java.util.List;

/* Created by Latoris on 2016/10/19.
 */
public class WeatherBean {
    private String updateTime;
    private String city;
    private Bitmap weatherImg;
    private String weatherNow;
    private String tmpNow;
    public List<Weather> Daily_forecast = new LinkedList<Weather>();

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getWeatherNow() {
        return weatherNow;
    }

    public void setWeatherNow(String weatherNow) {
        this.weatherNow = weatherNow;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getTmpNow() {
        return tmpNow;
    }

    public void setTmpNow(String tmpNow) {
        this.tmpNow = tmpNow;
    }

    public Bitmap getWeatherImg() {
        return weatherImg;
    }

    public void setWeatherImg(Bitmap weatherImg) {
        this.weatherImg = weatherImg;
    }
}
