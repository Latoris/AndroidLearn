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
			Toast.makeText(Read.this, "�����б����ڣ��뷵���������", Toast.LENGTH_SHORT).show();
		}
		Scanner scan = new Scanner(input) ;
		if(scan.hasNext()==false)
			Toast.makeText(Read.this, "��û�������أ�����������ʰ�", Toast.LENGTH_SHORT).show();
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
