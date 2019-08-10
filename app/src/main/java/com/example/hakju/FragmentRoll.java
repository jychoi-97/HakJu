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

public class FragmentRoll extends Fragment {

    View v;
    private RecyclerView myrecyclerview;
    List<Food> lstFood;
    public FragmentRoll() {
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
        lstFood.add(new Food("돈까스\n1500원", "Categorie Food", "Description Food", R.drawable.donkatse));
        lstFood.add(new Food("오믈렛", "Categorie Food", "Description Food", R.drawable.omelett));
        lstFood.add(new Food("소떡소떡", "Categorie Food", "Description Food", R.drawable.soddeok));
        lstFood.add(new Food("참치마요덮밥", "Categorie Food", "Description Food", R.drawable.tunamayo));
        lstFood.add(new Food("소금구이덮밥", "Categorie Food", "Description Food", R.drawable.sogumgui));
        lstFood.add(new Food("라면", "Categorie Food", "Description Food", R.drawable.ramyeon));
    }
}

