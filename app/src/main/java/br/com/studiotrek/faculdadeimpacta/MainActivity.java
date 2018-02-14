package br.com.studiotrek.faculdadeimpacta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    private WebView wvMainPrincipal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wvMainPrincipal = findViewById(R.id.wv_main_principal);

        WebSettings webSettings = wvMainPrincipal.getSettings();
        webSettings.setJavaScriptEnabled(true);

        wvMainPrincipal.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        wvMainPrincipal.setWebViewClient(new WebViewClient());
        wvMainPrincipal.loadUrl("http://account.impacta.edu.br/index.php");
    }
}
