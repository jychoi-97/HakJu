package com.example.hakju;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;

import java.util.Timer;
import java.util.TimerTask;

public class Payment extends AppCompatActivity {

    private WebView mainWebView;
    private static final String APP_SCHEME = "imp84763920";
    Button goBack;

    @SuppressLint("NewApi")@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        mainWebView = (WebView) findViewById(R.id.mainWebView);
        mainWebView.setWebViewClient(new DanalWebViewClient(this));
        WebSettings settings = mainWebView.getSettings();
        settings.setJavaScriptEnabled(true);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.setAcceptCookie(true);
            cookieManager.setAcceptThirdPartyCookies(mainWebView, true);
        }

        Intent intent = getIntent();
        Uri intentData = intent.getData();
        goBack = (Button)findViewById(R.id.goBack);

        if ( intentData == null ) {
            mainWebView.loadUrl("https://www.iamport.kr/demo");
        } else {
            //isp 인증 후 복귀했을 때 결제 후속조치

            final String url = intentData.toString();

            if ( url.startsWith(APP_SCHEME) ) {
                String redirectURL = url.substring(APP_SCHEME.length()+3);
                mainWebView.loadUrl(redirectURL);
            }

            goBack.setVisibility(View.VISIBLE);


        }

        Intent i = getIntent();
        final String studentId = i.getExtras().getString("StudentID");
        final String key = i.getExtras().getString("key");

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(Payment.this, OrderCompletionActivity.class);
                a.putExtra("StudentID", studentId);
                a.putExtra("key", key);
                startActivity(a);
            }
        });

    }
}
