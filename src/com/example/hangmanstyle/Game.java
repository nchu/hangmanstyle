package com.example.hangmanstyle;

import java.util.ArrayList;
import java.util.Random;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class Game extends Activity {
	
	private String[] words;
	private ArrayList<String> wordsGuessed;
	private String currentWord;
	private String currentGuess;
	private String currentLetterGuessed;
	private int triesLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        
        words = getResources().getStringArray(R.array.words);
        wordsGuessed = new ArrayList<String>();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_game, menu);
        return true;
    }
    
    @SuppressLint("NewApi") 
    public void onKeyUp(KeyEvent e) {
    	int letterCode = e.getKeyCode();
    	String letter = KeyEvent.keyCodeToString(letterCode);
    	
    	if( triesLeft > 0) {
    		if (isLegalLetter(letter)) onLetterSuccess();
    		else onLetterFailure();
    	}
    	else {
    		if( currentGuess.equals(currentWord) ) onWordSuccess();
    		else onWordFailure();
    	}
    }
    
    public void onWordSuccess() {
    	Toast toast = Toast.makeText(getApplicationContext(), R.string.success, Toast.LENGTH_SHORT);
    	toast.show();
    	reset();
    }
    
    public void onWordFailure() {
    	Toast toast = Toast.makeText(getApplicationContext(), R.string.failure, Toast.LENGTH_SHORT);
    	toast.show();
    	reset();
    }
    
    public void onLetterSuccess() {
    	for( int i = 0; i < currentWord.length(); i++) {
    		if( currentWord.charAt(i) == currentLetterGuessed.charAt(0) ) {
    			StringBuilder builder = new StringBuilder();
    			builder.append(currentGuess);
    			builder.setCharAt(i, currentLetterGuessed.charAt(0));
    			
    			currentGuess = builder.toString();
    		}
    	}
    	
    	if( currentGuess.equals(currentWord) ) onWordSuccess();
    }
    
    public void onLetterFailure() {
    	// endre bilde
    	
    	// endre count pŒ fors¿k
    	// sjekk om siste fors¿k
    }
    
    
    private boolean isLegalLetter(String c) {
    	return currentWord.contains(c);
    }
    
    private void reset() {
    	currentWord = getRandomWord();
    	wordsGuessed.add(currentWord);
    	
    	triesLeft = 6;
    	StringBuilder builder = new StringBuilder();
    	
    	for( int i = 0; i < currentWord.length(); i++ ) {
    		builder.append('-');
    	}
    	currentGuess = builder.toString();
    	
    	updateGuess(currentGuess);
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
    	while( !wordsGuessed.contains(word));
    	
    	return word;
    }
    
}
