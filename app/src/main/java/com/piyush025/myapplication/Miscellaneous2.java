package com.piyush025.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Miscellaneous2 extends AppCompatActivity {

    Button submit2;
    String base,veggieSelected,otherSelected,misc,cookingInstruction,type,sauceSelected,taste;
    int quantity,other;

    CheckBox ch1,ch2,ch3,ch4,ch5,ch6;

    EditText instruction;

    Spinner spinner;

    String[] options={"1","2","3","4","5"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_miscellaneous2);

        submit2=(Button)findViewById(R.id.submit2);
        instruction=(EditText)findViewById(R.id.cookInstruction2);

        spinner=(Spinner)findViewById(R.id.qty2);

        ArrayAdapter adapter=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_item,options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        ch1=(CheckBox)findViewById(R.id.miscb1);
        ch2=(CheckBox)findViewById(R.id.miscb2);
        ch3=(CheckBox)findViewById(R.id.miscb3);
        ch4=(CheckBox)findViewById(R.id.miscb4);
        ch5=(CheckBox)findViewById(R.id.miscb5);
        ch6=(CheckBox)findViewById(R.id.miscb6);

        submit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                type=getIntent().getStringExtra("type");
                base=getIntent().getStringExtra("base");
                veggieSelected=getIntent().getStringExtra("veggie");
                otherSelected=getIntent().getStringExtra("other");
                other=getIntent().getIntExtra("other number",0);
                sauceSelected=getIntent().getStringExtra("sauce");
                taste=getIntent().getStringExtra("taste");

                miscellaneous();

                if(cookingInstruction.contentEquals(""))
                    cookingInstruction="None";

                if(misc.contentEquals(""))
                    misc="None";

                Intent intent=new Intent(Miscellaneous2.this,Addon.class);
                intent.putExtra("type", type);
                intent.putExtra("base",base);
                intent.putExtra("veggie",veggieSelected);
                intent.putExtra("other",otherSelected);
                intent.putExtra("other number",other);
                intent.putExtra("sauce",sauceSelected);
                intent.putExtra("misc",misc);
                intent.putExtra("instruction",cookingInstruction);
                intent.putExtra("quantity",quantity);
                intent.putExtra("taste",taste);

                startActivity(intent);

            }
        });
    }

    void miscellaneous()
    {
        misc="";
        cookingInstruction="";

        if(ch1.isChecked())
        {
            misc=misc+" "+ch1.getText().toString();
        }

        if(ch2.isChecked())
        {
            misc=misc+" "+ch2.getText().toString();
        }


        if(ch3.isChecked())
        {
            misc=misc+" "+ch3.getText().toString();
        }


        if(ch4.isChecked())
        {
            misc=misc+" "+ch4.getText().toString();
        }


        if(ch5.isChecked())
        {
            misc=misc+" "+ch5.getText().toString();
        }


        if(ch6.isChecked())
        {
            misc=misc+" "+ch6.getText().toString();
        }

        quantity=Integer.parseInt(spinner.getSelectedItem().toString());
        cookingInstruction=instruction.getText().toString();


    }
}
