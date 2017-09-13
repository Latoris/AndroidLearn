package com.example.latoris.myapplication;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

        private ProgressDialog pDialog;
        JSONParser jsonParser = new JSONParser();
        EditText name;
        EditText password;
        Button login_button;
        String nameString;
        String passwordString;
        private String url = "http://45.76.99.222/test1.php";
        private static final String TAG_MESSAGE = "message";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=(EditText)findViewById(R.id.name);
        password=(EditText)findViewById(R.id.password);
        login_button=(Button)findViewById(R.id.submit);
        login_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if(validate()){
                    nameString = name.getText().toString();
                    passwordString = password.getText().toString();
                    //new Up().execute();
                    //PostString ps = new PostString(url, nameString, passwordString);
                    post();
                }
            }
        });
    }

    private boolean validate() {
        String name1 = name.getText().toString().trim();
        if (name1.equals("")) {
            DialogUtil.showDialog(this, "您还没有填写建议", false);
            return false;
        }
        String password1 = password.getText().toString().trim();
        if (password1.equals("")) {
            DialogUtil.showDialog(this, "您还没有填写建议", false);
            return false;
        }

        return true;
    }



    class Up extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("正在上传..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        protected String doInBackground(String... args) {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("name", nameString));
            params.add(new BasicNameValuePair("password", passwordString));
            try{
                post();
                //String message = json.getString(TAG_MESSAGE);
                //return message;
                return "aaaaaaaaaaa";
            }catch(Exception e){
                e.printStackTrace();
                return e.toString();
            }
        }

        protected void onPostExecute(String message) {
            pDialog.dismiss();
            //message 为接收doInbackground的返回值
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        }
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            try {
                JSONObject user = new JSONObject();
                user.put("username", nameString);
                user.put("password", passwordString);
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
