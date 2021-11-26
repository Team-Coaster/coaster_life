package com.teamcoaster.coasterlife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;

import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    private ImageButton ivProfilePicture;
    private Button btnProfileImg;
    private Button btnFriends;
    private Button btnRides;
    private Button btnLogout;
    private CheckBox cbCheckin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivProfilePicture = findViewById(R.id.ivProfilePicture);
        btnProfileImg = findViewById(R.id.btnProfileImg);
        btnFriends = findViewById(R.id.btnFriends);
        btnRides = findViewById(R.id.btnRides);
        btnLogout = findViewById(R.id.btnLogout);
        cbCheckin = findViewById(R.id.cbCheckin);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseUser.logOut();
                ParseUser currentUser =  ParseUser.getCurrentUser();
                if (currentUser == null) {
                    gotoLogin();
                }
            }
        });

    }

    private void gotoLogin() {
        Intent i = new Intent(this, Login.class);
        startActivity(i);
        finish();
    }
}