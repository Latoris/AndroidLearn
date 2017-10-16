package com.example.latoris.myword.JsonUtil;

import com.example.latoris.myword.Bean.Word;
import com.example.latoris.myword.Fetcher.DayJsonFetcher;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Latoris on 2017/10/2.
 */

public class DayJsonUtils {
    private Word word = new Word();
    private String urlstring = "http://open.iciba.com/dsapi/";
    public DayJsonUtils(){

    }
    public Word getDays(){
        String jsonText = new DayJsonFetcher().getJSONText(urlstring);
        while(jsonText.isEmpty()){
            jsonText = new DayJsonFetcher().getJSONText(urlstring);
        }
        try {
            JSONObject object = new JSONObject(String.valueOf(jsonText));
            word.setTime(object.getString("dateline"));
            word.setWord_meaning(object.getString("note"));
            word.setWord_name(object.getString("content"));

        } catch (JSONException e) {
            System.out.println("err");
            e.printStackTrace();
        }
        return word;
    }
}
