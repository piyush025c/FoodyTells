package com.piyush025.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Placed extends AppCompatActivity {

    Button submit4,back;
    TextView t1;

    String reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placed);

        t1=(TextView)findViewById(R.id.reference);
        submit4=(Button)findViewById(R.id.submit4);
        back=(Button)findViewById(R.id.back);

        reference=getIntent().getStringExtra("reference");

        t1.setText("Order Reference ID:"+reference);

        submit4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(Placed.this, "Thank you!", Toast.LENGTH_SHORT).show();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Placed.this,Home.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        Toast.makeText(Placed.this, "Can't go back once the order is placed!", Toast.LENGTH_SHORT).show();
    }
}
