package com.example.jardosh.downloadimage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> celebURLs = new ArrayList<String>();
    ArrayList<String> celebNames = new ArrayList<String>();
    int chosenCeleb = 0;

    Button button0;
    Button button1;
    Button button2;
    Button button3;

    int locationOfCorrectAnswer = 0;
    String[] answers = new String[4];

    ImageView imageView;

    public void checkAnswer(View view) {
        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){
            Toast.makeText(getApplicationContext(),"Correct",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(getApplicationContext(),"Wrong",Toast.LENGTH_LONG).show();
        }
        newQuestion();
    }


    public class ImageDownloader extends AsyncTask<String,Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... urls) {

            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream inputStream = connection.getInputStream();

                Bitmap myBitmap = BitmapFactory.decodeStream(inputStream);

                return myBitmap;

            } catch (Exception e) {

                e.printStackTrace();

            }
            return null;
        }
    }

    public class DownloadTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpURLConnection urlConnection;

            try {

                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();

                InputStreamReader reader = new InputStreamReader(in);

                int data = reader.read();

                while(data != -1){
                    char current = (char) data;

                    result += current;

                    data = reader.read();

                }

                return result;

            } catch (Exception e) {

                e.printStackTrace();

            }

            return null;
        }
    }

    public void newQuestion(){
        Random random = new Random();

        chosenCeleb = random.nextInt(celebURLs.size());

        ImageDownloader imageTask = new ImageDownloader();

        Bitmap celebImage;

        try {

            celebImage = imageTask.execute(celebURLs.get(chosenCeleb)).get();

            imageView.setImageBitmap(celebImage);

            //Location of correct answer

            locationOfCorrectAnswer = random.nextInt(4);

            int incorrectAnswerLocation;

            for(int i=0;i<4;i++){

                if(i==locationOfCorrectAnswer){
                    //Update the name of the correct Celeb
                    answers[i] = celebNames.get(chosenCeleb);

                }else{

                    incorrectAnswerLocation = random.nextInt(celebURLs.size());

                    while(incorrectAnswerLocation == chosenCeleb){

                        incorrectAnswerLocation = random.nextInt(celebURLs.size());

                    }
                    answers[i] = celebNames.get(incorrectAnswerLocation);
                }

            }
//            checkAnswer(answers[chosenCeleb]);
            System.out.println(answers[0]);
            System.out.println(answers[2]);
            System.out.println(answers[3]);
            System.out.println(answers[1]);

            button0.setText(answers[0]);
            button1.setText(answers[1]);
            button2.setText(answers[2]);
            button3.setText(answers[3]);
        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView)findViewById(R.id.imageView);
        button0 = (Button)findViewById(R.id.optionButton0);
        button1 = (Button)findViewById(R.id.optionButton1);
        button2 = (Button)findViewById(R.id.optionButton2);
        button3 = (Button)findViewById(R.id.optionButton3);


        DownloadTask task = new DownloadTask();
        String result = null;

        try {

            result= task.execute("http://www.posh24.se/kandisar").get();
            String[] splitResult = result.split("<div class=\"sidebarContainer\">");

            Pattern p = Pattern.compile("<img src=\"(.*?)\"");
            Matcher m = p.matcher(splitResult[0]);

            while(m.find()){
                celebURLs.add(m.group(1));
            }

            p = Pattern.compile("alt=\"(.*?)\"");
            m = p.matcher(splitResult[0]);

            while(m.find()){
                celebNames.add(m.group(1));
            }

            System.out.println(Arrays.toString(new ArrayList[]{celebURLs}));

            newQuestion();

        } catch (Exception e) {

            e.printStackTrace();

        }
    }
}
