package com.stevehead.music.instruments;

/**
 * Interface for musical instruments.
 * 
 * @author Steve Johnson
 */
public interface Instrument {
	
	/**
	 * Instruments are 'playable', but each is played differently. Each
	 * instrument must implement its own <code>play</code> method.
	 */
	void play();
}
