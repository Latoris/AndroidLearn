package com.example.myword;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;

public class TXTUtil {
	Context context;
	
	public TXTUtil(Context context) {
		this.context = context;
	}
	
	public String getFileName(int countxml) {
		switch(countxml) {
		case 1: return "num1.txt";
		case 2: return "num2.txt";
		case 3: return "num3.txt";
		case 4: return "num4.txt";
		case 5: return "num5.txt";
		case 6: return "num6.txt";
		case 7: return "num7.txt";
		case 8: return "num8.txt";
		case 9: return "num9.txt";
		case 10: return "num10.txt";
		}
		return null;
	}
	
	public void addNum(int num, int countxml) {
		String content = num + ".";
		FileOutputStream fileos;
		try {
			fileos = context.openFileOutput(this.getFileName(countxml), Context.MODE_APPEND);
			fileos.write(content.getBytes());
			fileos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<Integer> getNumList(int countxml) {
		List<Integer> list = new ArrayList<Integer>();
		try {
			FileInputStream fis = this.context.openFileInput(this.getFileName(countxml));
			byte[] b = new byte[1024*10];
			fis.read(b, 0, b.length);
			String str = new String(b);
			while(str.indexOf(".")!=-1) {
				int end = str.indexOf(".");
				String temp = str.substring(0, end).trim();
				list.add(Integer.parseInt(temp));
				str = str.substring(end+1);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
}
