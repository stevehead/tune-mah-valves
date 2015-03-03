package com.stevehead.music.physics;

import com.stevehead.music.Note;

public class Frequency {
	public static final double TUNING_STANDARD = 440.0;
	
	public double hertz;
	
	public Frequency(double hertz) {
		this.hertz = hertz;
	}
	
	/**
	 * Converts the frequency to a note.
	 * 
	 * @return		the musical note with this frequency
	 */
	public Note toNote() {
		// TODO: code logic
		return new Note();
	}
	
	/**
	 * Converts the frequency to a wavelength.
	 * 
	 * @return		the wavelength of the frequency
	 */
	public Wavelength toWavelength() {
		double inches = TUNING_STANDARD * Wavelength.MIDDLE_C_WAVELENGTH
				* Math.pow(2.0, - 3.0 / 4.0) / hertz;
		return new Wavelength(inches);
	}
	
	/**
	 * The string representation of the frequency.
	 * 
	 * @return		the frequency as a string
	 */
	public String toString() {
		return Double.toString(hertz) + "/s";
	}
}