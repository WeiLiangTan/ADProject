package com.example.sa38team7ad;

import org.json.*;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.os.Build;
import android.os.StrictMode.ThreadPolicy;

public class ApproveRequisitionActivity extends Activity {
	
	final static String HOST = "http://10.10.2.126/AD/Service1.svc/";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_approve_requisition);
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new ApproveRequisitionFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.approve_requisition, menu);
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
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class ApproveRequisitionFragment extends Fragment {

		public static final String ARG_APPROVEREQ_NUMBER = "approveReq_number";
		ListView lv;
		String deptID = "";
		String[] values; 

		public ApproveRequisitionFragment() {
		}
		
		@Override
		public void onCreate(Bundle savedInstanceState){
			super.onCreate(savedInstanceState);
			deptID = getArguments().getString("DeptID");
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(
					R.layout.fragment_approve_requisition, container, false);
			lv = (ListView) rootView.findViewById(R.id.reqListView);
			StrictMode.setThreadPolicy(ThreadPolicy.LAX);
			JSONArray ja = JsonParser.getJSONArrayFromUrl(HOST + "Requisitions/" + deptID);
			values = new String[ja.length()];
			for(int i = 0; i < ja.length(); i++){
				try {
					values[i] = ja.getJSONObject(i).getString("ReqName") + " -- " + ja.getJSONObject(i).getString("ReqDate");
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, values);
			lv.setAdapter(adapter);
			return rootView;
		}
	}
}
