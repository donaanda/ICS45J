// Written by James Hardjadinata and Richert Wang
// Modified for ICS 45J Lab 4 Fall 2015

package com.example.flashcard45j;

import java.util.ArrayList;
import java.util.List;

public class Statistics {
    //Simple average method - calculates for two integers a/b to be returned as a double value.
    public static double average(int a, int b) {return (double)a/b; }

    //Given a list of times in nanoseconds, returns the average of their differences.
    public static double getAverage(List<Long> times) {
        double sum = 0;
        int index = 0;
        for( int i = 0; i < times.size()-1; i++ ) {
            sum += (times.get(i+1)/1000d)-(times.get(i)/1000d);
        }

        return sum/times.size();
    }

    //Given a list of times in nanoseconds, returns the standard deviation of their differences.
    public static double getStandardDeviation(List<Long> times) {
        double mean = Statistics.getAverage(times), sum = 0;
        for(int i = 0; i < times.size()-1; i++) {
            double deltaTime = (times.get(i+1)/1000d)-(times.get(i)/1000d);
            sum += Math.pow(deltaTime - mean, 2);
        }
        return Math.sqrt(sum/(times.size()-1));
    }

    //Given the card list, average the difficulties.
    public static double getAverageDifficulty(ArrayList<Card> cardList) {
        int sum = 0;
        for(Card card: cardList)
            sum += card.getDifficulty();

        return (double)sum/cardList.size();
    }
}
