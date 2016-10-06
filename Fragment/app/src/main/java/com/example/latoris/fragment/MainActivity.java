package com.example.latoris.fragment;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements
        ItemFragment.OnListFragmentInteractionListener,
        DetailFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public void onListFragmentInteraction(String id) {
        Bundle arguments =new Bundle();
        arguments.putString("id",id);
        DetailFragment fragment=new DetailFragment();
        fragment.setArguments(arguments);
        getSupportFragmentManager().beginTransaction().replace(R.id.wordDeatil,fragment).commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}
