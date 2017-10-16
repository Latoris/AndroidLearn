package com.example.latoris.myword.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.latoris.myword.Bean.News;
import com.example.latoris.myword.R;

public class NewDetailActivity extends AppCompatActivity {
    private News news;
    private TextView title;
    private TextView time;
    private TextView content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
         news = getIntent().getParcelableExtra("new");
        title = (TextView)findViewById(R.id.news_content_title);
        time = (TextView)findViewById(R.id.news_content_time);
        content = (TextView)findViewById(R.id.news_content);
        content.setText(news.getContent());
        time.setText(news.getTime());
        title.setText(news.getTitle());
        content.setMovementMethod(ScrollingMovementMethod.getInstance());
    }
}
