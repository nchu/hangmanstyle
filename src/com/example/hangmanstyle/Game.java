package com.example.hangmanstyle;

import java.util.ArrayList;
import java.util.Random;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageView;

public class Game extends Activity {
	
	private String[] words;
	private ArrayList<String> wordsGuessed;
	private String currentWord;
	private String currentGuess;
	private String currentLetterGuessed;
	private int triesLeft;
	private ImageView image;
	private int gamesWon;
	private int gamesLost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        
        words = getResources().getStringArray(R.array.words);
        wordsGuessed = new ArrayList<String>();
        image = (ImageView) findViewById(R.id.hangmanImage);
        init();
        
        // Load gamesWon and gamesLost from preferences
       // SharedPreferences 
    }
    
    private void init()
    {
    	currentWord = getRandomWord();
    	wordsGuessed.add(currentWord);
    	
    	triesLeft = 6;
    	StringBuilder builder = new StringBuilder();
    	
    	for( int i = 0; i < currentWord.length(); i++ ) {
    		builder.append('-');
    	}
    	currentGuess = builder.toString();
    	
    	updateGuess(currentGuess);
    	
    	image.setImageResource(R.drawable.p1);
    	updateCounter(triesLeft);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_game, menu);
        return true;
    }
    
    @SuppressLint("NewApi") 
    public void onLetterClick(View view) {

    	Button button = (Button) view;
    	currentLetterGuessed = (String) button.getText();

    	if( triesLeft > 0) {
    		if (isLegalLetter(currentLetterGuessed))
    		{
    			onLetterSuccess(button);
    		}
    		else onLetterFailure(button);
    	}
    	else {
    		if( currentGuess.equals(currentWord) )
    		{
    			onWordSuccess();
    		}
    		else onWordFailure();
    	}
    }
    
    public void onWordSuccess() {
    	AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);

    	dlgAlert.setTitle("WIN!");
    	dlgAlert.setIcon(R.drawable.fuckyeah);
    	dlgAlert.setCancelable(true);
    	dlgAlert.setPositiveButton("Play again",
    		    new DialogInterface.OnClickListener() {
    		        public void onClick(DialogInterface dialog, int which) {
    		        	reset(); 
    		        }
    		    });
    	dlgAlert.create().show();
    }
    
    public void onWordFailure() {
    	image.setImageResource(R.drawable.p7);
    	
    	AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);

    	dlgAlert.setTitle("FAIL!");
    	dlgAlert.setIcon(R.drawable.okay);
    	dlgAlert.setCancelable(true);
    	dlgAlert.setPositiveButton("Try again",
    		    new DialogInterface.OnClickListener() {
    		        public void onClick(DialogInterface dialog, int which)
    		        {
    		        	reset(); 
    		        }
    		    });
    	dlgAlert.create().show();
    }
    
    public void onLetterSuccess(Button button) {
		StringBuilder builder = new StringBuilder();
		builder.append(currentGuess);
		
    	for( int i = 0; i < currentWord.length(); i++) {
    		if( currentWord.toLowerCase().charAt(i) == currentLetterGuessed.toLowerCase().charAt(0) ) {
    			builder.setCharAt(i, currentLetterGuessed.charAt(0));
    		}
    	}
		button.setVisibility(View.INVISIBLE);
		button.setClickable(false);
		
		currentGuess = builder.toString();
		updateGuess(currentGuess);
		
    	
    	if( currentGuess.toLowerCase().equals(currentWord.toLowerCase()) ) onWordSuccess();
    }
    
    public void onLetterFailure(Button button) {
    	
    	triesLeft--;
    	updateCounter(triesLeft);
    	
		button.setVisibility(View.INVISIBLE);
		button.setClickable(false);

		int tries = 6 - triesLeft;
		switch(tries)
		{
			case 1:  image.setImageResource(R.drawable.p2); break;
			case 2:  image.setImageResource(R.drawable.p3); break;
			case 3:  image.setImageResource(R.drawable.p4); break;
			case 4:  image.setImageResource(R.drawable.p5); break;
			case 5:	 image.setImageResource(R.drawable.p6); break;
			case 6:  onWordFailure(); break;
			default:  image.setImageResource(R.drawable.p1);
		}
    	
    }
    
    
    private boolean isLegalLetter(String c) {
    	return currentWord.toLowerCase().contains(c.toLowerCase());
    }
    
    private void reset() {
    	init();
    	
    	Resources res = getResources();
    	int id = res.getIdentifier("titleText", "id", getBaseContext().getPackageName());
    	
    	TableLayout tl = (TableLayout)findViewById(R.id.tableLayout1);
    	for(int i = 0; i < tl.getChildCount(); i++)
    	{
    		TableRow row = (TableRow)tl.getChildAt(i);
    		for(int j = 0; j < row.getChildCount(); j++)
    		{
    			Button but = (Button) row.getChildAt(j);
    			but.setVisibility(View.VISIBLE);
    			but.setClickable(true);	
    		}
    	}

    }
    
    private void updateGuess(String newWord) {
    	TextView view = (TextView) findViewById(R.id.word);
    	
    	view.setText(newWord);
    }
    
    private String getRandomWord() {
    	Random rand = new Random();
    	
    	String word;
    	
    	do {
    		word = words[rand.nextInt(words.length)];
    	}
    	while(wordsGuessed.contains(word));
    	
    	return word;
    }
    
    private void updateCounter(int newCounter)
    {
    	TextView counter = (TextView) findViewById(R.id.counter);
    	counter.setText(newCounter + "");
    }
    
    @Override 
    protected void onStop()
    {
    	
    }
    
}
