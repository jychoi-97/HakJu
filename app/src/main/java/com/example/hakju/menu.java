package com.example.hakju;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
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

import java.util.List;


public class menu extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    List<Food> lstFood;

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
        tabLayout = (TabLayout) findViewById(R.id.tablayout1);
        viewPager = (ViewPager) findViewById(R.id.viewpager1);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.AddFragment(new FragmentRoll(), "Roll & Noodle");
        adapter.AddFragment(new FragmentBob(), "The Bab");
        adapter.AddFragment(new FragmentFry(), "Fry & Rice");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        Intent intent = getIntent();
        studentId = intent.getExtras().getString("StudentID");



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
                if(spinnerMenu1.getSelectedItem().toString().equalsIgnoreCase("선택하세요")&&menu1num.getValue()==0){
                    Toast.makeText(menu.this, "메뉴와 개수를 선택하세요",Toast.LENGTH_SHORT).show();
                }else if(menu1num.getValue()==0) {
                    Toast.makeText(menu.this, "개수를 선택하세요",Toast.LENGTH_SHORT).show();
                }else if (spinnerMenu1.getSelectedItem().toString().equalsIgnoreCase("선택하세요")){
                    Toast.makeText(menu.this, "메뉴를 선택하세요",Toast.LENGTH_SHORT).show();
                }else{
                    writeMenu(studentId, productName, productNum, totalPrice);
                    showDialog();
                }


//                Intent intent = new Intent(getApplicationContext(), OrderActivity.class);
//                intent.putExtra("StudentID", studentId);
//                startActivity(intent);
            }
        });

        Button btnOrder = (Button)findViewById(R.id.btnOrder);

        btnOrder.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {if(spinnerMenu1.getSelectedItem().toString().equalsIgnoreCase("선택하세요")&&menu1num.getValue()==0){
                Toast.makeText(menu.this, "메뉴와 개수를 선택하세요",Toast.LENGTH_SHORT).show();
            }else if(menu1num.getValue()==0) {
                Toast.makeText(menu.this, "개수를 선택하세요",Toast.LENGTH_SHORT).show();
            }else if (spinnerMenu1.getSelectedItem().toString().equalsIgnoreCase("선택하세요")){
                Toast.makeText(menu.this, "메뉴를 선택하세요",Toast.LENGTH_SHORT).show();
            }else {
                Intent intent = new Intent(getApplicationContext(), Payment.class);
                startActivity(intent);
            }

            }
        });

    }

    private void writeMenu(String studentId, String productName, int productNum, int total){
        MenuData menuData = new MenuData(productName, productNum, total);

        mRootRef.child("장바구니").child(studentId).push().setValue(menuData);
    }

    public void showDialog(){
        final AlertDialog.Builder alert = new AlertDialog.Builder(menu.this);

        alert.setTitle("장바구니");
        alert.setMessage("장바구니로 이동하시겠습니까?");
        alert.setPositiveButton("이동", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getApplicationContext(), OrderActivity.class);
                intent.putExtra("StudentID", studentId);
                startActivity(intent);
            }
        });
        alert.setNegativeButton("안이동", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(menu.this,"메뉴를 고르세요",Toast.LENGTH_SHORT).show();
            }
        });
        alert.show();
    }
}


