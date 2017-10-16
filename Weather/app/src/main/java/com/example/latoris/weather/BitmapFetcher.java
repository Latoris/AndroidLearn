package com.example.latoris.weather;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Latoris on 2016/10/25.
 */
public class BitmapFetcher {
    Bitmap bitmap = null;

    //本方法通过指定url访问网络数据，并返回JSON格式的string。
    public Bitmap getBitmap(final URL url){


        Thread thread = new Thread(new Runnable(){

            @Override
            public void run() {
               try{
                   byte[] data = null;
                   HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                   conn.setConnectTimeout(5 * 1000);
                   conn.setRequestMethod("GET");
                   InputStream inStream = conn.getInputStream();
                   if(conn.getResponseCode()==200){
                       ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                       byte[] buffer = new byte[1024];
                       int len = 0;
                       while( (len=inStream.read(buffer)) != -1){
                           outStream.write(buffer, 0, len);
                       }
                       outStream.close();
                       inStream.close();
                       data =  outStream.toByteArray();;
                       bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                   }
               }catch (Exception e){

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

        return bitmap;
    }
}
