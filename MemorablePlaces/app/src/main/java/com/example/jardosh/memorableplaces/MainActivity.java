package com.example.jardosh.memorableplaces;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static ArrayList<String> places = new ArrayList<>();
    static ArrayList<LatLng> locations = new ArrayList<>();
    static ArrayAdapter addAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.listView);


        places.add("Add New Place");
        locations.add(new LatLng(0,0));

        addAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,places);

        listView.setAdapter(addAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//                Toast.makeText(MainActivity.this,position,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(),MapsActivity.class);
                intent.putExtra("Place Number",position);
//                Log.i("LE BENCHOD",intent.getStringExtra("latlng"));

                startActivity(intent);

            }
        });


    }
}
