/*
Elijah Phipps
CMSC 215 Week 8 Project 4
05/05/2024
This Exception is created in case the
user inputs a wrong time format
 */
package com.example.project4;

public class InvalidTime extends Exception{
    public InvalidTime(String message){
        super(message);
        ;
    }
}