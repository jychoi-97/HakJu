package com.example.hakju;

public class MenuData {
    public String studentId;
    public String productName;
    public int productNum;
    public int total;

    public MenuData(){

    }

    public MenuData(String productName, int productNum, int total){
        this.productName = productName;
        this.productNum = productNum;
        this.total = total;
    }

}
