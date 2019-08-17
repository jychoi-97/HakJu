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
        lstFood.add(new Food("야채김밥\n1500원", "Categorie Food", "Description Food", R.drawable.kimbap));
        lstFood.add(new Food("라면\n2300원", "Categorie Food", "Description Food", R.drawable.ramyeon));
        lstFood.add(new Food("불고기\n4800원", "Categorie Food", "Description Food", R.drawable.bulgogi));
        lstFood.add(new Food("물냉면\n2800원", "Categorie Food", "Description Food", R.drawable.mulnang));
        lstFood.add(new Food("돈육순두부찌개\n4000원", "Categorie Food", "Description Food", R.drawable.softtofu));
        lstFood.add(new Food("돈까스쟁반국수\n4300원", "Categorie Food", "Description Food", R.drawable.donjang));


    }
}

