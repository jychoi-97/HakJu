package com.example.hakju;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.NumberPicker;
import android.widget.SlidingDrawer;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class menu extends AppCompatActivity {

//    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    private DatabaseReference mRootRef;
//    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
//    DatabaseReference mRootRef = firebaseDatabase.getReference();



    String productName;
    int productNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

//        database.child("user").child("123").setValue("1");

        TabHost tabHost1 = (TabHost) findViewById(R.id.tabHost1) ;
        tabHost1.setup() ;

        // 첫 번째 Tab. (탭 표시 텍스트:"TAB 1"), (페이지 뷰:"content1")
        TabHost.TabSpec ts1 = tabHost1.newTabSpec("Tab Spec 1") ;
        ts1.setContent(R.id.content1) ;
        ts1.setIndicator("Roll&Noodle") ;
        tabHost1.addTab(ts1)  ;

        // 두 번째 Tab. (탭 표시 텍스트:"TAB 2"), (페이지 뷰:"content2")
        TabHost.TabSpec ts2 = tabHost1.newTabSpec("Tab Spec 2") ;
        ts2.setContent(R.id.content2) ;
        ts2.setIndicator("The bab") ;
        tabHost1.addTab(ts2) ;

        // 세 번째 Tab. (탭 표시 텍스트:"TAB 3"), (페이지 뷰:"content3")
        TabHost.TabSpec ts3 = tabHost1.newTabSpec("Tab Spec 3") ;
        ts3.setContent(R.id.content3) ;
        ts3.setIndicator("Fry&Rice") ;
        tabHost1.addTab(ts3) ;

        final Spinner spinnerMenu1 = (Spinner)findViewById(R.id.spinnerMenu1);
        spinnerMenu1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                productName = spinnerMenu1.getSelectedItem().toString();
//                Toast.makeText(menu.this, "item:" + spinnerMenu1.getSelectedItem().toString() + productName, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        final NumberPicker menu1num = (NumberPicker)findViewById(R.id.menu1num);
        menu1num.setMaxValue(20);
        menu1num.setMinValue(1);
        menu1num.setValue(1);
        menu1num.setWrapSelectorWheel(false);


        menu1num.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldValue, int newValue) {
                productNum = picker.getValue();

//                Toast.makeText(menu.this, "Value:" + productNum + newValue, Toast.LENGTH_SHORT).show();

            }
        });


        mRootRef = FirebaseDatabase.getInstance().getReference();

        Button btnCart = (Button)findViewById(R.id.btnCart);


        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                productNameRef.child("productName").push().setValue(productName);
//                productNumRef.child("productNum").push().setValue(productNum);



                writeMenu("1234", productName, productNum);

                Intent intent = new Intent(getApplicationContext(), cart.class);
                startActivity(intent);
            }
        });

        Button btnOrder = (Button)findViewById(R.id.btnOrder);

        btnOrder.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), OrderActivity.class);
                startActivity(intent);

            }
        });

    }

    private void writeMenu(String userId, String productName, int productNum){
        MenuData menuData = new MenuData(productName, productNum);

        mRootRef.child("장바구니").child(userId).setValue(menuData);
    }


}


