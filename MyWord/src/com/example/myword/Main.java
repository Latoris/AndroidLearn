package com.example.myword;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Main extends Activity {
	private EditText edit = null;
	private Button mybut = null ;
	private Button tuichu = null ;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.zhuce);
		this.edit = (EditText)super.findViewById(R.id.shuru);
		this.mybut = (Button) super.findViewById(R.id.mybut) ;
		this.mybut.setOnClickListener(new OnClickListenerImpl()) ;
		this.tuichu = (Button) super.findViewById(R.id.tui) ;
		this.tuichu.setOnClickListener(new OnClickListenerImpl2()) ;
	}
	private class OnClickListenerImpl implements OnClickListener{

		@Override
		public void onClick(View v) {
			Intent it = new Intent(Main.this, Welcome.class);
			String s = Main.this.edit.getText().toString();
			it.putExtra("myinfo",s) ;
			Main.this.startActivity(it) ;	// Ìø×ª
			System.exit(0);
		}
		
	}
	private class OnClickListenerImpl2 implements OnClickListener{

		@Override
		public void onClick(View v) {
			android.os.Process.killProcess(android.os.Process.myPid());
		}
		
	}
}
