package com.example.latoris.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private final static String USER="username";
    private final static String ClassNAME="classname";
    private final static String Number="Number";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] UserName={"李艺彤","戴萌","鞠婧炜","李梓"};
        String[] ClassName={"H Class","S Class","N Class","E Class"};
        String[] Numbers={"011202","0111201","0217761", "817184"};

        List<Map<String,Object>> items=new ArrayList<Map<String,Object>>();

        for(int i=0;i<UserName.length;i++) {
            Map<String,Object> item=new HashMap<String,Object>();
            item.put(USER, UserName[i]);
            item.put(ClassNAME, ClassName[i]);
            item.put(Number, Numbers[i]);
            items.add(item);
        }

        SimpleAdapter adapter=new SimpleAdapter(this,items,R.layout.item,new String[]{USER,ClassNAME,Number},new int[]{R.id.UserName,R.id.ClassName,R.id.Number});
        ListView list=(ListView)findViewById(R.id.list);

        list.setAdapter(adapter);
    }
}
