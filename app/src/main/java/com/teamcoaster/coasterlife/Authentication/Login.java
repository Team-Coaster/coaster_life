package com.teamcoaster.coasterlife.Authentication;

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
import com.teamcoaster.coasterlife.MainActivity;
import com.teamcoaster.coasterlife.R;

public class Login extends AppCompatActivity {

    public static final String TAG = "LoginActivity";
    private EditText etUserName;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnCreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(ParseUser.getCurrentUser() != null) {
            goMainActivity();
        }

        etUserName = findViewById(R.id.etUserName);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnCreateAccount = findViewById(R.id.btnCreateAccount);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "Onclick login button");
                String username = etUserName.getText().toString();
                String password = etPassword.getText().toString();
                loginUser(username, password);
            }
        });

        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "On click create account");
                goCreateAccount();
            }
        });
    }

    private void loginUser(String username, String password) {
        Log.i(TAG, "Attempting to login user " + username);
        //Navigate to Main Activity if User successfully signs in
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e != null) {
                    //Error handling
                    Log.e(TAG, "Issue with login", e);
                    Toast.makeText(Login.this, "Issue with login!", Toast.LENGTH_SHORT).show();
                    return;
                }
                //Successful Login
                goMainActivity();
                Toast.makeText(Login.this, "Success!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void goCreateAccount() {
        Intent i = new Intent(this, Signup.class);
        startActivity(i);
    }

    private void goMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}