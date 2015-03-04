package com.stevehead.music.instruments;

import java.util.ArrayList;

import com.stevehead.music.Note;

/**
 * Brass instruments with valves.
 * 
 * @author Steve Johnson
 */
public class ValvedBrassInstrument extends BrassInstrument {
	/**
	 * Arraylist that contains the valves of the instrument.
	 */
	protected ArrayList<Valve> valves = new ArrayList<Valve>();
	
	/**
	 * Plays the instrument in its current state and returns the note played.
	 * 
	 * @param fingering			the valve fingering
	 * @param harmonic			the harmonic played
	 * @return					the resulting note
	 */
	public Note play(String fingering, int harmonic) {
		resetValves();
		setFingering(fingering);
		Note playedNote = super.play(harmonic);
		resetValves();
		return playedNote;
	}
	
	/**
	 * Plays the instrument in its current state and returns the note played.
	 * Harmonic is defaulted to 1.
	 * 
	 * @param fingering			the valve fingering
	 * @return					the resulting note
	 */
	public Note play(String fingering) {
		return play(fingering, 1);
	}
	
	/**
	 * Plays the instrument in its current state and returns the note played.
	 * Fingering is defaulted to all open valves.
	 * 
	 * @param harmonic			the harmonic played
	 * @return					the resulting note
	 */
	public Note play(int harmonic) {
		return play(openFingering(), harmonic);
	}
	
	/**
	 * Plays the instrument in its current state and returns the note played.
	 * Fingering is defaulted to all open valves and the harmonic is defaulted
	 * to 1.
	 * 
	 * @return			the resulting note
	 */
	public Note play() {
		return play(openFingering(), 1);
	}
	
	/**
	 * Loops through the valves and determines the total length of
	 * the instrument.
	 * 
	 * @return			the total length of the instrument in inches
	 */
	public double getLength() {
		double totalLength = super.getLength();
		
		for (Valve valve : valves) {
			if (valve.isPressed) {
				totalLength += valve.getLength();
			}
		}
		
		return totalLength;
	}
	
	/**
	 * Adds a valve to the instrument.
	 * 
	 * @param valve		the valve to be added
	 */
	public void addValve(Valve valve) {
		valves.add(valve);
	}
	
	/**
	 * Clears the data structure containing the valves.
	 * 
	 */
	public void clearValves() {
		valves.clear();
	}
	
	/**
	 * Resets all the valves to open state (not pressed).
	 */
	public void resetValves() {
		for (Valve valve : valves) {
			valve.isPressed = false;
		}
	}
	
	/**
	 * The string that represents all valves open fingering.
	 * 
	 * @return
	 */
	public String openFingering() {
		String fingering = "";
		for (int i = 0; i < valves.size(); i++) {
			fingering += "o";
		}
		return fingering;
	}
	
	/**
	 * Takes fingering string and presses the valves. 
	 * 
	 * @param fingering		the fingering of the valves.
	 */
	public void setFingering(String fingering) {
		// Fingering length must match the number of valves.
		if (fingering.length() != valves.size()) {
			throw new IllegalArgumentException("Fingering length must match the number of valves.");
		}
		
		// Loop through each valve fingering.
		for (int i = 0; i < fingering.length(); i++) {
			// The fingering for the current valve.
			char currentValveFingering = fingering.charAt(i);
			
			switch (currentValveFingering) {
			// Valve is pressed.
			case 'X':
			case 'x':
				valves.get(i).isPressed = true;
				break;
			// Valve is not pressed.
			case 'O':
			case 'o':
				valves.get(i).isPressed = false;
				break;
			default:
				throw new IllegalArgumentException("Invalid fingering character: " + currentValveFingering);
			}
		}
	}
}
