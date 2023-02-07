package com.example.task5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView hourText,minText,secText;
    private Button startButton,holdButton,stopButton;
    private  int  seconds = 0 ;

    private Boolean isRunning=Boolean.FALSE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialisation of views
        init();

        //for stop watch functionality
        stopWatchFunctionality();

        //listeners for buttons
        clickListeners();
    }

    public void init(){
        hourText = findViewById(R.id.hour);
        minText = findViewById(R.id.min);
        secText = findViewById(R.id.sec);
        startButton = findViewById(R.id.start);
        holdButton = findViewById(R.id.hold);
        stopButton = findViewById(R.id.stop);
    }

    public void clickListeners(){
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isRunning=Boolean.TRUE;

            }
        });
//
        holdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isRunning = Boolean.FALSE;

            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isRunning=Boolean.FALSE;
                seconds = 0;
            }
        });
    }

    public void stopWatchFunctionality() {
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {

                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int second = seconds % 60;

                hourText.setText(String.format(Locale.getDefault(), "%02d", hours));
                minText.setText(String.format(Locale.getDefault(),"%02d",minutes));
                secText.setText(String.format(Locale.getDefault(),"%02d",second));

                if (isRunning) {
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });

    }

}