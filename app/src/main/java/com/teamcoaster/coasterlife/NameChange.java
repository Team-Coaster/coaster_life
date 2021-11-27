package com.teamcoaster.coasterlife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class NameChange extends AppCompatActivity {

    public static final String TAG = "NameChange";
    private EditText etPersonName;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_change);

        etPersonName = findViewById(R.id.etPersonName);
        btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etPersonName.getText().toString();
                if(!name.isEmpty()) {
                    ParseUser currentUser = ParseUser.getCurrentUser();
                    saveName(name, currentUser);
                    gotoMain();
                }
            }
        });
    }

    private void gotoMain() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    private void saveName(String sn, ParseUser currentUser) {
        Profile profile = new Profile();
        profile.setScreenName(sn);
        profile.setUser(currentUser);

        profile.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Error while saving", e);
                    Toast.makeText(NameChange.this, "Error while saving!", Toast.LENGTH_SHORT).show();
                }
                Log.i(TAG, "Profile save was successful");
                etPersonName.setText("");
            }
        });
    }
}