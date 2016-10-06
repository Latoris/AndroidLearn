package com.example.latoris.threadpractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Runnable myWorker = new Runnable(){
            @Override
            public void run(){
                long endTime = System.currentTimeMillis() + 10 * 1000;
                while(System.currentTimeMillis() < endTime){
                    while(!Thread.interrupted()){
                        synchronized (this){
                            try{
                                wait();
                            }
                            catch(Exception e){
                                e.printStackTrace();
                            }
                        }
                    }
                }

            }
        };
        Button button = (Button)findViewById(R.id.start);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread workThread = new Thread(null, myWorker, "WorkThread");
                workThread.start();
            }
        });
    }
}
