package com.example.hangmanstyle;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.app.Activity;
import android.content.Intent;

public class MainView extends Activity {
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);
    }
    
    protected void startGame(View view) {
    	try {
    		Intent i = new Intent(this, Game.class);
    		startActivity(i);
    	}
    	catch(Exception e) {
    		Log.e("exception", "Could not start game - intent failed: " + e.getMessage());
    	}
    }
    
    protected void showAbout(View view) {
    	try {
    		Intent i = new Intent(this, AboutView.class);
    		startActivity(i);
    	}
    	catch(Exception e) {
    		Log.e("exception", "Could not show about page - intent failed: " + e.getMessage());
    	}
    }

   
    
}
