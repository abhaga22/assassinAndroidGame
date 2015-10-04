package com.assassin.assassinandroidgame.activities;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.assassin.assassinandroidgame.R;
import com.assassin.assassinandroidgame.adapters.LobbyAdapter;
import com.google.gson.Gson;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParsePush;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LobbyActivity extends Activity {

    public static final String IS_HOST = "IS_HOST";

    private List<ParseUser> mUserList;

    private RecyclerView rvPlayers;
    private LobbyAdapter lobbyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);


        ParseUser myUser = ParseUser.getCurrentUser();

        // Set up the listview
        mUserList = new ArrayList<ParseUser>();

        loadParseUsers();

        // ...
        // Lookup the recyclerview in activity layout
        rvPlayers = (RecyclerView) findViewById(R.id.rvPlayers);
        // Create adapter passing in the sample user data
        lobbyAdapter = new LobbyAdapter(mUserList);
        // Attach the adapter to the recyclerview to populate items
        rvPlayers.setAdapter(lobbyAdapter);
        // Set layout manager to position the items
        rvPlayers.setLayoutManager(new LinearLayoutManager(this));
        // That's all!


        final Handler handler = new Handler();

        final Runnable r = new Runnable() {
            public void run() {
                loadParseUsers();
                handler.postDelayed(this, 5000);
            }
        };

        handler.postDelayed(r, 5000);

    }




    private void loadParseUsers(){

        final ParseQuery query = ParseUser.getQuery();
        query.findInBackground(new FindCallback<ParseObject>() {

            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    Toast.makeText(getApplicationContext(), objects.toString(), Toast.LENGTH_LONG)
                            .show();


                    for (int i = 0; i < objects.size(); i++) {

                        ParseUser u = (ParseUser)objects.get(i);
                        mUserList.add(u);
                    }

                    lobbyAdapter.setPlayers(mUserList);

                } else {
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
