package com.example.latoris.myapplication;

import android.provider.Settings;
import android.util.Log;

import com.google.gson.Gson;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import static android.R.attr.start;
import static android.webkit.ConsoleMessage.MessageLevel.LOG;


/**
 * Created by Latoris on 2017/3/5.
 */

public class JSONParser implements  Runnable{
    static InputStream is = null;
    static JSONObject jsobj = new JSONObject();
    static String JSON = null;
    static String json = "";
    private String url1=null;
    private List<NameValuePair>params=null;

    public JSONParser(){

    }

    public void makeHttpRequest(String url1, String method, List<NameValuePair>params) {
        Thread thread = new Thread(this);
        this.url1 = url1;
        this.params = params;
        thread.start();
        return;
    }

    @Override
    public void run(){
        try{
            HttpURLConnection conn = null;
            URL url = new URL(url1);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true); //允许输入流，即允许下载
            conn.setDoOutput(true); //允许输出流，即允许上传
            conn.setUseCaches(false); //不使用缓冲
            conn.setInstanceFollowRedirects(true);
            conn.setRequestProperty("Content-Type","application/json");
            //post
            conn.setRequestMethod("POST");
            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            Gson gson = new Gson();
            String jsonString = gson.toJson(params);
            Log.v("a", jsonString);
            wr.writeBytes(jsonString);
            wr.flush();
            wr.close();
            int statusCode = conn.getResponseCode();
            if(statusCode == 200){
                is = conn.getInputStream();

            }
            /*
            is = conn.getInputStream();   //获取输入流，此时才真正建立链接
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                is, "UTF-8"));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            json = sb.toString();
            jsobj = new JSONObject(json);
            */
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
