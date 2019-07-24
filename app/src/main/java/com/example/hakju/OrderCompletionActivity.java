package com.example.hakju;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class OrderCompletionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_completion);

        ImageView imageView1=findViewById(R.id.imageView1);
        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.translation);
        imageView1.startAnimation(anim);

    }
}
