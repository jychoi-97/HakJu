package com.example.hakju;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class OrderCompletionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_completion);

        ImageView imageView1=findViewById(R.id.imageView1);
        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.translation);
        imageView1.startAnimation(anim);
        RadioButton r_btn1, r_btn2, r_btn3;
        RadioGroup radioGroup;



    }
}
