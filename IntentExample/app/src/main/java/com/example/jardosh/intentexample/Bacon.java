package com.example.jardosh.intentexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;

public class Bacon extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bacon);
        Bundle applesMessage = getIntent().getExtras();
        if(applesMessage==null){
            return;
        }
        String appleMessage = applesMessage.getString("applesMessage");
        final TextView baconText = (TextView) findViewById(R.id.baconText);

        baconText.setText(appleMessage);
    }

    public void onClick(View view){
        Intent i = new Intent(this,Apple.class);

        startActivity(i);
    }

}
