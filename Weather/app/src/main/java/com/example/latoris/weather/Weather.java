package com.example.latoris.weather;

import android.graphics.Bitmap;

/**
 * Created by Latoris on 2016/10/25.
 */

//每日天气
public class Weather{
    private String weekday = "";
    private String weatherDay = "";
    private Bitmap weatherDayImg;
    private String weatherNight = "";
    private Bitmap weatherNightImg;
    private String tmpHigh = "";
    private String tmpLow ="";

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    public String getWeatherDay() {
        return weatherDay;
    }

    public void setWeatherDay(String weatherDay) {
        this.weatherDay = weatherDay;
    }

    public Bitmap getWeatherDayImg() {
        return weatherDayImg;
    }

    public void setWeatherDayImg(Bitmap weatherDayImg) {
        this.weatherDayImg = weatherDayImg;
    }

    public String getWeatherNight() {
        return weatherNight;
    }

    public void setWeatherNight(String weatherNight) {
        this.weatherNight = weatherNight;
    }

    public Bitmap getWeatherNightImg() {
        return weatherNightImg;
    }

    public void setWeatherNightImg(Bitmap weatherNightImg) {
        this.weatherNightImg = weatherNightImg;
    }

    public String getTmpHigh() {
        return tmpHigh;
    }

    public void setTmpHigh(String tmpHigh) {
        this.tmpHigh = tmpHigh;
    }

    public String getTmpLow() {
        return tmpLow;
    }

    public void setTmpLow(String tmpLow) {
        this.tmpLow = tmpLow;
    }



}