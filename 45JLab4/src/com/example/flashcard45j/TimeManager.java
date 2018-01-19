// Written by James Hardjadinata and Richert Wang
// Modified for ICS 45J Lab 4 Fall 2015

package com.example.flashcard45j;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TimeManager implements Serializable {
    List<Long> times = new ArrayList<Long>();
    public void insertTime(long time) {
        times.add(time);
    }

    //Convert the time array to be standardized relative to the first time taken.
    public List<Long> calculateDeltaTime() {
        List<Long> deltaTimes = new ArrayList<Long>();
        for(int i = times.size()-1; i >= 0; i--)
            deltaTimes.add(0, times.get(i) - times.get(0));

        return deltaTimes;
    }

    public double calculateElapsedTime() {
        return (times.get(times.size()-1)-times.get(0))/1000d;
    }
}
