package com.nikitha.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.databinding.DataBindingUtil
import com.nikitha.newsapp.databinding.ActivityWebViewBinding

class WebView : AppCompatActivity() {

    lateinit var binding: ActivityWebViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_web_view)
        val url=intent.getStringExtra("url")
        binding.newsWebView.settings.javaScriptEnabled = true
        binding.newsWebView.settings.setAppCacheEnabled(true)
        binding.newsWebView.loadUrl(url?:"file:///android_asset/error.html")
        binding.newsWebView.webViewClient = object : WebViewClient() {

            override fun onReceivedError(view: WebView, errorCode: Int, description: String, failingUrl: String) {

                binding.newsWebView.loadUrl("file:///android_asset/error.html")
            }
        }
    }
}