package com.example.latoris.calculator;

import android.content.res.Configuration;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    String calText = "";
    String DisplayText = "";

    //主面板
    private TextView input;
    private TextView answer;
    private Button zero;
    private Button one;
    private Button two;
    private Button three;
    private Button four;
    private Button five;
    private Button six;
    private Button seven;
    private Button eight;
    private Button nine;
    private Button plus;
    private Button minus;
    private Button equal;
    private Button percent;
    private Button chu;
    private Button multipy;
    private Button del;
    private Button clear;
    private Button pointN;

    //科学面板

    private Button logxy;
    private Button sqrt;
    private Button sin;
    private Button cos;
    private Button tan;
    private Button cot;
    private Button sinh;
    private Button cosh;
    private Button tanh;
    private Button coth;
    private Button ln;
    private Button e;
    private Button pi;
    private Button factorial;
    private Button ex;
    private Button left_parenthese;
    private Button right_parenthese;
    private Button double_power;
    private Button third_power;
    private Button power;
    private String KEY_DISPLAY = "displaytext";
    private String KEY_CAL = "caltext";
    public String cal = "cal";
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState != null){
            DisplayText = savedInstanceState.getString(KEY_DISPLAY);
            calText = savedInstanceState.getString(KEY_CAL);
            savedInstanceState.clear();
        }
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            initVal();
        }
        else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            /*横屏初始布局*/
            initVal();
            logxy = (Button)findViewById(R.id.Logxy);
            sqrt = (Button)findViewById(R.id.sqrt);
            sin = (Button)findViewById(R.id.sin);
            cos = (Button)findViewById(R.id.cos);
            tan = (Button)findViewById(R.id.tan);
            cot = (Button)findViewById(R.id.acos);
            sinh = (Button)findViewById(R.id.sinh);
            cosh = (Button)findViewById(R.id.cosh);
            tanh = (Button)findViewById(R.id.tanh);
            coth = (Button)findViewById(R.id.atan);
            ln = (Button)findViewById(R.id.ln);
            e = (Button)findViewById(R.id.e);
            pi = (Button)findViewById(R.id.pi);
            left_parenthese = (Button)findViewById(R.id.left_parenthese);
            right_parenthese = (Button)findViewById(R.id.right_parenthese);
            factorial = (Button)findViewById(R.id.jiechen);
            double_power = (Button)findViewById(R.id.pingfang);
            third_power = (Button)findViewById(R.id.sancifang);
            power = (Button)findViewById(R.id.ncifang);
            ex = (Button)findViewById(R.id.ex);

            sin.setOnClickListener(this);
            cos.setOnClickListener(this);
            sinh.setOnClickListener(this);
            cosh.setOnClickListener(this);
            tan.setOnClickListener(this);
            tanh.setOnClickListener(this);
            coth.setOnClickListener(this);
            cot.setOnClickListener(this);
            ln.setOnClickListener(this);
            ex.setOnClickListener(this);
            e.setOnClickListener(this);
            pi.setOnClickListener(this);
            double_power.setOnClickListener(this);
            third_power.setOnClickListener(this);
            power.setOnClickListener(this);
            left_parenthese.setOnClickListener(this);
            left_parenthese.setOnClickListener(this);
            right_parenthese.setOnClickListener(this);
            factorial.setOnClickListener(this);
            logxy.setOnClickListener(this);
            sqrt.setOnClickListener(this);
        }
    }

    private void initVal(){
        seven = (Button)findViewById(R.id.Seven);
        input = (TextView)findViewById(R.id.cal);
        input.setText(DisplayText);
        answer = (TextView)findViewById(R.id.answer);
        one = (Button)findViewById(R.id.one);
        two = (Button)findViewById(R.id.two);
        three = (Button)findViewById(R.id.three);
        four = (Button)findViewById(R.id.four);
        five = (Button)findViewById(R.id.five);
        six = (Button)findViewById(R.id.six);
        eight = (Button)findViewById(R.id.eight);
        nine = (Button)findViewById(R.id.nine);
        plus = (Button)findViewById(R.id.plus);
        minus = (Button)findViewById(R.id.minus);
        equal = (Button)findViewById(R.id.equal);
        chu = (Button)findViewById(R.id.chu);
        multipy = (Button)findViewById(R.id.multipy);
        del = (Button)findViewById(R.id.del);
        clear = (Button)findViewById(R.id.clear);
        pointN = (Button)findViewById(R.id.point);
        zero = (Button)findViewById(R.id.zero);
        percent = (Button)findViewById(R.id.per);
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        plus.setOnClickListener(this);
        minus.setOnClickListener(this);
        equal.setOnClickListener(this);
        chu.setOnClickListener(this);
        multipy.setOnClickListener(this);
        del.setOnClickListener(this);
        clear.setOnClickListener(this);
        pointN.setOnClickListener(this);
        zero.setOnClickListener(this);
        percent.setOnClickListener(this);

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(KEY_DISPLAY, DisplayText);
        savedInstanceState.putString(KEY_CAL, calText);
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.zero:
                button("0");
                break;
            case R.id.one:
                button("1");
                break;
            case R.id.two:
                button("2");
                break;
            case R.id.three:
                button("3");
                break;
            case R.id.four:
                button("4");
                break;
            case R.id.five:
                button("5");
                break;
            case R.id.six:
                button("6");
                break;
            case R.id.Seven:
                button("7");
                break;
            case R.id.eight:
                button("8");
                break;
            case R.id.nine:
                button("9");
                break;
            case R.id.plus:
                button("+");
                break;
            case R.id.multipy:
                if(calText.length()>1)
                    if(calText.charAt(calText.length()-1)=='*')
                        break;
                    else if(calText.charAt(calText.length()-1)=='/'){
                        calText = calText.substring(0, calText.length()-1)+'*';
                        DisplayText = DisplayText.substring(0, DisplayText.length()-1)+'*';
                        input.setText(DisplayText);
                    }
                    else
                        button("*");
                else
                    button("*");
                break;
            case R.id.minus:
                button("-");
                break;
            case R.id.chu:
                if(calText.length()>1)
                    if(calText.charAt(calText.length()-1)=='/')
                        break;
                    else if(calText.charAt(calText.length()-1)=='*'){
                        calText = calText.substring(0, calText.length()-1)+'/';
                        DisplayText = DisplayText.substring(0, calText.length()-1)+'/';
                        input.setText(DisplayText);
                    }
                    else
                        button("/");
                else
                    button("/");
                break;
            case R.id.per:
                calText += "/100";
                DisplayText += "%";
                input.setText(DisplayText);
                break;
            case R.id.equal:
                try{
                    String ans = "";
                    char last = calText.charAt(calText.length()-1);
                    System.out.println("zuihouyigeshi "+last);
                    if(calText.equals(""))
                        ans = "0.0";
                    else if(!(Character.isDigit(last))){
                        if(last != ')' && last != '%' && last != 'p' && last != 'e' && last !='!')
                            ans = "出错";
                        else
                            ans = Cal.caltoanswer(calText);
                    }
                    else{
                        ans = Cal.caltoanswer(calText);
                    }
                    answer.setText(ans);
                }
                catch (Exception e){}
                break;
            case R.id.del:
                if(DisplayText.isEmpty()){
                    break;
                }
                if(DisplayText.substring(DisplayText.length()-1,DisplayText.length()).equals("%")){
                    calText = calText.substring(0, calText.length()-4);
                    DisplayText = DisplayText.substring(0, DisplayText.length()-1);
                }
                else{
                    calText = calText.substring(0, calText.length()-1);
                    DisplayText = DisplayText.substring(0, DisplayText.length()-1);
                }
                input.setText(DisplayText);
                break;
            case R.id.clear:
                calText = "";
                DisplayText = "";
                answer.setText("");
                input.setText(DisplayText);
                break;
            case R.id.sin:
                if(buttonAddMulitiply("asin("))
                    break;
                button("sin(");
                break;
            case R.id.cos:
                buttonAddMulitiply("cos(");
                button("cos(");
                break;
            case R.id.tan:
                if(buttonAddMulitiply("tan("))
                    break;
                button("tan(");
                break;
            case R.id.acos:
                if(buttonAddMulitiply("acos("))
                    break;
                button("acos(");
                break;
            case R.id.sinh:
                if(buttonAddMulitiply("sinh("))
                    break;
                button("sinh(");
                break;
            case R.id.cosh:
                if(buttonAddMulitiply("cosh("))
                    break;
                button("cosh(");
                break;
            case R.id.tanh:
                if(buttonAddMulitiply("tanh("))
                    break;
                button("tanh(");
                break;
            case R.id.atan:
                if(buttonAddMulitiply("atan("))
                    break;
                button("atan(");
                break;
            case R.id.ex:
                if(buttonAddMulitiply("e^"))
                    break;
                button("e^");
                break;
            case R.id.e:
                if(buttonAddMulitiply("e"))
                    break;
                button("e");
                break;
            case R.id.pi:
                if(calText.length()>=1)
                    if(Character.isDigit(calText.charAt(calText.length()-1))){
                        calText = calText + "*p";
                        DisplayText = DisplayText + "*π";
                        input.setText(DisplayText);
                    }
                    else{
                        calText += "p";
                        DisplayText += "π";
                        input.setText(DisplayText);
                    }
                else{
                    calText += "p";
                    DisplayText += "π";
                    input.setText(DisplayText);
                }
                break;
            case R.id.ncifang:
                button("^");
                break;
            case R.id.sancifang:
                button("^3");
                break;
            case R.id.pingfang:
                button("^2");
                break;
            case R.id.sqrt:
                Log.v(cal, "sqrt");
                button("^0.5");
                break;
            case R.id.jiechen:
                button("!");
                break;
            case R.id.left_parenthese:
                if(buttonAddMulitiply("(")) {
                    break;
                }
                button("(");
                break;
            case R.id.right_parenthese:
                button(")");
                break;
            case R.id.ln:
                if(buttonAddMulitiply("log(e,")) {
                    break;
                }
                button("log(e,");
                break;
            case R.id.Logxy:
                String number = "";
                int i;
                if(calText.isEmpty())
                    break;
                for(i = calText.length()-1;i>=0;i--){
                    if(Character.isDigit(calText.charAt(i))){
                        number = calText.charAt(i)+number;
                    }
                    else
                        break;
                }
                DisplayText = DisplayText.substring(0, DisplayText.length()-calText.length()+i+1)+ "log("+number+",";
                calText = calText.substring(0, i+1)+ "log("+number+",";
                input.setText(DisplayText);
                break;
            case R.id.point:
                if(calText.length()>1)
                    if(calText.charAt(calText.length()-1)=='.')
                    break;
                button(".");
                break;
            default:
                break;
        }
    }
    public void button(String btn){
        calText += btn;
        DisplayText += btn;
        input.setText(DisplayText);
        Log.v("cal", DisplayText);
        Log.v("cal", calText);
    }

    public boolean buttonAddMulitiply(String btn){
        if(calText.length()>=1)
            if(Character.isDigit(calText.charAt(calText.length()-1))){
                calText = calText + "*" + btn;
                DisplayText = DisplayText + "*" + btn;
                input.setText(DisplayText);
                Log.v("cal", DisplayText);
                Log.v("cal", calText);
                return true;
        }
        return false;
    }
}

