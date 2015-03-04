package com.stevehead.music.instruments;

import com.stevehead.music.Note;
import com.stevehead.music.physics.AdjustableTube;
import com.stevehead.music.physics.Frequency;
import com.stevehead.music.physics.Wavelength;

/**
 * BrassInstrument represents a brass instrument, which an adjustable tube that
 * implements the instrument interface.
 * 
 * @author Steve Johnson
 */
public class BrassInstrument extends AdjustableTube implements Instrument {
	
	/**
	 * Brass instrument pitches are simply produced by the harmonic multiplied
	 * with the frequency of the fundamental pitch of that length.
	 * 
	 * @param harmonic		the harmonic or 'partial' that is being played
	 * @return				the resulting musical note
	 */
	public Note play(int harmonic) {
		// The harmonic series is a whole number sequence starting with 1.
		if (harmonic < 1) {
			throw new IllegalArgumentException("Harmonics cannot be less than 1.");
		}
		
		/*
		 * Calculate the wavelength of the fundamental pitch of this
		 * instrument's current length.
		 */
		Wavelength fundamentalLength = new Wavelength(getLength());
		
		/*
		 * Convert the fundemental's wavelength to a frequency and multiply it
		 * by the harmonic played. This produces the frequency of the
		 * played note.
		 */
		double playedHertz = fundamentalLength.toFrequency().hertz * harmonic;
		
		// Create frequency object from the hertz value.
		Frequency playedFrequency = new Frequency(playedHertz);
		
		// Return the frequency as a played note.
		return playedFrequency.toNote();
	}
	
	/**
	 * No argument constructor defaults to a harmonic of 1.
	 * 
	 * @return		the resulting musical note
	 */
	public Note play() {
		return this.play(1);
	}
}
