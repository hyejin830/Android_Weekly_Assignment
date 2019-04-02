package com.example.week2_project.assignment02_communicate_app_webview;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

public class WebAppInterface {

    Context mContext;

    WebAppInterface(Context c){
        mContext = c;
    }

    // Show a toast from the web page
    @JavascriptInterface
    public void doSearch(String toast){
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
    }
}
