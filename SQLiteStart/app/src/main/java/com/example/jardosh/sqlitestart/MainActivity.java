package com.example.jardosh.sqlitestart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText dhavalInput;
    TextView dhavalText;
    MyDBHandlers dbHandlers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dhavalInput = (EditText) findViewById(R.id.dhavalInput);
        dhavalText = (TextView) findViewById(R.id.dhavalText);
        dbHandlers = new MyDBHandlers(this,null,null,1);
        printDatabase();

    }

    public void addButtonClicked(View view) {
        Products product = new Products(dhavalInput.getText().toString());
        dbHandlers.addProduct(product);
        printDatabase();
    }

    public void deleteButtonClicked(View view) {
        String inputText = dhavalInput.getText().toString();
        System.out.print(inputText + "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        dbHandlers.deleteProduct(inputText);
        printDatabase();
    }


    public void printDatabase(){
        String dbString = dbHandlers.databaseToString();
        dhavalText.setText(dbString);
        dhavalInput.setText("");
    }

}
