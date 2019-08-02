package com.example.hakju;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.studentId);

        Button button1 = (Button) findViewById(R.id.toMenu);
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                if(editText.getText().toString().length() == 8) {
//                    Toast.makeText(MainActivity.this, editText.getText().toString(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), menu.class);
                    intent.putExtra("StudentID", editText.getText().toString());
                    startActivity(intent);
                }

                else
                    Toast.makeText(MainActivity.this, "학번을 다시 입력하세요!", Toast.LENGTH_SHORT).show();

            }

        });

    }

}
