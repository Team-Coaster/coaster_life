package com.teamcoaster.coasterlife;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class signup extends AppCompatActivity {

    public static final String TAG = "SignupActivity";
    private EditText etSignUpUsername;
    private EditText etPassword;
    private EditText etConfirmPassword;
    private Button btnRegister;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        etSignUpUsername = findViewById(R.id.etSignUpUsername);
        etPassword = findViewById(R.id.etSignUpPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String useName = etSignUpUsername.getText().toString();
                String pwd = etPassword.getText().toString();
                String confirm = etConfirmPassword.getText().toString();

                if (pwd.equals(confirm)) {
                    ParseUser user = new ParseUser();
                    user.setUsername(useName);
                    user.setPassword(pwd);
                    user.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e  == null) {
                                ParseUser.logOut();
                                Toast.makeText(signup.this, "You are registered " + useName, Toast.LENGTH_SHORT).show();
                                goLogin();
                            } else {
                                Log.e(TAG, "Issue with sign up", e);
                                return;
                            }
                        }
                    });
                }

                else {
                    Log.i(TAG, "Passwords dont match");
                    Toast.makeText(signup.this, "Passwords must match in order to register", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    private void goLogin() {
        Intent i = new Intent(this, Login.class);
        startActivity(i);
        finish();
    }
}
