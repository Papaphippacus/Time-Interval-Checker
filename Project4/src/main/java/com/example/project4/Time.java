/*
Elijah Phipps
CMSC 215 Week 8 Project 4
05/05/2024
This class is made to create a time in HH:MM
format and can compare one time to another
 */
package com.example.project4;

public final class Time implements Comparable<Time> {
    //initialize variables
    int hours, minutes;
    String meridian;

    //constructor
    public Time(int hours, int minutes, String meridian) throws InvalidTime {
        // Check if input is correct
        meridian = meridian.toUpperCase();
        if (hours > 12 || hours <= 0)
            throw new InvalidTime("Hours input are incorrect");
        if (minutes > 60 || minutes < 0)
            throw new InvalidTime("Minutes input incorrectly");
        if ((!meridian.equals("AM") && !meridian.equals("PM")))
            throw new InvalidTime("Meridian input incorrectly(must be \"AM\" or \"PM\"");

        //Add 12 to PM meridian so AM and PM are Comparable
        if(this.hours != 12) {
            if (this.meridian.equals("PM"))
                this.hours += 12;

            // If 12 AM then its set to 0 for comparability purpose
        } else if (this.meridian.equals("AM")) {
            this.hours = 0;
        }

        //set variables
        this.hours = hours;
        this.minutes = minutes;
        this.meridian = meridian;
    }

    //constructor for String input
    public Time(String time) throws InvalidTime {
        //error if empty
        if (time.isEmpty())
            throw new InvalidTime("A Text Box is Empty ");
        //accounting for no 0 in front of single digit hour(eg, 1:30 pm)
        time = time.trim();
        int colonIndex = time.indexOf(':');
        if (colonIndex == 1)
            time = "0" + time;

        //triming to eliminate spaciall difference
        time = time.replaceAll(" ", "");

        //accounting for single digit hours if no colon
        if (colonIndex == -1 && time.length() < 6) {
            time = "0" + time;

        }
        time = time.replaceAll(":", "");

        //seperating numbers in String to Char arrays
        char[] chars = time.toCharArray();
        char[] charHours = {chars[0], chars[1]};

        // error if Hour input is wrong
        if ((!Character.isDigit(charHours[0])) || (!Character.isDigit(charHours[1])) ||
                Integer.parseInt(String.valueOf(charHours)) > 12 || Integer.parseInt(String.valueOf(charHours)) <= 0)
            throw new InvalidTime("Hours input is incorrect");

        //set hours
        String stringHours = String.valueOf(charHours);
        this.hours = Integer.parseInt(stringHours);

        //error if minutes is input incorrectly
        char[] charMinutes = {chars[2], chars[3]};
        if ((!Character.isDigit(charMinutes[0])) || (!Character.isDigit(charMinutes[1])) ||
                Integer.parseInt(String.valueOf(charMinutes)) > 60 || Integer.parseInt(String.valueOf(charMinutes)) < 0)
            throw new InvalidTime("Minutes input is incorrect");

        //set minutes
        String stringMinute = String.valueOf(charMinutes);
        this.minutes = Integer.parseInt(stringMinute);

        //set meridian
        char[] charMeridian = {chars[4], chars[5]};
        this.meridian = String.valueOf(charMeridian);

        //Error if meridian is input incorrectly
        if (!this.meridian.equalsIgnoreCase("am") && !this.meridian.equalsIgnoreCase("pm"))
            throw new InvalidTime("Meridian input incorrectly (must be \"AM\" or \"PM\")");
        this.meridian = this.meridian.toUpperCase();

        //add 12 to pm hours for comparability
        if(this.hours != 12) {
            if (this.meridian.equals("PM"))
                this.hours += 12;
            // 12Am to 00 for comparability
        } else if (this.meridian.equals("AM")) {
            this.hours = 0;
        }

    }


    //method for comparing times
    public int compareTo(Time t) {
            if (this.hours < t.hours || (this.hours == t.hours && this.minutes < t.minutes))
                return -1;
            else if (this.hours > t.hours || (this.hours == t.hours && this.minutes > t.minutes))
                return 1;
            else
                return 0;
    }

    //to String method
    @Override
    public String toString() {
        return String.format("%02d:%02d %s", this.hours, this.minutes, this.meridian);
    }
}

