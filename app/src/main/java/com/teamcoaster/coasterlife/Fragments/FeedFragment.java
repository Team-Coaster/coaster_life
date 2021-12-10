package com.teamcoaster.coasterlife.Fragments;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.teamcoaster.coasterlife.NameChange;
import com.teamcoaster.coasterlife.Post;
import com.teamcoaster.coasterlife.PostsAdapter;
import com.teamcoaster.coasterlife.Profile;
import com.teamcoaster.coasterlife.R;

import java.util.ArrayList;
import java.util.List;

public class FeedFragment extends Fragment {


    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String TAG = "FeedFragment";
    private RecyclerView rvPosts;
    protected PostsAdapter adapter;
    protected List<Post> allPosts;

    private ImageButton composeBtn;

    private int mContainerId;
    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;
    private final static String ACTIVITY_TAG = "MainActivity";

    public FeedFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_feed, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvPosts = view.findViewById(R.id.rvPosts);

        composeBtn = view.findViewById(R.id.composeBtn);
        composeBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                //Navigate to Create a Post
                Fragment fragment = new ComposeFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });



        //Steps to use the recycler view:
        //0. Create layout for one row in the list
        //1. Create the adapter
        allPosts  = new ArrayList<>();
        adapter = new PostsAdapter(getContext(), allPosts);
        //2. Create the data source
        //3. Set the adapter on the recycler view
        rvPosts.setAdapter(adapter);
        //4. Set the layout manager on the recycler view
        rvPosts.setLayoutManager(new LinearLayoutManager(getContext()));
        queryPosts();
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

    public void replaceFragment(Fragment fragment, String TAG) {

        try {
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(mContainerId, fragment, TAG);
            fragmentTransaction.addToBackStack(TAG);
            fragmentTransaction.commitAllowingStateLoss();

        } catch (Exception e) {
            // TODO: handle exception
        }

    }

}
