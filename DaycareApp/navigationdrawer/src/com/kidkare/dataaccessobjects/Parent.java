package com.kidkare.dataaccessobjects;

public class Parent {
	private int id;
	private String name;
	private String title;
	private String number;
	private String email;
	private int group;
	
	public Parent(){
		
	}
	
	public Parent(int id, String name, String title, String number, String email, int group){
		this.id = id;
		this.name = name;
		this.title = title;
		this.number = number;
		this.email = email;
		this.group = group;
	}
	
	public int getId(){
		return id;
	}
	
	public String getName(){
		return name;
	}
	
	public String getTitle(){
		return title;
	}
	
	public String getnumber(){
		return number;
	}
	
	public String getEmail(){
		return email;
	}
	
	public int getGroup(){
		return group;
	}
	
	@Override
	public String toString(){
		return id + "," + name + "," + title + "," + number + "," + email + "," + group; 
	}
}
