package com.example.hakju;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class OrderCompletionActivity extends AppCompatActivity {
    Button completionButton;


    public FirebaseDatabase database;
    public DatabaseReference ref2;
    public DatabaseReference ref4;
    public DatabaseReference ref5;
    public DatabaseReference ref6;
    public DatabaseReference ref7;
    final ArrayList<String> stringList = new ArrayList<>();

//    int keyNumber = intent.getExtras().getInt("key");
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
                String s = String.valueOf(count-1);



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
            public void onClick(View v){

                ref5=ref4.push();
                ref7=ref6.push();
                for (int i = 0; i < stringList.size(); i++) {




                        String name = stringList.get(i);
                        ref4.child(ref5.getKey()).child(studentId).push().setValue(name);
                        ref6.child(ref7.getKey()).child(studentId).push().setValue(name);





                }
                ref6.child(ref7.getKey()).child(studentId).child("주문번호").setValue(name.substring(name.length()-6));




//                ref5=ref4.push();
//                ref2.child(name).addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//
//
//
//                        // 클래스 모델이 필요?
//                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//
//                            ref4.child(ref5.getKey()).push()=snapshot.getValue(String.class);
////                            name = snapshot.child("productName").getValue(String.class);
//                            //MyFiles filename = (MyFiles) fileSnapshot.getValue(MyFiles.class);
//                            //하위키들의 value를 어떻게 가져오느냐???
////                            String str = fileSnapshot.child("filename").getValue(String.class);
////                            Log.i("TAG: value is ", str);
////                            fileList.add(str);
//                        }
////                        adapter.notifyDataSetChanged();
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//                        Log.w("TAG: ", "Failed to read value", databaseError.toException());
//                    }
//                });
//
//
//
//
//
//
                ref2.child(name).removeValue();
            }
        });




    }
}
