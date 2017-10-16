package com.example.myword;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
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

public class Read extends Activity{
	private TextView lie = null;
	private Button button = null;
//	private static final String FILENAME = "myword.txt" ;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.read);
		this.lie = (TextView) super.findViewById(R.id.lie);

		if (!Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) { // 不存在不操作
			return; // 返回到程序的被调用处
		}
		File file = new File(Environment.getExternalStorageDirectory()
				+ File.separator + "data" + File.separator
				+ "danci.xml"); // 要输出文件的路径
		if (!file.exists()) { // 文件不存在
			return;
		}

		InputStream input = null ;
		try {
			input = new FileInputStream(file) ;	// 取得输入流
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Toast.makeText(Read.this, "生词列表不存在，请返回添加生词", Toast.LENGTH_SHORT).show();
		}
		Scanner scan = new Scanner(input) ;
		if(scan.hasNext()==false)
			Toast.makeText(Read.this, "还没有生词呢，返回添加生词吧", Toast.LENGTH_SHORT).show();
		while(scan.hasNext()) {
			Read.this.lie.append(scan.nextLine()+"\n") ;
		}
		scan.close();

		this.button = (Button) super.findViewById(R.id.back);
		this.button.setOnClickListener(new OnClickListenerImpl());
		
	}
	
	public class OnClickListenerImpl implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			Intent it = new Intent(Read.this, Welcome.class);
			Read.this.startActivity(it) ;
			System.exit(0);
		}
		
	}
}
