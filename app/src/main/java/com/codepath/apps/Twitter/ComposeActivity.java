package com.codepath.apps.Twitter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ComposeActivity extends AppCompatActivity {
    EditText edBody;
    String data;
    ImageView ivProfilePicture;
    TextView tvUserName;
    TextView tvUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);
        Intent intent = getIntent();
        String url = intent.getStringExtra("picture_url");
        String userName = intent.getStringExtra("user_name");
        String userId = intent.getStringExtra("user_id");
        setCustomActionBar();
        edBody = (EditText) findViewById(R.id.etBody);
        tvUserName = (TextView) findViewById(R.id.myUserName);
        tvUserId = (TextView) findViewById(R.id.myId);
        ivProfilePicture = (ImageView) findViewById(R.id.myProfileImage);
        Picasso.with(ComposeActivity.this).load(url).into(ivProfilePicture);
        tvUserName.setText(userName);
        tvUserId.setText(userId);

    }

    private void setCustomActionBar() {
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Compose new Tweet");
        getSupportActionBar().setLogo(R.drawable.twitter_icon);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_compose, menu);
        return true;
    }

    public void onClickTweet(MenuItem item) {
        data = edBody.getText().toString();
        Intent returnIntent = new Intent();
        returnIntent.putExtra("MyTweetBody", data);
        setResult(RESULT_OK, returnIntent);
        finish();
    }
}
