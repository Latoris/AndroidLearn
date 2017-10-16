package com.example.latoris.myword;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.latoris.myword.Fragments.SearchWord;
import com.example.latoris.myword.Fragments.TranslateFragment;
import com.example.latoris.myword.Fragments.WordList;
import com.youdao.sdk.app.YouDaoApplication;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private RelativeLayout layoutTab01;//Tab四个按钮之一
    private RelativeLayout layoutTab02;
    private RelativeLayout layoutTab03;
    private ImageView search;  //Tab图片
    private ImageView main;
    private ImageView settings;
    private TextView list_text;
    private TextView search_text;
    private TextView book_text;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private SearchWord search_fragment;   //内容区域
    private WordList main_fragment;
    private TranslateFragment setting_fragment;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu_main; this adds items to the action bar if it is present.
        ActionBar actionBar = getSupportActionBar();
        actionBar.show();
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        YouDaoApplication.init(this,"3163fdea7a239804");//创建应用，每个应用都会有一个Appid，绑定对应的翻译服务实例，即可使用
        initView();
        initEvent();
        setSelect(1);
    }

    private void setSelect(int i) {
        clearImageView();
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        hideFragment(transaction);
        switch (i) {
            case 0:
                System.out.println("search pressed");
                search.setImageResource(R.mipmap.search_button_pressed);
                if(search_fragment == null){
                    search_fragment = new SearchWord();
                    transaction.add(R.id.fragment_ui, search_fragment);
                }else {
                    transaction.show(search_fragment);
                }
                break;
            case 1:
                System.out.println("list pressed");
                main.setImageResource(R.mipmap.list_button_pressed);
                if(main_fragment == null){
                    main_fragment = new WordList();
                    transaction.add(R.id.fragment_ui, main_fragment);
                }else {
                    transaction.show(main_fragment);
                }
                break;
            case 2:
                System.out.println("book pressed");
                settings.setImageResource(R.mipmap.book_pressed);
                if(setting_fragment == null){
                    setting_fragment = new TranslateFragment();
                    transaction.add(R.id.fragment_ui, setting_fragment);
                }else {
                    transaction.show(setting_fragment);
                }
                break;
            default:
                break;
        }
        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction2) {
        // TODO Auto-generated method stub
        if(search_fragment != null){
            transaction2.hide(search_fragment);
        }

        if(main_fragment != null){
            transaction2.hide(main_fragment);
        }

        if(setting_fragment != null){
            transaction2.hide(setting_fragment);
        }
    }

    private void clearImageView() {
        System.out.println("clear button view");
        search.setImageResource(R.mipmap.search_button);
        main.setImageResource(R.mipmap.list_button);
        settings.setImageResource(R.mipmap.book_button);
    }

    private void initEvent() {
        layoutTab01.setOnClickListener(this);
        layoutTab02.setOnClickListener(this);
        layoutTab03.setOnClickListener(this);

    }

    private void initView() {
        layoutTab01 = (RelativeLayout) findViewById(R.id.search_button_layout);
        layoutTab02 = (RelativeLayout) findViewById(R.id.main_button_layout);
        layoutTab03 = (RelativeLayout) findViewById(R.id.settings_button_layout);
        list_text = (TextView)findViewById(R.id.list_text);
        search_text = (TextView)findViewById(R.id.search_text);
        book_text = (TextView)findViewById(R.id.book_text);
        search = (ImageView) findViewById(R.id.search_button);
        main = (ImageView) findViewById(R.id.main_button);
        settings = (ImageView) findViewById(R.id.settings_button);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_button_layout:
                setSelect(0);
                break;
            case R.id.main_button_layout:
                setSelect(1);
                break;
            case R.id.settings_button_layout:
                setSelect(2);
                break;
            default:
                break;
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
    //如果用以下这种做法则不保存状态，再次进来的话会显示默认tab
    //总是执行这句代码来调用父类去保存视图层的状态
    //super.onSaveInstanceState(outState);
    }

}
