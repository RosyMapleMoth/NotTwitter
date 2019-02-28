package com.codepath.apps.restclienttemplate.models;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

@Parcel
public class User {
    public String name;
    public String handle;
    public long UID;
    public String ImageID;


    public User()
    {

    }

    public static User fromJson(JSONObject JsonUser) throws JSONException
    {
        User user = new User();
        user.name = JsonUser.getString("name");
        user.UID = JsonUser.getLong("id");
        user.ImageID = JsonUser.getString("profile_image_url").replace("http","https");
        user.handle = JsonUser.getString("screen_name");
        return user;
    }
}
