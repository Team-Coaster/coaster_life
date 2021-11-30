package com.teamcoaster.coasterlife.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.parse.ParseUser;
import com.teamcoaster.coasterlife.Login;
import com.teamcoaster.coasterlife.NameChange;
import com.teamcoaster.coasterlife.Profile;
import com.teamcoaster.coasterlife.R;


public class ProfileFragment extends Fragment {

    public static final String TAG = "MainActivity";
    private ImageButton ivProfilePicture;
    private Button btnProfileImg;
    private Button btnFriends;
    private Button btnRides;
    private Button btnLogout;
    private Button btnSN;
    private TextView tvSN;
    private CheckBox cbCheckin;

//    final FragmentManager fragmentManager = getSupportFragmentManager();
//    private BottomNavigationView bottomNavigationView;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_profile, container, false);

    }

    //This event is triggered soon after onCreateView().
    //Any view setup should occur here. E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ivProfilePicture = view.findViewById(R.id.ivProfilePicture);
        btnProfileImg = view.findViewById(R.id.btnProfileImg);
        btnFriends = view.findViewById(R.id.btnFriends);
        btnRides = view.findViewById(R.id.btnRides);
        btnLogout = view.findViewById(R.id.btnLogout);
        btnSN = view.findViewById(R.id.btnSN);
        tvSN = view.findViewById(R.id.tvSN);

        cbCheckin = view.findViewById(R.id.cbCheckin);


        btnProfileImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoGallery();
            }
        });

        btnSN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseUser currentUser = ParseUser.getCurrentUser();
                Profile profile = new Profile();
                gotoNameChange();
                //String name = getName(currentUser);
            }
        });


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

    /*
    private String getName(ParseUser currentUser) {
        ParseQuery<Profile> query = ParseQuery.getQuery(Profile.class);
        query.whereEqualTo("")
    }
    */

    private void gotoNameChange() {
        Intent i = new Intent(getActivity(), NameChange.class);
        startActivity(i);
    }

    private void gotoGallery() {
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i,3);
    }

    private void gotoLogin() {
        Intent i = new Intent(getActivity(), Login.class);
        startActivity(i);
    }
}
