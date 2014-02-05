package com.kidkare.navigationdrawer;

import java.util.ArrayList;

import com.example.navigationdrawer.R;
import com.kidkare.dataaccessobjects.Notification;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
 
public class FragmentNotifications extends Fragment {
	
	public ArrayList<Notification> notificationList = new ArrayList<Notification>();
 
    public static Fragment newInstance(Context context) {
    	FragmentNotifications f = new FragmentNotifications();
 
        return f;
    }
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View root = (ViewGroup) inflater.inflate(R.layout.fragment_notifications, null);
        
        /*
        Notification n1 = new Notification(1, 1, "Monday, Dec. 9, 2014:\nShow and Tell", "Hey this is a description of show and tell. It's "
        		+ "this thing about kids bringing stuff to school and showing it to other kids");
        Notification n2 = new Notification(1, 1, "Thursday, Dec. 5, 2014:\nPajama day", "Hey this is a description of pajama day. It's "
        		+ "this thing about kids bringing pajamas and blankets to school and having a party");
        
        notificationList.add(n1);
        notificationList.add(n2);
        */
        notificationList = MainActivity.datasource.getNotification(MainActivity.parent.getId());
        ListAdapter adapter = new NotificationListAdapter(getActivity(), R.layout.notification_list_item, notificationList);
        ListView listview = (ListView)root.findViewById(R.id.notificationList);
        listview.setAdapter(adapter);
        
        return root;
    }
 
}