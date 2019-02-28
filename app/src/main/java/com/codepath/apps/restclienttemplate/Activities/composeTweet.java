package com.codepath.apps.restclienttemplate.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.TwitterApp;
import com.codepath.apps.restclienttemplate.TwitterClient;
import com.codepath.apps.restclienttemplate.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import cz.msebera.android.httpclient.Header;

public class composeTweet extends AppCompatActivity {

    Button btnTweet;
    TextView mltvCompose;
    TextView tvCharRem;
    TwitterClient client;

    public static final int MAX_TWEET_LANGTH = 140;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose_tweet);

        btnTweet = findViewById(R.id.btnSubmit);
        mltvCompose = findViewById(R.id.tvTweet);
        client = TwitterApp.getRestClient(this);


        btnTweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String data = mltvCompose.getText().toString();

                if (data.isEmpty())
                {
                    Toast.makeText(composeTweet.this,
                            "You can only post morally empty tweets, physically empty ones",
                            Toast.LENGTH_SHORT).show();
                }
                else if (data.length() > MAX_TWEET_LANGTH )
                {
                    Toast.makeText(composeTweet.this,
                            "Your tweet is to long, try to make it more vapid",
                            Toast.LENGTH_SHORT).show();
                }
                else
                {
                    client.composeTweet(new JsonHttpResponseHandler()
                    {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            super.onSuccess(statusCode, headers, response);

                            try {
                                Tweet tweet = Tweet.fromJson(response);
                                Intent i = new Intent();
                                i.putExtra("tweet", Parcels.wrap(tweet));
                                setResult(RESULT_OK, i);
                                finish();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                            super.onFailure(statusCode, headers, responseString, throwable);
                        }

                    }, data);
                }

            }
        });


    }
}
