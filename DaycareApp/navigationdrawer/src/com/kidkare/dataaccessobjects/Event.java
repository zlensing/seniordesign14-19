package com.kidkare.dataaccessobjects;

public class Event {
	private int id;
	private int date;
	private String title;
	private String description;
	private int group;
	
	public Event(int id, int date, String title, String description, int group){
		this.id = id;
		this.date = date;
		this.title = title;
		this.description = description;
		this.group = group;
	}
	
	public int getId(){
		return id;
	}
	
	public int getDate(){
		return date;
	}
	
	public String getTitle(){
		return title;
	}
	
	public String getDescription(){
		return description;
	}
	
	public int getGroup(){
		return group;
	}
	
	@Override
	public String toString(){
		return id + "," + date + "," + title + "," + description + "," + group; 
	}
}
