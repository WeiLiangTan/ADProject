package com.example.sa38team7ad;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class LoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		if (id == R.id.deptHead){
			Intent i = new Intent(this, DeptHeadMainActivity.class);
			startActivity(i);
			return true;
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	public void click1(View v){
		Intent i = new Intent(this, DeptHeadMainActivity.class);
		//i.putExtra("role","DeptHead");
		startActivity(i);
	}
	public void click2(View v){
		Intent i = new Intent(this, StoreClerkMainActivity.class);
		i.putExtra("role","StoreClerk");
		startActivity(i);
	}
}
