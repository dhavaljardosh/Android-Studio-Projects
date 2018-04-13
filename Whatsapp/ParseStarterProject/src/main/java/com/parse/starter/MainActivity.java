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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;


public class MainActivity extends AppCompatActivity {

  Boolean canLogin = true;

  public void toggleSignin(View view){

    TextView toggler = (TextView) findViewById(R.id.ToggleSigninText);
    Button signupSigninButton = (Button) findViewById(R.id.signupLoginButton);

    if(toggler.getText() == "Sign In") {
      canLogin = false;
      toggler.setText("or, Sign Up");
      signupSigninButton.setText("Sign In");
    } else{
      toggler.setText("or, Sign In");
      signupSigninButton.setText("Sign Up");
    }

  }


  public void signupLogin(View view){

    final EditText usernameEditText = (EditText) findViewById(R.id.usernameEditText);
    EditText  passwordEditText = (EditText) findViewById(R.id.passwordEditText);

    if(canLogin){

      ParseUser.logInInBackground(usernameEditText.getText().toString(),passwordEditText.getText().toString());
      Toast.makeText(getApplicationContext(),usernameEditText.getText().toString()+" Logged In",Toast.LENGTH_LONG);

    }else{
      ParseUser user = new ParseUser();

      user.setUsername(usernameEditText.getText().toString());
      user.setPassword(passwordEditText.getText().toString());

      user.signUpInBackground(new SignUpCallback() {
        @Override
        public void done(ParseException e) {
          if(e==null){
            Log.i("Success: ",usernameEditText.getText().toString()+" signed up successfully");
          }else{
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
            Log.i("Error",e.getMessage());
          }
        }
      });
    }
  }


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    
    ParseAnalytics.trackAppOpenedInBackground(getIntent());
  }

}