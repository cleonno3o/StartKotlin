package com.example.startkotlin

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged

class AndroidHW2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_android_hw2)

        val urlBox: EditText = findViewById(R.id.urlBox)
        urlBox.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                Log.d("HW2","beforeTextChanged: $s $start $count $after")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.d("HW2","onTextChanged: $s $start $before $count")
            }

            override fun afterTextChanged(s: Editable?) {
                Log.d("HW2","afterTextChanged: $s")
            }
        })
        val webView: WebView = findViewById(R.id.web)
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                return super.shouldOverrideUrlLoading(view, request)
            }
        }
        try {
            webView.loadUrl(intent.data!!.toString())
        } catch (exception: Exception) {
        }
        findViewById<Button>(R.id.btn).apply {
            setOnClickListener {
                Log.d("HW2", urlBox.text.toString())
                startActivity(Intent(Intent.ACTION_VIEW,
                    Uri.parse(urlBox.text.toString())
                ))
            }
        }
    }
}