package com.piyush025.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Crust extends AppCompatActivity {

    Button next1;
    String type,crust;
    RadioGroup rg1;
    RadioButton rb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crust);

        next1=(Button)findViewById(R.id.next1);
        rg1=(RadioGroup)findViewById(R.id.rg1);

        AlertDialog alertDialog = new AlertDialog.Builder(Crust.this).create();
        alertDialog.setTitle("Start!");
        alertDialog.setCancelable(false);
        alertDialog.setMessage("Complete 3 more steps to design your Pizza!");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();

        next1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                type=getIntent().getStringExtra("type");

                int selectedId=rg1.getCheckedRadioButtonId();
                rb=(RadioButton)findViewById(selectedId);

                if(selectedId==-1)
                {
                    Toast.makeText(Crust.this, "Please select Crust!", Toast.LENGTH_SHORT).show();
                }

                else {

                    //
                    Toast.makeText(Crust.this, rb.getText().toString()+" selected!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Crust.this, Vegetables.class);
                    intent.putExtra("type", type);
                    intent.putExtra("base",rb.getText().toString());
                    startActivity(intent);
                }
            }
        });
    }
}
