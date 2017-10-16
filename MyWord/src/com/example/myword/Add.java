package com.example.myword;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Add extends Activity{
	private EditText input1 = null ;
	private EditText input2 = null ;
	private Button que = null;
	private Button fan = null;
	private static final String FILENAME = "myword.txt" ;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.add);
		this.input1 = (EditText) super.findViewById(R.id.input1);
		this.input2 = (EditText) super.findViewById(R.id.input2);
		this.que = (Button) super.findViewById(R.id.que);
		this.fan = (Button) super.findViewById(R.id.fan);
		this.que.setOnClickListener(new OnClickListenerImpl1());
		this.fan.setOnClickListener(new OnClickListenerImpl2());
	}
	public class OnClickListenerImpl1 implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			if (!Environment.getExternalStorageState().equals(
					Environment.MEDIA_MOUNTED)) { // 不存在不操作
				return; // 返回到程序的被调用处
			}
			File file = new File(Environment.getExternalStorageDirectory()
					+ File.separator + "data" + File.separator
					+ "danci.xml"); // 要输出文件的路径
			if (file.getParentFile().exists()) { // 文件不存在
				InputStream input = null ;
				try {
					input = new FileInputStream(file) ;	// 取得输入流
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				String result = Add.this.input1.getText().toString();
				Scanner scan = new Scanner(input) ;
				int p = 0;
				while(scan.hasNext()) {
					String s = scan.nextLine();
					String arr[] = s.split("     ");
					if(result.equals(arr[0])){
						Toast.makeText(Add.this, "该单词已存在，请换一个吧", Toast.LENGTH_SHORT).show();
						break;
					}
				}
			}
			
			file.getParentFile().mkdirs() ;	// 创建文件夹
			OutputStream output = null ;	// 接收文件输出对象
			try {
				output = new FileOutputStream(file,true);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
			PrintStream out = new PrintStream(output) ; 	
			out.print(input1.getText().toString()+"     ") ;
			out.println(input2.getText().toString()) ;
			out.close() ;	
		Toast.makeText(Add.this, "添加生词成功，请返回生词表查看", Toast.LENGTH_SHORT).show();
			
		}
		
	}
	public class OnClickListenerImpl2 implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			Intent it = new Intent(Add.this, Welcome.class);
			Add.this.startActivity(it) ;
			System.exit(0);
		}
		
	}
}
