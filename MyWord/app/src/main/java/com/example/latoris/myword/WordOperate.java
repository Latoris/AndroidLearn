package com.example.latoris.myword;

/**
 * Created by Latoris on 2017/9/5.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.latoris.myword.Bean.Word;
import com.example.latoris.myword.Database.WordDB;
import com.example.latoris.myword.Database.Words;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

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
    public void UpdateUseSql(String strId, String strWord, String strMeaning) {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        String sql = "update words set word=?,meaning=? where _id=?";
        db.execSQL(sql, new String[]{strWord, strMeaning, strId});
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


    public void DeleteDict() {
        String sql = "delete from Word_Dict";
        //Gets the data repository in write mode*/
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        db.execSQL(sql);
    }

    public void create_word() throws Exception{
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        InputStreamReader in = new InputStreamReader(context.getResources().getAssets().open("test.txt"));
        //FileInputStream in = new FileInputStream("test.txt");
        //StringBuffer buffer = new StringBuffer();
        Cursor c = db.rawQuery("select * from word_Dict", null);
        if(c.getCount() != 0)
            return;
        String line; // 用来保存每行读取的内容
        BufferedReader bufferreader = new BufferedReader(in);
        line = bufferreader.readLine(); // 读取第一行
        System.out.println("读取"+line);
        db.beginTransaction();
        int id = 0;
        while (line != null) { // 如果 line 为空说明读完了
            //Gets the data repository in write mode
            int word_length = 0;
            for(int i = 0;i < line.length();i++){
                if(Character.isAlphabetic(line.charAt(i)) && line.charAt(i)!=' ')
                    word_length++;
                else
                    break;
            }
            String word_temp = line.substring(0, word_length);
            String word_meaning_temp = line.substring(word_length+1, line.length());
            //word_meaning_temp = URLEncoder.encode(word_meaning_temp, "GB2312");
            db.execSQL("insert into Word_Dict(ID,word,word_meaning) values(?,?,?)", new String[]{
                    String.valueOf(id),word_temp , word_meaning_temp});
            line = bufferreader.readLine(); // 读取下一行
            System.out.println("读取"+line);
            id++;
        }
        db.setTransactionSuccessful();
        db.endTransaction();
        in.close();
    }

    public ArrayList<Word> search_word(String word_suffix){
        ArrayList<Word> wordlist = new ArrayList<Word>();
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        String[] projection = {"word", "word_meaning"};
        String[] selectionArgs = {word_suffix + "%"};

        Cursor c = db.query(
                "Word_Dict",  // The table to query
                projection,                               // The columns to return
                "word LIKE ?",                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                "word"                                 // The sort order
        );
        c.moveToFirst();
        int n = c.getCount();
        System.out.println("总共"+c.getCount()+"个数据");
        if(n >= 15)
            n = 15;
        for(int i = 0; i < n;i++){
            Word wordtmp = new Word();
            System.out.println("第"+i+"个数据"+c.getString(0));
            //wordtmp.setId(c.getInt(0));
            wordtmp.setWord_name(c.getString(0));
            wordtmp.setWord_meaning(c.getString(1));
            c.moveToNext();
            wordlist.add(wordtmp);
        }
        c.close();
        return wordlist;
    }

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

    public ArrayList<Word> getAllSortByAlpha() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        String[] projection = {
                Words.Word._ID,
                Words.Word.COLUMN_NAME_WORD,
                Words.Word.COLUMN_NAME_MEANING,
                Words.Word.COLUMN_NAME_SAMPLE
        };

        Cursor cur = db.query(Words.Word.TABLE_NAME, projection, null, null, null, null, Words.Word.COLUMN_NAME_WORD);

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
