package com.piyush025.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;
import java.util.List;

public class ComboMeals extends Fragment {

    List<Meal> productList;

    RecyclerView recyclerView;



    public ComboMeals()
    { }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //
        View v=inflater.inflate(R.layout.combo_meals,container,false);

        recyclerView=(RecyclerView)v.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        productList = new ArrayList<>();

        productList.add(new Meal(1,"Kids Meal 1","Veggie Crunch Burger+French Fries+Sprite+Ice Cream Fudge ","₹369",R.drawable.kids_meal1));
        productList.add(new Meal(2,"Kids Meal 2","Farmhouse Pizza+Ice Tea","₹379",R.drawable.kids_meal2));
        productList.add(new Meal(3,"Kids Meal 3","Student Sandwich+ Apple Pudding+Mirinda","₹350",R.drawable.kids_meal3));
        productList.add(new Meal(4,"Party Pack","2 Deluxe Supreme Pizza+3 Ham Burgers+2 Grill Sandwich+3 Pepsi+2 Apple Pudding+ 2 Iceream fudge","₹1999",R.drawable.party_pack));
        productList.add(new Meal(5,"Super Saver Pack","Paneer Tikka Sandwich+Mexican Burger+Paradise Pizza+2 Ice Tea+1 Apple Pudding","₹999",R.drawable.super_saver));


        MealAdapter adapter=new MealAdapter(getContext(),productList);
        recyclerView.setAdapter(adapter);



        //
        return v;
    }
}
