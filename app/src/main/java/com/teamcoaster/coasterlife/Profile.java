package com.teamcoaster.coasterlife;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Profile")
public class Profile extends ParseObject {
    public static final String KEY_USER = "user";
    public static final String KEY_SCREENNAME = "screenName";
    public static final String KEY_IMAGE = "profileImage";
    public static final String KEY_PARK = "atPark";
    public static final String KEY_FRIEND = "friends";
    public static final String KEY_RIDE = "Rides";
    public static final String KEY_favRide = "favoriteRide";

    public ParseUser getUser() { return getParseUser(KEY_USER); }

    public void setUser(ParseUser user) { put(KEY_USER, user); }

    public String getScreenName() { return getString(KEY_SCREENNAME); }

    public void setScreenName(String screenName) { put(KEY_SCREENNAME, screenName); }

    //TODO Finish the rest of the Profile getters and setters

    public ParseFile getImage() { return getParseFile(KEY_IMAGE); }

    public void setImage(ParseFile image) { put(KEY_IMAGE, image); }

    public ParseGeoPoint getPark() { return getParseGeoPoint(KEY_PARK); }

    public void setPark(ParseGeoPoint park) { put(KEY_PARK, park); }

    public String getFavRide() {return getString(KEY_favRide); }

    public void setFavRide(String favRide) { put(KEY_favRide, favRide); }
}
