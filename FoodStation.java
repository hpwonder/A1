package com.mycompany.a2;

public class FoodStation extends Fixed{
	
	private int capacity; 
	
	public FoodStation(int size, float x, float y)
	{
		//color gray
		super(size,x,y,128,128,128);
		//capacity proportional to size
		this.capacity = this.getSize();
	}
	
	public int getCapacity() {
		return this.capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	@Override
	public String toString() {
		return "FoodStation: " + this.printLocation() + " " + this.printColor() + " " + this.printSize() + " capacity=" + this.capacity;
	}
}
