package com.wz.testweb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    WebView webView;
    private TextView textView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = (WebView) findViewById(R.id.webview);
        textView = (TextView) findViewById(R.id.text);
        button = (Button) findViewById(R.id.button);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/ceshi.html");
        webView.addJavascriptInterface(this,"ceshi");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //无参数调用
//                webView.loadUrl("javascript:actionFromNative()");
                webView.loadUrl("javascript:actionFromNativeWithParam("+"'come from app'"+")");
            }
        });
    }

    @JavascriptInterface
    public void actionFromJs(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "js调用了Native函数", Toast.LENGTH_SHORT).show();
                String text = textView.getText() + "\njs调用了Native函数";
                textView.setText(text);
            }
        });
    }

    @JavascriptInterface
    public void actionFromJsWithParam(final String str){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "js调用了Native函数传递参数：" + str, Toast.LENGTH_SHORT).show();
                String text = textView.getText() +  "\njs调用了Native函数传递参数：" + str;
                textView.setText(text);
            }
        });
    }

}
