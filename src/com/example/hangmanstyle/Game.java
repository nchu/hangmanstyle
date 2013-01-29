package com.example.hangmanstyle;

import java.util.ArrayList;
import java.util.Random;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        
        words = getResources().getStringArray(R.array.words);
        wordsGuessed = new ArrayList<String>();
        image = (ImageView) findViewById(R.id.hangmanImage);
        init();
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_game, menu);
        return true;
    }
    
    @SuppressLint("NewApi") 
    public void onLetterClick(View view) {
    	triesLeft--;
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
    	Toast toast = Toast.makeText(getApplicationContext(), R.string.success, Toast.LENGTH_SHORT);
    	toast.show();
    	reset();
    }
    
    public void onWordFailure() {
    	image.setImageResource(R.drawable.p7);
    	Toast toast = Toast.makeText(getApplicationContext(), R.string.failure, Toast.LENGTH_SHORT);
    	toast.show();
    	reset();
    }
    
    public void onLetterSuccess(Button button) {
    	
    	image.setImageResource(R.drawable.p7);
    	Toast toast = Toast.makeText(getApplicationContext(), R.string.success, Toast.LENGTH_SHORT);
    	
		StringBuilder builder = new StringBuilder();
		builder.append(currentGuess);
		
    	for( int i = 0; i < currentWord.length(); i++) {
    		if( currentWord.charAt(i) == currentLetterGuessed.charAt(0) ) {
    			builder.setCharAt(i, currentLetterGuessed.charAt(0));
    		}
    	}
		button.setVisibility(View.INVISIBLE);
		button.setClickable(false);
		
		currentGuess = builder.toString();
		updateGuess(currentGuess);
		
    	
    	if( currentGuess.equals(currentWord) ) onWordSuccess();
    }
    
    public void onLetterFailure(Button button) {
    	
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
    	return currentWord.contains(c);
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
    
}
