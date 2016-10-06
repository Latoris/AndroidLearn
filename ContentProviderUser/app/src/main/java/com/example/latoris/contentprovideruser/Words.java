package com.example.latoris.contentprovideruser;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Latoris on 2016/9/9.
 */
public class Words {
    public Words() {
    }
    public static abstract class Word implements BaseColumns {
        public static final String COLUMN_NAME_WORD = "word";//列：单词
        public static final String COLUMN_NAME_MEANING = "meaning";//列：单词含义
        public static final String COLUMN_NAME_SAMPLE = "sample";//单词示例

        //Content Uri
        public static final String CONTENT_URI_STRING = "content://com.example.latoris.databasepractice/word";
        public static final Uri CONTENT_URI = Uri.parse(CONTENT_URI_STRING);
    }
}
