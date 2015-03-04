package com.stevehead.music.instruments;

import com.stevehead.music.physics.AdjustableTube;

/**
 * Compensating valves add additional length by routing the air back through
 * these valves if another valve is pressed. Most common for 4-valved
 * euphoniums, but some 4-valve tubas are compensating as well as 3-valve
 * variations for both instrument types.
 * 
 * @author Steve Johnson
 */
public class CompensatingValve extends Valve {
	
	/**
	 * The valve that activates the compensating portion of this valve.
	 */
	protected Valve parentValve;
	
	/**
	 * The additional tubing that the airflow must go throw is the parent valve
	 * is pressed.
	 */
	protected AdjustableTube compensatingTube;
	
	/**
	 * Sets the parent valve.
	 * 
	 * @param parentValve			the valve that must be pressed to cause
	 * 								the airflow to direct through the
	 * 								compensating tube
	 */
	public void setParentValve(Valve parentValve) {
		this.parentValve = parentValve;
	}
	
	/**
	 * Returns the total length provided by this valve. If the parent valve is
	 * depreseed, the compsensating tube must be added.
	 * 
	 * @return			the total length of the valve
	 */
	public double getLength() {
		// The length provided regardless of the parent valve being depressed.
		double totalLength = super.getLength();
		
		// Must add the compensating portion if the parent valve is depressed.
		if (parentValve.isPressed) {
			totalLength += compensatingTube.getLength();
		}
		
		return totalLength;
	}
}
