package com.example.latoris.myword.Fragments;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.latoris.myword.Adapter.SearchWordAdapter;
import com.example.latoris.myword.Bean.Word;
import com.example.latoris.myword.R;
import com.example.latoris.myword.WordOperate;
import com.youdao.sdk.app.Language;
import com.youdao.sdk.app.LanguageUtils;
import com.youdao.sdk.ydonlinetranslate.TranslateErrorCode;
import com.youdao.sdk.ydonlinetranslate.TranslateListener;
import com.youdao.sdk.ydonlinetranslate.TranslateParameters;
import com.youdao.sdk.ydonlinetranslate.Translator;
import com.youdao.sdk.ydtranslate.Translate;
import com.youdao.sdk.ydtranslate.WebExplain;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class SearchWord extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener{
    private Context context;
    private Translator translator;
    private EditText search_text;
    private Button search_button;
    private ListView search_word_list;
    private TextView word_name;
    private TextView word_meaning;
    private TextView word_meaning_dict;
    private TextView word_voice_text;
    private Button word_voice;
    private Button add_word;
    private WordOperate operate;
    private SearchWordAdapter adapter;
    private ArrayList<Word> list = new ArrayList<Word>();
    private MediaPlayer mediaPlayer;
    private boolean isfilefinish = false;
    private LinearLayout meaning_layout;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        context = this.getActivity();
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.search_button:
                meaning_layout.setVisibility(View.VISIBLE);
                String search = search_text.getText().toString();
                if (!search.isEmpty()) {
                    list.clear();
                    list.addAll(operate.search_word(search));
                    adapter.notifyDataSetChanged();
                }
                return;
            case R.id.search_word_text:
                meaning_layout.setVisibility(View.GONE);
                return;

        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ListView listview = (ListView)parent;
        Word a = (Word)listview.getItemAtPosition(position);
        word_name.setText(a.getWord_name());
        word_meaning_dict.setText(a.getWord_meaning());
        query(a.getWord_name());
    }

    private void query(String query) {

        Language langFrom = LanguageUtils.getLangByName("英文");
        Language langTo = LanguageUtils.getLangByName("中文");

        TranslateParameters tps = new TranslateParameters.Builder()
        .source("wordtest").from(langFrom).to(langTo).timeout(3000).build();// appkey可以省略

        translator = Translator.getInstance(tps);

        //showLoadingView("正在查询");
        System.out.println("query "+query);

        translator.lookup(query, new TranslateListener() {

            @Override
            public void onResult(final Translate result, String input) {
                System.out.println("Result "+result.getQuery());
                List<WebExplain> explains = result.getWebExplains();
                StringBuilder sb = new StringBuilder();
                sb.append("网络释义：");
                sb.append("\n");
                if (explains != null) {
                    for (WebExplain s : explains) {
                        sb.append(s.getKey()).append(listStr(s.getMeans())).append("\n");
                    }
                }
                System.out.println("resu"+sb);
                word_meaning.setText(sb);
                word_voice.setVisibility(View.VISIBLE);
                add_word.setVisibility(View.VISIBLE);
                word_voice_text.setText(result.getUkPhonetic());
                add_word.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        operate.Insert(word_name.getText().toString(),word_meaning_dict.getText().toString());
                        Toast.makeText(context, "添加："+word_name.getText().toString(),Toast.LENGTH_SHORT);
                    }
                });
                word_voice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MediaPlayer mp = new MediaPlayer();
                    try {
                        createVoice();
                        System.out.println("loading.");
                        while (!isfilefinish){
                            System.out.print(".");
                        }
                    mp.setDataSource("/sdcard/audio.mp3");
                    mediaPlayer = mp;
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                    isfilefinish = false;
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            mediaPlayer.release();
                            System.out.println(DeleteVoice());
                        }
                    });
                    }
                    catch (Exception e){
                        System.out.println(e);
                    }
                }
            });
        }

            @Override
            public void onError(TranslateErrorCode error) {
                Toast.makeText(context,"查询错误:" + error.name().toString(),Toast.LENGTH_SHORT);
            }
        });
    }
    private String listStr(List<String> list) {
        StringBuilder sb = new StringBuilder();
        if (list != null) {
            for (String s : list) {
                sb.append(s).append(",");
            }
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

    public void createVoice(){
        Thread t = new Thread() {
        @Override
        public void run() {
            try {
                isfilefinish = false;
                URL urlt = new URL("http://dict.youdao.com/dictvoice?type=1&audio=" + word_name.getText());
                HttpURLConnection conn = (HttpURLConnection) urlt.openConnection();
                InputStream is = conn.getInputStream();
                BufferedInputStream bf = new BufferedInputStream(is);
                FileOutputStream fio = new FileOutputStream("/sdcard/audio.mp3");
                BufferedOutputStream buo = new BufferedOutputStream(fio);
                int ch = 0;
                while ((ch = bf.read()) != -1) {
                    buo.write(ch);
                }
                isfilefinish = true;
                bf.close();
                buo.close();
                is.close();
            //latch.countDown();
                System.out.println("file load finish");
            }
            catch (Exception e) {
                System.out.println(e);
            }
        }};
    t.start();
    }

    private String DeleteVoice(){
        File file = new File("/sdcard/audio.mp3");
        if (file.isFile() && file.exists()) {
            file.delete();
            return "del sucess";
        }
        else
            return "del fail";
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_word, container, false);
        operate = new WordOperate(context);
        search_word_list = (ListView)view.findViewById(R.id.search_word_list);
        search_text =(EditText) view.findViewById(R.id.search_word_text);
        search_button = (Button)view.findViewById(R.id.search_button);
        word_name = (TextView)view.findViewById(R.id.word_name);
        word_meaning = (TextView)view.findViewById(R.id.meaning);
        word_meaning_dict = (TextView)view.findViewById(R.id.meaning_dict);
        word_voice_text = (TextView)view.findViewById(R.id.voice_content);
        word_voice = (Button)view.findViewById(R.id.voice);
        add_word =(Button)view.findViewById(R.id.add_word);
        meaning_layout = (LinearLayout)view.findViewById(R.id.meaning_layout);
        adapter = new SearchWordAdapter(list, context);
        search_word_list.setAdapter(adapter);
        search_button.setOnClickListener(this);
        search_word_list.setOnItemClickListener(this);

        return view;
    }

}
