package com.example.myword;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.stream.StreamResult;

import com.example.myword.R;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector.OnGestureListener;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.SimpleAdapter.ViewBinder;


public class XueXi extends Activity implements OnTouchListener,
		OnGestureListener, View.OnClickListener {
	private GestureDetector detector;
	private TextView word;
	private TextView chinese;
	private TextView numView;
	private Button back;
	private int num = 1;// 记录当前是第几个单词
	private int countxml = 1;// 记录当前是第几份xml文件
	private TXTUtil txt;
	private TextView msg; 
	private Button enter;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.activity_main);

		detector =  new GestureDetector(this, this);
		word = (TextView) this.findViewById(R.id.word);
		chinese = (TextView) this.findViewById(R.id.chinese);
		numView = (TextView) this.findViewById(R.id.num);
		back = (Button) this.findViewById(R.id.back);
		back.setOnClickListener(this);
		txt = new TXTUtil(this.getApplicationContext());

		this.setWord(num);

		LinearLayout layout = (LinearLayout) this.findViewById(R.id.layout);
		layout.setOnTouchListener(this);
		layout.setLongClickable(true);
	}
	/**
	 * @param num设置要显示的单词
	 *            ，num为第几个单词
	 */
	public boolean setWord(int id) {
		Word temp = this.readxml(id);
		if (temp == null) {

			if (countxml == 11) {
				return false;
			} else {
				countxml++;
				this.num = 1;
				temp = this.readxml(this.num);
			}
		}
		word.setText(temp.englist);
		chinese.setText(temp.chinese);
		int tempnum = (countxml - 1) * 501 + this.num;
		numView.setText("这是第 " + tempnum + "个单词");
		return true;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// TODO Auto-generated method stub
		if (e1.getX() - e2.getX() > 50 && Math.abs(velocityX) > 0) {
			// 向左滑动,显示下一个单词
			num++;
			boolean isOk = this.setWord(num);// 如果没有单词了，则isOk为false
			if (!isOk) {
				Toast.makeText(this.getApplicationContext(), "后面没有单词了。",
						Toast.LENGTH_SHORT).show();
			}
		} else if (e2.getX() - e1.getX() > 50 && Math.abs(velocityX) > 0) {
			// 向右滑动,显示上一个单词
			if (num == 1) {
				if (countxml == 1) {
					Toast.makeText(this.getApplicationContext(), "这已经是第一个单词了。",
							Toast.LENGTH_SHORT).show();
					return false;
				}
				countxml--;
				num = 502;
			}
			num--;
			this.setWord(num);
		}
		return false;
	}

	/**
	 * @return当前是第几份单词xml
	 */
	public int getXmlId() {
		int xmlId = 0;
		switch (countxml) {
		case 1:
			xmlId = R.xml.words1;
			break;
		case 2:
			xmlId = R.xml.words2;
			break;
		case 3:
			xmlId = R.xml.words3;
			break;
		case 4:
			xmlId = R.xml.words4;
			break;
		case 5:
			xmlId = R.xml.words5;
			break;
		case 6:
			xmlId = R.xml.words6;
			break;
		case 7:
			xmlId = R.xml.words7;
			break;
		case 8:
			xmlId = R.xml.words8;
			break;
		case 9:
			xmlId = R.xml.words9;
			break;
		case 10:
			xmlId = R.xml.words10;
			break;
		}
		return xmlId;
	}

	/**
	 * @param num输入要检查的数字
	 * @return是否是已经删除的数字，true为是，false为未删除
	 */
	public boolean isDel(int num) {
		List<Integer> list = txt.getNumList(this.countxml);
		for(int i=0; i<list.size(); i++) {
			if(num == list.get(i)) {
				return true;
			}
		}
		return false;
	}
	
	public void del() {
//		InputStream is = this.getResources().openRawResource(R.xml.words1);
//		File f = new File(path)
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		Document dom = null;
		try {
			InputStream is = this.getBaseContext().getAssets().open("words1.xml");
			DocumentBuilder db = dbf.newDocumentBuilder();
			dom = db.parse(is);
			
			Node englist = dom.getElementsByTagName("englist").item(0);
			englist.getParentNode().removeChild(englist);
			
			
			
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer t = tf.newTransformer();
			Result r = new StreamResult(this.getApplicationContext().openFileOutput("res/xml/words1.xml", Context.MODE_PRIVATE));
			t.transform(new DOMSource(dom), r);
			
			is.close();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @param id要读取的第几个单词
	 *            ,从1开始
	 * @return
	 */
	public Word readxml(int id) {
		Word word = new Word();
		Resources r = this.getResources();
		XmlResourceParser xrp = r.getXml(this.getXmlId());

		int wordNum = 0;
		boolean isEnglist = false;// 当前标签是否为englist,如果不是englist那就是chinese
		boolean isNeed = false;// 当前单词是否是所找单词

		try {
			while (xrp.getEventType() != XmlResourceParser.END_DOCUMENT) {

				if (xrp.getEventType() == XmlResourceParser.START_TAG) {
					if (xrp.getName().equals("englist")) {
						wordNum++;// 读到一个单词wordNum 就加1
//						if(this.isDel(wordNum)) {
//							id++;
//							this.num++;
//							continue;
//						}
						if (wordNum == id) {
							isNeed = true;
						}
						isEnglist = true;
					}
				} else if (xrp.getEventType() == XmlResourceParser.TEXT
						&& isNeed) {
					if (isEnglist) {
						word.englist = xrp.getText();
					}
					if (!isEnglist) {
						word.chinese = xrp.getText();
					}
				} else if (xrp.getEventType() == XmlResourceParser.END_TAG
						&& isNeed) {
					if (isEnglist) {
						isEnglist = false;
					} else {
						isNeed = false;
						return word;
					}
				}
				xrp.next();
			}
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		xrp.close();
		return null;
	}

	// write into the file of word
	public void write() {

	}

	/**
	 * @author Administrator 用与封装单词
	 */
	private class Word {
		String englist;
		String chinese;
	}

	@Override
	public void onLongPress(MotionEvent e) {
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		return false;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		return detector.onTouchEvent(event);
	}

	@Override
	public boolean onDown(MotionEvent e) {
		return false;
	}

	@Override
	public void onClick(View v) {
		Intent it = new Intent(XueXi.this, Welcome.class);
		XueXi.this.startActivity(it) ;
		System.exit(0);
	}
 }
















