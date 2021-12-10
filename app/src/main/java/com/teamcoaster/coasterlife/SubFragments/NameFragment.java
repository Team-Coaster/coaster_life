package com.teamcoaster.coasterlife.SubFragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.teamcoaster.coasterlife.Fragments.ProfileFragment;
import com.teamcoaster.coasterlife.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NameFragment extends Fragment {

    public static final String TAG = "NameChange";
    private EditText etPersonName;
    private Button btnSubmit;

    public NameFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_name, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etPersonName = view.findViewById(R.id.etPersonName);
        btnSubmit = view.findViewById(R.id.btnSubmit);

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
        //Navigate back to Profile Screen
        Fragment fragment = new ProfileFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void saveName(String sn, ParseUser currentUser) {

//        Profile profile = new Profile();
//        profile.setScreenName(sn);
//        profile.setUser(currentUser);

        ParseUser user = currentUser;
        user.setUsername(sn);
        user.saveInBackground();
//        profile.saveInBackground(new SaveCallback() {
//            @Override
//            public void done(ParseException e) {
//                if (e != null) {
//                    Log.e(TAG, "Error while saving", e);
//                    Toast.makeText(NameChange.this, "Error while saving!", Toast.LENGTH_SHORT).show();
//                }
//                Log.i(TAG, "Profile save was successful");
//                etPersonName.setText("");
//            }
//        });
    }
}