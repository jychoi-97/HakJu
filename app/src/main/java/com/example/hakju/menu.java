package com.example.hakju;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.ValueCallback;
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

    private DatabaseReference mRootRef;

    String studentId;
    String productName;
    int productNum;
    int total;
    int totalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intent = getIntent();
        studentId = intent.getExtras().getString("StudentID");

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

        final int[] arrayTotal = getResources().getIntArray(R.array.price);

        spinnerMenu1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                productName = spinnerMenu1.getSelectedItem().toString();
                if(position == 1)
                    total = arrayTotal[0];

                else if (position == 2)
                    total = arrayTotal[1];

                else if (position == 3)
                    total = arrayTotal[2];

                else if (position == 4)
                    total = arrayTotal[3];

                else if (position == 5)
                    total = arrayTotal[4];

                else if (position == 6)
                    total = arrayTotal[5];

                else if (position == 7)
                    total = arrayTotal[6];

                else if (position == 8)
                    total = arrayTotal[7];

                else if (position == 9)
                    total = arrayTotal[8];

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        final NumberPicker menu1num = (NumberPicker)findViewById(R.id.menu1num);
        menu1num.setMaxValue(20);
        menu1num.setMinValue(0);
        menu1num.setValue(0);
        menu1num.setWrapSelectorWheel(false);

        menu1num.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldValue, int newValue) {
                productNum = picker.getValue();
                totalPrice = productNum * total;

                TextView tprice = (TextView)findViewById(R.id.tprice);
                tprice.setText("총 금액: " + totalPrice + "원");

            }
        });

        mRootRef = FirebaseDatabase.getInstance().getReference();

        Button btnCart = (Button)findViewById(R.id.btnCart);

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeMenu(studentId, productName, productNum, totalPrice);


                Intent intent = new Intent(getApplicationContext(), OrderActivity.class);
                intent.putExtra("StudentID", studentId);
                startActivity(intent);
            }
        });

        Button btnOrder = (Button)findViewById(R.id.btnOrder);

        btnOrder.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), cart.class);
                startActivity(intent);

            }
        });

    }

    private void writeMenu(String studentId, String productName, int productNum, int total){
        MenuData menuData = new MenuData(productName, productNum, total);

        mRootRef.child("장바구니").child(studentId).push().setValue(menuData);
    }
}


