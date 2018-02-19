package br.com.studiotrek.faculdadeimpacta.presentation

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import br.com.studiotrek.faculdadeimpacta.R

@SuppressLint("SetJavaScriptEnabled")
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val wvMainPrincipal = findViewById<WebView>(R.id.wv_main_principal)

        val webSettings = wvMainPrincipal.settings
        webSettings.javaScriptEnabled = true

        wvMainPrincipal.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
        wvMainPrincipal.webViewClient = WebViewClient()
        wvMainPrincipal.loadUrl("http://account.impacta.edu.br/index.php")
    }
}
