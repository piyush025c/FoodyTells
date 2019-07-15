package com.piyush025.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class CustomMeals extends Fragment {

    ListView list;

    String[] maintitle ={"Make Pizza","Make Burger", "Make Sandwich"};

    String[] subtitle ={" ₹ 349/-"," ₹ 249/-", " ₹ 199/-"};

    Integer[] imgid={R.drawable.pizza, R.drawable.burger,R.drawable.sandwich};



    public CustomMeals()
    { }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

       final View v1=inflater.inflate(R.layout.custom_meals,container,false);
        MyListAdapter1 adapter=new MyListAdapter1(getActivity(), maintitle, subtitle,imgid);
        list=(ListView)v1.findViewById(R.id.list);
        list.setAdapter(adapter);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(position == 0) {
                    Intent intent=new Intent(getContext(), Crust.class);
                    intent.putExtra("type","Pizza");
                    startActivity(intent);
                }

                else if(position == 1) {
                    //code specific to 2nd list item
                    Intent intent=new Intent(getContext(), Bread.class);
                    intent.putExtra("type","Burger");
                    startActivity(intent);
                }

                else if(position == 2) {

                    Intent intent=new Intent(getContext(), Bread.class);
                    intent.putExtra("type","Sandwich");
                    startActivity(intent);
                }

            }
        });






        return v1;
    }
}
