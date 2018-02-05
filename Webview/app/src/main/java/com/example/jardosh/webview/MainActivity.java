package com.example.jardosh.webview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
//        webView.loadUrl("http://ramtoo.dcodesoftware.com/");
        webView.loadData("<html><body><h1>Dhaval Jardosh</h1><p style='margin-top:-20px; color:red;'>Working on Ramtoo</p></body></html>","text/html","utf-8");
    }
}
