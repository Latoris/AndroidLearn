package com.example.latoris.dialogpractice;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Latoris on 2016/9/4.
 */
public class informDialog extends Dialog {

    private TextView text;
    private Button exit;

    protected informDialog(Context context){
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.inform_dialog);
        exit = (Button)findViewById(R.id.exit);
        text =(TextView)findViewById(R.id.text);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}

