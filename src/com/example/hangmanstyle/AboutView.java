package com.example.hangmanstyle;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class AboutView extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceBundle) {
		super.onCreate(savedInstanceBundle);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.about_view);
	}
	
}
