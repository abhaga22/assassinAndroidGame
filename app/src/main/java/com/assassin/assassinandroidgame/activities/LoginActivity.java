package com.assassin.assassinandroidgame.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.assassin.assassinandroidgame.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by seanrsain on 10/3/15.
 */
public class LoginActivity extends AppCompatActivity{

    Button loginButton;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(this);
        callbackManager = CallbackManager.Factory.create();

        setContentView(R.layout.activity_login);
        loginButton = (Button) findViewById(R.id.login_button);
//        loginButton.setReadPermissions("user_friends");
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<String> permissions = new ArrayList<>();
                permissions.add("user_friends");
                ParseFacebookUtils.logInWithReadPermissionsInBackground(LoginActivity.this, permissions, new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException err) {
                        if (user == null) {
                            Log.d("MyApp", "Uh oh. The user cancelled the Facebook login.");
                        } else if (user.isNew()) {
                            Log.d("MyApp", "User signed up and logged in through Facebook!");
                            presentJoinGameActivity();
                        } else {
                            Log.d("MyApp", "User logged in through Facebook!");
                            presentJoinGameActivity();
                        }
                    }
                });

            }
        });
        // If using in a fragment

        // Other app specific specialization

        // Callback registration
        /*loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {


            }

            @Override
            public void onCancel() {
                // App code

            }

            @Override
            public void onError(FacebookException exception) {
                // App code

            }
        });*/
    }

    private void presentJoinGameActivity() {
        // go to join game

        Intent intent = new Intent(getApplicationContext(), JoinGameActivity.class);

        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ParseFacebookUtils.onActivityResult(requestCode, resultCode, data);
    }
}
