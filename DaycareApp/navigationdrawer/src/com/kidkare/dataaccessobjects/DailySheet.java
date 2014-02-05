package com.kidkare.dataaccessobjects;

public class DailySheet {
	private int id;
	private int child;
	private int date;
	private String attitude;
	private String nap;
	private String notes;
	private String main;
	private String carb;
	private String vegi;
	private String fruit;
	private String milk;
	private String other;
	
	public DailySheet(int id, int child, int date, String attitude, String nap, String notes, 
		String main, String carb, String vegi, String fruit, String milk, String other){
		
		this.id = id;
		this.child = child;
		this.date = date;
		this.attitude = attitude;
		this.nap = nap;
		this.notes = notes;
		this.main = main;
		this.carb = carb;
		this.vegi = vegi;
		this.fruit = fruit;
		this.milk = milk;
		this.other = other;
		
	}
	
	public int getId(){
		return id;
	}
	
	public int getChild(){
		return child;
	}
	
	public int getDate(){
		return date;
	}
	
	public String getAttitude(){
		return attitude;
	}
	
	public String getNap(){
		return nap;
	}
	
	public String getNotes(){
		return notes;
	}
	
	public String getMain(){
		return main;
	}

	public String getCarb(){
		return carb;
	}
	
	public String getVegi(){
		return vegi;
	}
	
	public String getFruit(){
		return fruit;
	}
	
	public String getMilk(){
		return milk;
	}

	public String getOther(){
		return other;
	}
	
	@Override
	public String toString(){
		return id + "," + child + "," + date + "," + attitude + "," + nap + "," + notes + "," + 
				main + "," + carb + "," + vegi + "," + fruit + "," + milk + "," + other; 
	}
}
