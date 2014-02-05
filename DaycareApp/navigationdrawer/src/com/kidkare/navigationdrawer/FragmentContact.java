package com.kidkare.navigationdrawer;

import java.util.ArrayList;

import com.example.navigationdrawer.R;
import com.kidkare.dataaccessobjects.Teacher;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
 
public class FragmentContact extends Fragment{
	
	public ArrayList<Teacher> contactList = new ArrayList<Teacher>();
 
    public static FragmentContact newInstance(Context context) {
    	FragmentContact f = new FragmentContact();
 
        return f;
    }
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View root = (ViewGroup) inflater.inflate(R.layout.fragment_contact, container, false);
        
        /*
        Teacher t1 = new Teacher(1, "Teacher One", "Administrator", "555-555-1234", "teacherone@gmail.com", 1);
        Teacher t2 = new Teacher(1, "Teacher Two", "Teacher", "555-555-1234", "teachertwo@gmail.com", 2);
        Teacher t3 = new Teacher(1, "Teacher Three", "Teacher", "555-555-1234", "teacherthree@gmail.com", 3);
        
        contactList.add(t1);
        contactList.add(t2);
        contactList.add(t3);
        */
        
        contactList = MainActivity.datasource.getContacts();
        
        ListAdapter adapter = new ContactListAdapter(getActivity(), R.layout.contact_list_item, contactList);
        
        ListView listview = (ListView)root.findViewById(R.id.contactView);
        listview.setAdapter(adapter);
         
        return root;
    }
    
}