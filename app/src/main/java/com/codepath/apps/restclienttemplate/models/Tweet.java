package com.codepath.apps.restclienttemplate.models;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

@Parcel
public class Tweet {
    public String body, Created;
    public long id;
    public User user;



    public Tweet()
    {}


    public static Tweet fromJson(JSONObject Jsontweet) throws JSONException
    {
        Tweet tweet = new Tweet();
        tweet.body = Jsontweet.getString("text");
        tweet.id =Jsontweet.getLong("id");
        tweet.Created = Jsontweet.getString("created_at");
        tweet.user = User.fromJson(Jsontweet.getJSONObject("user"));

        return tweet;
    }

}
