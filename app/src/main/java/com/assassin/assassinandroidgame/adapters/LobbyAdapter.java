package com.assassin.assassinandroidgame.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.assassin.assassinandroidgame.R;
import com.parse.Parse;
import com.parse.ParseUser;

import java.util.List;

/**
 * Created by seanrsain on 10/4/15.
 */
// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class LobbyAdapter extends
        RecyclerView.Adapter<LobbyAdapter.ViewHolder> {

    private List<ParseUser> mUserList;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView playerTextView;


        public ViewHolder(View itemView) {

            super(itemView);

            playerTextView = (TextView) itemView.findViewById(R.id.player_name);
        }
    }

    public LobbyAdapter(List<ParseUser> players) {
        mUserList = players;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_lobby_player, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        // Get the data model based on position
        ParseUser player = mUserList.get(position);

        // Set item views based on the data model
        TextView textView = viewHolder.playerTextView;
        textView.setText(player.getUsername());


    }

    // Return the total count of items
    @Override
    public int getItemCount() {
        return mUserList.size();
    }

    public void addPlayer(ParseUser player) {

        if (!mUserList.contains(player)) {
            mUserList.add(player);
        }

    }

    public void addPlayers(List<ParseUser> players) {

        for (ParseUser player : players) {

            addPlayer(player);
        }

        notifyDataSetChanged();
    }
}
