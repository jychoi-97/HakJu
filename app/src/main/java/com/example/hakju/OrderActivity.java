package com.example.hakju;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;


public class OrderActivity extends AppCompatActivity {


    private FirebaseRecyclerAdapter<OrderItem, OrderRecyclerAdapter.ViewHolder> mFirebaseAdapter;
    OrderRecyclerAdapter adapter;
    private DatabaseReference mFirebaseDatabaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();

        Button btnSelection;

        RecyclerView orderRecyclerView = findViewById(R.id.recycler1);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        orderRecyclerView.setLayoutManager(layoutManager);

        btnSelection = (Button) findViewById(R.id.orderButton);
        List<OrderItem> orderDataList = new ArrayList<>();
        for(int i=0; i<10; i++){
            orderDataList.add(new OrderItem(i+"음식", i, i,true));
        }
        adapter = new OrderRecyclerAdapter(orderDataList);
        orderRecyclerView.setAdapter(adapter);

        btnSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = "";
                List<OrderItem> stList = adapter.getOrderItemList();

                for(int i=0; i< stList.size(); i++){
                    OrderItem singleOrder = stList.get(i);
                    if(singleOrder.isSelected()){
                        data = data + "Wn" +singleOrder.getFoodName();
                    }
                }
                Toast.makeText(OrderActivity.this, data, Toast.LENGTH_LONG).show();
            }
        });
        Query query = mFirebaseDatabaseReference.child("장바구니");
        FirebaseRecyclerOptions<OrderItem> options = new FirebaseRecyclerOptions.Builder<OrderItem>()
                .setQuery(query, OrderItem.class)
                .build();

        mFirebaseAdapter = new FirebaseRecyclerAdapter<OrderItem, OrderRecyclerAdapter.ViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull OrderRecyclerAdapter.ViewHolder holder, int position, @NonNull OrderItem model) {
                holder.foodName.setText(model.getFoodName());
                holder.foodNumberMoney.setText(model.getFoodMoney());
                holder.foodNumber.setText(model.getFoodNumber());
            }

            @NonNull
            @Override
            public OrderRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_order_list, parent, false);
                return new OrderRecyclerAdapter.ViewHolder(view);
            }
        };

        orderRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        orderRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mFirebaseAdapter.stopListening();
    }
}
