package com.mycompany.a2;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.models.Point;

public abstract class GameObject {
	protected int size;
	private int color;
	private Point location; 
	
	public GameObject(int size, float x, float y, int r, int g, int b) {	
		this.size = size;
		this.location = new Point(x,y);
		this.color = ColorUtil.rgb(r, g, b);
	}

	public int getSize() {
		return this.size;
	}
	
	public String printSize() {
		return "size=" + this.getSize();
	}
	
	public Point getLocation() {
		return this.location;
	}

	public void setLocation(float x, float y) {
		this.location.setX(x);
		this.location.setY(y);
	}
	
	public String printLocation() {
		return "loc=" + Math.round(this.location.getX() * 10.0) / 10.0 + "," + Math.round(this.location.getY() * 10.0) / 10.0;
	}
	
	public void changeLocation(Point newLocation) {
		this.location = newLocation;
	}
	
	public int getColor() {
		return this.color; 
	}
	
	public void setColor(int r, int g, int b) {
		this.color = ColorUtil.rgb(r,g,b);
	}
	
	public String printColor() {
		return "color=[" + ColorUtil.red(this.getColor()) + "," + ColorUtil.green(this.getColor()) + "," + ColorUtil.blue(this.getColor()) + "]";
	}
	
	public abstract String toString();
}
