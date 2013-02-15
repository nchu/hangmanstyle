package com.example.hangmanstyle;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class AppPrefs {
 private static final String USER_PREFS = "USER_PREFS";
 private SharedPreferences appSharedPrefs;
 private SharedPreferences.Editor prefsEditor;
 private String games_won = "user_games_won";
 private String games_lost = "user_games_lost";

public AppPrefs(Context context){
 this.appSharedPrefs = context.getSharedPreferences(USER_PREFS, Activity.MODE_PRIVATE);
 this.prefsEditor = appSharedPrefs.edit();
 }
public int getGamesWon() {
 return appSharedPrefs.getInt(games_won, 0);
 }

public void setGamesWon(int gamesWon) {
 prefsEditor.putInt(games_won, gamesWon).commit();
}
public int getGamesLost() {
 return appSharedPrefs.getInt(games_lost, 0);
 }

 public void setGamesLost( int gamesLost) {
 prefsEditor.putInt(games_lost, gamesLost).commit();
 }

}