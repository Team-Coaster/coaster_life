package com.teamcoaster.coasterlife.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.teamcoaster.coasterlife.Authentication.Login;
import com.teamcoaster.coasterlife.SubFragments.NameFragment;
import com.teamcoaster.coasterlife.R;
import com.teamcoaster.coasterlife.Resources.Post;
import com.teamcoaster.coasterlife.Resources.PostsAdapter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.List;

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

    protected PostsAdapter adapter;
    protected List<Post> allPosts;

    private File photoFile;
    public String photoFileName = "photo.jpg";

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

        //Initialize profile Image
        ParseFile image = ParseUser.getCurrentUser().getParseFile("image");

        byte[] byteArray;

        try {
            byteArray = image.getData();
            Bitmap savedImage = BitmapFactory.decodeByteArray(byteArray,0,byteArray.length); //Bitmap with [rofile picture
            ivProfilePicture.setImageBitmap(savedImage);
        } catch (ParseException e) {
            e.printStackTrace();
        }


//        if (image != null) {
//            Glide.with(getActivity()).load(image.getUrl()).into(ivProfilePicture);
//                Glide.with(getActivity()).load(image.getFileInBackground()).into(ivProfilePicture);
//        }


        btnProfileImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                gotoGallery();
                launchCamera();
            }
        });

        tvSN.setText(ParseUser.getCurrentUser().getUsername());
        btnSN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
    protected void queryPosts() {
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.include(Post.KEY_USER);
        query.setLimit(20);
        query.addDescendingOrder(Post.KEY_CREATED_KEY);

        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with getting posts", e);
                    return;
                }
                for (Post post : posts) {
                    Log.i(TAG, "Post: " + post.getDescription() + ", username: " + post.getUser().getUsername());
                }
                allPosts.addAll(posts);
                adapter.notifyDataSetChanged();
            }
        });
    }

    /*
    private String getName(ParseUser currentUser) {
        ParseQuery<Profile> query = ParseQuery.getQuery(Profile.class);
        query.whereEqualTo("")
    }
    */

    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    //check if the user took the photo
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        //By the point we have the camera photo on disk
                        Bitmap takenImage = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
                        //Load the image taken into a preview
                        ivProfilePicture.setImageBitmap(takenImage);

                        //Upload image
                        ParseUser user = ParseUser.getCurrentUser();
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        // Compress image to lower quality scale 1 - 100
                        takenImage.compress(Bitmap.CompressFormat.PNG, 100, stream);
                        byte[] image = stream.toByteArray();

                        // Create the ParseFile
                        ParseFile file = new ParseFile(user.getUsername() + ".png", image);
                        // Upload the image into Parse Cloud
                        file.saveInBackground();

                        // Insert the image into the file
                        user.put("image", file);

                        // Create the class and the columns
                        user.saveInBackground();

                    } else{
                        //Result was a failure
                        Toast.makeText(getContext(), "Picture wasn't taken!", Toast.LENGTH_SHORT).show();
                    }

                }
            });
    private void launchCamera() {
        // create Intent to take a picture and return control to the calling application
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Create a File reference for future access
        photoFile = getPhotoFileUri(photoFileName);

        // wrap File object into a content provider
        // required for API >= 24
        // See https://guides.codepath.com/android/Sharing-Content-with-Intents#sharing-files-with-api-24-or-higher
        Uri fileProvider = FileProvider.getUriForFile(getContext(), "com.codepath.fileprovider", photoFile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider);

        // If you call startActivityForResult() using an intent that no app can handle, your app will crash.
        // So as long as the result is not null, it's safe to use the intent.
        //Almost all phones have camera so will always be null
        if (intent.resolveActivity(getContext().getPackageManager()) != null) {
            // Start the image capture intent to take photo
            someActivityResultLauncher.launch(intent);
            //DEPRECIATED
            //startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
        }
    }

    private File getPhotoFileUri(String fileName) {
        // Get safe storage directory for photos
        // Use `getExternalFilesDir` on Context to access package-specific directories.
        // This way, we don't need to request external read/write runtime permissions.
        File mediaStorageDir = new File(getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES), TAG);

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()){
            Log.d(TAG, "failed to create directory");
        }

        // Return the file target for the photo based on filename
        return new File(mediaStorageDir.getPath() + File.separator + fileName);
    }

    private void gotoNameChange() {
        //Navigate to Create a Post
        Fragment fragment = new NameFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void gotoGallery() {
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i,3);
    }

    private void gotoLogin() {
        Intent i = new Intent(getActivity(), Login.class);
        startActivity(i);
        getActivity().finish();
    }
}
