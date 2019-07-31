package com.example.hakju;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class OrderRecyclerAdapter extends RecyclerView.Adapter<OrderRecyclerAdapter.ViewHolder> {
    private final List<OrderItem> mOrderFoodList;
    public OrderRecyclerAdapter(List<OrderItem> orderFoodList){
        mOrderFoodList = orderFoodList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        OrderItem item = mOrderFoodList.get(position);
        holder.foodName.setText(item.getFoodName());
        holder.foodNumber.setText(item.getFoodNumber());

    }

    @Override
    public int getItemCount() {
        return mOrderFoodList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
//        ImageView circle;
        TextView foodName;
        TextView foodNumber;
//        TextView foodNumberGae;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
//            circle = itemView.findViewById(R.id.circleCircle);
            foodName = itemView.findViewById(R.id.orderFoodName);
            foodNumber = itemView.findViewById(R.id.orderFoodNumber);
//            foodNumberGae = itemView.findViewById(R.id.orderFoodGae);
        }
    }
}

