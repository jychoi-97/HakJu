package com.example.hakju;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.util.ArrayList;

public class OrderCompletionActivity extends AppCompatActivity {
    Button completionButton;
    private static final String TAG = "OrderCompletionActivity";


    public FirebaseDatabase database;
    public DatabaseReference ref2;
    public DatabaseReference ref4;
    public DatabaseReference ref5;
    public DatabaseReference ref6;
    public DatabaseReference ref7;
    final ArrayList<String> stringList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_completion);

        completionButton = (Button) findViewById(R.id.payCompleteButton);
        Intent intent = getIntent();
        final String name = intent.getExtras().getString("key"); /*String형*/


        final String studentId = intent.getExtras().getString("StudentID");
        database = FirebaseDatabase.getInstance();
        ref2 = database.getReference("결제");
        ref4 = database.getReference("결제완료");
        ref6 = database.getReference("음식확인");
        ImageView imageView1=findViewById(R.id.imageView1);
        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.translation);
        imageView1.startAnimation(anim);
        final TextView myNumber = findViewById(R.id.myNumberData);
        final TextView waitingNumber = findViewById(R.id.waitingNumberData);

        myNumber.setText(name.substring(name.length()-6, name.length()));


        ref6.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                long count = dataSnapshot.getChildrenCount();
                String s = String.valueOf(count);

                waitingNumber.setText(s);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("OrderActivity", "loadPost:onCancelled", databaseError.toException());
            }
        });

        ref2.child(name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // 부모가 User 인데 부모 그대로 가져오면 User 각각의 데이터 이니까 자식으로 가져와서 담아줌
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    stringList.add(snapshot.getValue(String.class));


                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("OrderActivity", "loadPost:onCancelled", databaseError.toException());
            }
        });

        completionButton.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {


                FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "getInstanceId failed", task.getException());
                            return;
                        }
                        String token = task.getResult().getToken();

                        String msg = getString(R.string.msg_token_fmt, token);
                        Log.d(TAG, msg);

                        ref5 = ref4.push();
                        ref7 = ref6.push();
                        for (int i = 0; i < stringList.size(); i++) {
                            String name = stringList.get(i);
                            ref4.child(ref5.getKey()).child(studentId).push().setValue(name);
                            ref6.child(ref7.getKey()).child(studentId).push().setValue(name);
                        }
                        ref6.child(ref7.getKey()).child(studentId).child("주문번호").setValue(name.substring(name.length() - 6));
                        ref6.child(ref7.getKey()).child(studentId).child("토큰값").setValue(token);

                        ref2.child(name).removeValue();

                    }

                });

                Intent intent2 = new Intent(getApplicationContext(), menu.class);
                startActivity(intent2);

            }
    });

        BottomNavigationView bottom = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_menu:
                        Intent i = new Intent(getApplicationContext(), menu.class);
                        i.putExtra("StudentID", studentId);
                        i.putExtra("key", name);
                        startActivity(i);
                        break;
                    case R.id.action_cart:
                        Intent i1 = new Intent(getApplicationContext(), OrderActivity.class);
                        i1.putExtra("StudentID", studentId);
                        startActivity(i1);
                        break;
                    case R.id.action_paid:
//                        Intent i2 = new Intent(getApplicationContext(), OrderCompletionActivity.class);
//                        i2.putExtra("StudentID", studentId);
//                        i2.putExtra("key", name);
//                        startActivity(i2);
                        break;
                }
                return false;
            }
        });


    }
}
