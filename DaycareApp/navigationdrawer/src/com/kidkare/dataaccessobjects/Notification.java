package com.kidkare.dataaccessobjects;

public class Notification {
	private int id;
	private int parent;
	private String title;
	private String description;
	
	public Notification(int id, int parent, String title, String description){
		this.id = id;
		this.parent = parent;
		this.title = title;
		this.description = description;
	}
	
	public int getId(){
		return id;
	}
	
	public int getParent(){
		return parent;
	}
	
	public String getTitle(){
		return title;
	}
	
	public String getDescription(){
		return description;
	}
	
	@Override
	public String toString(){
		return id + "," + parent + "," + title + "," + description; 
	}
}
