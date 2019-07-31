package com.example.hakju;

import android.widget.ImageView;
import android.widget.TextView;

public class OrderItem {
    private String foodName;
    private int foodNumber;

    public OrderItem(String foodName, int foodNumber) {
        this.foodName = foodName;
        this.foodNumber = foodNumber;
    }

    public String getFoodName() {
        return foodName;
    }

    public int getFoodNumber() {
        return foodNumber;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void setFoodNumber(int foodNumber) {
        this.foodNumber = foodNumber;
    }


}
