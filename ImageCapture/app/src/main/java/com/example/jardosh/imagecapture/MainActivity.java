package com.example.jardosh.imagecapture;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView dhavalImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button dhavalButton = (Button) findViewById(R.id.dhavalButton);
        dhavalImageView = (ImageView) findViewById(R.id.dhavalImageView);

        //Disable the button if the user has no camera
        if(!hasCamera()) {
            dhavalButton.setEnabled(false);
        }
    }

        //check if user has a Camera
        private boolean hasCamera(){
             return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
        }

        public void launchCamera (View view) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            //Take a pic and pass results along the onActivityResult
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
        }

        //If you want to return the image taken


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
                //Get the photo
                Bundle extras = data.getExtras();
                Bitmap photo = (Bitmap) extras.get("data");

                dhavalImageView.setImageBitmap(photo);
            }
    }
}
