package com.example.sa38team7ad;

import org.json.*;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class LoginActivity extends Activity {

	EditText email;
	EditText password;
	Button login;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		email = (EditText) findViewById(R.id.emailEditText);
		password = (EditText) findViewById(R.id.passwordEditText);
		login = (Button) findViewById(R.id.loginButton);
		login.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				new Login().execute();
			}
		});
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
	
	private class Login extends AsyncTask<Void, Void, Void>{
		JSONObject result = new JSONObject();
		@Override
		protected Void doInBackground(Void... params) {
			JSONObject jo = new JSONObject();
			try {
				jo.put("Email", email.getText().toString());
				jo.put("Password", password.getText().toString());
				String s = JsonParser.postStream(R.string.wcf_service + "login", jo.toString());
				result = new JSONObject(s);
			} catch (JSONException e) {
				Log.i("JSON EXCEPTION", e.getMessage());
			}
			return null;
		}
		
		protected Void onPostExecute(Void... params){
			try {
				Toast t = Toast.makeText(LoginActivity.this, result.getString("RoleName"), Toast.LENGTH_LONG);
				t.show();
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return null;
		}

	}
	
}
