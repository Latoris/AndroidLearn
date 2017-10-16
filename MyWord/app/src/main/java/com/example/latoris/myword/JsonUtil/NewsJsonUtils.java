package com.example.latoris.myword.JsonUtil;

import com.example.latoris.myword.Bean.News;
import com.example.latoris.myword.Fetcher.JsonFetcher;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Latoris on 2017/10/2.
 */

public class NewsJsonUtils {
    private List<News> list = new ArrayList<News>();
    private String urlstring = "https://47.90.63.143/news/bbc?catid=%2Fcps%2Fnews%2Fworld&apikey=zzhTqF2XPEoTw7vmpdNqva3eOo3aRpZkV9CTUxTNSnNWDFiAPrYae7bZ5MSuhA6V";
    private String jsonText;
    public NewsJsonUtils(){

    }
    public List<News> getNews(){
        while(jsonText == null){
            jsonText = new JsonFetcher().getJSONText(urlstring);
        }
        try {
            JSONObject object = new JSONObject(String.valueOf(jsonText));
            JSONArray rootArray = (JSONArray) object.get("data");
            for(int i = 0; i < 7; i++){
                News news = new News();
                JSONObject now = rootArray.getJSONObject(i);
                news.setTime(now.getString("publishDateStr"));
                news.setContent(now.getString("content"));
                news.setUrl(now.getString("url"));
                news.setTitle(now.getString("title"));
                if(now.getString("imageUrls") != null){
                    JSONArray picarray = now.getJSONArray("imageUrls");
                    List<String> piclist = new ArrayList<String>();
                    for (int j = 0; j < 1; j++) {
                        String pic = (String) picarray.get(j);
                        String b = "http://ichef.bbci.co.uk/news/500/";
                        pic = b + pic.substring(50, pic.length());
                        piclist.add(pic);
                    }
                    news.setPic_s((String) picarray.get(0));
                    news.setPics_url(piclist);
                }
                System.out.println("第"+i+"条新闻: "+news.getTitle());
                list.add(news);
            }
        } catch (JSONException e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
        return list;
    }
}
