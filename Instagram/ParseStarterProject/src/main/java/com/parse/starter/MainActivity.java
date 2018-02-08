/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import org.w3c.dom.Text;

import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnKeyListener {

  static boolean login = false;
  static Button signupButton;
  TextView changeSignupTextview;
  EditText passwordEditText;
  EditText usernameEditText;

  public void showUserList(){
    Intent intent = new Intent(getApplicationContext(),PostLogin.class);
    startActivity(intent);
  }


  public void signUp(View view){
    usernameEditText = (EditText) findViewById(R.id.usernameEditText);
    passwordEditText = (EditText) findViewById(R.id.passwordEditText);

    passwordEditText.setOnKeyListener(this);


    if(usernameEditText.getText().toString().matches("") || passwordEditText.getText().toString().matches("")){
      Toast.makeText(MainActivity.this, "Username and Password is Required",Toast.LENGTH_LONG).show();
    } else {

      if (!login) {

        ParseUser user = new ParseUser();

        user.setUsername(usernameEditText.getText().toString());
        user.setPassword(passwordEditText.getText().toString());

        user.signUpInBackground(new SignUpCallback() {
          @Override
          public void done(ParseException e) {
            if (e == null) {
              Toast.makeText(MainActivity.this, "Sign up Success", Toast.LENGTH_LONG).show();
              showUserList();
            } else {
              Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
          }
        });

      } else {
        ParseUser.logInInBackground(usernameEditText.getText().toString(), passwordEditText.getText().toString(), new LogInCallback() {
          @Override
          public void done(ParseUser user, ParseException e) {
            if(user != null){
              Toast.makeText(MainActivity.this,user.getUsername(),Toast.LENGTH_LONG).show();
              showUserList();
            }
            else {
              Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
          }
        });
      }
    }
  }


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    signupButton = (Button) findViewById(R.id.signupButton);
    changeSignupTextview = (TextView) findViewById(R.id.changeSignupModeTextView);

    RelativeLayout backgroundRelativeLayout = (RelativeLayout) findViewById(R.id.backgroundRelativeLayout);
    ImageView logoImageView = (ImageView) findViewById(R.id.logoImageView);

    backgroundRelativeLayout.setOnClickListener(this);
    logoImageView.setOnClickListener(this);
    changeSignupTextview.setOnClickListener(this);

    if(ParseUser.getCurrentUser() != null){
      showUserList();
    }


    ParseAnalytics.trackAppOpenedInBackground(getIntent());
  }

  @Override
  public void onClick(View view) {
    if(view.getId() ==  R.id.changeSignupModeTextView){
      if(!login){
        login = true;
        signupButton.setText("LOGIN");
        changeSignupTextview.setText("SIGN UP");
      } else{
        login=false;
        signupButton.setText("Sign up");
        changeSignupTextview.setText("LOG IN");
      }
    } else if(view.getId() == R.id.backgroundRelativeLayout || view.getId() == R.id.logoImageView) {
      InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
      inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
    }
  }

  @Override
  public boolean onKey(View v, int keyCode, KeyEvent event) {
    if(keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
      signUp(v);
    }
    return false;
  }
}