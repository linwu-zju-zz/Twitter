package com.codepath.apps.Twitter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.Twitter.models.Tweet;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Lin on 12/8/15.
 */

// Taking the Tweet objects and turning the into Views displayed in the list
public class TweetsArrayAdapter extends ArrayAdapter<Tweet>{


    public TweetsArrayAdapter(Context context, List<Tweet> tweets) {
        super(context, 0, tweets);
    }

    // Override and setup custom template
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 1. Get the tweet
        Tweet tweet = getItem(position);
        // 2. Find or inflate the template
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_tweet, parent, false);
        }
        // 3. Find the subviews to fill with dat in the template
        ImageView ivProfileImage = (ImageView) convertView.findViewById(R.id.ivProfileImage);
        TextView tvUserName = (TextView) convertView.findViewById(R.id.tvUserName);
        TextView tvBody = (TextView) convertView.findViewById(R.id.tvBody);
        // 4. Populate data into subviews
        tvUserName.setText(tweet.getUser().getScreenName());
        tvBody.setText(tweet.getBody());
        ivProfileImage.setImageResource(android.R.color.transparent);
        Picasso.with(getContext()).load(tweet.getUser().getProfileImageUrl()).into(ivProfileImage);
        // 5. Return the view to be inserted into the list
        return convertView;
    }
}
