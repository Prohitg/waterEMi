package com.cognus.waterdrinking;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;

public class MainActivity extends ActionBarActivity {

	SharedPreferences sharedpreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		sharedpreferences = getSharedPreferences(Utills.MyPREFERENCES,
				Context.MODE_PRIVATE);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void ReplaceFragment(Fragment fragment) {

		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction trancation = fragmentManager.beginTransaction();
		trancation.addToBackStack("hello");
		trancation.replace(R.id.main_frame, fragment);
		trancation.commit();

	}

}
