package com.example.week2_project.assignment02_communicate_app_webview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.week2_project.R;

public class CommunicateAppWithWebpage extends AppCompatActivity implements View.OnClickListener {

    private WebSettings webSettings;
    private WebView webView;
    private EditText sendMsgToJavaScriptEditText;
    private Button doSendMsgJavaScriptButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_communicate_app_with_webpage);

        initView();
    }

    void initView() {
        webView = findViewById(R.id.web_view);
        sendMsgToJavaScriptEditText = findViewById(R.id.et_input_msg);
        doSendMsgJavaScriptButton = findViewById(R.id.btn_do_send_text_android_to_javascript);

        // Enabling JavaScript
        webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new WebAppInterface(this), "Android");
        webView.loadUrl("file:///android_asset/JSInterfaceSample.html");

        doSendMsgJavaScriptButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_do_send_text_android_to_javascript:
                Toast.makeText(getApplicationContext(), "check", Toast.LENGTH_SHORT).show();

                String sendMsg = sendMsgToJavaScriptEditText.getText().toString();
                webView.loadUrl("javascript:doAndroidReceive('" + sendMsg + "')");
                break;
        }
    }
}
