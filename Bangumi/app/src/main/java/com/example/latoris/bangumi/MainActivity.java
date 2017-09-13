package com.example.latoris.bangumi;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private View page1, page2, page3;
    private List<View> pageList;
    private ViewPager mViewPager;
    
   // private List<String> mTitleString = Arrays.asList("追番","发现","设置");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LayoutInflater inflater = getLayoutInflater();
        page1 = inflater.inflate(R.layout.page1, null);
    }
}
