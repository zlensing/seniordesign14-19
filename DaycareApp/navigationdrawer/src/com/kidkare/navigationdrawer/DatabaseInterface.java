package com.kidkare.navigationdrawer;

import java.util.ArrayList;
import com.kidkare.dataaccessobjects.*;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseInterface {
	private SQLiteDatabase database;
	private MySQLiteHelper dbhelper;
	
	public DatabaseInterface(Context context){
		dbhelper = new MySQLiteHelper(context);
	}
	
	public void open() throws SQLException {
	    database = dbhelper.getWritableDatabase();
	}

	public void close() {
	    dbhelper.close();
	}
	
	public ArrayList<DailySheet> getSheets(int child){
		ArrayList<DailySheet> res = new ArrayList<DailySheet>();
		
		Cursor cursor = database.query("dailysheet", null, "child = " + child, null, null, null, "id DESC");
		cursor.moveToFirst();
		while(!cursor.isAfterLast()){
			DailySheet d = cursorToDailySheet(cursor);
			res.add(d);
			cursor.moveToNext();
		}
		cursor.close();
		return res;
	}
	
	public ArrayList<Event> getEvent(int date){
		ArrayList<Event> res = new ArrayList<Event>();
		
		Cursor cursor = database.query("events", null, "date = " + date, null, null, null, "id");
		cursor.moveToFirst();
		while(!cursor.isAfterLast()){
			Event e = cursorToEvent(cursor);
			res.add(e);
			cursor.moveToNext();
		}
		cursor.close();
		return res;
	}
	
	public Parent getParent(String email){
		Parent p = new Parent();
		Cursor cursor = database.query("parents", null, "email LIKE '" + email + "%'", null, null, null, "id");
		cursor.moveToFirst();
		while(!cursor.isAfterLast()){
			p = cursorToParent(cursor);
			cursor.moveToNext();
		}
		cursor.close();
		return p;
	}
	
	public Child getChildFromParent(int parentID){
		Child c = new Child();
		Cursor cursor = database.query("children", null, "parent = " + parentID, null, null, null, "id");
		cursor.moveToFirst();
		while(!cursor.isAfterLast()){
			c = cursorToChild(cursor);
			cursor.moveToNext();
		}
		cursor.close();
		return c;
	}
	
	public Child getChild(int childID){
		Child c = new Child();
		Cursor cursor = database.query("children", null, "id = " + childID, null, null, null, "id");
		cursor.moveToFirst();
		while(!cursor.isAfterLast()){
			c = cursorToChild(cursor);
			cursor.moveToNext();
		}
		cursor.close();
		return c;
	}
	
	public ArrayList<Notification> getNotification(int parentID){
		ArrayList<Notification> res = new ArrayList<Notification>();
		Cursor cursor = database.query("notifications", null, "parent = " + parentID + " OR parent = 0", 
				null, null, null, "id DESC");
		cursor.moveToFirst();
		while(!cursor.isAfterLast()){
			Notification n = cursorToNotification(cursor);
			res.add(n);
			cursor.moveToNext();
		}
		cursor.close();
		return res;
	}
	
	public ArrayList<Teacher> getContacts(){
		ArrayList<Teacher> res = new ArrayList<Teacher>();
		Cursor cursor = database.query("teachers", null, null, null, null, null, "id");
		cursor.moveToFirst();
		while(!cursor.isAfterLast()){
			Teacher t = cursorToTeacher(cursor);
			res.add(t);
			cursor.moveToNext();
		}
		cursor.close();
		return res;
	}
	
	private DailySheet cursorToDailySheet(Cursor c){
		return new DailySheet(c.getInt(0), c.getInt(1), c.getInt(2), c.getString(3), c.getString(4), c.getString(5),
				c.getString(6), c.getString(7), c.getString(8), c.getString(9), c.getString(10), c.getString(11));
	}
	
	private Event cursorToEvent(Cursor c){
		return new Event(c.getInt(0), c.getInt(1), c.getString(2), c.getString(3), c.getInt(4));
	}
	
	private Parent cursorToParent(Cursor c){
		return new Parent(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4),
				c.getInt(5));
	}
	
	private Child cursorToChild(Cursor c){
		return new Child(c.getInt(0), c.getString(1), c.getInt(2), c.getInt(3));
	}
	
	private Notification cursorToNotification(Cursor c){
		Notification n = new Notification(c.getInt(0), c.getInt(1), c.getString(2), c.getString(3));
		return n;
	}
	
	private Teacher cursorToTeacher(Cursor c){
		Teacher t = new Teacher(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4),
				c.getInt(5));
		return t;
	}
	
	public void simulateExternalDatabase(){
		ContentValues values = new ContentValues();
		//Populate Teacher tables
		values.put("id", 0);
		values.put("name", "Jen Van Ryswyk");
		values.put("title", "Lab School Administrator");
		values.put("number", "515-294-7478");
		values.put("email", "jenvan@iastate.edu");
		values.put("ngroup", 0);
		database.insert("teachers", null, values);
		values.clear();
		values.put("id", 1);
		values.put("name", "Kim Venteicher");
		values.put("title", "Parent Coordinator");
		values.put("number", "515-294-3040");
		values.put("email", "kimberly@iastate.edu");
		values.put("ngroup", 0);
		database.insert("teachers", null, values);
		values.clear();
		values.put("id", 2);
		values.put("name", "Kere Hughes-Belding");
		values.put("title", "Faculty Coordinator");
		values.put("number", "515-294-8441");
		values.put("email", "kereh@iastate.edu");
		values.put("ngroup", 1);
		database.insert("teachers", null, values);
		values.clear();
		values.put("id", 3);
		values.put("name", "Donna Oliver");
		values.put("title", "Storekeeper");
		values.put("number", "515-294-8878");
		values.put("email", "dmoliver@iastate.edu");
		values.put("ngroup", 1);
		database.insert("teachers", null, values);
		values.clear();
		
		//Populate notifications;
		values.put("parent", 1);
		values.put("title", "Monday, Dec. 9, 2014:\nShow and Tell");
		values.put("description", "Hey this is a description of show and tell. It's "
        		+ "this thing about kids bringing stuff to school and showing it to other kids");
		database.insert("notifications", null, values);
		values.clear();
		values.put("parent", 0);
		values.put("title", "Thursday, Dec. 5, 2014:\nPajama day");
		values.put("description", "Hey this is a description of pajama day. It's "
        		+ "this thing about kids bringing pajamas and blankets to school and having a party");
		database.insert("notifications", null, values);
		values.clear();
		
		//populate parents
		values.put("id", 1);
		values.put("name", "Ben Stark");
		values.put("title", "Mr.");
		values.put("number", "515-555-5555");
		values.put("email", "zlensing@iastate.edu");
		values.put("ngroup", 1);
		database.insert("parents", null, values);
		values.clear();
		
		//populate children
		values.put("id", 1);
		values.put("name", "Timothy Stark");
		values.put("parent", 1);
		values.put("teacher", 2);
		database.insert("children", null, values);
		values.clear();
		
		//Populate daily sheets
		values.put("child", 1);
		values.put("date", 20141203);
		values.put("attitude", "Good");
		values.put("nap", "Yes");
		values.put("notes", "Played well with others today.");
		values.put("main", "1Chicken");
		values.put("carb", "2Roll");
		values.put("vegi", "3Carrot");
		values.put("fruit", "1Apple");
		values.put("milk", "22% Milk");
		values.put("other", "3Pudding");
		database.insert("dailysheet", null, values);
		values.clear();
		
		//populate events
		values.put("date", 20131211);
		values.put("title", "Stuffed Animal Picnic");
		values.put("description", "Make sure to bring a stuffed animal!");
		values.put("ngroup", 1);
		database.insert("events", null, values);
		values.clear();
		values.put("date", 20131211);
		values.put("title", "Parent Teacher Conferences");
		values.put("description", "Don't forget about parent teach conferences starting at 6:00pm.");
		values.put("ngroup", 1);
		database.insert("events", null, values);
		values.clear();
		values.put("date", 20131212);
		values.put("title", "Nursery Rhyme Parade");
		values.put("description", "Send your child with their favorite nursery rhyme");
		values.put("ngroup", 1);
		database.insert("events", null, values);
		values.clear();
		
	}
}
