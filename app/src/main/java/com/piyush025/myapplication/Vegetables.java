package com.piyush025.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class Vegetables extends AppCompatActivity {

    Button next2;

    String type,base,veggieSelected,otherSelected;

    int other=0;
    CheckBox ch1,ch2,ch3,ch4,ch5,ch6,ch7,ch8,ch9,ch10;
    CheckBox ch11,ch12,ch13,ch14,ch15,ch16,ch17,ch18;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vegetables);

        next2=(Button)findViewById(R.id.next2);

        ch1=(CheckBox)findViewById(R.id.veggie1);
        ch2=(CheckBox)findViewById(R.id.veggie2);
        ch3=(CheckBox)findViewById(R.id.veggie3);
        ch4=(CheckBox)findViewById(R.id.veggie4);
        ch5=(CheckBox)findViewById(R.id.veggie5);
        ch6=(CheckBox)findViewById(R.id.veggie6);
        ch7=(CheckBox)findViewById(R.id.veggie7);
        ch8=(CheckBox)findViewById(R.id.veggie8);
        ch9=(CheckBox)findViewById(R.id.veggie9);
        ch10=(CheckBox)findViewById(R.id.veggie10);

        ch11=(CheckBox)findViewById(R.id.other1);
        ch12=(CheckBox)findViewById(R.id.other2);
        ch13=(CheckBox)findViewById(R.id.other3);
        ch14=(CheckBox)findViewById(R.id.other4);
        ch15=(CheckBox)findViewById(R.id.other5);
        ch16=(CheckBox)findViewById(R.id.other6);
        ch17=(CheckBox)findViewById(R.id.other7);
        ch18=(CheckBox)findViewById(R.id.other8);


        next2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                type=getIntent().getStringExtra("type");
                base=getIntent().getStringExtra("base");

                selectedVeggie();
                selectedOther();

                if(veggieSelected.contentEquals(""))
                    veggieSelected="None";

                if(otherSelected.contentEquals("")) {
                    otherSelected = "None";
                    other=0;
                }

                if(type.contentEquals("Pizza")) {
                    Intent intent=new Intent(Vegetables.this,Miscellaneous1.class);
                    intent.putExtra("type", type);
                    intent.putExtra("base",base);
                    intent.putExtra("veggie",veggieSelected);
                    intent.putExtra("other",otherSelected);
                    intent.putExtra("other number",other);
                    startActivity(intent);

                }
                else if(type.contentEquals("Sandwich")||type.contentEquals("Burger")) {
                    Intent intent = new Intent(Vegetables.this, Sauces.class);
                    intent.putExtra("type", type);
                    intent.putExtra("base",base);
                    intent.putExtra("veggie",veggieSelected);
                    intent.putExtra("other",otherSelected);
                    intent.putExtra("other number",other);
                    startActivity(intent);
                }



            }
        });
    }

    void selectedVeggie()
    {
        veggieSelected="";

        if(ch1.isChecked())
        {
         veggieSelected=veggieSelected+" "+ch1.getText().toString();
        }

        if(ch2.isChecked())
        {
            veggieSelected=veggieSelected+" "+ch2.getText().toString();
        }

        if(ch3.isChecked())
        {
            veggieSelected=veggieSelected+" "+ch3.getText().toString();
        }

        if(ch4.isChecked())
        {
            veggieSelected=veggieSelected+" "+ch4.getText().toString();
        }

        if(ch5.isChecked())
        {
            veggieSelected=veggieSelected+" "+ch5.getText().toString();
        }

        if(ch6.isChecked())
        {
            veggieSelected=veggieSelected+" "+ch6.getText().toString();
        }

        if(ch7.isChecked())
        {
            veggieSelected=veggieSelected+" "+ch7.getText().toString();
        }

        if(ch8.isChecked())
        {
            veggieSelected=veggieSelected+" "+ch8.getText().toString();
        }

        if(ch9.isChecked())
        {
            veggieSelected=veggieSelected+" "+ch9.getText().toString();
        }

        if(ch10.isChecked())
        {
            veggieSelected=veggieSelected+" "+ch10.getText().toString();
        }
    }

    void selectedOther()
    {
        other=0;
        otherSelected="";

        if(ch11.isChecked())
        {
            other++;

            otherSelected=otherSelected+" "+ch11.getText().toString();
        }

        if(ch12.isChecked())
        {
            other++;

            otherSelected=otherSelected+" "+ch12.getText().toString();
        }

        if(ch13.isChecked())
        {
            other++;

            otherSelected=otherSelected+" "+ch13.getText().toString();
        }

        if(ch14.isChecked())
        {
            other++;

            otherSelected=otherSelected+" "+ch14.getText().toString();
        }

        if(ch15.isChecked())
        {
            other++;

            otherSelected=otherSelected+" "+ch15.getText().toString();
        }

        if(ch16.isChecked())
        {
            other++;

            otherSelected=otherSelected+" "+ch16.getText().toString();
        }

        if(ch17.isChecked())
        {
            other++;

            otherSelected=otherSelected+" "+ch17.getText().toString();
        }

        if(ch18.isChecked())
        {
            other++;

            otherSelected=otherSelected+" "+ch18.getText().toString();
        }
    }
}
