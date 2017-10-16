package com.example.latoris.myword.Fetcher;

/**
 * Created by Latoris on 2017/10/2.
 */
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class JsonFetcher {
    String result = null;
    //final String urlstring = "https://47.90.63.143/news/bbc?catid=%2Fcps%2Fnews%2Fworld&apikey=zzhTqF2XPEoTw7vmpdNqva3eOo3aRpZkV9CTUxTNSnNWDFiAPrYae7bZ5MSuhA6V";
    //本方法通过指定url访问网络数据，并返回JSON格式的string。
    public  String getJSONText(final String urlstring){


        Thread thread = new Thread(new Runnable(){

            @Override
            public void run() {
                BufferedReader reader = null;
                StringBuffer sbf = new StringBuffer();
                try {
                    SSLContext context = SSLContext.getInstance("SSL");
                    TrustManager[] trustManager = {new myX509TrustManager()};
                    context.init(null, trustManager, new SecureRandom());
                    SSLSocketFactory ssf = context.getSocketFactory();
                    URL url = new URL(String.valueOf(urlstring));
                    HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
                    connection.setHostnameVerifier(new TrustAnyHostNameVerifier());
                    connection.setSSLSocketFactory(ssf);
                    connection.setRequestMethod("GET");
                    connection.connect();
                    InputStream is = connection.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                    String strRead = null;
                    while ((strRead = reader.readLine()) != null) {
                        sbf.append(strRead);
                        sbf.append("\r\n");
                    }
                    reader.close();
                    result = sbf.toString();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        });

        thread.start();

        //等待上述线程完成执行后再返回jsonText。
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result;
    }

    private static class TrustAnyHostNameVerifier implements HostnameVerifier{

        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }

    private static class myX509TrustManager implements javax.net.ssl.X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    }
}