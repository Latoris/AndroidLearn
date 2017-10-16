package com.example.latoris.myword.Bean;

/**
 * Created by Latoris on 2017/9/6.
 */

public class Word {
    private int id;
    private String word_name;
    private String word_meaning;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    private String time;

    public int getId() {
        return id;
    }

    public String getWord_name() {
        return word_name;
    }

    public void setWord_name(String word_name) {
        this.word_name = word_name;
    }

    public String getWord_meaning() {
        return word_meaning;
    }

    public void setWord_meaning(String word_meaning) {
        this.word_meaning = word_meaning;
    }

    public void setId(int id) {
        this.id = id;
    }
}
