//package com.example.hakju;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.TextView;
//
//import java.util.List;
//
//
//public class ListAdapter extends BaseAdapter{
//    @Override
//    public void notifyDataSetChanged() {
//        super.notifyDataSetChanged();
//    }
//
//    List<MenuData> menuList;
//    Context context;
//    LayoutInflater inflater;
//
//    public ListAdapter(List<MenuData> menuList, Context context){
//        this.menuList = menuList;
//        this.context = context;
//        this.inflater = (LayoutInflater) context. getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//    }
//
//    @Override
//    public int getCount() {
//        return menuList.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return menuList.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(int i, View convertView, ViewGroup viewGroup) {
//        if(convertView == null){
//            convertView = inflater.inflate(R.layout.item_order_list, null);
//        }
//
//        TextView foodName = (TextView)convertView.findViewById(R.id.orderFoodName);
//        TextView foodNumber = (TextView)convertView.findViewById(R.id.orderFoodNumber);
//        TextView foodMoney = (TextView)convertView.findViewById(R.id.orderFoodMoney);
//
//        MenuData menuData = menuList.get(i);
//        foodName.setText(menuData.productName);
//        foodNumber.setText(menuData.productNum);
//        foodMoney.setText(menuData.total);
//
//        return convertView;
//    }
//}
