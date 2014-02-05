package com.kidkare.navigationdrawer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteHelper extends SQLiteOpenHelper{
	
	//Teacher table
	public static final String TABLE_TEACHERS = "teachers";
	public static final String TEACHERS_ID = "id";
	public static final String TEACHERS_NAME = "name";
	public static final String TEACHERS_TITLE = "title";
	public static final String TEACHERS_NUMBER = "number";
	public static final String TEACHERS_EMAIL = "email";
	public static final String TEACHERS_GROUP = "ngroup";
	
	//Parent table
	public static final String TABLE_PARENTS = "parents";
	public static final String PARENTS_ID = "id";
	public static final String PARENTS_NAME = "name";
	public static final String PARENTS_TITLE = "title";
	public static final String PARENTS_NUMBER = "number";
	public static final String PARENTS_EMAIL = "email";
	public static final String PARENTS_GROUP = "Ngroup";
	
	//Child table
	public static final String TABLE_CHILDREN = "children";
	public static final String CHILDREN_ID = "id";
	public static final String CHILDREN_NAME = "name";
	public static final String CHILDREN_PARENT = "parent";
	public static final String CHILDREN_TEACHER = "teacher";
	
	//Event table
	public static final String TABLE_EVENTS = "events";
	public static final String EVENTS_ID = "id";
	public static final String EVENTS_DATE = "date";
	public static final String EVENTS_TITLE = "title";
	public static final String EVENTS_DESCRIPTION = "description";
	public static final String EVENTS_GROUP = "ngroup";
	
	//Notification table
	public static final String TABLE_NOTIFICATIONS = "notifications";
	public static final String NOTIFICATIONS_ID = "id";
	public static final String NOTIFICATIONS_PARENT = "parent";
	public static final String NOTIFICATIONS_TITLE = "title";
	public static final String NOTIFICATIONS_DESCRIPTION = "description";
	
	//Daily Sheet table
	public static final String TABLE_SHEET = "dailysheet";
	public static final String SHEET_ID = "id";
	public static final String SHEET_CHILD = "child";
	public static final String SHEET_DATE = "date";
	public static final String SHEET_ATTITUDE = "attitude";
	public static final String SHEET_NAP = "nap";
	public static final String SHEET_NOTES = "notes";
	public static final String SHEET_MAIN = "main";
	public static final String SHEET_CARB = "carb";
	public static final String SHEET_VEGI = "vegi";
	public static final String SHEET_FRUIT = "fruit";
	public static final String SHEET_MILK = "milk";
	public static final String SHEET_OTHER = "other";
	
	private static final String DATABASE_NAME = "kidkare";
	private static final int DATABASE_VERSION = 3;
	
	//table creation statements
	private static final String TEACHER_CREATE = "create table " + TABLE_TEACHERS + "( " +
			TEACHERS_ID + " integer primary key, " + TEACHERS_NAME + " text not null, "
			+ TEACHERS_TITLE + " text not null, " + TEACHERS_NUMBER + " text not null, " + 
			TEACHERS_EMAIL + " text not null, " + TEACHERS_GROUP + " integer);";
	
	private static final String PARENT_CREATE = "create table " + TABLE_PARENTS + "(" +
			PARENTS_ID + " integer primary key, " + PARENTS_NAME + " text not null, "
			+ PARENTS_TITLE + " text not null, " + PARENTS_NUMBER + " text not null, " + 
			PARENTS_EMAIL + " text not null, " + PARENTS_GROUP + " integer not null);";
	
	private static final String CHILD_CREATE = "create table " + TABLE_CHILDREN + "(" +
			CHILDREN_ID + " integer primary key, " + CHILDREN_NAME + " text not null, "
			+ CHILDREN_PARENT + " integer not null, " + CHILDREN_TEACHER + " integer not null);";
	
	private static final String EVENT_CREATE = "create table " + TABLE_EVENTS + "(" +
			EVENTS_ID + " integer primary key autoincrement, " + EVENTS_DATE + " integer not null, "
			+ EVENTS_TITLE + " text not null, " + EVENTS_DESCRIPTION + " text not null, " + 
			EVENTS_GROUP + " integer not null);";
	
	private static final String NOTIFICATION_CREATE = "create table " + TABLE_NOTIFICATIONS + "(" +
			NOTIFICATIONS_ID + " integer primary key autoincrement, " + NOTIFICATIONS_PARENT + " integer not null, "
			+ NOTIFICATIONS_TITLE + " text not null, " + NOTIFICATIONS_DESCRIPTION + " text not null);";
	
	private static final String SHEET_CREATE = "create table " + TABLE_SHEET + "(" +
			SHEET_ID + " integer primary key autoincrement, " + SHEET_CHILD + " integer not null, "
			+ SHEET_DATE + " integer not null, " + SHEET_ATTITUDE + " text not null, " + 
			SHEET_NAP + " text not null, " + SHEET_NOTES + " text not null, " + SHEET_MAIN + 
			" text not null, " + SHEET_CARB + " text not null, " + SHEET_VEGI + " text not null, "
			+ SHEET_FRUIT + " text not null, " + SHEET_MILK + " text not null, " + SHEET_OTHER + 
			" text not null);";
	

	public MySQLiteHelper(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(TEACHER_CREATE);
		db.execSQL(PARENT_CREATE);
		db.execSQL(CHILD_CREATE);
		db.execSQL(EVENT_CREATE);
		db.execSQL(NOTIFICATION_CREATE);
		db.execSQL(SHEET_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_TEACHERS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PARENTS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHILDREN);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTIFICATIONS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_SHEET);
		
		onCreate(db);
	}
}
