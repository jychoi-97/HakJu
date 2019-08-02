package com.example.hakju;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

//
public class OrderActivity extends AppCompatActivity {
//    public ListAdapter adapter;
    public FirebaseDatabase database;
    Button allSelected;

//    public FirebaseListAdapter firebaseListAdapter;


    //데이터베이스의 정보
    public DatabaseReference ref;
    String studentId;

    //정보 담을 객체
//    public List<OrderItem> orderList = new ArrayList<>();

    //화면에 뿌려줄 뷰
//    public ListView listView;
//    public class ListAdapter extends BaseAdapter{
//        @Override
//        public void notifyDataSetChanged() {
//            super.notifyDataSetChanged();
//        }
//
//        List<OrderItem> orderList;
//        Context context;
//        LayoutInflater inflater;
//
//        public ListAdapter(List<OrderItem> orderList, Context context){
//            this.orderList = orderList;
//            this.context = context;
//            this.inflater = (LayoutInflater) context. getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        }
//
//        @Override
//        public int getCount() {
//            return orderList.size();
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return orderList.get(position);
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return position;
//        }
//
//        @Override
//        public View getView(int i, View convertView, ViewGroup viewGroup) {
//            if(convertView == null){
//                convertView = inflater.inflate(R.layout.activity_order, null);
//            }
//
//            TextView foodName = (TextView)convertView.findViewById(R.id.orderFoodName);
//            TextView foodNumber = (TextView)convertView.findViewById(R.id.orderFoodNumber);
//            TextView foodMoney = (TextView)convertView.findViewById(R.id.orderFoodMoney);
//
//            OrderItem orderItem = orderList.get(i);
//            foodName.setText(orderItem.getFoodNumber());
//            foodNumber.setText(orderItem.getFoodNumber());
//            foodMoney.setText(orderItem.getFoodMoney());
//
//            return convertView;
//        }
//    }






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        allSelected = (Button)findViewById(R.id.allSelected);
        Intent intent = getIntent();
        studentId = intent.getExtras().getString("StudentID");
        final ArrayList<String> midList = new ArrayList<>();
        final ListView listView = (ListView) findViewById(R.id.listView);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, midList);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setAdapter(adapter);




        //파이어 베이스와 연결
        database = FirebaseDatabase.getInstance();
        Log.i("start", "start");

        // 파이어 베이스에서 레퍼런스를 가져옴
        ref = database.getReference("장바구니");


//        adapter = new ListAdapter(orderList, this);

                // 파이어베이스 에서 데이터를 가져 옴


        ref.child(studentId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // 부모가 User 인데 부모 그대로 가져오면 User 각각의 데이터 이니까 자식으로 가져와서 담아줌
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    String name = snapshot.child("productName").getValue(String.class);
                    int total = snapshot.child("total").getValue(Integer.class);
                    int number = snapshot.child("productNum").getValue(Integer.class);

                    Log.i("MenuName", name);
//                           Log.i("MenuNumber", number);
//                           Log.i("Total", total);
                    midList.add(name + " "+ number +"개      "+total+"원");
                    //어뎁터한테 데이터 넣어줬다고 알려줌 (안하면 화면에 안나온다)
                    adapter.notifyDataSetChanged();
                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("OrderActivity", "loadPost:onCancelled", databaseError.toException());
            }
        });

        allSelected.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                int count = 0;
                count = adapter.getCount();
                for(int i=0; i<count;i++){
                    listView.setItemChecked(i, true);
                }

            }
        });






    }
}
//
//
//    private FirebaseRecyclerAdapter<OrderItem, OrderRecyclerAdapter.ViewHolder> mFirebaseAdapter;
//    OrderRecyclerAdapter adapter;
//    private DatabaseReference mFirebaseDatabaseReference;
//
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_order);
//
//        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
//
//        Button btnSelection;
//
//        RecyclerView orderRecyclerView = findViewById(R.id.recycler1);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
//        orderRecyclerView.setLayoutManager(layoutManager);
//
//        btnSelection = (Button) findViewById(R.id.orderButton);
//        List<OrderItem> orderDataList = new ArrayList<>();
//        for(int i=0; i<10; i++){
//            orderDataList.add(new OrderItem(i+"음식", i, i,true));
//        }
//        adapter = new OrderRecyclerAdapter(orderDataList);
//        orderRecyclerView.setAdapter(adapter);
//
//        btnSelection.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String data = "";
//                List<OrderItem> stList = adapter.getOrderItemList();
//
//                for(int i=0; i< stList.size(); i++){
//                    OrderItem singleOrder = stList.get(i);
//                    if(singleOrder.isSelected()){
//                        data = data + "Wn" +singleOrder.getFoodName();
//                    }
//                }
//                Toast.makeText(OrderActivity.this, data, Toast.LENGTH_LONG).show();
//            }
//        });
//        Query query = mFirebaseDatabaseReference.child("장바구니");
//        FirebaseRecyclerOptions<OrderItem> options = new FirebaseRecyclerOptions.Builder<OrderItem>()
//                .setQuery(query, OrderItem.class)
//                .build();
//
//        mFirebaseAdapter = new FirebaseRecyclerAdapter<OrderItem, OrderRecyclerAdapter.ViewHolder>(options) {
//            @Override
//            protected void onBindViewHolder(@NonNull OrderRecyclerAdapter.ViewHolder holder, int position, @NonNull OrderItem model) {
//                holder.foodName.setText(model.getFoodName());
//                holder.foodNumberMoney.setText(model.getFoodMoney());
//                holder.foodNumber.setText(model.getFoodNumber());
//            }
//
//            @NonNull
//            @Override
//            public OrderRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                View view = LayoutInflater.from(parent.getContext())
//                        .inflate(R.layout.item_order_list, parent, false);
//                return new OrderRecyclerAdapter.ViewHolder(view);
//            }
//        };
//
//        orderRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        orderRecyclerView.setAdapter(mFirebaseAdapter);
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        mFirebaseAdapter.startListening();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        mFirebaseAdapter.stopListening();
//    }
//}




