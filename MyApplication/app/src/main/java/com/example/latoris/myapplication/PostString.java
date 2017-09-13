package com.example.latoris.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Latoris on 2017/4/27.
 */

public class PostString extends AppCompatActivity {

    public String url;
    public String name;
    public String password;

    public PostString(String url, String name, String password){
        this.url = url;
        this.name = name;
        this.password = password;

    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            try {
                JSONObject user = new JSONObject();
                user.put("username", name);
                user.put("password", password);
                JSONObject person = new JSONObject();
                person.put("person", user);
                String Content = String.valueOf(person);
                URL httpurl = new URL(url);
                HttpURLConnection conn = (HttpURLConnection) httpurl.openConnection();
                conn.setDoOutput(true);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type","application/json");
                OutputStream os = conn.getOutputStream();
                os.write(Content.getBytes());
                os.close();
                int code = conn.getResponseCode();
                if(code == 200){
                    //Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT);
                }

            }
            catch (JSONException je){
                je.printStackTrace();
            }
            catch (Exception e){
                e.printStackTrace();
            }


        }
    };
    public void post() {
        Thread th = new Thread(runnable);
        th.start();
    }
}
