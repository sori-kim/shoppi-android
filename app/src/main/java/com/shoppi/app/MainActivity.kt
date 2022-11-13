package com.shoppi.app

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val webView = findViewById<WebView>(R.id.webView)

        webView.apply {
            webViewClient = WebViewClient()
            webChromeClient = WebChromeClient()
            webView.settings.javaScriptEnabled = true // 자바스크립트 허용
        }

        webView.addJavascriptInterface(WebAppInterface(this), "Pilleye")

        //pilleye 웹뷰
        webView.loadUrl("https://dev.mobile.piileye.io?member_idx=12345&region=kr")
    }

    class WebAppInterface(private val mContext: Context){
        @JavascriptInterface
        fun showToast(toast: String){
            Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show()
        }

        @JavascriptInterface
        fun nativeTabChange(toast: String){
            Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show()
        }
    }



    // 백버튼을 클릭했을때
    override fun onBackPressed() {
        super.onBackPressed()
        val webView = findViewById<WebView>(R.id.webView)
        // 뒤로 갈 페이지가 존재한다면
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed() // 안드로이드의 백버튼 기능 수행
        }
    }

}




