package com.example.latoris.weather;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Latoris on 2016/10/19.
 */
public class JsonFetcher {
    String result = null;

    //本方法通过指定url访问网络数据，并返回JSON格式的string。
    public  String getJSONText(final URL url){


        Thread thread = new Thread(new Runnable(){

            @Override
            public void run() {
                BufferedReader reader = null;
                StringBuffer sbf = new StringBuffer();
                try {
                    HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.connect();
                    InputStream is = connection.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                    String strRead = null;
                    while ((strRead = reader.readLine()) != null) {
                        sbf.append(strRead);
                        sbf.append("\r\n");
                    }
                    reader.close();
                    result = sbf.toString();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        });

        thread.start();

        //等待上述线程完成执行后再返回jsonText。
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result;
    }
}
