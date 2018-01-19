// Written by James Hardjadinata and Richert Wang
// Modified for ICS 45J Lab 4 Fall 2015

package com.example.flashcard45j;

import java.io.Serializable;

// Class defining a TruthAnswer object for true/false questions.
// Contains a boolean field representing the correct value for this Answer
public class TruthAnswer implements Answer, Serializable {
	// *** Declare class variables here ***

	// Constructor assigning the correct answer to the class field
	public TruthAnswer(boolean answer) {
		// *** Provide your implementation here. ***
	}
	
	// Method that checks if the answer passed in the parameter is equal to the
	// correct answer (checking case sensitivity if necessary).
	public boolean checkAnswer(String answer) { 
		// REMOVE THIS
		return true;
		
		// *** Provide your implementation here. ***
	}
	
	// Method that returns the correct answer
    public String toString() {
    	// REMOVE THIS
    	return null;
    	
		// *** Provide your implementation here. ***
    }

    // Method that checks if the Answer object has the correct answer.
    public boolean equals(Answer answer) {
    	// REMOVE THIS
    	return true;
    	
		// *** Provide your implementation here. ***
    }
}
