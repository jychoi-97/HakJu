package com.example.hakju;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class OrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        RecyclerView orderRecyclerView = findViewById(R.id.recycler1);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        orderRecyclerView.setLayoutManager(layoutManager);

        List<OrderItem> orderDataList = new ArrayList<>();
        for(int i=0; i<10; i++){
            orderDataList.add(new OrderItem(i+"음식", i));
        }
        OrderRecyclerAdapter adapter = new OrderRecyclerAdapter(orderDataList);
        orderRecyclerView.setAdapter(adapter);
    }
}
