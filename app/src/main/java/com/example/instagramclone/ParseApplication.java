package com.example.instagramclone;

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
                .applicationId("UCRHzax98mK3wnVjlecTIAy9p0jC5PaRvckvOjnw")
                .clientKey("uSxwBgF1QJq8d675TjkF7Ajv0oZYBed0Ikq9tyup")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
