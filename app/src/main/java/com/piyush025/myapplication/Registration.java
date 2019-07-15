package com.piyush025.myapplication;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registration extends AppCompatActivity {

    Button register,birth;
    EditText regEmail,regPassword,regMobile,regName,regConfirm;
    TextView goToLogin,tc;
    TextView date;
    int mYear,mMonth,mDay;

    CheckBox ch;

    private FirebaseAuth firebaseAuth;

    ProgressDialog progressDialog;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_registration);

        register=(Button)findViewById(R.id.register);
        regEmail=(EditText)findViewById(R.id.regEmail);
        regPassword=(EditText)findViewById(R.id.regPassword);
        goToLogin=(TextView)findViewById(R.id.gotoLogin);
        regMobile=(EditText) findViewById(R.id.mob);
        regName=(EditText)findViewById(R.id.name);
        regConfirm=(EditText)findViewById(R.id.regConfirmPassword);
        date=(TextView)findViewById(R.id.dob);
        birth=(Button)findViewById(R.id.dobbtn);
        ch=(CheckBox)findViewById(R.id.tc1);
        tc=(TextView)findViewById(R.id.tc2) ;


        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference("User");

        progressDialog = new ProgressDialog(this);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                registerUser();
            }
        });

        goToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Registration.this,Login.class);
                startActivity(intent);

            }
        });

        birth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(Registration.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }

        });

        tc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog alertDialog = new AlertDialog.Builder(Registration.this).create();
                alertDialog.setTitle("Terms and Conditions");
                alertDialog.setCancelable(false);
                alertDialog.setMessage("This app is meant only to be an educational purpose project. Hence it does not deliver the product. However, this is a fully functional app which helps to develop virtual connection between the chief and the client. This app is meant only for education purpose and is not business oriented.");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();

            }
        });

    }

    void registerUser()
    {
        String email = regEmail.getText().toString().trim();
        String password  = regPassword.getText().toString().trim();

        //checking if email and passwords are empty
        if(!validateEntry())
            return;


        //if the email and password are not empty
        //displaying a progress dialog

        progressDialog.setMessage("Registering Please Wait...");
        progressDialog.show();

        //creating a new user
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if(task.isSuccessful()){
                            //display some message here

                            try {
                                saveUserInfo();
                                Thread.sleep(1000);
                                Toast.makeText(Registration.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Registration.this, Login.class);
                                startActivity(intent);
                            }
                            catch (Exception e)
                            {
                                Toast.makeText(Registration.this, "Internal Error Occured", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            //display some message here
                            Toast.makeText(getApplicationContext(),"Registration Error " +task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });





    }

    public boolean validateEntry()
    {
        String name=regName.getText().toString().trim();
        String mob=regMobile.getText().toString().trim();
        String email=regEmail.getText().toString().trim();
        String password=regPassword.getText().toString().trim();
        String confirm=regConfirm.getText().toString().trim();
        String dob=date.getText().toString().trim();

        if(validateName(name)&&validateEmail(email)&&validateMobile(mob)&&validatePassword(password))
        {
            if(password.contentEquals(confirm))
            {
                if(dob.isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Enter DOB.", Toast.LENGTH_SHORT).show();
                    return false;
                }

                else {
                    if(validateDate(dob))
                    {
                        if(checkCheckbox())
                        return true;

                        else
                        {
                            Toast.makeText(this, "Agree to Terms and Conditions!", Toast.LENGTH_SHORT).show();
                            return false;
                        }
                    }

                    else
                    {
                        Toast.makeText(getApplicationContext(), "Enter a valid date", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                }
            }

            else{
                Toast.makeText(getApplicationContext(), "Password fields don't match.", Toast.LENGTH_SHORT).show();
                return false;
            }

        }

        else
        {
            if(!validatePassword(password))
            {
                Toast.makeText(getApplicationContext(), "Invalid Password type.", Toast.LENGTH_SHORT).show();
                return false;
            }

            if(!validateEmail(email))
            {
                Toast.makeText(getApplicationContext(), "Invalid Email type.", Toast.LENGTH_SHORT).show();
                return false;
            }

            if(!validateMobile(mob))
            {
                Toast.makeText(getApplicationContext(), "Invalid Mobile Number type.", Toast.LENGTH_SHORT).show();
                return false;
            }

            if(!validateName(name))
            {
                Toast.makeText(getApplicationContext(), "Invalid Name type.", Toast.LENGTH_SHORT).show();
                return false;
            }

            return false;

        }

    }

    public boolean validateName(String name)
    {
        String regx = "^[\\p{L} .'-]+$";
        Pattern pattern = Pattern.compile(regx,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(name);
        return matcher.find();
    }

    public boolean validateMobile(String mob)
    {
        String regx = "^[6789][0-9]{9}$";
        Pattern pattern = Pattern.compile(regx,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(mob);
        return matcher.find();
    }

    public boolean validateEmail(String emailId)
    {
        if (!TextUtils.isEmpty(emailId)) {
            return Patterns.EMAIL_ADDRESS.matcher(emailId).matches();
        }

        return false;

    }

    public boolean validatePassword(String password)
    {
        String regx = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
        Pattern pattern = Pattern.compile(regx,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }

    public boolean validateDate(String date)
    {

        Date checkDate=null;
        try
        {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            checkDate = sdf.parse(date);
        }catch (Exception ex)
        {
            // enteredDate will be null if date="287686";
        }
        Date currentDate = new Date();
        if(checkDate.after(currentDate)){
            return  false;
        }else
            return true;
    }

    public boolean checkCheckbox()
    {
        if(ch.isChecked())
            return true;

        else return false;
    }

    void saveUserInfo()
    {
        String name=regName.getText().toString().trim();
        String mob=regMobile.getText().toString().trim();
        String email=regEmail.getText().toString().trim();
        String password=regPassword.getText().toString().trim();
        String dob=date.getText().toString().trim();

        UserInformation info=new UserInformation(name,mob,email,password,dob);

        FirebaseUser user=firebaseAuth.getCurrentUser();

        databaseReference.child(user.getUid()).setValue(info);


    }
}

