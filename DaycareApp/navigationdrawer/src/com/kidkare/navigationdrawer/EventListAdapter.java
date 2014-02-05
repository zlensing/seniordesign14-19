package com.kidkare.navigationdrawer;

import java.util.List;
import com.kidkare.dataaccessobjects.Event;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.navigationdrawer.R;

public class EventListAdapter extends ArrayAdapter<Event>{

	private List<Event> data = null;
	private static LayoutInflater inflater=null;
	
	public EventListAdapter(Context context, int layoutResourceId, List<Event> data) {
		super(context, layoutResourceId, data);
		this.data = data;
	}
	
	@SuppressLint("DefaultLocale")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		if(convertView == null){
			inflater = LayoutInflater.from(getContext());
			row = inflater.inflate(R.layout.notification_list_item, null);
		}
		
		TextView title = (TextView)row.findViewById(R.id.notificationTitle);
		TextView description = (TextView)row.findViewById(R.id.notificationDescription);
		
		Event e = data.get(position);
		title.setText(e.getTitle());
		description.setText(e.getDescription());
		
		return row;
	}
}
