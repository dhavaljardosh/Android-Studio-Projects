package com.parse.starter;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PostLogin extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();

        menuInflater.inflate(R.menu.share_menu, menu);

        return  super.onCreateOptionsMenu(menu);

    }

    public void getPhoto(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 1) {
            if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED) {
                getPhoto();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.share) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[] {
                            android.Manifest.permission.READ_EXTERNAL_STORAGE }, 1);
                } else {
                    getPhoto();
                }
            }else {
                getPhoto();
            }
        }

        return  super.onOptionsItemSelected(item);
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_login);

        final ListView userListView = (ListView) findViewById(R.id.usersListView);

        final ArrayList<String> users = new ArrayList<>();

        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereNotEqualTo("username",ParseUser.getCurrentUser().getUsername());
        query.addAscendingOrder("username");

        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {
                if(e== null){
                    if(objects.size()>0){
                        for(ParseUser user: objects){
                            users.add(user.getUsername());
                        }
                    }
                }else{

                }
            }
        });
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,users);
        userListView.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == RESULT_OK && data!= null){
            Uri selectedImage = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),selectedImage);

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);

                byte[] byteArray = stream.toByteArray();

                ParseFile file = new ParseFile("image.png",byteArray);

                ParseObject object = new ParseObject("Image");

                object.put("image",file);
                object.put("username",ParseUser.getCurrentUser().getUsername());
                object.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e == null){
                            Toast.makeText(PostLogin.this,"Image Shared!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(PostLogin.this,"NOT STORED", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

//                Toast.makeText(getApplicationContext(),"Image Received",Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
