package com.example.latoris.myword.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.latoris.myword.Bean.News;
import com.example.latoris.myword.Fetcher.BitmapFetcher;
import com.example.latoris.myword.R;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Latoris on 2017/9/22.
 */

public class NewsAdapter extends BaseAdapter {

    private List<News> list = new ArrayList<News>();
    private LayoutInflater inflater;

    public NewsAdapter() {

    }

    public NewsAdapter(List<News> list, Context context) {
        this.list = list;
        this.inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public News getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BitmapFetcher bitmapFetcher = new BitmapFetcher();
        View view=inflater.inflate(R.layout.newslist_item,null);
        News news = getItem(position);
        ImageView iv = (ImageView)view.findViewById(R.id.news_pic);
        TextView tv= (TextView) view.findViewById(R.id.news_name);
        tv.setText(news.getTitle());
        try {
            URL imageurl = new URL(news.getPics_url().get(0));
            System.out.println("加载: "+news.getPics_url().get(0));
            iv.setImageBitmap(bitmapFetcher.getBitmap(imageurl));
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return view;
    }
}
