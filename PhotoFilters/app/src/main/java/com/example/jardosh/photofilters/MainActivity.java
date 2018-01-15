package com.example.jardosh.photofilters;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
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
                R=Color.red(pixelColor);
                G=Color.green(pixelColor);
                B=Color.blue(pixelColor);

                finalImage.setPixel(x,y, Color.argb(A, R, G, B));
            }
        }

        return finalImage;
    }
}
