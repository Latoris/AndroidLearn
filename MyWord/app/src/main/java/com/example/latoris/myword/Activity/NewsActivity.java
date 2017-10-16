package com.example.latoris.myword.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.latoris.myword.Adapter.NewsAdapter;
import com.example.latoris.myword.Bean.News;
import com.example.latoris.myword.Bean.Word;
import com.example.latoris.myword.JsonUtil.DayJsonUtils;
import com.example.latoris.myword.JsonUtil.NewsJsonUtils;
import com.example.latoris.myword.R;

import java.util.List;

public class NewsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private TextView day_title;
    private TextView day_content;

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ListView listview = (ListView)parent;
        News a = (News)listview.getItemAtPosition(position);
        Intent intent = new Intent(this, NewDetailActivity.class);
        intent.putExtra("new", a);
        startActivity(intent);
        //.setText(a.getWord_name());
        //word_meaning_dict.setText(a.getWord_meaning());
        //query(a.getWord_name());
    }

    private TextView day_meaning;
    private TextView news_title;
    private ListView newslist;
    private List<News> list;
    private Word word;
    private NewsAdapter adapter;

    NewsJsonUtils newsJsonUtils = new NewsJsonUtils();
    DayJsonUtils dayJsonUtils = new DayJsonUtils();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        day_content = (TextView)findViewById(R.id.day_content);
        day_meaning = (TextView)findViewById(R.id.day_meaning);
        day_title  = (TextView)findViewById(R.id.day_title);
        newslist = (ListView)findViewById(R.id.news_list);
        news_title = (TextView)findViewById(R.id.news_title);
        initdata();
    }

    private void initdata(){

        list = newsJsonUtils.getNews();
        word = dayJsonUtils.getDays();
        day_title.setText("今日一句" + word.getTime() + "：");
        day_meaning.setText(word.getWord_meaning());
        day_content.setText(word.getWord_name());
        adapter = new NewsAdapter(list, this);
        newslist.setAdapter(adapter);
        newslist.setOnItemClickListener(this);
    }

    public void refreshList(){
        // 注意：千万不要直接赋值，如：orderList = ordersDao.getAllDate() 此时相当于重新分配了一个内存 原先的内存没改变 所以界面不会有变化
        // Java中的类是地址传递 基本数据才是值传递
        list.clear();
        list.addAll(newsJsonUtils.getNews());
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu_main; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_news, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automat/ically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_hide:
                //新增单词
                day_content.setVisibility(View.GONE);
                day_meaning.setVisibility(View.GONE);
                day_title.setVisibility(View.GONE);
                return true;
            case R.id.action_refresh:
                refreshList();
                return true;
        }
        return  true;
    }
}
