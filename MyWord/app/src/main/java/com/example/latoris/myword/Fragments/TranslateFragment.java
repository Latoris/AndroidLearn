package com.example.latoris.myword.Fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.latoris.myword.Activity.NewsActivity;
import com.example.latoris.myword.R;
import com.youdao.sdk.app.Language;
import com.youdao.sdk.app.LanguageUtils;
import com.youdao.sdk.ydonlinetranslate.TranslateErrorCode;
import com.youdao.sdk.ydonlinetranslate.TranslateListener;
import com.youdao.sdk.ydonlinetranslate.TranslateParameters;
import com.youdao.sdk.ydonlinetranslate.Translator;
import com.youdao.sdk.ydtranslate.Translate;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class TranslateFragment extends Fragment implements View.OnClickListener{
    private Context context;
    private Translator translator;
    private TextView translate_result;
    private EditText translate_text;
    private Button translate;
    private Button newsButton;
    private Spinner from_language;
    private Spinner to_language;
    private String from_language_text;
    private String to_language_text;
    public TranslateFragment() {
        // Required empty public constructor
    }


    private void query(String query, String from_language_text, String to_language_text) {
        Language langFrom = LanguageUtils.getLangByName(from_language_text);
        Language langTo = LanguageUtils.getLangByName(to_language_text);

        TranslateParameters tps = new TranslateParameters.Builder()
                .source("wordtest").from(langFrom).to(langTo).timeout(3000).build();// appkey可以省略

        translator = Translator.getInstance(tps);

        //showLoadingView("正在查询");
        System.out.println("query "+query);

        translator.lookup(query, new TranslateListener() {

            @Override
            public void onResult(final com.youdao.sdk.ydtranslate.Translate result, String input) {
                System.out.println("Result "+result.getQuery());
                //List<WebExplain> explains = result.getWebExplains();
                result.getTranslations().get(0);
                StringBuilder sb = new StringBuilder();
                System.out.println("resu"+sb);
                translate_result.setText(result.getTranslations().get(0));
            }

            @Override
            public void onError(TranslateErrorCode error) {
                translate_result.setText(error.toString());
                Toast.makeText(context,"查询错误:" + error.name().toString(),Toast.LENGTH_SHORT).show();
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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this.getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //q Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        translate_result = (TextView)view.findViewById(R.id.translate_result);
        translate_text = (EditText)view.findViewById(R.id.translate_from);
        translate = (Button)view.findViewById(R.id.translate);
        newsButton = (Button)view.findViewById(R.id.news);
        from_language = (Spinner)view.findViewById(R.id.translate_from_lang);
        to_language = (Spinner)view.findViewById(R.id.translate_to_lang);
        to_language.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] languages = getResources().getStringArray(R.array.languages);
                to_language_text = languages[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        from_language.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] languages = getResources().getStringArray(R.array.languages);
                from_language_text = languages[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        translate.setOnClickListener(this);
        newsButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.translate:
                query(translate_text.getText().toString(), from_language_text, to_language_text);
                return;
            case R.id.news:
                Intent intent = new Intent(getActivity(), NewsActivity.class);
                startActivity(intent);
                return;
        }
    }
}
