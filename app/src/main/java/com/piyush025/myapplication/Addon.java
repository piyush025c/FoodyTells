package com.piyush025.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

public class Addon extends AppCompatActivity {

    Button done;

    String base,veggieSelected,otherSelected,misc,cookingInstruction,type,addonSelected,sauceSelected,taste;
    int quantity,other,addonTotal;

    CheckBox ch1,ch2,ch3,ch4,ch5,ch6,ch7,ch8,ch9;


    Spinner sp1,sp2,sp3,sp4,sp5,sp6,sp7,sp8,sp9;

    String[] options={"1","2","3","4","5"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addon);

        done=(Button)findViewById(R.id.done);

        ch1=(CheckBox)findViewById(R.id.softdrink1);
        ch2=(CheckBox)findViewById(R.id.softdrink2);
        ch3=(CheckBox)findViewById(R.id.softdrink3);
        ch4=(CheckBox)findViewById(R.id.softdrink4);
        ch5=(CheckBox)findViewById(R.id.softdrink5);

        ch6=(CheckBox)findViewById(R.id.specialItem1);
        ch7=(CheckBox)findViewById(R.id.specialItem2);
        ch8=(CheckBox)findViewById(R.id.specialItem3);
        ch9=(CheckBox)findViewById(R.id.specialItem4);

        sp1=(Spinner)findViewById(R.id.sd1);
        sp2=(Spinner)findViewById(R.id.sd2);
        sp3=(Spinner)findViewById(R.id.sd3);
        sp4=(Spinner)findViewById(R.id.sd4);
        sp5=(Spinner)findViewById(R.id.sd5);

        sp6=(Spinner)findViewById(R.id.si1);
        sp7=(Spinner)findViewById(R.id.si2);
        sp8=(Spinner)findViewById(R.id.si3);
        sp9=(Spinner)findViewById(R.id.si4);

        ArrayAdapter adapter=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_item,options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sp1.setAdapter(adapter);
        sp2.setAdapter(adapter);
        sp3.setAdapter(adapter);
        sp4.setAdapter(adapter);
        sp5.setAdapter(adapter);
        sp6.setAdapter(adapter);
        sp7.setAdapter(adapter);
        sp8.setAdapter(adapter);
        sp9.setAdapter(adapter);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                type=getIntent().getStringExtra("type");
                base=getIntent().getStringExtra("base");
                veggieSelected=getIntent().getStringExtra("veggie");
                otherSelected=getIntent().getStringExtra("other");
                other=getIntent().getIntExtra("other number",0);
                misc=getIntent().getStringExtra("misc");
                cookingInstruction=getIntent().getStringExtra("instruction");
                quantity=getIntent().getIntExtra("quantity",1);

                if(type.contentEquals("Sandwich")||type.contentEquals("Burger"))
                {
                    sauceSelected=getIntent().getStringExtra("sauce");
                    taste=getIntent().getStringExtra("taste");
                }

                addonBill();

               if(addonSelected.contentEquals(""))
                {
                    addonSelected="None";
                    addonTotal=0;
                }

                Intent intent=new Intent(Addon.this,Address.class);
                intent.putExtra("type", type);
                intent.putExtra("base",base);
                intent.putExtra("veggie",veggieSelected);
                intent.putExtra("other",otherSelected);
                intent.putExtra("other number",other);
                intent.putExtra("misc",misc);
                intent.putExtra("instruction",cookingInstruction);
                intent.putExtra("quantity",quantity);
                intent.putExtra("addon",addonSelected);
                intent.putExtra("addon total",addonTotal);

                if(type.contentEquals("Sandwich")||type.contentEquals("Burger"))
                {
                    intent.putExtra("sauce",sauceSelected);
                    intent.putExtra("taste",taste);
                }
                startActivity(intent);
            }
        });
    }

    void addonBill()
    {
        addonSelected="";
        addonTotal=0;

        if(ch1.isChecked())
        {
            addonSelected=addonSelected+" "+ch1.getText().toString()+" "+sp1.getSelectedItem().toString()+"\n";
            addonTotal+=60*Integer.parseInt(sp1.getSelectedItem().toString());
        }

        if(ch2.isChecked())
        {
            addonSelected=addonSelected+" "+ch2.getText().toString()+" "+sp2.getSelectedItem().toString()+"\n";
            addonTotal+=60*Integer.parseInt(sp2.getSelectedItem().toString());
        }

        if(ch3.isChecked())
        {
            addonSelected=addonSelected+" "+ch3.getText().toString()+" "+sp3.getSelectedItem().toString()+"\n";
            addonTotal+=60*Integer.parseInt(sp3.getSelectedItem().toString());
        }

        if(ch4.isChecked())
        {
            addonSelected=addonSelected+" "+ch4.getText().toString()+" "+sp4.getSelectedItem().toString()+"\n";
            addonTotal+=60*Integer.parseInt(sp4.getSelectedItem().toString());
        }

        if(ch5.isChecked())
        {
            addonSelected=addonSelected+" "+ch5.getText().toString()+" "+sp5.getSelectedItem().toString()+"\n";
            addonTotal+=40*Integer.parseInt(sp5.getSelectedItem().toString());
        }

        if(ch6.isChecked())
        {
            addonSelected=addonSelected+" "+ch6.getText().toString()+" "+sp6.getSelectedItem().toString()+"\n";
            addonTotal+=90*Integer.parseInt(sp6.getSelectedItem().toString());
        }

        if(ch7.isChecked())
        {
            addonSelected=addonSelected+" "+ch7.getText().toString()+" "+sp7.getSelectedItem().toString()+"\n";
            addonTotal+=70*Integer.parseInt(sp7.getSelectedItem().toString());
        }

        if(ch8.isChecked())
        {
            addonSelected=addonSelected+" "+ch8.getText().toString()+" "+sp8.getSelectedItem().toString()+"\n";
            addonTotal+=120*Integer.parseInt(sp8.getSelectedItem().toString());
        }

        if(ch9.isChecked())
        {
            addonSelected=addonSelected+" "+ch9.getText().toString()+" "+sp9.getSelectedItem().toString()+"\n";
            addonTotal+=100*Integer.parseInt(sp9.getSelectedItem().toString());
        }
    }
}
