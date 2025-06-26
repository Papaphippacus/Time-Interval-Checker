/*
Elijah Phipps
CMSC 215 Week 8 Project 4
05/05/2024
This Class creates and interval object which takes
is created from 2 Times and can return if they overlap,
if one is a subinterval and if they contain a time
 */
package com.example.project4;

public class Interval <T extends Comparable<T> > {
    //initialize variables
    T start;
    T end;
    //constructor
    public Interval(T start, T end){
        this.start = start;
        this.end = end;
    }
    //method to return if a given time is in the interval
    public boolean within(T t){
        return this.start.compareTo(t) <= 0 && this.end.compareTo(t) >= 0;
    }
    //method to return if one interval is a subinterval of another
    public boolean subinterval (Interval interval){
        return (this.start.compareTo((T) interval.start) >= 0) && (this.end.compareTo((T) interval.end) <= 0);
    }
    //method that returns if two intervals overlaps
    public boolean overlaps(Interval interval){
        if ((this.start.compareTo((T) interval.start) >= 0 && this.start.compareTo((T) interval.end) <= 0) ||
                (this.end.compareTo((T) interval.end) <= 0 && this.end.compareTo((T) interval.start) >= 0))
            return true;
        else if ((interval.start.compareTo(this.start) >= 0 && interval.start.compareTo(this.end) <= 0) ||
                    (interval.end.compareTo(this.end) <= 0 && interval.end.compareTo(this.start) >= 0)) {
            return true;
        } else
            return false;
    }
}

