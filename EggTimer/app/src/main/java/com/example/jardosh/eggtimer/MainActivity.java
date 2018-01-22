package com.example.jardosh.eggtimer;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar timeSeekBar;
    TextView timerTextView;
    Button controllerButton;
    Boolean counterIsActive = false;
    CountDownTimer countDownTimer;

    public void updateTimer(int secondsLeft){
        int minutes = secondsLeft / 60;
        int seconds = secondsLeft - minutes * 60;

        String secondString = Integer.toString(seconds);


        if(seconds <= 9){
            secondString = "0" + seconds;
        }

        timerTextView.setText(Integer.toString(minutes) + ":"+secondString);
    }

    public void controlTimer(View view){

        if(!counterIsActive){
            counterIsActive = true;
            timeSeekBar.setEnabled(false);
            controllerButton.setText("STOP");

            countDownTimer = new CountDownTimer(timeSeekBar.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer((int) millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {
                    timerTextView.setText("0:00");
                    MediaPlayer mPlayer = MediaPlayer.create(getApplicationContext(),R.raw.alarm);
                    mPlayer.start();
                }

            }.start();
        }
        else{
            timerTextView.setText("0:30");
            timeSeekBar.setProgress(30);
            countDownTimer.cancel();
            controllerButton.setText("GO");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeSeekBar = (SeekBar) findViewById(R.id.timerSeekBar);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        controllerButton = (Button) findViewById(R.id.controllerButton);

        timeSeekBar.setMax(600);
        timeSeekBar.setProgress(30);

        timeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
