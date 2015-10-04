package com.assassin.assassinandroidgame.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.assassin.assassinandroidgame.R;
import com.parse.ParseUser;

public class LaunchActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        if (ParseUser.getCurrentUser() != null) {
            // Start an intent for the logged in activity
            startActivity(new Intent(this, JoinGameActivity.class));
        } else {
            // Start and intent for the logged out activity
            startActivity(new Intent(this, LoginActivity.class));

        }
    }
}
