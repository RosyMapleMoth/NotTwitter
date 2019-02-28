package com.codepath.apps.restclienttemplate.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.codepath.apps.restclienttemplate.R;

public class composeTweet extends AppCompatActivity {

    Button btnTweet;
    TextView mltvCompose;
    TextView tvCharRem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose_tweet);

        btnTweet = findViewById(R.id.btnSubmit);
        mltvCompose = findViewById(R.id.tvTweet);
        tvCharRem = findViewById(R.id.tvRemChar);

                

        btnTweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = mltvCompose.getText().toString();

            }
        });


    }
}
