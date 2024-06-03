package edu.`val`.cntgapp.webview

import android.os.Bundle
import android.webkit.WebView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import edu.`val`.cntgapp.R

class WebActivity : AppCompatActivity() {

    val urlRemota:String = "https://cntg.xunta.gal/"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
        var webView = findViewById<WebView>(R.id.webview)
        webView.loadUrl(urlRemota)
        webView.settings.javaScriptEnabled = true

    }
}