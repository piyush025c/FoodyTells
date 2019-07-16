package com.piyush025.myapplication;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {


    ProgressBar progressBar;
    Timer timer;
    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_main);

        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        progressBar.setMax(55);

        final long period = 54;
        timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //this repeats every 100 ms
                if (i<70){
                    progressBar.setProgress(i);
                    i++;
                }else{
                    //closing the timer
                    timer.cancel();
                    Intent intent =new Intent(MainActivity.this,Login.class);
                    startActivity(intent);
                    // close this activity
                    finish();
                }
            }
        }, 0, period);





    }
}
