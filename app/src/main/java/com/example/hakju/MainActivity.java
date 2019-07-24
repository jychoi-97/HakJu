package com.example.hakju;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.toCart);
        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), cart.class);
                startActivity(intent);
            }
        });

        Button button1 = (Button) findViewById(R.id.mn);
        button1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Intent int1 = new Intent(getApplicationContext(), menu.class);
                startActivity(int1);
            }
        });

    }
}
