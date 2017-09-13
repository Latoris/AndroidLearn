package com.example.latoris.wordtest;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TableLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity{
    RecyclerView recyclerView;
    WordAdapter adapter;
    WordOperate operate;
    ArrayList<Word> list = new ArrayList<>();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu_main; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void InsertDialog() {
        final TableLayout tableLayout = (TableLayout) getLayoutInflater().inflate(R.layout.insert, null);
        new AlertDialog.Builder(this)
                .setTitle("新增单词")//标题
                .setView(tableLayout)//设置视图
                //确定按钮及其动作
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String strWord = ((EditText) tableLayout.findViewById(R.id.txtWord)).getText().toString();
                        String strMeaning = ((EditText) tableLayout.findViewById(R.id.txtMeaning)).getText().toString();

                        //既可以使用Sql语句插入，也可以使用使用insert方法插入
                        // InsertUserSql(strWord, strMeaning, strSample);
                        operate.Insert(strWord, strMeaning);
                        //ArrayList<Map<String, String>> items = operate.getAll();
                        refreshList();

                    }
                })
                //取消按钮及其动作
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .create()//创建对话框
                .show();//显示对话框


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_search:
                //查找
                //SearchDialog();
                return true;
            case R.id.action_insert:
                //新增单词
                InsertDialog();
                return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        operate = new WordOperate(this);
//        recyclerView.setHasFixedSize(true);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        initData();
        adapter = new WordAdapter(list);
        //adapter.setOnRecyclerViewListener(this);
        recyclerView.setAdapter(adapter);
    }


    public void initData(){
        list = operate.getAll();
    }

    private void refreshList(){
        // 注意：千万不要直接赋值，如：orderList = ordersDao.getAllDate() 此时相当于重新分配了一个内存 原先的内存没改变 所以界面不会有变化
        // Java中的类是地址传递 基本数据才是值传递
        list.clear();
        list.addAll(operate.getAll());
        adapter.notifyDataSetChanged();
    }

}
