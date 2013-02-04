package com.example.hangmanstyle;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;

public class MainView extends Activity {
	
	//Sharedpreferences stuff
	private static final String HANGMAN_STATS = "HANGMAN_STATS";
	private String user_wins = "user_wins_prefs";
	private String user_losses = "user_losses_prefs";
	private SharedPreferences appSharedPrefs;
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);
        
        this.appSharedPrefs = this.getSharedPreferences(HANGMAN_STATS, Activity.MODE_PRIVATE);
         
        int gamesWon = 0;
        int gamesLost = 0;
        try
        {
        	gamesWon = appSharedPrefs.getInt(user_wins, 0);
            gamesLost = appSharedPrefs.getInt(user_losses, 0);
        }
        catch(Exception e){}
        
     	setWinsAndLossesText(gamesWon, gamesLost);

    }
    
    private void setWinsAndLossesText(int wins, int losses)
    {
    	 TextView textView = (TextView) findViewById(R.id.winsAndLossesCount);
    	 textView.setText("@string/wins" + wins + "@string/and" + losses + "@string/losses");
    }
    
    public void startGame(View view) {
    	try {
    		Intent i = new Intent(this, Game.class);
    		startActivity(i);
    	}
    	catch(Exception e) {
    		Log.e("exception", "Could not start game - intent failed: " + e.getMessage());
    	}
    }
    
    public void showAbout(View view) {
    	try {
    		Intent i = new Intent(this, AboutView.class);
    		startActivity(i);
    	}
    	catch(Exception e) {
    		Log.e("exception", "Could not show about page - intent failed: " + e.getMessage());
    	}
    }

   
    
}
