package com.example.sa38team7ad;

import com.example.sa38team7ad.DisburstmentListActivity.DisbursementListFragment;
import com.example.sa38team7ad.ReportDiscrepancyActivity.ReportDiscrepancyFragment;
import com.example.sa38team7ad.RetrieveStationeryActivity.RetrieveStationeryFragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class StoreClerkMainActivity extends Activity {

	private int mCurrentSelectedPosition = 0;
	//private View mFragmentContainerView;
	
	DrawerLayout mDrawerLayout;
    ListView mDrawerList;
    String[] mPlanetTitles;
    String role;
    ActionBarDrawerToggle mDrawerToggle;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_store_clerk_main);
		
	    mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
	    
	    mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer icon to replace 'Up' caret */
                R.string.navigation_drawer_open,  /* "open drawer" description */
                R.string.navigation_drawer_close/* "close drawer" description */
                ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getActionBar().setTitle(R.string.app_name);
                
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActionBar().setTitle(role);
            }
        };

        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
	    
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mDrawerList.setBackgroundColor(android.graphics.Color.LTGRAY);
        role = getIntent().getExtras().getString("role");
        mPlanetTitles = getResources().getStringArray(R.array.store_clerk_menu_array);

        mDrawerList
		.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				selectItem(position);
			}
		});	
        

        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, mPlanetTitles));
        
        mDrawerList.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				onNavigationDrawerItemSelected(position);
				
			}
        	
        });
	}
	
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by replacing fragments
	    Fragment fragment = new PlaceholderFragment();
	    Bundle args = new Bundle();
	    
	    
	    android.app.FragmentManager fragmentManager = getFragmentManager();
	    switch(position) {
	        case 0:
	        	args.putInt(RetrieveStationeryFragment.ARG_RETRIEVE_NUMBER, position);
	        	fragment.setArguments(args);
	            fragment = new RetrieveStationeryFragment();
	            break;
	            
	        case 1:
	        	args.putInt(DisbursementListFragment.ARG_DISBURSEMENT_NUMBER, position);
	        	fragment.setArguments(args);
	            fragment = new DisbursementListFragment();
	            break;
	            
	        case 2:
	        	args.putInt(ReportDiscrepancyFragment.ARG_REPORTDISC_NUMBER, position);
	        	fragment.setArguments(args);
	            fragment = new ReportDiscrepancyFragment();
	            break;
	    }
	    fragmentManager.beginTransaction()
	        .replace(R.id.content_frame, fragment)
	        .commit();
	}


	@Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }
	
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.store_clerk_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void selectItem(int position) {
		mCurrentSelectedPosition = position;
		if (mDrawerList != null) {
			mDrawerList.setItemChecked(position, true);
		}
	}
	
	
	
	public static class PlaceholderFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_SECTION_NUMBER2 = "section_number2";

		/**
		 * Returns a new instance of this fragment for the given section number.
		 */
		public static PlaceholderFragment newInstance(int sectionNumber) {
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER2, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_dept_head_main,
					container, false);
			return rootView;
		}

		@Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);
			((DeptHeadMainActivity) activity).onSectionAttached(getArguments()
					.getInt(ARG_SECTION_NUMBER2));
		}
	}
}


