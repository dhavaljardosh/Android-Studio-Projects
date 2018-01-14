package com.example.jardosh.videoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final VideoView dhavalVideo = (VideoView) findViewById(R.id.dhavalVideo);

        dhavalVideo.setVideoPath("https://www.youtube.com/watch?v=wiv2fmXtulc&ab_channel=DhavalJardosh");
        dhavalVideo.start();
    }
}
