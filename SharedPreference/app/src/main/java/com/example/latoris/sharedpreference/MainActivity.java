package com.example.latoris.sharedpreference;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private final static String SharedPreferencesFileName = "config";

    private final static String key_userName = "UserName";
    private final static String key_LoginDate = "LoginDate";
    private final static String key_UserType = "UserType";
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = getSharedPreferences(SharedPreferencesFileName, MODE_PRIVATE);
        editor = preferences.edit();
        Button write = (Button)findViewById(R.id.write);
        Button read = (Button)findViewById(R.id.read);
        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                 //       java.text.SimpleDateFormat.getDateInstance().toString());
            //String strLoginDate = simpleDateFormat.format(new Date());

                editor.putString(key_userName, "3岁御姐美云");
                editor.putString(key_LoginDate, "12345");
                editor.putInt(key_UserType, 1);

                editor.apply();
            }
        });
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strUserName = preferences.getString(key_userName, null);
                String strLoginDate = preferences.getString(key_LoginDate, null);
                int nUserType = preferences.getInt(key_UserType, 0);
                if(strUserName != null && strLoginDate != null)
                    Toast.makeText(MainActivity.this, "用户名： "+
                            strUserName + "登录时间: "
                            + strLoginDate + "用户类型" + nUserType, Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this, "无数据", Toast.LENGTH_LONG).show();
            }
        });
    }
}
