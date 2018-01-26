package com.example.jardosh.whatstheweather;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    EditText cityName;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cityName = (EditText) findViewById(R.id.cityEditText);
        result = (TextView) findViewById(R.id.resultTextView);

    }

    public void getResult(View view){

        InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(cityName.getWindowToken(),0);

;
        DownloadTask task = new DownloadTask();
        task.execute("http://api.openweathermap.org/data/2.5/weather?q="+cityName.getText()+"&appid=3e18e703d0466a03578f6022a2bc5fbe");

        Log.i("Complete","http://api.openweathermap.org/data/2.5/weather?q="+cityName.getText()+"&appid=3e18e703d0466a03578f6022a2bc5fbe");
    }


    public class DownloadTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... urls) {

            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();

                InputStream in = urlConnection.getInputStream();

                InputStreamReader reader = new InputStreamReader(in);

                int data = reader.read();

                while(data!=-1){
                    char current = (char) data;
                    result += current;

                    data = reader.read();
                }
                return result;

            } catch (MalformedURLException e) {

                e.printStackTrace();

            } catch (IOException e) {
                e.printStackTrace();
                Log.i("FILE ERROR","Check for File");
            }
            return null;
        }

        @Override
        protected void onPostExecute(String results) {
            super.onPostExecute(results);

            try {

                String message = "";

                JSONObject jsonObject = new JSONObject(results);
//                String check = jsonObject.getString("list");

                String check = jsonObject.getString("weather");

                JSONArray arr = new JSONArray(check);
                for(int i = 0; i < arr.length();i++){
                    JSONObject jsonPart = arr.getJSONObject(i);

                    String main = jsonPart.getString("main");
                    String Desc = jsonPart.getString("description");

                    Log.i("Main",main);
                    Log.i("Description",Desc);
                    if(main!="" && Desc !=""){
                        message += main + ": " +Desc+ "\r\n";
                    }

                }

                if (message != "") {
                    result.setText(message);
                } else{
                    Toast.makeText(getApplicationContext(),"Not Found",Toast.LENGTH_LONG).show();
                }



                Log.i("CHECK  THIS >>>>>>>>>", check);

            } catch (JSONException e) {

                e.printStackTrace();

            }
            catch (NullPointerException e) {
                Toast.makeText(getApplicationContext(),"Enter valid City Name",Toast.LENGTH_LONG).show();
                Log.i("ERROR","Not Valid City Name");

            }

        }
    }
}
