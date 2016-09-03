package com.example.latoris.layoutpractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class MainActivity extends AppCompatActivity {

    private EditText addressInput;
    private TextView mainMenu;
    private TextView snackMenu;
    private CheckBox friedSquid;
    private CheckBox chickWings;
    private CheckBox chips;
    private RadioGroup mainDish;
    private Button address;
    boolean friedSquidState = false;
    boolean chickenWingsState = false;
    boolean chipsState = false;
    String snackText = "小吃：";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addressInput = (EditText) findViewById(R.id.addressInput);
        mainMenu = (TextView) findViewById(R.id.dishMenu);
        snackMenu = (TextView) findViewById(R.id.snackMenu);
        friedSquid = (CheckBox) findViewById(R.id.friedSquid);
        chickWings = (CheckBox) findViewById(R.id.chickwings);
        chips = (CheckBox) findViewById(R.id.chips);
        mainDish = (RadioGroup)findViewById(R.id.radiogroup);
        address = (Button)findViewById(R.id.address);
        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addressInput.setText("十字路口");
            }
        });

        mainDish.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkId){
                if(checkId == R.id.pizza)
                    mainMenu.setText("主餐：披萨");
                else if(checkId == R.id.hamburger)
                    mainMenu.setText("主餐： 汉堡");
            }
        });
        chips.setOnCheckedChangeListener(snackListener);
        friedSquid.setOnCheckedChangeListener(snackListener);
        chickWings.setOnCheckedChangeListener(snackListener);
    }

    private OnCheckedChangeListener snackListener = new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
            if(compoundButton.getId() == R.id.friedSquid){
                if(isChecked)
                    friedSquidState = true;
                else
                    friedSquidState = false;
            }
            else if(compoundButton.getId() == R.id.chickwings){
                if(isChecked)
                    chickenWingsState = true;
                else
                    chickenWingsState = false;
            }
            else if(compoundButton.getId() == R.id.chips){
                if(isChecked)
                    chipsState = true;
                else
                    chipsState = false;
            }
            setSnackString();
        }
    };

    public void setSnackString(){
        snackText = "小吃: ";
        if(friedSquidState)
            snackText += "薯条 ";
        if(chickenWingsState)
            snackText += "炸鸡翅";
        if(chipsState)
            snackText += "炸鱿鱼";
        snackMenu.setText(snackText);
    }

}
