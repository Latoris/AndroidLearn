package com.example.latoris.myword.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Latoris on 2017/9/13.
 */

public class WordDB extends SQLiteOpenHelper {
        private final static String DATABASE_NAME = "wordsDb";//数据库名字
        private final static int DATABASE_VERSION = 1;//数据库版本

    //建表SQL
    private final static String SQL_CREATE_DATABASE = "CREATE TABLE " + Words.Word.TABLE_NAME + " (" +
            Words.Word._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            Words.Word.COLUMN_NAME_WORD + " TEXT" + "," +
            Words.Word.COLUMN_NAME_MEANING + " TEXT" + ","
            + Words.Word.COLUMN_NAME_SAMPLE + " TEXT" + " )";

    private final static String SQL_CREATE_WORD = "CREATE TABLE Word_Dict (ID INTEGER PRIMARY KEY AUTOINCREMENT,word TEXT, word_meaning TEXT)";
    //删表SQL
    private final static String SQL_DELETE_DATABASE = "DROP TABLE IF EXISTS " + Words.Word.TABLE_NAME;

    public WordDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //创建数据库
        sqLiteDatabase.execSQL(SQL_CREATE_DATABASE);
        sqLiteDatabase.execSQL(SQL_CREATE_WORD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        //当数据库升级时被调用，首先删除旧表，然后调用OnCreate()创建新表
        sqLiteDatabase.execSQL(SQL_DELETE_DATABASE);
        onCreate(sqLiteDatabase);
    }



}
