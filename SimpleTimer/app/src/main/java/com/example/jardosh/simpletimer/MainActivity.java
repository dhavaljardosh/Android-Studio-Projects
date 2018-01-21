package com.example.jardosh.simpletimer;

import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new CountDownTimer(10000,100){
            @Override
            public void onTick(long millisUntilFinished) {
                //Countdown is counting down
                Log.i("Seconds Left",String.valueOf(millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                //Countdown is Finished
                Log.i("Done!","Pati Gyu");
            }
        }.start();

//        final Handler handler = new Handler();
//        Runnable run = new Runnable() {
//            @Override
//            public void run() {
//                //Insert Code to be run every second
//                Log.i("runnable has run","Second Must have passd");
//                handler.postDelayed(this,1000);
//            }
//        };
//        handler.post(run);
    }
}
