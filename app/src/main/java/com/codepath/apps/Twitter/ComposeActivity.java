package com.codepath.apps.Twitter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.codepath.apps.Twitter.models.Tweet;
import com.codepath.apps.Twitter.models.User;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.apache.http.Header;
import org.json.JSONObject;

public class ComposeActivity extends AppCompatActivity {
    private TwitterClient client;
    EditText edBody;
    String data;
    ImageView ivProfilePicture;
    TextView tvUserName;
    TextView tvUserId;
    Tweet tweet;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);
        setCustomActionBar();
        edBody = (EditText) findViewById(R.id.etBody);
        tvUserName = (TextView) findViewById(R.id.myUserName);
        tvUserId = (TextView) findViewById(R.id.myId);
        ivProfilePicture = (ImageView) findViewById(R.id.myProfileImage);

        client = TwitterApplication.getRestClient();// singleton client
        getLoginUser();
    }

    private void setCustomActionBar() {
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Compose new Tweet");
        getSupportActionBar().setLogo(R.drawable.twitter_icon);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
    }

    private void getLoginUser() {
        client.getLoginJson(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                setupView(response);
                //Toast.makeText(TimelineActivity.this, User.fromJSON(response).getScreenName(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });
    }

    private void setupView(JSONObject response) {
        user = User.fromJSON(response);
        Picasso.with(ComposeActivity.this).load(user.getProfileImageUrl()).into(ivProfilePicture);
        tvUserName.setText(user.getName());
        tvUserId.setText(user.getUid()+"");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_compose, menu);
        return true;
    }

    public void onClickTweet(MenuItem item) {
        data = edBody.getText().toString();
        tweet = new Tweet(user, data);
        Intent returnIntent = new Intent();
        returnIntent.putExtra("MyTweet", tweet);
        setResult(RESULT_OK, returnIntent);
        finish();
    }
}
