package com.mycompany.a2;

import com.mycompany.a2.ISteerable;
import com.mycompany.a2.Movable;

public class Ant extends Movable implements ISteerable {

	private int maximumSpeed;
	private int foodLevel;
	private int foodConsumptionRate;
	private int healthLevel;
	private int lastFlagReached;
	
	public Ant(float x, float y, int heading, int speed) {
		//size 20 color brown
		super(20, x, y, 165,122,42, heading, speed);	
		this.maximumSpeed = 100;
		this.foodLevel = 20;
		this.foodConsumptionRate = 2;
		this.healthLevel = 10;
		this.lastFlagReached = 1;
	}
	
	@Override
	public void Move(int heading, int speed) {
		//deltaX = cos(theta)*speed
		float deltaX = (float) (Math.cos(Math.toRadians(90-heading))*speed);
		//deltaY = cos(theta)*speed
		float deltaY = (float) (Math.cos(Math.toRadians(90-heading))*speed);
		
		//new location(x) = oldLocation(x) + deltaX
		float newLocX = this.getLocation().getX() + deltaX;
		//new location(y) = oldLocation(y) + deltaY
		float newLocY = this.getLocation().getY() + deltaY;
		
		//new location(x,y)
		this.setLocation(newLocX, newLocY);
	}
	
	@Override
	public void steer(int h) {
			//set heading to current heading + new heading
			setHeading((int) (getHeading() + h));			
		}
	
	
	public int getMaximumSpeed() {
		return this.maximumSpeed;
	}

	public void setMaximumSpeed(int maximumSpeed) {
		this.maximumSpeed = maximumSpeed;
	}

	public int getFoodLevel() {
		return this.foodLevel;
	}

	public void setFoodLevel(int foodLevel) {
		this.foodLevel = foodLevel;
	}
	
	public int getFoodConsumptionRate() {
		return this.foodConsumptionRate;
	}
	
	public void setFoodConsumptionRate(int foodConsumptionRate) {
		this.foodConsumptionRate = foodConsumptionRate;
	}
	
	public int getHealthLevel() {
		return this.healthLevel;
	}
	
	public void setHealthLevel(int healthLevel) {
		this.healthLevel = healthLevel;
	}
	
	public int getLastFlagReached() {
		return this.lastFlagReached;
	}
	
	public void setLastFlagReached(int lastFlagReached) {
		this.lastFlagReached = lastFlagReached;
	}
	
	public int decreaseHealthLevel() {
		this.healthLevel = healthLevel - 1; 
		return this.healthLevel;
	}
	
	@Override
	public String toString() {
		return "Ant: " + this.printLocation() + " " + this.printColor() + " heading=" + this.getHeading() + " speed=" + this.getSpeed() + " " + this.printSize() + " maxSpeed=" + this.maximumSpeed + " foodConsumptionRate=" + this.foodConsumptionRate;
	}

}