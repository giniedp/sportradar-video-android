package com.example.sportradarvideo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        WebView.setWebContentsDebuggingEnabled(true)

        val frame = findViewById<ViewGroup>(R.id.frame)
        val fullscreen = findViewById<ViewGroup>(R.id.fullscreen)

        with(findViewById<WebView>(R.id.web_view), {
            webChromeClient = object : WebChromeClient() {

                override fun onShowCustomView(view: View?, callback: CustomViewCallback?) {
                    frame.visibility = View.INVISIBLE
                    fullscreen.addView(view)
                    fullscreen.visibility = View.VISIBLE
                }

                override fun onHideCustomView() {
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
        })
    }
}