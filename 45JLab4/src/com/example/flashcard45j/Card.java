// Written by James Hardjadinata and Richert Wang
// Modified for ICS 45J Lab 4 Fall 2015

package com.example.flashcard45j;

import java.io.Serializable;

//This is the implementation for the card class.
//A card consists of a string type, string question, one of the types of answers, and the difficulty as an int variable.

// The Card class represents a FlashCard used for this application.
// All cards consists of a String question and a String question type (i.e. subject such as "Inheritance",
// "Exceptions", "Loops", etc). Each card has a correct Answer associated with it and a difficulty
// (between 1 - 3). For this lab, you can assume all Cards have a difficulty of "1".
public class Card implements Serializable {

	// *** Declare class variables here ***

	// Constructor taking in to necessary fields to assign to the Card's class variables.
	public Card(String question, Answer answer, int difficulty, String type) {
		// Provide your implementation here
	}
	
	// Copy constructor
	public Card(Card c) {
		// Provide your implementation here
	}
	
	// Getter method returning the Card's Question.
	public String getQuestion() {
		// REMOVE THIS
		return null;
		
		// *** Provide your implementation here. ***
	}
	
	// Getter method returning the Card's Answer
	public Answer getAnswer() {
		// REMOVE THIS
		return null;
		
		// *** Provide your implementation here. ***
	}
	
	// Getter method returning the Card's Difficulty
	public int getDifficulty() {
		// REMOVE THIS
		return 1;
		
		// *** Provide your implementation here. ***
	}

	// Getter method returning the Card's Type
    public String getType() {
    	// REMOVE THIS
    	return null;
    	
    	// *** Provide your implementation here. ***
    }

    // Checks if two cards are equal if they have the same question, question type, and answer.
    public boolean equals(Card card) {
    	// REMOVE THIS
    	return true;
    	
    	// *** Provide your implementation here. ***
    }

    // The cardFactory method takes in a String array containing multiple pieces of information that is required
    // to construct specific cards.
	// Strings passed a form of: {question};{difficulty};{question type};{answer type};{answer};{case sensitive if applicable}.
    // You should use the card type to construct the appropriate Answer object.
    // Once all pieces are obtained, a new Card is constructed and returned.
    // If the answer type is unrecognizable, then throw an IllegalArgumentException()
	public static Card cardFactory(String[] args) throws IllegalArgumentException {
		// REMOVE THIS
		return null;
		
    	// *** Provide your implementation here. ***
	}
}
