package com.codepath.apps.restclienttemplate;


import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.codepath.apps.restclienttemplate.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class TimelineActivity extends AppCompatActivity {


    private TwitterClient client;
    private RecyclerView rcTweets;
    private TweetAdaptor adapt;
    private List<Tweet> tweets;
    private SwipeRefreshLayout swipeContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);


        client = TwitterApp.getRestClient(this);
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                populateHomeTimeline();

            }
        });

        rcTweets = findViewById(R.id.rvTweets);
        tweets = new ArrayList<>();
        adapt = new TweetAdaptor(this,tweets);

        rcTweets.setLayoutManager(new LinearLayoutManager(this));
        rcTweets.setAdapter(adapt);

        populateHomeTimeline();
    }



    private void populateHomeTimeline() {
        client.getHomeTimeLine(new JsonHttpResponseHandler()
        {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                Log.d("twitter",response.toString());

                List<Tweet> tweestsToAdd = new ArrayList<>();
                for (int i = 0; i < response.length(); i++)
                {
                    try
                    {
                        JSONObject jsonTweet = response.getJSONObject(i);
                        tweestsToAdd.add(Tweet.fromJson(jsonTweet));
                        //adapt.notifyItemInserted(tweets.size() - 1);
                    }
                    catch (JSONException e)
                    {
                        Toast.makeText(getBaseContext(), "we fucked up", Toast.LENGTH_SHORT).show();
                    }
                }
                adapt.clear();
                adapt.addAll(tweestsToAdd);
                swipeContainer.setRefreshing(false);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });
    }
}
