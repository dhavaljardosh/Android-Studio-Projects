package com.example.jardosh.customlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    int[] IMAGES = {R.drawable.amit, R.drawable.aris, R.drawable.gandhi, R.drawable.modi, R.drawable.musk, R.drawable.ronaldo, R.drawable.steve, R.drawable.wash};

    String[] NAMES = {"Amitabh Bachchan","Aristotale Bhai", "Mahatma Gandhi", "Narendra Modi", "Elon Musk", "Cristiano Ronaldo","Steve Jobs", "George Washington"};

    String[] DESCRIPTIONS = {"Actor","Researcher","Freedom Fighter","Prime Minister","Enterepreuner","Footballer","Legend","US President"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.listview);

        CustomAdapter customAdapter = new CustomAdapter();

        listView.setAdapter(customAdapter);
    }


    class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return IMAGES.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            view = getLayoutInflater().inflate(R.layout.customlayout,null);
            ImageView imageView = (ImageView)view.findViewById(R.id.imageView);
            TextView textView_name = (TextView)view.findViewById(R.id.textView_name);
            TextView textView_description = (TextView)view.findViewById(R.id.textView_description);

            imageView.setImageResource(IMAGES[position]);
            textView_name.setText(NAMES[position]);
            textView_description.setText(DESCRIPTIONS[position]);

            return null;
        }
    }
}
