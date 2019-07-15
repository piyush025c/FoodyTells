package com.piyush025.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.file.Files;
import java.util.List;
import java.util.Locale;

public class Address extends AppCompatActivity {

    TextView t1;
    EditText t2;
    Button confirm;

    Boolean flag;

    String base,veggieSelected,otherSelected,misc,cookingInstruction,type,addonSelected,sauceSelected,taste;
    int quantity,other,addonTotal;

    String desc;

    RadioGroup rg;
    RadioButton rb;

    double latitude,longitude;

    String location;

    String address,city,knownName,state,country,postalCode;

    private static final int REQUEST_CODE_PERMISSION = 2;
    String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;



    // GPSTracker class
    GPSTracker gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        confirm=(Button)findViewById(R.id.address);
        rg=(RadioGroup)findViewById(R.id.rg3);
        t1=(TextView) findViewById(R.id.t1);
        t2=(EditText)findViewById(R.id.t2);

        try {
            if (ActivityCompat.checkSelfPermission(this, mPermission)
                    != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this, new String[]{mPermission},
                        REQUEST_CODE_PERMISSION);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if(checkedId==R.id.rb1)
                {
                    t2.setVisibility(View.INVISIBLE);
                    t1.setVisibility(View.VISIBLE);

                    setLocation();
                }

                else
                {
                    t2.setVisibility(View.VISIBLE);
                    t1.setVisibility(View.INVISIBLE);
                }

            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                flag=true;

                int selected=rg.getCheckedRadioButtonId();

                if(selected==-1)
                {
                    Toast.makeText(Address.this, "Select appropriate option!", Toast.LENGTH_SHORT).show();
                }

                else {
                    rb = findViewById(selected);
                    type = getIntent().getStringExtra("type");



                    if(type.contentEquals("Pizza")||type.contentEquals("Burger")||type.contentEquals("Sandwich")) {
                        base = getIntent().getStringExtra("base");
                        veggieSelected = getIntent().getStringExtra("veggie");
                        otherSelected = getIntent().getStringExtra("other");
                        other = getIntent().getIntExtra("other number", 0);
                        misc = getIntent().getStringExtra("misc");
                        cookingInstruction = getIntent().getStringExtra("instruction");
                        quantity = getIntent().getIntExtra("quantity", 1);
                        addonSelected = getIntent().getStringExtra("addon");
                        addonTotal = getIntent().getIntExtra("addon total", 0);

                        if (type.contentEquals("Sandwich") || type.contentEquals("Burger")) {
                            sauceSelected = getIntent().getStringExtra("sauce");
                            taste = getIntent().getStringExtra("taste");
                        }

                    }

                    else
                    {
                        desc=getIntent().getStringExtra("desc");
                    }

                    if (selected == R.id.rb1) {
                        if (t1.getText().toString().contentEquals("")) {
                            Toast.makeText(Address.this, "Unable to use current location", Toast.LENGTH_SHORT).show();
                            flag = false;
                        } else {
                            location = t1.getText().toString();

                        }
                    } else if (selected == R.id.rb2) {

                        if (t2.getText().toString().contentEquals("")) {
                            Toast.makeText(Address.this, "Enter the manual location!", Toast.LENGTH_SHORT).show();
                            flag = false;
                        } else {
                            location = t2.getText().toString();
                        }
                    }

                    if (flag) {
                        Intent intent = new Intent(Address.this, ConfirmBill.class);

                        intent.putExtra("type", type);
                        intent.putExtra("address", location);

                        if(type.contentEquals("Pizza")||type.contentEquals("Burger")||type.contentEquals("Sandwich"))
                        {

                            intent.putExtra("base", base);
                            intent.putExtra("veggie", veggieSelected);
                            intent.putExtra("other", otherSelected);
                            intent.putExtra("other number", other);
                            intent.putExtra("misc", misc);
                            intent.putExtra("instruction", cookingInstruction);
                            intent.putExtra("quantity", quantity);
                            intent.putExtra("addon", addonSelected);
                            intent.putExtra("addon total", addonTotal);


                            if (type.contentEquals("Sandwich") || type.contentEquals("Burger")) {
                                intent.putExtra("sauce", sauceSelected);
                                intent.putExtra("taste", taste);
                            }
                        }

                        else
                        {
                            intent.putExtra("desc",desc);
                        }

                        startActivity(intent);
                    }
                }
            }
        });



    }

    void setLocation()
    {
        t1.setText("");
        gps = new GPSTracker(getApplicationContext());

        // check if GPS enabled
        if(gps.canGetLocation()){

            latitude = gps.getLatitude();
            longitude = gps.getLongitude();


        }else{
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }


        Geocoder geocoder;
        List<android.location.Address> addresses;
        geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());

        try {

            addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

            address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            city = addresses.get(0).getLocality();
            state = addresses.get(0).getAdminArea();
            country = addresses.get(0).getCountryName();
            postalCode = addresses.get(0).getPostalCode();
            knownName = addresses.get(0).getFeatureName();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        if(address==null)
        {
            t1.setText("");
        }
        else {

            t1.setText(address + "\n" + city + "\n" + postalCode + "\n" + state);
        }

    }
}
