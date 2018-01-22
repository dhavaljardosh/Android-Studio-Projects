package com.example.dhaval.showandhideuielements;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    public void show(View view) {
        textView.setVisibility(view.VISIBLE);
    }

    public void hide(View view){
        textView.setVisibility(view.INVISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        Button showButton = (Button) findViewById(R.id.showButton);
        Button hideButton = (Button) findViewById(R.id.hideButton);

    }
}
