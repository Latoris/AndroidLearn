package com.example.latoris.activitydatasimple;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class AnotherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);
        Intent intent=getIntent();
        String name=intent.getStringExtra("CVname");
        Integer age=intent.getIntExtra("number",5);
        Toast.makeText(this,name+"角色数： "+age, Toast.LENGTH_LONG).show();

    }
}
