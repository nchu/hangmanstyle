package com.example.hangmanstyle;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class MainView extends Activity {
	
	private AppPrefs appPrefs;
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);
        
        Context context = getApplicationContext();
        appPrefs = new AppPrefs(context);
          
        int gamesWon = 0;
        int gamesLost = 0;
        try
        {
         	gamesWon = appPrefs.getGamesWon();
        	gamesLost = appPrefs.getGamesLost();
        }
        catch(Exception e){}
        
     	setWinsAndLossesText(gamesWon, gamesLost);

    }
    
    private void setWinsAndLossesText(int wins, int losses)
    {
    	 TextView textView = (TextView) findViewById(R.id.winsAndLossesCount);
    	 textView.setText(getResources().getString(R.string.wins) + " " + wins + " " + getResources().getString(R.string.and) + " " + losses +getResources().getString(R.string.losses));
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
