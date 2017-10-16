package com.example.latoris.myword.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.latoris.myword.Bean.Word;
import com.example.latoris.myword.R;

import java.util.ArrayList;

/**
 * Created by Latoris on 2017/9/5.
 */

public class WordAdapter extends RecyclerView.Adapter{
    public static interface OnRecyclerViewListener {
        void onItemClick(int position);
        boolean onItemLongClick(int position);
    }
    int j = 0;
    private OnRecyclerViewListener onRecyclerViewListener;
    public void setOnRecyclerViewListener(OnRecyclerViewListener onRecyclerViewListener) {
        this.onRecyclerViewListener = onRecyclerViewListener;
    }

    private static final String TAG = WordAdapter.class.getSimpleName();
    private ArrayList<Word> list;

    public void setShow_Meaning_Switch(boolean show_Meaning_Switch) {
        Show_Meaning_Switch = show_Meaning_Switch;
    }

    public boolean isShow_Meaning_Switch() {
        return Show_Meaning_Switch;
    }

    private boolean Show_Meaning_Switch = true;
    public WordAdapter(ArrayList<Word> list) {
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
    //    Log.v(TAG, "onCreateViewHolder, i: " + i);
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.wordlist_item, null);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return new WordsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
      //  Log.v(TAG, "onBindViewHolder, i: " + i + ", viewHolder: " + viewHolder);
        //holder.position = i;
        Word word = list.get(i);
        WordsViewHolder holder = (WordsViewHolder) viewHolder;
        holder.word_name.setText(word.getWord_name());
        if (Show_Meaning_Switch) {
            holder.word_meanings.setVisibility(View.VISIBLE);
            holder.word_meanings.setText(word.getWord_meaning());
        } else {
            holder.word_name.setTextSize(30);
            holder.word_meanings.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class WordsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener
    {
        public View rootView;
        public TextView word_name;
        public TextView word_meanings;
        public int position;

        public WordsViewHolder(View itemView) {
            super(itemView);
            word_name = (TextView) itemView.findViewById(R.id.word_name);
            word_meanings = (TextView) itemView.findViewById(R.id.word_meaning);
            rootView = itemView.findViewById(R.id.item_view);
            rootView.setOnClickListener(this);
            rootView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (null != onRecyclerViewListener) {
                onRecyclerViewListener.onItemClick(position);
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if(null != onRecyclerViewListener){
                return onRecyclerViewListener.onItemLongClick(position);
            }
            return false;
        }
    }
}