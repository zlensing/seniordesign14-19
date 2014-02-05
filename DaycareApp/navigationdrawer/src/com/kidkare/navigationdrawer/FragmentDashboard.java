package com.kidkare.navigationdrawer;

import java.util.ArrayList;
import java.util.regex.Pattern;

import com.example.navigationdrawer.R;
import com.kidkare.dataaccessobjects.Parent;
import com.kidkare.dataaccessobjects.Child;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
 
public class FragmentDashboard extends Fragment {
	private static ViewGroup root;
	ArrayList<String> accountList = new ArrayList<String>();
 
    public static Fragment newInstance(Context context) {
    	FragmentDashboard f = new FragmentDashboard();
        return f;
    }
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        root = (ViewGroup) inflater.inflate(R.layout.fragment_dashboard, null);
        if(!MainActivity.prefs.getBoolean("signedIn", false)){
        	createAccountDialog();
        	SharedPreferences.Editor editor = MainActivity.prefs.edit();
			editor.putBoolean("signedIn", true);
			editor.commit();
        }
        else{
        	String delims = "[,]";
        	String[] parentTokens = MainActivity.prefs.getString("parent", "").split(delims);
        	String[] childTokens = MainActivity.prefs.getString("child", "").split(delims);
        	
        	MainActivity.parent = new Parent(Integer.parseInt(parentTokens[0]), parentTokens[1], parentTokens[2],
        			parentTokens[3], parentTokens[4], Integer.parseInt(parentTokens[5]));
        	MainActivity.child = new Child(Integer.parseInt(childTokens[0]), childTokens[1],
        			Integer.parseInt(childTokens[2]), Integer.parseInt(childTokens[3]));
        			
        	TextView t = (TextView)root.findViewById(R.id.welcomeText);
    		t.setText("Welcome, " + MainActivity.parent.getName());
        }
        Button b = (Button)root.findViewById(R.id.logoutButton);
        b.setOnClickListener(new OnClickListener(){

        	@Override
        	public void onClick(View v) {
        		createAccountDialog();
        	}
        });
        
        return root;
    }
    
	public void createAccountDialog() {
		Pattern emailPattern = Patterns.EMAIL_ADDRESS; // API level 8+
		Account[] accounts = AccountManager.get(root.getContext()).getAccounts();
		for (Account account : accounts) {
		    if (emailPattern.matcher(account.name).matches()) {
		        String possibleEmail = account.name;
		        if(!accountList.contains(possibleEmail)){
		        	accountList.add(possibleEmail);
		        }
		        
		    }
		}
		
		AlertDialog.Builder builder = new AlertDialog.Builder(root.getContext());
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
		MainActivity.parent = MainActivity.datasource.getParent(accountList.get(num));
		MainActivity.child = MainActivity.datasource.getChildFromParent(MainActivity.parent.getId());
		SharedPreferences.Editor editor = MainActivity.prefs.edit();
		editor.putString("parent", MainActivity.parent.toString()).commit();
		editor.putString("child", MainActivity.child.toString()).commit();
		TextView t = (TextView)root.findViewById(R.id.welcomeText);
		t.setText("Welcome, " + MainActivity.parent.getName());
	}
 
}