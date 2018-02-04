package com.example.jardosh.databasedemo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {

            SQLiteDatabase events = this.openOrCreateDatabase("Events",MODE_PRIVATE,null);
            events.execSQL("CREATE TABLE IF NOT EXISTS event(name VARCHAR ,year INT(4))");
            events.execSQL("INSERT INTO event (name,year) VALUES('WorldCup 2016',2016)");
            events.execSQL("INSERT INTO event (name,year) VALUES('WorldCup 2015',2015)");

            Cursor c = events.rawQuery("SELECT * FROM event",null);

            int nameIndex = c.getColumnIndex("name");
            int yearIndex = c.getColumnIndex("year");

            c.moveToFirst();

            while (c!= null){
                Log.i("name",c.getString(nameIndex));
                Log.i("year",Integer.toString(Integer.parseInt(c.getString(yearIndex))));
                c.moveToNext();
            }

        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
