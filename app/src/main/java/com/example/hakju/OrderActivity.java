package com.example.hakju;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class OrderActivity extends AppCompatActivity {

    public static class OrderViewHolder extends RecyclerView.ViewHolder{

        ImageView circle;
        TextView foodName;
        TextView foodNumber;
        TextView foodNumberGae;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            circle = itemView.findViewById(R.id.circleCircle);
            foodName = itemView.findViewById(R.id.orderFoodName);
            foodNumber = itemView.findViewById(R.id.orderFoodNumber);
            foodNumberGae = itemView.findViewById(R.id.orderFoodGae);
        }
    }

    private RecyclerView oOrderRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        oOrderRecyclerView = findViewById(R.id.recycler1);
    }
}
