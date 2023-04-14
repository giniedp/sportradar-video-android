package com.example.sportradarvideo

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        WebView.setWebContentsDebuggingEnabled(true)

        val frame = findViewById<ViewGroup>(R.id.frame)
        val fullscreen = findViewById<ViewGroup>(R.id.fullscreen)

        // https://developer.android.com/reference/android/webkit/WebChromeClient#onShowCustomView(android.view.View,%20android.webkit.WebChromeClient.CustomViewCallback)
        with(findViewById<WebView>(R.id.web_view)) {
            webChromeClient = object : WebChromeClient() {

                override fun onShowCustomView(view: View?, callback: CustomViewCallback?) {
                    Log.d("FULLSCREEN", "enable")
                    frame.visibility = View.INVISIBLE
                    fullscreen.addView(view)
                    fullscreen.visibility = View.VISIBLE
                    // Why do we have to call this to make fullscreen work?
                    callback?.onCustomViewHidden()
                }

                override fun onHideCustomView() {
                    Log.d("FULLSCREEN", "disable")
                    frame.visibility = View.VISIBLE
                    fullscreen.removeAllViews()
                    fullscreen.visibility = View.INVISIBLE
                }
            }

            settings.allowUniversalAccessFromFileURLs = true
            settings.allowFileAccess = true
            settings.allowContentAccess = true
            settings.domStorageEnabled = true
            settings.javaScriptEnabled = true

            loadUrl("file:///android_asset/index.html")
        }
    }
}