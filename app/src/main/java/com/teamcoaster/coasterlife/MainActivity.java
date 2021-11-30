package com.teamcoaster.coasterlife;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {

//    public static final String TAG = "MainActivity";
//    private ImageButton ivProfilePicture;
//    private Button btnProfileImg;
//    private Button btnFriends;
//    private Button btnRides;
//    private Button btnLogout;
//    private Button btnSN;
//    private TextView tvSN;
//    private CheckBox cbCheckin;
//
//    final FragmentManager fragmentManager = getSupportFragmentManager();
//    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);

        //Set starting Fragment as Profile Fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfileFragment()).commit();

        bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                int id = item.getItemId();
                switch (id) {
                    //Check Item ID
                    case R.id.nav_profile:
                        selectedFragment = new ProfileFragment();
                        break;
                    case R.id.nav_feed:
                        selectedFragment = new FeedFragment();
                        break;
                    case R.id.nav_people:
                        selectedFragment = new PeopleFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

                return true;
            }
        });

//        ivProfilePicture = findViewById(R.id.ivProfilePicture);
//        btnProfileImg = findViewById(R.id.btnProfileImg);
//        btnFriends = findViewById(R.id.btnFriends);
//        btnRides = findViewById(R.id.btnRides);
//        btnLogout = findViewById(R.id.btnLogout);
//        btnSN = findViewById(R.id.btnSN);
//        tvSN = findViewById(R.id.tvSN);
//
//        cbCheckin = findViewById(R.id.cbCheckin);
//
//        //bottomNavigationView = findViewById(R.id.bottomNavigation);
//
//        btnProfileImg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                gotoGallery();
//            }
//        });
//
//        btnSN.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ParseUser currentUser = ParseUser.getCurrentUser();
//                Profile profile = new Profile();
//                gotoNameChange();
//                //String name = getName(currentUser);
//            }
//        });


//        btnLogout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ParseUser.logOut();
//                ParseUser currentUser =  ParseUser.getCurrentUser();
//                if (currentUser == null) {
//                    gotoLogin();
//                }
//            }
//        });
//
//
    }

//    /*
//    private String getName(ParseUser currentUser) {
//        ParseQuery<Profile> query = ParseQuery.getQuery(Profile.class);
//        query.whereEqualTo("")
//    }
//    */
//    private void gotoNameChange() {
//        Intent i = new Intent(this, NameChange.class);
//        startActivity(i);
//        finish();
//    }
//
//    private void gotoGallery() {
//        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        startActivityForResult(i,3);
//    }
//
//    private void gotoLogin() {
//        Intent i = new Intent(this, Login.class);
//        startActivity(i);
//        finish();
//    }
    }
