package com.example.hakju;

import android.widget.ImageView;
import android.widget.TextView;

public class OrderItem {
    private String foodName;
    private int foodNumber;
    private boolean isSelected;
    private int foodMoney;

    public OrderItem(String foodName, int foodNumber, int footMoney, boolean isSelected) {
        this.foodName = foodName;
        this.foodNumber = foodNumber;
        this.foodMoney = foodMoney;
        this.isSelected = isSelected;
    }

    public String getFoodName() {
        return foodName;
    }

    public int getFoodNumber() {
        return foodNumber;
    }
    public int getFoodMoney() {
        return foodMoney;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }


    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void setFoodMoney(int foodMoney) {
        this.foodNumber = foodMoney;
    }
    public void setFoodNumber(int foodNumber) {
        this.foodNumber = foodNumber;
    }


}
