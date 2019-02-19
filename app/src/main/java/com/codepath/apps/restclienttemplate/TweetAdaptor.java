package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;

import java.util.List;

public class TweetAdaptor extends RecyclerView.Adapter<TweetAdaptor.ViewHolder>
{

    private Context con;
    private List<Tweet> tweets;

    public TweetAdaptor(Context con, List<Tweet> tweets) {
        this.con = con;
        this.tweets = tweets;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(con).inflate(R.layout.itweet, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        Tweet tweet = tweets.get(i);
        holder.Body.setText(tweet.body);
        holder.Name.setText(tweet.user.handle);
        holder.TimeStamp.setText(TimeFormatter.getTimeDifference(tweet.Created));

        Glide.with(con).load(tweet.user.ImageID).into(holder.profilePic);
    }

    @Override
    public int getItemCount() {
        return tweets.size();
    }

    public void clear() {
        tweets.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Tweet> list) {
        tweets.addAll(list);
        notifyDataSetChanged();
    }


    // pass in contacts and list of tweets


    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView profilePic;
        TextView Name,Body,TimeStamp;

        public ViewHolder(View itemView)
        {
            super(itemView);
            profilePic = itemView.findViewById(R.id.imProfilePic);
            Name = itemView.findViewById(R.id.tvTwitterHandle);
            Body = itemView.findViewById(R.id.tvTweetBody);
            TimeStamp = itemView.findViewById(R.id.tvTimeStamp);
        }


    }
}
