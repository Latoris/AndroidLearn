package com.example.latoris.dialogpractice;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Latoris on 2016/9/4.
 */
public class loginDialog extends Dialog {

    private Button btn1;
    private EditText username;
    private EditText password;

    protected  loginDialog(Context context){
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog);
        btn1 =(Button)findViewById(R.id.confrim);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernameInput = username.getText().toString().trim();
                String passwordInput = password.getText().toString().trim();
                if(!usernameInput.equals("abc")){
                    Toast.makeText(loginDialog.this.getContext(),"用户名错误", Toast.LENGTH_LONG).show();
                }
                else if(!passwordInput.equals("123")){
                    Toast.makeText(loginDialog.this.getContext(),"密码错误", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(loginDialog.this.getContext(), "正确，登陆成功", Toast.LENGTH_LONG).show();
                    dismiss();
                }
            }
        });
    }
}

