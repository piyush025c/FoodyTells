package com.piyush025.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class Sauces extends AppCompatActivity {

    Button next4;
    String type,base,veggieSelected,otherSelected,sauceSelected,taste;
    int other;

    CheckBox ch1,ch2,ch3,ch4,ch5,ch6,ch7,ch8,ch9,ch10;
    CheckBox ch11,ch12,ch13,ch14;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sauces);

        next4=(Button)findViewById(R.id.next4);

        ch1=(CheckBox)findViewById(R.id.sauce1);
        ch2=(CheckBox)findViewById(R.id.sauce2);
        ch3=(CheckBox)findViewById(R.id.sauce3);
        ch4=(CheckBox)findViewById(R.id.sauce4);
        ch5=(CheckBox)findViewById(R.id.sauce5);
        ch6=(CheckBox)findViewById(R.id.sauce6);
        ch7=(CheckBox)findViewById(R.id.sauce7);
        ch8=(CheckBox)findViewById(R.id.sauce8);
        ch9=(CheckBox)findViewById(R.id.sauce9);
        ch10=(CheckBox)findViewById(R.id.sauce10);

        ch11=(CheckBox)findViewById(R.id.taste1);
        ch12=(CheckBox)findViewById(R.id.taste2);
        ch13=(CheckBox)findViewById(R.id.taste3);
        ch14=(CheckBox)findViewById(R.id.taste4);

        next4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                type=getIntent().getStringExtra("type");
                base=getIntent().getStringExtra("base");
                veggieSelected=getIntent().getStringExtra("veggie");
                otherSelected=getIntent().getStringExtra("other");
                other=getIntent().getIntExtra("other number",0);

                selectSauce();
                selectTaste();

                if(sauceSelected.contentEquals(""))
                    sauceSelected="None";

                if(taste.contentEquals(""))
                    taste="None";

                if(type.contentEquals("Burger")) {
                    Intent intent=new Intent(Sauces.this,Miscellaneous2.class);
                    intent.putExtra("type", type);
                    intent.putExtra("base",base);
                    intent.putExtra("veggie",veggieSelected);
                    intent.putExtra("other",otherSelected);
                    intent.putExtra("other number",other);
                    intent.putExtra("sauce",sauceSelected);
                    intent.putExtra("taste",taste);

                    Toast.makeText(Sauces.this, sauceSelected+"\n"+taste, Toast.LENGTH_SHORT).show();
                    startActivity(intent);

                }
                else if(type.contentEquals("Sandwich")) {
                    Intent intent = new Intent(Sauces.this, Miscellaneous3.class);
                    intent.putExtra("type", type);
                    intent.putExtra("base",base);
                    intent.putExtra("veggie",veggieSelected);
                    intent.putExtra("other",otherSelected);
                    intent.putExtra("other number",other);
                    intent.putExtra("sauce",sauceSelected);
                    intent.putExtra("taste",taste);

                    startActivity(intent);
                }
            }
        });
    }

    void selectSauce()
    {
        sauceSelected="";

        if(ch1.isChecked())
        {
            sauceSelected=sauceSelected+" "+ch1.getText().toString();
        }

        if(ch2.isChecked())
        {
            sauceSelected=sauceSelected+" "+ch2.getText().toString();
        }

        if(ch3.isChecked())
        {
            sauceSelected=sauceSelected+" "+ch3.getText().toString();
        }

        if(ch4.isChecked())
        {
            sauceSelected=sauceSelected+" "+ch4.getText().toString();
        }

        if(ch5.isChecked())
        {
            sauceSelected=sauceSelected+" "+ch5.getText().toString();
        }

        if(ch6.isChecked())
        {
            sauceSelected=sauceSelected+" "+ch6.getText().toString();
        }

        if(ch7.isChecked())
        {
            sauceSelected=sauceSelected+" "+ch7.getText().toString();
        }

        if(ch8.isChecked())
        {
            sauceSelected=sauceSelected+" "+ch8.getText().toString();
        }

        if(ch9.isChecked())
        {
            sauceSelected=sauceSelected+" "+ch9.getText().toString();
        }

        if(ch10.isChecked())
        {
            sauceSelected=sauceSelected+" "+ch10.getText().toString();
        }


    }

    void selectTaste()
    {
        taste="";

        if(ch11.isChecked())
        {
            taste=taste+" "+ch11.getText().toString();
        }

        if(ch12.isChecked())
        {
            taste=taste+" "+ch12.getText().toString();
        }

        if(ch13.isChecked())
        {
            taste=taste+" "+ch13.getText().toString();
        }

        if(ch14.isChecked())
        {
            taste=taste+" "+ch14.getText().toString();
        }


    }
}
