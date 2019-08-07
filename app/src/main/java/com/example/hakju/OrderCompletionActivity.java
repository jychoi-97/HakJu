package com.example.hakju;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class OrderCompletionActivity extends AppCompatActivity {

    public FirebaseDatabase database;
    public DatabaseReference ref2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_completion);

        Intent intent = getIntent();

        String studentId = intent.getExtras().getString("StudentID");
        database = FirebaseDatabase.getInstance();
        ref2 = database.getReference("결제");
        ImageView imageView1=findViewById(R.id.imageView1);
        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.translation);
        imageView1.startAnimation(anim);
        final TextView myNumber = findViewById(R.id.myNumberData);
        final TextView waitingNumber = findViewById(R.id.waitingNumberData);

        myNumber.setText("1");


        ref2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                long count = dataSnapshot.getChildrenCount();
                String s = String.valueOf(count-1);



                waitingNumber.setText(s);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("OrderActivity", "loadPost:onCancelled", databaseError.toException());
            }
        });





    }
}
