package com.example.jardosh.newsreader;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ArticleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        WebView webView = (WebView) findViewById(R.id.webView);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());

        Intent intent = getIntent();
        Log.i("CHECKING", String.valueOf(intent.getStringExtra("content")));

//        webView.loadUrl("https://arstechnica.com/gaming/2018/02/donkey-kong-scoreboard-strips-billy-mitchells-high-score-claims/");
        webView.loadData(intent.getStringExtra("content"),"text/html","UTF-8");

    }
}
