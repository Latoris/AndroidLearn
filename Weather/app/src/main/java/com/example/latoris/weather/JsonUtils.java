package com.example.latoris.weather;
import android.graphics.Bitmap;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by Latoris on 2016/10/19.
 */
public class JsonUtils {
    public static String weatherImgUrl = "http://files.heweather.com/cond_icon/";

    public static WeatherBean getWeatherBean(URL url) {
        Date today = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        int weekday=cal.get(Calendar.DAY_OF_WEEK);

        String jsonText = new JsonFetcher().getJSONText(url);
        while(jsonText.isEmpty()){
            jsonText = new JsonFetcher().getJSONText(url);
        }
        Log.v("weather", jsonText);
        WeatherBean weather = new WeatherBean();
        try {
            JSONObject object = new JSONObject(String.valueOf(jsonText));
            JSONArray rootArray = (JSONArray) object.get("HeWeather5");
            JSONObject root = rootArray.getJSONObject(0);
            JSONObject basic = root.getJSONObject("basic");
            JSONArray daily_forecast = (JSONArray) root.get("daily_forecast");
            JSONObject now = root.getJSONObject("now");
            JSONObject nowWeather = now.getJSONObject("cond");
            JSONObject Time = basic.getJSONObject("update");
            /*
            JSONArray hourly_forecast = root.getJSONArray("hourly_forecast");
            JSONObject aqi = root.getJSONObject("aqi");
            JSONObject suggestion = root.getJSONObject("suggestion");
            */

            for(int i = 0; i < 6; i++){
                JSONObject dailyForecast = daily_forecast.getJSONObject(i);
                JSONObject weatherDay = dailyForecast.getJSONObject("cond");
                JSONObject tmpDay = dailyForecast.getJSONObject("tmp");
                Weather daily = new Weather();
                daily.setWeekday(weekDay(weekday,i));
                daily.setWeatherDay(weatherDay.getString("txt_d"));
                daily.setWeatherDayImg(new BitmapFetcher().getBitmap(String2URL(
                        weatherImgUrl+weatherDay.getString("code_d")+".png")));
                daily.setWeatherNight(weatherDay.getString("txt_n"));
                daily.setWeatherNightImg(new BitmapFetcher().getBitmap(String2URL(
                        weatherImgUrl+weatherDay.getString("code_n")+".png")));
                daily.setTmpHigh(tmpDay.getString("max")+"°");
                daily.setTmpLow(tmpDay.getString("min")+"°");
                weather.Daily_forecast.add(i, daily);
            }

            String city = basic.getString("city");
            String tmpNow = now.getString("tmp")+"°";
            String weatherNow = nowWeather.getString("txt");
            Bitmap weatherNowImg = new BitmapFetcher().getBitmap(String2URL(
                    weatherImgUrl+nowWeather.getString("code")+".png"));
            String tmpHigh = weather.Daily_forecast.get(0).getTmpHigh();
            String tmpLow = weather.Daily_forecast.get(0).getTmpLow();

            weather.setCity(city);
            weather.setTmpNow(tmpNow);
            weather.setWeatherNow(weatherNow);
            weather.setWeatherImg(weatherNowImg);
            weather.setUpdateTime(Time.getString("loc").substring(11, 15));
        } catch (JSONException e) {
            System.out.println("err");
            e.printStackTrace();
        }

        return weather;
    }

    public static URL String2URL(String s){
        URL url = null;
        try {
            url = new URL(s);
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static String weekDay(int weekday,int i){
        String weekDay = "";
        if(i == 0){
            return "今天";
        }
        if(weekday>7){
                weekday = weekday-7;
        }
        switch(weekday){
            case 1:
                weekDay = "周日";
                break;
            case 2:
                weekDay = "周一";
                break;
            case 3:
                weekDay = "周二";
                break;
            case 4:
                weekDay = "周三";
                break;
            case 5:
                weekDay = "周四";
                break;
            case 6:
                weekDay = "周五";
                break;
            case 7:
                weekDay = "周六";
                break;
        }
        return weekDay;

    }
}