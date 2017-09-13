package com.example.latoris.wordtest;

/**
 * Created by Latoris on 2017/9/5.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//package com.example.latoris.words;


/**
 * Created by Latoris on 2016/10/12.
 */
public class WordOperate {

    private Context context;
    private WordDB mDbHelper;
    public WordOperate(Context context){
        this.context = context;
        mDbHelper = new WordDB(context);
    }


    //使用Sql语句更新单词
    public void UpdateUseSql(String strId, String strWord, String strMeaning, String strSample) {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        String sql = "update words set word=?,meaning=?,sample=? where _id=?";
        db.execSQL(sql, new String[]{strWord, strMeaning, strSample, strId});
    }

    //使用方法更新
    public void Update(String strId, String strWord, String strMeaning, String strSample) {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(Words.Word.COLUMN_NAME_WORD, strWord);
        values.put(Words.Word.COLUMN_NAME_MEANING, strMeaning);
        values.put(Words.Word.COLUMN_NAME_SAMPLE, strSample);

        String selection = Words.Word._ID + " = ?";
        String[] selectionArgs = {strId};

        int count = db.update(
                Words.Word.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }

    //修改对话框




    //使用query方法查找
    public ArrayList<Word> Search(String strWordSearch) {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                Words.Word._ID,
                Words.Word.COLUMN_NAME_WORD,
                Words.Word.COLUMN_NAME_MEANING,
                Words.Word.COLUMN_NAME_SAMPLE
        };

        String sortOrder =
                Words.Word.COLUMN_NAME_WORD + " DESC";

        String selection = Words.Word.COLUMN_NAME_WORD + " LIKE ?";
        String[] selectionArgs = {"%" + strWordSearch + "%"};

        Cursor c = db.query(
                Words.Word.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        return ConvertCursor2List(c);
    }

    public ArrayList<Word> getAll() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        String[] projection = {
                Words.Word._ID,
                Words.Word.COLUMN_NAME_WORD,
                Words.Word.COLUMN_NAME_MEANING,
                Words.Word.COLUMN_NAME_SAMPLE
        };

        Cursor cur = db.query(Words.Word.TABLE_NAME, projection, null, null, null, null, null);

        return ConvertCursor2List(cur);
    }
    //使用Sql语句查找
    public ArrayList<Word> SearchUseSql(String strWordSearch) {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String sql = "select * from words where word like ? order by word desc";
        Cursor c = db.rawQuery(sql, new String[]{"%" + strWordSearch + "%"});

        return ConvertCursor2List(c);
    }

    //使用Sql语句插入单词
    public void InsertUserSql(String strWord, String strMeaning) {
        String sql = "insert into  words(word,meaning,sample) values(?,?,?)";

        //Gets the data repository in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        db.execSQL(sql, new String[]{strWord, strMeaning});
    }

    //使用insert方法增加单词
    public void Insert(String strWord, String strMeaning) {

        //Gets the data repository in write mode*/
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(Words.Word.COLUMN_NAME_WORD, strWord);
        values.put(Words.Word.COLUMN_NAME_MEANING, strMeaning);
        //values.put(Words.Word.COLUMN_NAME_SAMPLE, strSample);

        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                Words.Word.TABLE_NAME,
                null,
                values);
    }

    //使用Sql语句删除单词
    public void DeleteUseSql(String strId) {
        String sql = "delete from words where _id='" + strId + "'";

        //Gets the data repository in write mode*/
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        db.execSQL(sql);
    }

    public ArrayList<Word> ConvertCursor2List(Cursor cursor){
        ArrayList<Word> wordlist = new ArrayList<>();

        cursor.moveToFirst();
        for(int i = 0; i < cursor.getCount();i++){
            Word wordtmp = new Word();
            wordtmp.setId(cursor.getInt(0));
            wordtmp.setWord_name(cursor.getString(1));
            wordtmp.setWord_meaning(cursor.getString(2));
            cursor.moveToNext();
            wordlist.add(wordtmp);
        }
        cursor.close();
        return wordlist;
    }

}
