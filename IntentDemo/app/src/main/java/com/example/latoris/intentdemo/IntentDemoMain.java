package com.example.latoris.intentdemo;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class IntentDemoMain extends AppCompatActivity {
    private Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_demo_main);
        button1 =(Button)findViewById(R.id.button);
        button1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.weibo.com"));
                startActivity(intent);
                /*
                显示启动
                Intent intent = new Intent(IntentDemoMain.this, NewActivity.class);
                startActivity(intent);
                */
            }
        });

    }
}
