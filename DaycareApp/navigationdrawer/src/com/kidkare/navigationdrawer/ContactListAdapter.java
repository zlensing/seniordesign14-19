package com.kidkare.navigationdrawer;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.navigationdrawer.R;
import com.kidkare.dataaccessobjects.Teacher;

public class ContactListAdapter extends ArrayAdapter<Teacher> {

	 private List<Teacher> data = null;
	 private static LayoutInflater inflater=null;
	
	public ContactListAdapter(Context context, int layoutResourceId, List<Teacher> data) {
		super(context, layoutResourceId, data);
		this.data = data;
	}
	
	@SuppressLint("DefaultLocale")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		if(convertView == null){
			inflater = LayoutInflater.from(getContext());
			row = inflater.inflate(R.layout.contact_list_item, null);
		}
		
		TextView name = (TextView)row.findViewById(R.id.name);
		TextView title = (TextView)row.findViewById(R.id.title);
		TextView number = (TextView)row.findViewById(R.id.number);
		TextView email = (TextView)row.findViewById(R.id.email);
		
		Teacher t = data.get(position);
		name.setText(t.getName());
		title.setText(t.getTitle());
		number.setText(t.getnumber());
		email.setText(t.getEmail());
		
		return row;
	}
	
}
