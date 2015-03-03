package com.stevehead.music.physics;

import com.stevehead.music.Note;

/**
 * Wavelength is a class that represents the length of a wave in physics. More
 * specifically a soundwave for music calculations.
 * 
 * @author	Steve Johnson
 */
public class Wavelength {
	
	/**
	 * The length of the wavelength of middle C. Based on the approximation of
	 * C's being in powers of 2 feet (1ft, 2ft, 4ft, 8ft, etc.). Since the
	 * speed of sound is not constant and varies with temperature, air density,
	 * etc., a standard must be set, much like how a tuning standard must be set
	 * for A.
	 */
	public static final double MIDDLE_C_WAVELENGTH = 24;
	
	/**
	 * The wavelength in inches.
	 */
	public double inches;
	
	/**
	 * Constructor for inches input.
	 * 
	 * @param inches		the length of the wavelength in inches
	 */
	public Wavelength(double inches) {
		this.inches = inches;
	}
	
	/**
	 * Converts the wavelength to a frequency.
	 * 
	 * @return		the frequency of the wavelength
	 */
	public Frequency toFrequency() {
		double hertz = Frequency.TUNING_STANDARD * MIDDLE_C_WAVELENGTH
				* Math.pow(2.0, -3.0 / 4.0) / inches;
		return new Frequency(hertz);
	}
	
	/**
	 * Converts the wavelength to a note.
	 * 
	 * @return		the musical note with this wavelength
	 */
	public Note toNote() {
		// TODO: code logic
		return new Note();
	}
	
	/**
	 * The string representation of the wavelength.
	 * 
	 * @return		the length of the wavelength as a string
	 */
	public String toString() {
		return Double.toString(inches) + "in";
	}
}
