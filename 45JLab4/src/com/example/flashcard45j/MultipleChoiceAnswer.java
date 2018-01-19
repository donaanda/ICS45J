// Written by James Hardjadinata and Richert Wang
// Modified for ICS 45J Lab 4 Fall 2015

package com.example.flashcard45j;

import java.io.Serializable;

// Class defining a MultipleChoiceAnswer Object for multiple choice questions.
// Contains a string representing the correct answer and a String array containing the list of possible answers
// given to the user.
// The convention for this answer type is that the first element of the array of answers is the correct answer.
public class MultipleChoiceAnswer implements Answer, Serializable {
	// *** Declare class variables here ***
	
	// A constructor that takes in the possible answers and assigns the appropriate values to the
	// class variables.
	public MultipleChoiceAnswer(String[] answers) {
		// *** Provide your implementation here. ***
	}
	
	// Method that returns the correct answer
	public String toString() {
		// REMOVE THIS
		return null;
		
		// *** Provide your implementation here. ***
	}

	// Method that returns the String array containing the possible answers to the user.
	public String[] getAnswerChoices() {
		// REMOVE THIS
		return null;
		
		// *** Provide your implementation here. ***
	}

	// Method that checks if the answer passed in the parameter is equal to the
	// correct answer.
	public boolean checkAnswer(String answer) {
		// REMOVE THIS
		return true;
		
		// *** Provide your implementation here. ***
	}

    // Method that checks if the Answer object has the correct answer.
    public boolean equals(Answer answer) {
        // REMOVE THIS
    	return true;
    	
		// *** Provide your implementation here. ***
    }
}
