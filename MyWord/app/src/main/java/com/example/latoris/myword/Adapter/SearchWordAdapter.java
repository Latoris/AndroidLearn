package com.example.latoris.myword.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.latoris.myword.Bean.Word;
import com.example.latoris.myword.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Latoris on 2017/9/22.
 */

public class SearchWordAdapter extends BaseAdapter {

    private List<Word> list = new ArrayList<Word>();
    private LayoutInflater inflater;

    public SearchWordAdapter() {

    }

    public SearchWordAdapter(List<Word> list, Context context) {
        this.list = list;
        this.inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Word getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=inflater.inflate(R.layout.list_item,null);
        Word word = getItem(position);
        TextView tv= (TextView) view.findViewById(R.id.word_search);
        tv.setText(word.getWord_name());
        return view;
    }
}
