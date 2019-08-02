package com.example.hakju;

public class MenuData {
    public String studentId;
    public String productName;
    public int productNum;
    public int total;


    public MenuData(){

    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductNum() {
        return productNum;
    }

    public void setProductNum(int productNum) {
        this.productNum = productNum;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public MenuData(String productName, int productNum, int total){
        this.productName = productName;
        this.productNum = productNum;
        this.total = total;
    }

}
