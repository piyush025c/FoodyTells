package com.piyush025.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ConfirmBill extends AppCompatActivity {

    String base,veggieSelected,otherSelected,misc,cookingInstruction,type,addonSelected,sauceSelected,taste,location;
    int quantity,other,addonTotal;

    String desc;

    String summary;
    int total;

    Button confirm,cancel;

    DatabaseReference databaseReference;

    TextView t1,t2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_bill);

        t1=(TextView)findViewById(R.id.summary);
        t2=(TextView)findViewById(R.id.total);
        confirm=(Button)findViewById(R.id.confirmOrder);
        cancel=(Button)findViewById(R.id.cancel);

        t1.setMovementMethod(new ScrollingMovementMethod());

        databaseReference= FirebaseDatabase.getInstance().getReference("order");

        generate();

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(ConfirmBill.this,Home.class);
                startActivity(intent);
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String uniqueId=databaseReference.push().getKey();

                Order order=new Order(uniqueId,t1.getText().toString(),t2.getText().toString());
                databaseReference.child(uniqueId).setValue(order);

                Intent intent=new Intent(ConfirmBill.this,Placed.class);
                intent.putExtra("reference",uniqueId);
                startActivity(intent);
            }
        });



    }

    void generate()
    {

        summary="";
        total=0;

        type = getIntent().getStringExtra("type");
        location=getIntent().getStringExtra("address");
        desc=getIntent().getStringExtra("desc");

        if(!(type.contentEquals("Pizza")||type.contentEquals("Burger")||type.contentEquals("Sandwich")))
        {

            summary+="Type:"+type;
            summary+="\n\nDescription:\n"+desc+"\n\n";
            summary+="Location:\n"+location;

            switch (type)
            {
                case "Kids Meal 1": total=369; break;
                case "Kids Meal 2": total=379; break;
                case "Kids Meal 3": total=350; break;
                case "Party Pack": total=1999; break;
                case "Super Saver Pack": total=999; break;

                default:total=0;

            }
        }

        else {


            base = getIntent().getStringExtra("base");
            veggieSelected = getIntent().getStringExtra("veggie");
            otherSelected = getIntent().getStringExtra("other");
            other = getIntent().getIntExtra("other number", 0);
            misc = getIntent().getStringExtra("misc");
            cookingInstruction = getIntent().getStringExtra("instruction");
            quantity = getIntent().getIntExtra("quantity", 1);
            addonSelected = getIntent().getStringExtra("addon");
            addonTotal = getIntent().getIntExtra("addon total", 0);


            summary += "Type:" + type + "\n" + "Base:" + base + "\n\n" + "Vegetables Selected:\n" + veggieSelected + "\n\n";

            summary += "Others:\n" + otherSelected + "\n\n";

            if (type.contentEquals("Sandwich") || type.contentEquals("Burger")) {
                sauceSelected = getIntent().getStringExtra("sauce");
                summary = summary + "Sauce Selected:\n" + sauceSelected + "\n";

                taste = getIntent().getStringExtra("taste");
                summary = summary + "Taste:\n" + taste + "\n";
            }

            summary += "Miscellaneous choice\n" + misc + "\n\n";
            summary += "Instruction:" + cookingInstruction + "\n";

            summary += "\nQuantity:" + String.valueOf(quantity) + "\n";

            summary += "\nAddons:\n" + addonSelected + "\n\n";

            summary += "Location:\n" + location;


            if (type.contentEquals("Pizza")) {
                total = ((349 + (25 * other)) * quantity) + addonTotal;
            } else if (type.contentEquals("Burger")) {
                total = ((249 + (25 * other)) * quantity) + addonTotal;
            } else if (type.contentEquals("Sandwich")) {
                total = ((199 + (25 * other)) * quantity) + addonTotal;
            }

        }
        t1.setText(summary);

        t2.setText("₹"+String.valueOf(total));




    }
}
