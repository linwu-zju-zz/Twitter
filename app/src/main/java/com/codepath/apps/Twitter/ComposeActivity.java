package com.codepath.apps.Twitter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class ComposeActivity extends AppCompatActivity {
    EditText edBody;
    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);
        setCustomActionBar();
        edBody = (EditText) findViewById(R.id.etBody);
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
