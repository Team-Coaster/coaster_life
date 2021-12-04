package com.teamcoaster.coasterlife;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Register your parse models
        ParseObject.registerSubclass(Post.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("gVAm71f5Ueg98SD0qmvbdYeZ1bcpaCvoGlkAgH1f")
                .clientKey("oaw94LDLIWxKqG9dXFoIrJ8EnXIkFUhq7nV2l6ti")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
