package com.teamcoaster.coasterlife.Fragments;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.teamcoaster.coasterlife.R;

import java.util.ArrayList;
import java.util.List;

public class FeedFragment extends Fragment {

//COPIED FROM INSTAPARSE APP
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    public static final String TAG = "PostsFragment";
//    private RecyclerView rvPosts;
//    protected PostsAdapter adapter;
//    protected List<Post> allPosts;
//
//    public FeedFragment() {
//        // Required empty public constructor
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_feed, container, false);
    }

//      COPIED FROM INSTAPARSE APP
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        rvPosts = view.findViewById(R.id.rvPosts);
//
//        //Steps to use the recycler view:
//        //0. Create layout for one row in the list
//        //1. Create the adapter
//        allPosts  = new ArrayList<>();
//        adapter = new PostsAdapter(getContext(), allPosts);
//        //2. Create the data source
//        //3. Set the adapter on the recycler view
//        rvPosts.setAdapter(adapter);
//        //4. Set the layout manager on the recycler view
//        rvPosts.setLayoutManager(new LinearLayoutManager(getContext()));
//        queryPosts();
//    }
//
//    protected void queryPosts() {
//        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
//        query.include(Post.KEY_USER);
//        query.setLimit(20);
//        query.addDescendingOrder(Post.KEY_CREATED_KEY);
//
//        query.findInBackground(new FindCallback<Post>() {
//            @Override
//            public void done(List<Post> posts, ParseException e) {
//                if (e != null) {
//                    Log.e(TAG, "Issue with getting posts", e);
//                    return;
//                }
//                for (Post post : posts) {
//                    Log.i(TAG, "Post: " + post.getDescription() + ", username: " + post.getUser().getUsername());
//                }
//                allPosts.addAll(posts);
//                adapter.notifyDataSetChanged();
//            }
//        });
//    }

}
