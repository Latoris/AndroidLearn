package com.example.latoris.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView input;
    private TextView answer;
    private Button nine;
    private Button eight;
    private Button seven;
    private Button one;
    private Button two;
    private Button six;
    private Button three;
    private Button four;
    private Button five;
    private Button plus;
    private Button minus;
    private Button equal;
    private Button chu;
    String calText = "";
    String answerText = "";
    private Button multipy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input = (TextView)findViewById(R.id.cal);
        answer = (TextView)findViewById(R.id.answer);
        one = (Button)findViewById(R.id.one);
        two = (Button)findViewById(R.id.two);
        three = (Button)findViewById(R.id.three);
        four = (Button)findViewById(R.id.four);
        five = (Button)findViewById(R.id.five);
        six = (Button)findViewById(R.id.six);
        seven = (Button)findViewById(R.id.seven);
        eight = (Button)findViewById(R.id.eight);
        nine = (Button)findViewById(R.id.nine);
        plus = (Button)findViewById(R.id.plus);
        minus = (Button)findViewById(R.id.minus);
        equal = (Button)findViewById(R.id.equal);
        chu = (Button)findViewById(R.id.chu);
        multipy = (Button)findViewById(R.id.multipy);
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calText += "1";
                input.setText(calText);
            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calText += "2";
                input.setText(calText);
            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calText += "3";
                input.setText(calText);
            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calText += "4";
                input.setText(calText);
            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calText += "5";
                input.setText(calText);
            }
        });
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calText += "6";
                input.setText(calText);
            }
        });
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calText += "7";
                input.setText(calText);
            }
        });
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calText += "8";
                input.setText(calText);
            }
        });
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calText += "9";
                input.setText(calText);
            }
        });
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calText += "+";
                input.setText(calText);
            }
        });
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calText += "-";
                input.setText(calText);
            }
        });
        multipy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calText += "*";
                input.setText(calText);
            }
        });
        chu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calText += "/";
                input.setText(calText);
            }
        });
    }

}

