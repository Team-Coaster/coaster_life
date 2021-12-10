package com.teamcoaster.coasterlife.Resources;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;
import com.teamcoaster.coasterlife.Resources.Post;

public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Register your parse models
        ParseObject.registerSubclass(Post.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("6r6HH3JwaKJcuJmVx3qEzjABU3ILGXxX2yWajtDN")
                .clientKey("b0Yg9QkzLkTowYcWOUN5u9rJaSyL74EiOdAqNgbI")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
