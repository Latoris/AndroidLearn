package com.example.latoris.filepractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    private Button write;
    private Button read;
    String MyFileName = "test";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        write = (Button)findViewById(R.id.write);
        read = (Button)findViewById(R.id.read);
        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OutputStream out = null;
                try{
                    FileOutputStream fileOutputStream = openFileOutput(MyFileName, MODE_PRIVATE);
                    out = new BufferedOutputStream(fileOutputStream);
                    String content = "LL is very good";
                    try{
                        out.write(content.getBytes(StandardCharsets.UTF_8));
                        Toast.makeText(MainActivity.this, "写入数据成攻："+ content,
                                Toast.LENGTH_SHORT).show();
                    }
                    finally{
                        if(out!=null)
                            out.close();
                    }
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputStream in = null;
                try{
                    FileInputStream fileInputStream = openFileInput(MyFileName);
                    in = new BufferedInputStream(fileInputStream);
                    int c;
                    StringBuilder stringBuilder = new StringBuilder("");
                    try{
                        while((c = in.read())!= -1) {
                            stringBuilder.append((char) c);
                        }
                        Toast.makeText(MainActivity.this, stringBuilder.toString(),
                                    Toast.LENGTH_LONG).show();

                    }
                    finally{
                        if(in != null)
                            in.close();
                    }
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}
