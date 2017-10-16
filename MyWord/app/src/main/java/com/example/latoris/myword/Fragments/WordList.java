package com.example.latoris.myword.Fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.Toast;

import com.example.latoris.myword.Adapter.WordAdapter;
import com.example.latoris.myword.Bean.Word;
import com.example.latoris.myword.R;
import com.example.latoris.myword.WordOperate;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class WordList extends Fragment {
    private String tag = "word";
    private Context context;
    private SwipeMenuRecyclerView recyclerView;
    private WordAdapter adapter;
    private WordOperate operate;
    private ArrayList<Word> list = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        context = this.getActivity();
        super.onCreate(savedInstanceState);
        operate = new WordOperate(context);
        try {
            operate.create_word();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Log.v("word", "list onCreate");
        setHasOptionsMenu(true);
    }

    public void refreshList(){
        // 注意：千万不要直接赋值，如：orderList = ordersDao.getAllDate() 此时相当于重新分配了一个内存 原先的内存没改变 所以界面不会有变化
        // Java中的类是地址传递 基本数据才是值传递
        list.clear();
        list.addAll(operate.getAll());
        adapter.notifyDataSetChanged();
    }


    /**
     * 菜单创建器，在Item要创建菜单的时候调用。
     */
    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
            int width = getResources().getDimensionPixelSize(R.dimen.dp_70);

            // 1. MATCH_PARENT 自适应高度，保持和Item一样高;
            // 2. 指定具体的高，比如80;
            // 3. WRAP_CONTENT，自身高度，不推荐;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;

            // 根据ViewType来决定哪一个item该如何添加菜单。
            // 这里模拟业务，实际开发根据自己的业务计算。
            if (viewType % 3 == 0) {
                SwipeMenuItem deleteItem = new SwipeMenuItem(context)
                        .setBackground(R.drawable.selector_red)
                        .setText("删除")
                        .setTextColor(Color.WHITE)
                        .setWidth(width)
                        .setHeight(height);
                swipeRightMenu.addMenuItem(deleteItem);// 添加菜单到右侧。
                SwipeMenuItem addItem = new SwipeMenuItem(context)
                        .setBackground(R.drawable.selector_green)
                        .setText("编辑")
                        .setTextColor(Color.WHITE)
                        .setWidth(width)
                        .setHeight(height);
                swipeRightMenu.addMenuItem(addItem); // 添加菜单到右侧。
            }

        }
    };

    /**
     * RecyclerView的Item的Menu点击监听。
     */
    private SwipeMenuItemClickListener mMenuItemClickListener = new SwipeMenuItemClickListener() {
        @Override
        public void onItemClick(SwipeMenuBridge menuBridge) {
            menuBridge.closeMenu();
            int direction = menuBridge.getDirection(); // 左侧还是右侧菜单。
            int adapterPosition = menuBridge.getAdapterPosition(); // RecyclerView的Item的position。
            int menuPosition = menuBridge.getPosition(); // 菜单在RecyclerView的Item中的Position。

            if (direction == SwipeMenuRecyclerView.RIGHT_DIRECTION) {
                //Toast.makeText(MainActivity.this, "list第" + adapterPosition + "; 右侧菜单第" + menuPosition, Toast.LENGTH_SHORT).show();
                if(menuPosition == 1){
                    System.out.println("xiugai");
                    Toast.makeText(context, "修改", Toast.LENGTH_SHORT).show();
                    UpdatetDialog(String.valueOf(list.get(adapterPosition).getId()),list.get(adapterPosition).getWord_name(), list.get(adapterPosition).getWord_meaning());
                    //refreshList();
                }
                else if(menuPosition == 0){
                    System.out.println("shanchu");
                    Toast.makeText(context, "删除", Toast.LENGTH_SHORT).show();
                    operate.DeleteUseSql(String.valueOf(list.get(adapterPosition).getId()));
                    refreshList();
                }
            }

        }
    };

    @Override
    public void onCreateOptionsMenu(Menu menu,MenuInflater inflater) {
        // Inflate the menu_main; this adds items to the action bar if it is present
        inflater.inflate(R.menu.menu_list, menu);
        Log.v("word", "create option menu");
        //onCreateOptionsMenu(menu, inflater);
        super.onCreateOptionsMenu(menu,inflater);
    }



    private void InsertDialog() {
        final TableLayout tableLayout = (TableLayout) getActivity().getLayoutInflater().inflate(R.layout.insert, null);
        new AlertDialog.Builder(context)
                .setTitle("新增单词")//标题
                .setView(tableLayout)//设置视图
                //确定按钮及其动作
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String strWord = ((EditText) tableLayout.findViewById(R.id.txtWord)).getText().toString();
                        String strMeaning = ((EditText) tableLayout.findViewById(R.id.txtMeaning)).getText().toString();
                        //既可以使用Sql语句插入，也可以使用使用insert方法插入
                        operate.Insert(strWord, strMeaning);
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
    private void UpdatetDialog(String sid, String word, String meaning) {
        final TableLayout tableLayout = (TableLayout) getActivity().getLayoutInflater().inflate(R.layout.insert, null);
        final EditText txtWord = (EditText) tableLayout.findViewById(R.id.txtWord);
        final EditText txtMeaning = (EditText) tableLayout.findViewById(R.id.txtMeaning);
        final String id = sid;
        txtWord.setText(word);
        txtMeaning.setText(meaning);
        new AlertDialog.Builder(context)
                .setTitle("编辑单词")//标题
                .setView(tableLayout)//设置视图
                //确定按钮及其动作
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        System.out.println("queding");
                        String strWord = txtWord.getText().toString();
                        String strMeaning = txtMeaning.getText().toString();
                        //既可以使用Sql语句插入，也可以使用使用insert方法插入
                        // InsertUserSql(strWord, strMeaning, strSample);
                        operate.UpdateUseSql(id,strWord, strMeaning);
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
        // automat/ically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case R.id.action_order_alpha:
                list.clear();
                list.addAll(operate.getAllSortByAlpha());
                adapter.notifyDataSetChanged();
                return true;
            case R.id.action_show_meaning:
                //查找
                //SearchDialog();
                if(adapter.isShow_Meaning_Switch()){
                    adapter.setShow_Meaning_Switch(false);
                    refreshList();
                }
                else{
                    adapter.setShow_Meaning_Switch(true);
                    refreshList();
                }
                return true;
            case R.id.action_insert:
                //新增单词
                InsertDialog();
                return true;
            case R.id.action_refresh:
                refreshList();
                return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        Log.v(tag, "list onresume");
        super.onResume();
    }

    @Override
    public void onStop(){
        Log.v(tag, "list stop");
        super.onStop();
    }

    @Override
    public void onStart() {
        Log.v(tag, "list start");
        super.onStart();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        Log.v("word", "list onCreateView");
        View view = inflater.inflate(R.layout.fragment_word_list, container, false);
        recyclerView = (SwipeMenuRecyclerView)view.findViewById(R.id.recyclerview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        list = operate.getAll();
        adapter = new WordAdapter(list);
        //adapter.setOnRecyclerViewListener(this);
        recyclerView.setSwipeMenuCreator(swipeMenuCreator);
        recyclerView.setSwipeMenuItemClickListener(mMenuItemClickListener);
        recyclerView.setAdapter(adapter);
        return view;
    }
}
