package com.example.jardosh.customlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] food = {"Pav Bhaji", "Dabeli", "Ras Malai", "Paneer Tikka Masala","Garlic Naan","Hydrabadi Biryani", "Paneer Makhanwala"};
        ListAdapter dhavalAdapter = new CustomAdapter(this, food);
        ListView dhavalListView = (ListView) findViewById(R.id.dhavalListView);
        dhavalListView.setAdapter(dhavalAdapter);

        dhavalListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String food = String.valueOf(parent.getItemAtPosition(position));
                        Toast.makeText(MainActivity.this,food,Toast.LENGTH_SHORT).show();
                    }
                }
        );

    }
}
