package com.kidkare.navigationdrawer;

import java.util.ArrayList;
import java.util.regex.Pattern;

import com.example.navigationdrawer.R;
import com.kidkare.dataaccessobjects.Child;
import com.kidkare.dataaccessobjects.Parent;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends FragmentActivity {
	private final String[] data ={"Dashboard","Notifications","Daily Sheet", "Calendar", "Contact"};
	private final String[] fragments ={
			"com.kidkare.navigationdrawer.FragmentDashboard",
			"com.kidkare.navigationdrawer.FragmentNotifications",
			"com.kidkare.navigationdrawer.FragmentDailysheet",
			"com.kidkare.navigationdrawer.FragmentCalendar",
			"com.kidkare.navigationdrawer.FragmentContact"};
	
	private DrawerLayout drawer;
	private ListView navList;
	private Context context;
	public static Parent parent;
	public static Child child;
	public static SharedPreferences prefs;
	private ActionBarDrawerToggle mDrawerToggle;
	ArrayList<String> accountList = new ArrayList<String>();
	public static DatabaseInterface datasource;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
		 setContentView(R.layout.activity_main);
		 context = this;
		 
		 datasource = new DatabaseInterface(this);
		 datasource.open();
		 prefs = PreferenceManager.getDefaultSharedPreferences(this);
		 if(!prefs.getBoolean("firstTime", false)){
			datasource.simulateExternalDatabase();
			SharedPreferences.Editor editor = prefs.edit();
			editor.putBoolean("firstTime", true);
			editor.commit();
		 }
		 
		 
		 ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActionBar().getThemedContext(), android.R.layout.simple_list_item_1, data);

		 drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
		 navList = (ListView) findViewById(R.id.drawer);
		 
		 drawer.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
		 
		 mDrawerToggle = new ActionBarDrawerToggle(
	                this,                  /* host Activity */
	                drawer,         /* DrawerLayout object */
	                R.drawable.ic_drawer,  /* nav drawer icon to replace 'Up' caret */
	                R.string.drawer_open,  /* "open drawer" description */
	                R.string.drawer_close  /* "close drawer" description */
	                ) {

	            /** Called when a drawer has settled in a completely closed state. */
	            public void onDrawerClosed(View view) {
	                getActionBar().setTitle("Kid Kare");
	            }

	            /** Called when a drawer has settled in a completely open state. */
	            public void onDrawerOpened(View drawerView) {
	                getActionBar().setTitle("Kid Kare");
	            }
	        };
	        
	     drawer.setDrawerListener(mDrawerToggle);
	     getActionBar().setDisplayHomeAsUpEnabled(true);
	     getActionBar().setHomeButtonEnabled(true);


		 
		 //drawer
		 navList.setAdapter(adapter);
		 navList.setOnItemClickListener(new OnItemClickListener(){
		         @Override
		         public void onItemClick(AdapterView<?> parent, View view, final int pos,long id){
		                 drawer.setDrawerListener( new DrawerLayout.SimpleDrawerListener(){
		                         @Override
		                         public void onDrawerClosed(View drawerView){
		                                 super.onDrawerClosed(drawerView);
		                                 FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
		                                 tx.replace(R.id.main, Fragment.instantiate(MainActivity.this, fragments[pos]));
		                                 tx.commit();
		                         }
		                 });
		                 drawer.closeDrawer(navList);
		         }
		 });
		 FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
		 tx.replace(R.id.main,Fragment.instantiate(MainActivity.this, fragments[0]));
		 tx.commit();
	}
	
	public void createAccountDialog() {
		Pattern emailPattern = Patterns.EMAIL_ADDRESS; // API level 8+
		Account[] accounts = AccountManager.get(this.getApplicationContext()).getAccounts();
		for (Account account : accounts) {
		    if (emailPattern.matcher(account.name).matches()) {
		        String possibleEmail = account.name;
		        if(!accountList.contains(possibleEmail)){
		        	accountList.add(possibleEmail);
		        }
		        
		    }
		}
		
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setCancelable(false);
		builder.setTitle("Select Account");
		builder.setItems(accountList.toArray(new String[accountList.size()]), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            	setAccount(which);
        }
		});
		builder.create();
		builder.show();
		
	}
	
	private void setAccount(int num){
		parent = datasource.getParent(accountList.get(num));
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
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
          return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
      datasource.open();
      super.onResume();
    }

    @Override
    protected void onPause() {
      datasource.close();
      super.onPause();
    }
	

}
