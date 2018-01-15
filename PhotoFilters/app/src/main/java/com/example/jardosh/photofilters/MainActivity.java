package com.example.jardosh.photofilters;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.media.Image;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ImageView dhavalImageView;
        Drawable dhavalFace;
        Bitmap bitmapImage;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        dhavalImageView = (ImageView) findViewById(R.id.dhavalImageView);

        dhavalFace = getResources().getDrawable(R.drawable.main);
        bitmapImage = ((BitmapDrawable) dhavalFace).getBitmap();
        Bitmap newPhoto = invertImage(bitmapImage);

        dhavalImageView.setImageBitmap(newPhoto);

        /*
        Drawable[] layers = new Drawable[2];
        layers[0] = getResources().getDrawable(R.drawable.main);
        layers[1] = getResources().getDrawable(R.drawable.over);
        LayerDrawable layerDrawable = new LayerDrawable(layers);
        dhavalImageView.setImageDrawable(layerDrawable);
        */

        MediaStore.Images.Media.insertImage(getContentResolver(),newPhoto,"title", "description");

    }

    //Invert a bitmap Image
    public static Bitmap invertImage(Bitmap original) {

        Bitmap finalImage = Bitmap.createBitmap(original.getWidth(), original.getHeight(), original.getConfig());

        int A, R, G, B;
        int pixelColor;
        int height = original.getHeight();
        int width = original.getWidth();

        for(int y = 0; y<height; y++){
            for(int x = 0;x < width; x++){
                pixelColor = original.getPixel(x,y);
                A= Color.alpha(pixelColor);
                R=255 - Color.red(pixelColor);
                G=255 - Color.green(pixelColor);
                B=255 - Color.blue(pixelColor);

                finalImage.setPixel(x,y, Color.argb(A, R, G, B));
            }
        }

        return finalImage;
    }
}
