package com.stevehead.music.physics;

/**
 * Represents a hollow tube that can have its length adjusted.
 * 
 * @author Steve Johnson
 */
public class AdjustableTube extends Tube {
	
	/**
	 * The adjustment to the length (such as tuning a slide). Positive numbers
	 * represent lengthening the tube while negative numbers represent
	 * shortening the tube.
	 */
	protected double adjustedLength = 0.0;
	
	/**
	 * Calculates the total length of the tube from the initial length and the
	 * adjusted length.
	 * 
	 * @return			the total length of the tube in inches
	 */
	public double getLength() {
		return super.getLength() + adjustedLength;
	}
	
	/**
	 * Sets the adjusted length.
	 * 
	 * @param adjustment		the length in inches to be added or removed
	 */
	public void adjustLength(double adjustment) {
		adjustedLength = adjustment;
	}
}
