// Written by James Hardjadinata and Richert Wang
// Modified for ICS 45J Lab 4 Fall 2015

package com.example.flashcard45j;

// An interface defining methods that must be provided for all classes
// implementing this interface (i.e. MultipleChoiceAnswer, ShortAnswer, TruthAnswer)
public interface Answer {
	// Takes in an Answer object and returns true if the Answer object has the correct
	// answer.
    boolean equals(Answer answer);
    
    // Checks if the answer passed in the parameter is equal to the correct
    // answer for the class implementing this interface.
    // Returns true if the answer is the correct answer, returns false otherwise.
	boolean checkAnswer(String answer);
	
	// All classes that implement Answer must have a correct answer. 
	// .toString() will return the correct answer.
    String toString();
}
