package com.mycompany.a2;

public class Flag extends Fixed {
	
	private int sequenceNumber;
	
	public Flag(float x, float y, int sequenceNumber) {
		//size 10 color green
		super(10, x, y, 124,252,0);	
		this.sequenceNumber = sequenceNumber;
	}
	
	public  int getSequenceNumber() {
		return this.sequenceNumber;
	}
	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	
	@Override
	public String toString() {
		return "Flag: " + this.printLocation() + " " + this.printColor() + " " + this.printSize() + " seqNum=" + this.sequenceNumber;
	}
}
