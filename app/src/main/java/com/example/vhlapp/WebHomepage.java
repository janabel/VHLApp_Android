package com.example.vhlapp;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class WebHomepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_homepage);
        //retrieve website URL, passed from item clicked on homepage
        Intent i = getIntent();
        String URL = i.getStringExtra("URL");

        String pageName = i.getStringExtra("pageName");
        setTitle(pageName);

//        Log.d("retrieved url: ", "blah");

        //open up URL *within* the app
        WebView webView = findViewById(R.id.webview1);
        webView.getSettings().setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                view.loadUrl(url);
                return true;
            }
        });

        webView.loadUrl(URL);
    }


}