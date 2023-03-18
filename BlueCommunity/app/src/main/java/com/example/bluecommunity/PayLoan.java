package com.example.bluecommunity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class PayLoan extends AppCompatActivity {
    String sunrise_url="https://tinypesa.com/sunrise";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_loan);
        WebView webView=(WebView) findViewById(R.id.webView);
        webView.loadUrl(sunrise_url);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
    }
}