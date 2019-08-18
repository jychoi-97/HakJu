package com.example.hakju;
import java.util.StringTokenizer;


import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;
import com.example.hakju.*;
import com.example.hakju.OrderActivity;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;


import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

public class  OrderActivity extends AppCompatActivity {
    private static final String TAG = "OrderActivity";
    public FirebaseDatabase database;
    TextView chong;
    Button allSelected;
    Button orderButton;
    Button removeButton;

    String key;

    //데이터베이스의 정보
    public DatabaseReference ref;
    String studentId;
    public DatabaseReference ref1;
    DatabaseReference ref3;


    String name;
    int total;
    int number;
    int waitingNumber;

    int chongCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        chong = (TextView) findViewById(R.id.text2);


        allSelected = (Button) findViewById(R.id.allSelected);
        Intent intent = getIntent();
        studentId = intent.getExtras().getString("StudentID");
        final ArrayList<String> midList = new ArrayList<>();


        final ArrayList<Integer> moneyList = new ArrayList<>();
        final ListView listView = (ListView) findViewById(R.id.listView);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, midList);


        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setAdapter(adapter);
        orderButton = (Button) findViewById(R.id.orderButton);
        removeButton = (Button) findViewById(R.id.removeButton);

//        final ArrayList<String> copyList = new ArrayList<>();

        //파이어 베이스와 연결
        database = FirebaseDatabase.getInstance();
        Log.i("start", "start");

        // 파이어 베이스에서 레퍼런스를 가져옴
        ref = database.getReference("장바구니");
        ref1 = database.getReference("결제");


        ref.child(studentId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // 부모가 User 인데 부모 그대로 가져오면 User 각각의 데이터 이니까 자식으로 가져와서 담아줌
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {


                    name = snapshot.child("productName").getValue(String.class);
                    total = snapshot.child("total").getValue(Integer.class);
                    number = snapshot.child("productNum").getValue(Integer.class);

                    moneyList.add(total);
                    Log.i("MenuName", name);
//                           Log.i("MenuNumber", number);
//                           Log.i("Total", total);
                    midList.add(name + "  " + number + " 개   " + total + " 원");
                    //어뎁터한테 데이터 넣어줬다고 알려줌 (안하면 화면에 안나온다)
                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("OrderActivity", "loadPost:onCancelled", databaseError.toException());
            }
        });


        allSelected.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                int count = 0;
                int totalTotal = 0;
                count = adapter.getCount();
                if (listView.getCheckedItemCount() == count) {
                    for (int i = 0; i < count; i++) {
                        listView.setItemChecked(i, false);
                    }
                    chong.setText(null);
                } else {
                    for (int i = 0; i < count; i++) {
                        listView.setItemChecked(i, true);
                        totalTotal += moneyList.get(i);
                    }
                    String t = Integer.toString(totalTotal);
                    chong.setText(t);
                }

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                int countCount = 0;
                int totalTotal = 0;
                countCount = adapter.getCount();
                for (int i = 0; i < countCount; i++) {
                    if (listView.isItemChecked(i) == true) {
                        totalTotal += moneyList.get(i);
                    }
                }

                String t = Integer.toString(totalTotal);
                chong.setText(t);
            }
        });


        orderButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                ArrayList<String> copyList = new ArrayList<>();

                if (listView.getCheckedItemCount() == 0) {
                    Toast.makeText(OrderActivity.this, "메뉴를 선택해주세요", Toast.LENGTH_SHORT).show();
                } else {


                    int count = 0;
                    int totalTotal = 0;
                    count = adapter.getCount();
                    ref3 = ref1.push();


                    for (int i = 0; i < count; i++) {

                        if (listView.isItemChecked(i) == true) {


                            String name = midList.get(i);
                            ref1.child(ref3.getKey()).push().setValue(name);
                        }


                    }
                    Intent intent = new Intent(getApplicationContext(), OrderCompletionActivity.class);
                    intent.putExtra("StudentID", studentId);
                    intent.putExtra("key", ref3.getKey());

                    startActivity(intent);

                    }


                SparseBooleanArray sb = listView.getCheckedItemPositions();
                if (sb.size() != 0) {
                    for (int i = listView.getCount() - 1; i >= 0; i--) {
                        if (sb.get(i)) {
                            midList.remove(i);
                        }
                        if (!sb.get(i)) {
                            int k = 0;
                            copyList.add(k++, midList.get(i));
                        }
                    }
                    listView.clearChoices();
                    adapter.notifyDataSetChanged();
                }
                int count = adapter.getCount();


                ref.child(studentId).removeValue();

                for (int i = 0; i < count; i++) {
                    String str = copyList.get(i);
                    StringTokenizer token = new StringTokenizer(str, " ");

                    String a[] = new String[token.countTokens()];
                    int j = 0;

                    while (token.hasMoreTokens()) {
                        a[j] = token.nextToken();
                        j++;
                    }
                    rewriteMenu(studentId, a[0], Integer.parseInt(a[1]), Integer.parseInt(a[3]));
                }

                chong.setText(null);

                        }
                    });


        removeButton.setOnClickListener(new Button.OnClickListener() {
            ArrayList<String> copyList = new ArrayList<>();

            public void onClick(View v) {

                SparseBooleanArray sb = listView.getCheckedItemPositions();
                if (sb.size() != 0) {
                    for (int i = listView.getCount() - 1; i >= 0; i--) {
                        if (sb.get(i)) {
                            midList.remove(i);
                        }
                        if (!sb.get(i)) {
                            int k = 0;
                            copyList.add(k++, midList.get(i));
                        }
                    }
                    listView.clearChoices();
                    adapter.notifyDataSetChanged();
                }
                int count = adapter.getCount();


                ref.child(studentId).removeValue();

                for (int i = 0; i < count; i++) {
                    String str = copyList.get(i);
                    StringTokenizer token = new StringTokenizer(str, " ");

                    String a[] = new String[token.countTokens()];
                    int j = 0;

                    while (token.hasMoreTokens()) {
                        a[j] = token.nextToken();
                        j++;
                    }
                    rewriteMenu(studentId, a[0], Integer.parseInt(a[1]), Integer.parseInt(a[3]));
                }

                chong.setText(null);
            }


        });


    }

    public void writeOrderItem(String foodName, int foodNumber, int foodMoney, boolean isSelected) {
        OrderItem orderItem = new OrderItem(foodName, foodNumber, foodMoney, isSelected);

        ref1.child(studentId).push().setValue(orderItem);
    }

    public void rewriteMenu(String studentId, String productName, int productNum, int total) {
        MenuData menuData = new MenuData(productName, productNum, total);

        ref.child(studentId).push().setValue(menuData);
    }
}




