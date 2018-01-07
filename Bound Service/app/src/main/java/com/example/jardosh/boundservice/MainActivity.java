package com.example.jardosh.boundservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.jardosh.boundservice.MyService.MyLocalBinder;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    MyService dhavalService;
    boolean isBound = false;

    public void showTime(View view){
        String currentTime = dhavalService.getCurrentTime();
        TextView dhavalText = (TextView) findViewById(R.id.dhavalText);
        dhavalText.setText(currentTime);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent i = new Intent(this, MyService.class);
        bindService(i, dhavalConnection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection dhavalConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyLocalBinder binder = (MyLocalBinder) service;
            dhavalService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };
}

