package com.stevehead.music.physics;

import com.stevehead.music.Note;

/**
 * Represents a hollow tube.
 * 
 * @author Steve Johnson
 */
public class Tube {
	
	/**
	 * The length of the tube.
	 */
	protected final double length;
	
	/**
	 * No-argument constructor defaults to a length of 0.
	 */
	public Tube() {
		this(0);
	}
	
	/**
	 * Constructor for length in inches input.
	 * 
	 * @param length		the length of the tube in inches
	 */
	public Tube(double length) {
		this.length = length;
	}
	
	/**
	 * Constructor for frequency input.
	 * 
	 * @param frequency		the frequency of the tube
	 */
	public Tube(Frequency frequency) {
		this.length = frequency.toWavelength().inches;
	}
	
	/**
	 * Constructor for music note input.
	 * 
	 * @param note			the musical note of the tube
	 */
	public Tube(Note note) {
		this.length = note.toWavelength().inches;
	}
	
	/**
	 * Constructor for wavelength input.
	 * 
	 * @param wavelength	the wavelength of the tube
	 */
	public Tube(Wavelength wavelength) {
		this.length = wavelength.inches;
	}
	
	
	/**
	 * Gets the length of the tube.
	 * 
	 * A getter method is used since this class is to be extended by others that
	 * may not have a static state length. 
	 * 
	 * @return				the total length of the tube
	 */
	public double getLength() {
		return length;
	}
}
