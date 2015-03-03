package com.stevehead.music;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.stevehead.music.physics.Frequency;
import com.stevehead.music.physics.Wavelength;

public class Note {
	
	/**
	 * The octave that represents middle C in MIDI-style notation.
	 */
	public static final int MIDDLE_OCTAVE = 4;
	
	/**
	 * The numeric value of middle C in MIDI-style notation.
	 */
	public static final int MIDDLE_C_NUMERIC_VALUE = 60;
	
	/**
	 * The regex pattern for MIDI-style notes.
	 */
	private static final Pattern MIDI_NOTE_PATTERN = Pattern.compile("^[A-G]{1}[b#x]*(-{0,1}[1-9]{1}[0-9]*|0{1})$");
	
	/**
	 * The numeric value of this note.
	 */
	public double numericValue;
	
	/**
	 * Constructor for inputing the numeric value of a MIDI-style notes.
	 * 
	 * @param numericValue		the numeric value of the note
	 */
	public Note(double numericValue) {
		this.numericValue = numericValue;
	}
	
	/**
	 * Constructor for inputing a MIDI-style note (A4, Bb5, etc.).
	 * 
	 * @param midiNote			the MIDI-style representation of the note
	 */
	public Note(String midiNote) {
		this(midiNote, 0);
	}
	
	/**
	 * Constructor for inputing a MIDI-style note (A4, Bb5, etc.) with
	 * cents offset.
	 * 
	 * @param midiNote			the MIDI-style representation of the note
	 * @param centsOffset		the number of cents off the note is
	 */
	public Note(String midiNote, double centsOffset) {
		double numericValue;
		
		// Match the input to the regex pattern.
		Matcher noteMatcher = MIDI_NOTE_PATTERN.matcher(midiNote);
		
		// If there is a match.
		if (noteMatcher.find()) {
			// String manipulations.
			String octaveNumberString = noteMatcher.group(1);
			String noteString = midiNote.replace(octaveNumberString, "");
			char diatonic = noteString.charAt(0);
			String accidentals = noteString.substring(1);
			int octaveNumber = Integer.parseInt(octaveNumberString);
			
			// Determine the numeric value from the supplied note.
			numericValue = getDiatonicOffset(diatonic);
			numericValue += getAccidentalOffset(accidentals);
			numericValue += getOctaveOffset(octaveNumber);
			numericValue += centsOffset / 100.0;
			
			this.numericValue = numericValue;
		} else {
			throw new IllegalArgumentException("Argument is not a valid MIDI note.");
		}
	}
	
	/**
	 * Converts the note to a frequency.
	 * 
	 * @return		the frequency of the music note
	 */
	public Frequency toFrequency() {
		double hertz = Frequency.TUNING_STANDARD * Math.pow(2.0, (numericValue
				- MIDDLE_C_NUMERIC_VALUE - 9.0) / 12.0);
        return new Frequency(hertz);
	}
	
	/**
	 * Converts the note to a wavelength.
	 * 
	 * @return		the wavelength of the music note
	 */
	public Wavelength toWavelength() {
		double inches = Wavelength.MIDDLE_C_WAVELENGTH
				* Math.pow(2.0, -3.0 / 4.0) / Math.pow(2.0, (numericValue
				- MIDDLE_C_NUMERIC_VALUE - 9.0) / 12.0);
		return new Wavelength(inches);
	}
	
	/**
	 * The string representation of the note.
	 * 
	 * @return		the note as a string
	 */
	public String toString() {
		return Double.toString(numericValue);
	}
	
	/**
	 * Determines the offset from 'C' of the input character.
	 * 
	 * @param note		a diatonic musical note (A-G)
	 * @return			integer distance from 'C'
	 */
	private static int getDiatonicOffset(char note) {
		int offset = MIDDLE_C_NUMERIC_VALUE % 12;
		
		switch (note) {
		case 'A':
			offset -= 3;
			break;
		case 'B':
			offset -= 1;
			break;
		case 'C':
			break;
		case 'D':
			offset += 2;
			break;
		case 'E':
			offset += 4;
			break;
		case 'F':
			offset += 5;
			break;
		case 'G':
			offset += 7;
			break;
		default:
			throw new IllegalArgumentException("Invalid diatonic note.");
		}
		
		return offset;
	}
	
	/**
	 * Determines the offset based on an accidental.
	 * 
	 * @param accidental		accidental character such as b, #, b, x
	 * @return					integer offset by accidental
	 */
	private static int getAccidentalOffset(char accidental) {
		switch (accidental) {
		case 'b': // Flat
			return -1;
		case '#': // Sharp
			return 1;
		case 'x': // Double-Sharp
			return 2;
		default:
			throw new IllegalArgumentException("Invalid accicental: " + accidental);
		}
	}
	
	/**
	 * Determines the offset based on accidentals.
	 * 
	 * @param accidentals		accidental string such as b, #, bb, x
	 * @return					integer offset by accidentals
	 */
	private static int getAccidentalOffset(String accidentals) {
		int offset = 0;
		
		// Loop through each accidental to get offset.
		for (int i = 0; i < accidentals.length(); i++) {
			char accidental = accidentals.charAt(i);
			offset += getAccidentalOffset(accidental);
		}
		
		return offset;
	}
	
	/**
	 * Determines the offset based on the numeric representation of the octave.
	 * 
	 * @param octave			the starting octave
	 * @return					integer offset based on octave
	 */
	private static int getOctaveOffset(int octave) {
		int multiplier = (MIDDLE_C_NUMERIC_VALUE - getDiatonicOffset('C')) / 12;
		int octaveAdustment = multiplier - MIDDLE_OCTAVE;
		return 12 * (octave + octaveAdustment);
	}
}
