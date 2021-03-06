package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class LoginActivity extends AppCompatActivity {

    public static final String TAG = "LoginActivity";
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;

    private Button btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(ParseUser.getCurrentUser() != null){
            goMainActicity();
        }

        etUsername = findViewById(R.id.Username);
        etPassword = findViewById(R.id.Password);
        btnLogin = findViewById(R.id.signup);
        btnSignup = findViewById(R.id.btnSignup);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "OnClck Login button");
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                loginUser(username,password);
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                signupUser(username,password);

            }
        });
    }

    private void signupUser(String username, String password) {
        // Create the ParseUser
        ParseUser user = new ParseUser();
// Set core properties
        user.setUsername(username);
        user.setPassword(password);
// Invoke signUpInBackground
        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    // Hooray! Let them use the app now.
                    Toast.makeText(LoginActivity.this, "Successful Sign up!", Toast.LENGTH_SHORT).show();

                } else {
                    // Sign up didn't succeed. Look at the ParseException
                    // to figure out what went wrong
                    Log.e(TAG, "Issue with login", e);
                    Toast.makeText(LoginActivity.this, "Issue with Signup", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }




    private void loginUser(String username, String password)
    {
        Log.i(TAG,"Attempting to login user " + username);
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if(e != null)
                {
                    Log.e(TAG, "Issue with login",e);
                    Toast.makeText(LoginActivity.this, "Issue with Login", Toast.LENGTH_SHORT).show();
                    return;
                }

                goMainActicity();
                Toast.makeText(LoginActivity.this, "Suscess Login", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void goMainActicity(){

        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
        finish();

    }
}