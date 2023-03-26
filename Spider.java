
package com.mycompany.a2;

public class Spider extends Movable{
	
	public Spider(int size, float x, float y,int heading, int speed) {
		//color black
		super(size, x, y, 0, 0, 0, heading, speed);	
	}
	
	@Override
	public void Move(int heading, int speed) {
		//deltaX = cos(theta)*speed
		float deltaX = (float) (Math.cos(Math.toRadians(90 - heading)) * speed);
		//deltaY = cos(theta)*speed
		float deltaY = (float) (Math.sin(Math.toRadians(90 - heading)) * speed);
		
		//new location(x) = oldLocation(x) + deltaX
		float newLocX = this.getLocation().getX() + deltaX;
		//new location(y) = oldLocation(y) + deltaY
		float newLocY = this.getLocation().getY() + deltaY;
		
		//check if out of bounds
		if(newLocX <= 0.0 || newLocX >= 1000.0 || newLocY <= 0.0 || newLocY >= 1000) {
			if (heading < 180)
				heading += 180;
			else if (heading > 180)
				heading -= 180;
			else if (heading == 180)
				heading = 0;
			
			deltaX = (float) (Math.cos(Math.toRadians(90 - heading)) * speed);
			deltaY = (float) (Math.sin(Math.toRadians(90 - heading)) * speed);
			
			newLocX = this.getLocation().getX() + deltaX;
			newLocY = this.getLocation().getY() + deltaY;
		}
		
		//new location(x,y)
		this.setLocation(newLocX, newLocY);
	}
	
	@Override
	public String toString() {
		return "Spider: " + this.printLocation() + " " + this.printColor() + " " + this.printHeading() + " " + this.printSpeed() + " " + this.printSize();
	}

}
