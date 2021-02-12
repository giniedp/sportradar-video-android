package com.example.sportradarvideo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.webkit.WebChromeClient
import android.webkit.WebView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        WebView.setWebContentsDebuggingEnabled(true)
        with(findViewById<WebView>(R.id.web_view), {
            webChromeClient = object : WebChromeClient() {

            }
            settings.allowUniversalAccessFromFileURLs = true
            settings.allowFileAccess = true
            settings.allowContentAccess = true
            settings.domStorageEnabled = true
            settings.javaScriptEnabled = true

            loadUrl("file:///android_asset/index.html")
        })
    }
}