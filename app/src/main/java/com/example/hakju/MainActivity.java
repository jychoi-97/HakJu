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

    public EditText editText;
//    public String studentId;
//    SharedPreferences pref;
//    SharedPreferences.Editor editor;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.studentId);
//        pref = getSharedPreferences("pref", MODE_PRIVATE);
//        editor= pref.edit();

//        sp = getSharedPreferences("shared", MODE_PRIVATE);
//        SharedPreferences.Editor editor = sp.edit();
//        editor.putString("data", editText.getText().toString());
//        editor.commit();

//        studentId = editText.getText().toString();

        Button button1 = (Button) findViewById(R.id.toMenu);
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
//                editor.putString("userID", editText.getText().toString());
//                editor.commit();
                Toast.makeText(MainActivity.this, editText.getText().toString(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), menu.class);
                intent.putExtra("StudentID", editText.getText().toString());
                startActivity(intent);


            }
        });

    }
//    private void getPreferences(){
//        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
//        pref.getString("hi", "");
//        String a = pref.getString("key1", "");
//        Toast.makeText(this, a, Toast.LENGTH_LONG).show();
//
//
//    }



//
//    private void savePreferences(){
//
//        editor.putString("userID", editText.getText().toString());
//        editor.commit();
//    }






}
