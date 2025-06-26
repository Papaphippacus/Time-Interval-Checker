/*
Elijah Phipps
CMSC 215 Week 8 Project 4
05/05/2024
This program is made to display GUI, take input Time intervals
and display whether they are overlapping, subset, and if a
given time is contained in either or both intervals.
 */
package com.example.project4;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Project4 extends Application{
    //call main

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws InvalidTime{
        //initialize nodes
        TextField start1Tf = new TextField();
        start1Tf.setAlignment(Pos.CENTER);
        TextField start2Tf = new TextField();
        start2Tf.setAlignment(Pos.CENTER);
        TextField end1Tf = new TextField();
        end1Tf.setAlignment(Pos.CENTER);
        TextField end2Tf = new TextField();
        end2Tf.setAlignment(Pos.CENTER);
        TextField checkTf = new TextField();
        checkTf.setAlignment(Pos.CENTER);
        TextField outputTf = new TextField();
        Button compareIntervals = new Button("Compare Intervals");
        Button checkTime = new Button("Check Time");
        outputTf.setEditable(false);

        //set nodes size to comfort
        compareIntervals.setPrefWidth(387);
        checkTime.setPrefWidth(387);
        checkTf.setPrefWidth(305);
        outputTf.setPrefWidth(387);

        //initialize pane
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(5));
        pane.setHgap(5);
        pane.setVgap(5);

        //create Hboxs for pane
        HBox hBox = new HBox(new Label(""), new Label("Start Time"), new Label("End Time"));
        HBox hBox2 = new HBox(new Label("Time Interval 1"), start1Tf, end1Tf);
        HBox hBox3 = new HBox(new Label("Time Interval 2"), start2Tf, end2Tf);
        HBox hBox4 = new HBox(new Label("Time to Check"), checkTf);

        //Initialize spacing
        hBox2.setSpacing(5);
        hBox3.setSpacing(5);
        hBox4.setSpacing(5);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(100);

        //add Hbox's to pane
        pane.add(hBox, 0, 0);
        pane.add(hBox2, 0, 1);
        pane.add(hBox3, 0, 2);
        pane.add(compareIntervals, 0, 3);
        pane.add(hBox4, 0, 4);
        pane.add(checkTime, 0, 5);
        pane.add(outputTf, 0, 6);


        compareIntervals.setOnAction(e -> {
            //set compare buttons actions
                try {
                    //set variables to text fields
                    Time start1 = parseTime(start1Tf.getText());

                    Time end1 = parseTime(end1Tf.getText());

                    Time start2 = parseTime(start2Tf.getText());
                    Time end2 = parseTime(end2Tf.getText());

                    //create intervals
                    Interval<Time> interval1 = new Interval<Time>(start1, end1);
                    Interval<Time> interval2 = new Interval<Time>(start2, end2);


                    //check if intervals overlap
                    if (interval1.overlaps(interval2)) {
                        //check if intervals are subintervals
                        if (interval1.subinterval(interval2))
                            outputTf.setText("Interval 1 is a sub-interval of interval 2");
                        else if (interval2.subinterval(interval1))
                            outputTf.setText("Interval 2 is a sub-interval of interval 1");
                        else
                            outputTf.setText("The intervals overlap");
                    }
                    //display if they are neither
                    else
                        outputTf.setText("The intervals are disjoint");
                    //catch invalid inputs
                } catch(InvalidTime ex){
                    outputTf.setText(ex.getMessage());
                }


        });

        checkTime.setOnAction(e -> {
            //set compare buttons actions
            try {
                //set variables to text fields
                Time start1 = new Time(start1Tf.getText());
                Time end1 = new Time(end1Tf.getText());
                Time start2 = new Time(start2Tf.getText());
                Time end2 = new Time(end2Tf.getText());

                //check Intervals
                Interval<Time> interval1 = new Interval<Time>(start1, end1);
                Interval<Time> interval2 = new Interval<Time>(start2, end2);
                Time timeCheck = new Time((checkTf.getText()));

                //Display what intervals contain the given time
                if (interval1.within(timeCheck) && interval2.within(timeCheck)) {
                    outputTf.setText("Both intervals contains the time " + timeCheck.toString());
                    return;
                } else if (interval1.within(timeCheck)) {
                    outputTf.setText(" Only interval 1 contains the time " + timeCheck.toString());
                } else if (interval2.within(timeCheck)) {
                    outputTf.setText("Only interval 2 contains the time " + timeCheck.toString());
                } else
                    outputTf.setText("Neither interval contains the time " + timeCheck.toString());
                //catch invalid inputs
            } catch(InvalidTime ex){
                outputTf.setText(ex.getMessage());
            }

        });

        //initialize Scene
        Scene scene = new Scene(pane, 450, 250);
        primaryStage.setTitle("Time Interval Checker");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    //method for better readability of variable initialization
    private Time parseTime(String input) throws InvalidTime{
            return new Time(input);

    }
}
