package com.example.hakju;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;

import java.util.Timer;
import java.util.TimerTask;

public class Payment extends AppCompatActivity {

    private WebView mainWebView;
    private static final String APP_SCHEME = "imp84763920";

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

        if ( intentData == null ) {
            mainWebView.loadUrl("https://www.iamport.kr/demo");
        } else {
            //isp 인증 후 복귀했을 때 결제 후속조치

//            Handler hand = new Handler();
//
//            hand.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    Intent i = new Intent(Payment.this, OrderCompletionActivity.class);
//                    startActivity(i);
//                    finish();
//                }
//            }, 2000);

//            Intent intent1 = new Intent(getApplicationContext(), OrderCompletionActivity.class);
//            startActivity(intent1);

            String url = intentData.toString();

            if ( url.startsWith(APP_SCHEME) ) {
                String redirectURL = url.substring(APP_SCHEME.length()+3);
                mainWebView.loadUrl(redirectURL);
//                finish();

//                Handler hand = new Handler();
//
//                hand.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        finish();
//                        Intent i = new Intent(Payment.this, OrderCompletionActivity.class);
//                        startActivity(i);
//
//                    }
//                }, 2000);

            }


        }


    }
}
