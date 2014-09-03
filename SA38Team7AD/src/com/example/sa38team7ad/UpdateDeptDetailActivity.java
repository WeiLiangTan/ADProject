package com.example.sa38team7ad;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class UpdateDeptDetailActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update_dept_detail);
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new UpdateDeptDetailFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.update_dept_detail, menu);
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
	public static class UpdateDeptDetailFragment extends Fragment implements AdapterView.OnItemSelectedListener{

		public static final String ARG_UPDATEDEPT_NUMBER = "updateDept_number";

		TextView repNameTextView;
		TextView colPtTextView;
		Spinner spinner;
		RadioGroup rg;
		RadioButton r1;RadioButton r2;RadioButton r3;
		RadioButton r4;RadioButton r5;RadioButton r6;
		Button updateBtn;
		
		List<User> employees;
		ArrayList<CollectionPoint> collectionPoints;
		ArrayList<String> empNames;
		
		CollectionPoint collectionPoint;
		String repName;
		String repId;
		String deptID = "comm";
		
		public UpdateDeptDetailFragment() {
		}
		
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			employees = new ArrayList<User>();
			empNames = new ArrayList<String>();
			collectionPoints = new ArrayList<CollectionPoint>();
			getDepartmentEmployee(deptID);
			getCollectionPoints();
			getDepartmentDetail(deptID);
		}
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(
					R.layout.fragment_update_dept_detail, container, false);
			
			repNameTextView = (TextView)rootView.findViewById(R.id.textView2);
			colPtTextView = (TextView)rootView.findViewById(R.id.textView4);
			
			spinner = (Spinner)rootView.findViewById(R.id.spinner1);
			spinner.setOnItemSelectedListener(this);
			
			rg = (RadioGroup)rootView.findViewById(R.id.radioGroup1);
			
			r1 = (RadioButton)rootView.findViewById(R.id.radioButton1);
			r2 = (RadioButton)rootView.findViewById(R.id.radioButton2);
			r3 = (RadioButton)rootView.findViewById(R.id.radioButton3);
			r4 = (RadioButton)rootView.findViewById(R.id.radioButton4);
			r5 = (RadioButton)rootView.findViewById(R.id.radioButton5);
			r6 = (RadioButton)rootView.findViewById(R.id.radioButton6);
			
			rg.setOnCheckedChangeListener(new OnCheckedChangeListener(){

				@Override
				public void onCheckedChanged(RadioGroup group, int checkedId) {
					if (r1.isChecked()) collectionPoint = collectionPoints.get(0);
					if (r2.isChecked()) collectionPoint = collectionPoints.get(1);
					if (r3.isChecked()) collectionPoint = collectionPoints.get(2);
					if (r4.isChecked()) collectionPoint = collectionPoints.get(3);
					if (r5.isChecked()) collectionPoint = collectionPoints.get(4);
					if (r6.isChecked()) collectionPoint = collectionPoints.get(5);
				}});
			
			updateBtn = (Button)rootView.findViewById(R.id.button1);
//			updateBtn.setOnClickListener(new OnClickListener(){
//
//				@Override
//				public void onClick(View v) {
//					// TODO Auto-generated method stub
//					for(int i = 0; i < employees.size();i++){
//						if(employees.get(i).get("Name") == repName){
//							repId = employees.get(i).get("UserId");
//							System.out.println(repId);
//							break;
//						}
//					}
//					System.out.println(deptID+" "+repId);
//					new UpdateDepartmentDetail().execute("http://10.10.2.126/ad/service1.svc//SetRep/"+deptID+"/"+repId,
//							"http://10.10.2.126/ad/service1.svc//SetCollectionPoint/"+deptID+"/"+collectionPoint.get("CpID"));
//					repNameTextView.setText(repName);
//					colPtTextView.setText(collectionPoint.get("CpName"));
//				}
//				
//			});
			return rootView;
		}

		@Override
		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {
			// TODO Auto-generated method stub
			TextView spinnerText = (TextView) view;
			Toast.makeText(getActivity(), spinnerText.getText(), Toast.LENGTH_SHORT).show();
			repName = spinnerText.getText().toString();
			System.out.println("1. "+repName);
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			// TODO Auto-generated method stub			
		}
		
		public void updateClick(){
			for(int i = 0; i < employees.size();i++){
				if(employees.get(i).get("Name")==repName){
					repId = employees.get(i).get("UserId");
					break;
				}
			}
			System.out.println(deptID+" "+repId);
			new UpdateDepartmentDetail().execute("http://10.10.2.126/ad/service1.svc//SetRep/"+deptID+"/"+repId);
			repNameTextView.setText(repName);
//			"http://10.10.2.126/ad/service1.svc//SetCollectionPoint/"+deptID+"/"+collectionPoint.get("CpID")
		}

	    public boolean getDepartmentEmployee(String deptId) {
	        String url = "http://10.10.2.126/ad/service1.svc//GetDepartmentEmployees/"+deptId;
	        new DownLoadDeptEmployee().execute(url);
	        return true;
	    }
	    
	    public boolean getCollectionPoints(){
	    	String url = "http://10.10.2.126/ad/service1.svc//AllCollectionPoints";
	    	new DownLoadCollectionPoint().execute(url);
	    	return true;
	    }
	    
	    public boolean getDepartmentDetail(String deptId){
			String url = "http://10.10.2.126/ad/service1.svc//GetDepartment/"+deptId;
			new DownLoadDepartmentInfo().execute(url);
	    	return true;
	    }
	    
	    private class DownLoadDeptEmployee extends AsyncTask<String, Void, JSONArray>{

			@Override
			protected JSONArray doInBackground(String... url) {
				// TODO Auto-generated method stub
				JSONArray a = JsonParser.getJSONArrayFromUrl(url[0]);
				return a;
			}
			
			protected void onPostExecute(JSONArray result){
				try {
					for (int i=0; i<result.length(); i++) {
						JSONObject c = result.getJSONObject(i);
						String isTempHead = "false";
						if (c.getBoolean("IsTempHead") == true) isTempHead = "true";
						employees.add(new User(Integer.toString(c.getInt("UserID")),
										  c.getString("Name"), 
	                                      c.getString("RoleName"),
	                                      c.getString("Password"),
	                                      c.getString("Email"),
	                                      c.getString("Phone"),
	                                      c.getString("DeptID"),
	                                      isTempHead));
						empNames.add(c.getString("Name"));
					}
					ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item,empNames);
					spinner.setAdapter(adapter);
				} catch (Exception e) {
					Log.e("DownLoad Error", "Fail to download employee list" + e.toString());
				}
			}	
	    }
	    
	    private class DownLoadCollectionPoint extends AsyncTask<String, Void, JSONArray>{

			@Override
			protected JSONArray doInBackground(String... url) {
				// TODO Auto-generated method stub
				JSONArray a = JsonParser.getJSONArrayFromUrl(url[0]);
				return a;
			}
			protected void onPostExecute(JSONArray result){
				try {
					for (int i=0; i<result.length(); i++) {
						JSONObject c = result.getJSONObject(i);
						collectionPoints.add(new CollectionPoint(c.getString("CpID"),c.getString("CpName")));
					}
				} catch (Exception e) {
					Log.e("DownLoad Error", "Fail to download collection point" + e.toString());
				}
			}
	    }
	    
	    private class DownLoadDepartmentInfo extends AsyncTask<String, Void, JSONObject>{

			@Override
			protected JSONObject doInBackground(String... url) {
				// TODO Auto-generated method stub
				JSONObject a = JsonParser.getJSONFromUrl(url[0]);
				System.out.println("1235");
				return a;
			}
			protected void onPostExecute(JSONObject result){
				System.out.println("12345");
				try {
					repName = result.getString("RepName");
					repId = Integer.toString(result.getInt("RepID"));
					repNameTextView.setText(repName);
					
					for (int i = 0; i<collectionPoints.size();i++){
						if(collectionPoints.get(i).get("CpID")==result.getString("CpID")){
							collectionPoint = collectionPoints.get(i);
							break;
						}
					}
					colPtTextView.setText(collectionPoint.get("CpName"));
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	    }
	    
	    private class UpdateDepartmentDetail extends AsyncTask<String, Void, Void>{

			@Override
			protected Void doInBackground(String... url) {
				try{
					JsonParser.getStream(url[0]);
					JsonParser.getStream(url[1]);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
	    	
	    }
	}
}
