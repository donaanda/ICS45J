// Written by James Hardjadinata and Richert Wang
// Modified for ICS 45J Lab 4 Fall 2015

package com.example.flashcard45j;

import java.io.Serializable;

// Class defining a ShortAnswer object for short answer questions.
// Contains a String representing the correct answer and a boolean field
// representing if the answer is case sensitive or not.
public class ShortAnswer implements Answer, Serializable {
	// *** Declare class variables here ***
	
    // Constructor that sets the answer and caseSensitive flag to the
    // class variables.
	public ShortAnswer(String answer, boolean caseSensitive) {
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
