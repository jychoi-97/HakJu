package com.example.hakju;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class FragmentFry extends Fragment {
//    View v;
//    public FragmentFry(){
//
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        v=inflater.inflate(R.layout.fryrice_fragment, container, false);
//        return v;
//    }

    View v;
    private RecyclerView myrecyclerview;
    List<Food> lstFood;
    public FragmentFry() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.rollnoodle_fragment, container, false);
        myrecyclerview = (RecyclerView) v.findViewById(R.id.call_recyclerview);
        RecyclerViewAdapter recyclerAdapter = new RecyclerViewAdapter(getContext(), lstFood);
        myrecyclerview.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        myrecyclerview.setAdapter(recyclerAdapter);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lstFood = new ArrayList<>();
        lstFood.add(new Food("오므라이스\n2800원", "Categorie Food", "Description Food", R.drawable.omelett));
        lstFood.add(new Food("등심돈까스\n3800원", "Categorie Food", "Description Food", R.drawable.donkatse));
        lstFood.add(new Food("소떡소떡\n3000원", "Categorie Food", "Description Food", R.drawable.soddeok));
        lstFood.add(new Food("양념감자\n오므라이스\n3800원", "Categorie Food", "Description Food", R.drawable.potato));
        lstFood.add(new Food("타코야끼\n2500원", "Categorie Food", "Description Food", R.drawable.takoyaki));
        lstFood.add(new Food("돈까스카레동\n4200원", "Categorie Food", "Description Food", R.drawable.donkatsecurry));
    }
}