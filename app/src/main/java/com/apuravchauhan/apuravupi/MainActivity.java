package com.apuravchauhan.apuravupi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * @author apuravchauhan
 */
public class MainActivity extends AppCompatActivity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebView = findViewById(R.id.activity_main_webview);
        // Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.addJavascriptInterface(new WebAppInterface(this), "ApuSDK");

        // REMOTE RESOURCE
        // mWebView.loadUrl("https://www.google.com");

        // LOCAL RESOURCE
        mWebView.loadUrl("file:///android_asset/index.html");

    }

}
