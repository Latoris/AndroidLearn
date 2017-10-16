package com.example.myword;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import android.app.Activity;
import android.content.Intent;
import android.gesture.GestureOverlayView.OnGestureListener;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Welcome extends Activity {
	private TextView show = null ;
	private Button mybut1 = null;
	private Button mybut2 = null;
	private Button mybut3 = null;
	private Button mybut4 = null;
	private Button mybut5 = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.welcome);
		this.show = (TextView) super.findViewById(R.id.welcome);
		this.mybut1 = (Button) super.findViewById(R.id.mybut1);
		this.mybut2 = (Button) super.findViewById(R.id.mybut2);
		this.mybut3 = (Button) super.findViewById(R.id.mybut3);
		this.mybut4 = (Button) super.findViewById(R.id.mybut4);
		this.mybut5 = (Button) super.findViewById(R.id.mybut5);
		Intent it = super.getIntent() ;	// 取得当前的Intent
		String info = it.getStringExtra("myinfo") ;
		this.show.setText("亲爱的"+info+",欢迎您光临自创背单词，现在开始背单词新体验吧。");
		mybut1.setOnClickListener(new OnClickListenerImpl());
		mybut2.setOnClickListener(new OnClickListenerImpl2());
		mybut3.setOnClickListener(new OnClickListenerImpl3());
		mybut4.setOnClickListener(new OnClickListenerImpl4());
		mybut5.setOnClickListener(new OnClickListenerImpl5());
	}
	public class OnClickListenerImpl implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			Intent it = new Intent(Welcome.this, XueXi.class);
			Welcome.this.startActivity(it) ;	// 跳转
			
		}
		
	}
	
	public class OnClickListenerImpl2 implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			Intent it = new Intent(Welcome.this, Find.class);
			Welcome.this.startActivity(it) ;	// 跳转
			System.exit(0);
		}
		
	}
	


	public class OnClickListenerImpl3 implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			Intent it = new Intent(Welcome.this, Add.class);
			Welcome.this.startActivity(it) ;
		}
		
	}

	public class OnClickListenerImpl4 implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			Intent it = new Intent(Welcome.this, Read.class);
			Welcome.this.startActivity(it) ;
		}
		
	}
	
	public class OnClickListenerImpl5 implements OnClickListener{

		@Override
		public void onClick(View v) {
			Intent it = new Intent(Welcome.this, Main.class);
			Welcome.this.startActivity(it) ;	// 跳转
			
		}
	}
	

}