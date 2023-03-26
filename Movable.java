package com.mycompany.a2;

public abstract class Movable extends GameObject{
	private int heading;
	private int speed;
	
	public Movable(int size, float x, float y, int r, int g, int b, int heading, int speed) {
		super(size,x,y,r,g,b);
		this.speed = speed;
		this.heading = heading;
	}
	
	public abstract void Move(int heading, int speed);

	public int getSpeed() {
		return this.speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public String printSpeed() {
		return "speed=" + this.speed;
	}
	
	public int getHeading() {
		return this.heading;
	}

	public void setHeading(int heading) {
		this.heading = heading;
	}

	public String printHeading() {
		return "heading=" + this.heading;
	}
}
