package com.kidkare.dataaccessobjects;

public class Child {
	private int id;
	private String name;
	private int parent;
	private int teacher;
	
	public Child(){
		
	}
	
	public Child(int id, String name, int parent, int teacher){
		this.id = id;
		this.name = name;
		this.parent = parent;
		this.teacher = teacher;
	}
	
	public int getId(){
		return id;
	}
	
	public String getName(){
		return name;
	}
	
	public int getParent(){
		return parent;
	}
	
	public int teacher(){
		return teacher;
	}
	
	@Override
	public String toString(){
		return id + "," + name + "," + parent + "," + teacher;
	}
}
