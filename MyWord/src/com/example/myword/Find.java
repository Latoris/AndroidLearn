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

public class Find extends Activity{
	private EditText find = null ;
	private Button go = null;
	private TextView chinese = null;
	private Button hui = null;
//	private static final String FILENAME = "myword.txt" ;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.find);
		this.find = (EditText) super.findViewById(R.id.find);
		this.go = (Button) super.findViewById(R.id.go);
		this.chinese = (TextView) super.findViewById(R.id.chinese);
		this.go.setOnClickListener(new OnClickListenerImpl());
		this.hui = (Button) super.findViewById(R.id.hui);
		this.hui.setOnClickListener(new OnClickListenerImpl2());
	}
	public class OnClickListenerImpl implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			if (!Environment.getExternalStorageState().equals(
					Environment.MEDIA_MOUNTED)) { // �����ڲ�����
				return; // ���ص�����ı����ô�
			}
			File file = new File(Environment.getExternalStorageDirectory()
					+ File.separator + "data" + File.separator
					+ "danci.xml"); // Ҫ����ļ���·��
			if (!file.exists()) { // �ļ�������
				return;
			}

			InputStream input = null ;
			try {
				input = new FileInputStream(file) ;	// ȡ��������
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			String result = Find.this.find.getText().toString();
			Scanner scan = new Scanner(input) ;
			int p = 0;
			while(scan.hasNext()) {
				String s = scan.nextLine();
				String arr[] = s.split("     ");
				if(result.equals(arr[0])){
					p--;
					Find.this.chinese.setText("�õ��ʵ���˼�ǣ�"+arr[1]);
					Toast.makeText(Find.this, "��ϲ�㣬���ҵ��ʳɹ�", Toast.LENGTH_SHORT).show();
				}
			
			}
			if(p==0){
				Find.this.chinese.setText("");
				Toast.makeText(Find.this, "�Բ�������ҵĵ��ʲ�����", Toast.LENGTH_SHORT).show();
			}
				
			scan.close() ;

		}
		
	}
	public class OnClickListenerImpl2 implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			Intent it = new Intent(Find.this, Welcome.class);
			Find.this.startActivity(it) ;
			System.exit(0);
			}
		}
}
