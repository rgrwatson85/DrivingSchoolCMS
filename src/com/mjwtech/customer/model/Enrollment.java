/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mjwtech.customer.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author mrgnwatson
 */
public class Enrollment {
        //CourseName Property
        private StringProperty CourseName;
        public String getCourseName(){return CourseNameProperty().get();}
        public void setCourseName(String value){CourseNameProperty().set(value);}
        public StringProperty CourseNameProperty(){
            if(CourseName==null){
                CourseName = new SimpleStringProperty(this, "CourseName");
            }
            return CourseName;
        }
        //CourseCost
        private DoubleProperty CourseCost;
        public double getCourseCost(){return CourseCostProperty().get();}
        public void setCourseCost(double value){CourseCostProperty().set(value);}
        public DoubleProperty CourseCostProperty(){
            if(CourseCost==null){
                CourseCost = new SimpleDoubleProperty(this, "CourseCost");
            }
            return CourseCost;
        }
        //ClassID Property
        private IntegerProperty ClassID;
        public int getClassID(){return ClassIDProperty().get();}
        public void setClassID(int value){ClassIDProperty().set(value);}
        public IntegerProperty ClassIDProperty(){
            if(ClassID==null){
                ClassID = new SimpleIntegerProperty(this, "ClassID");
            }
            return ClassID;
        }
        //RecurrenceString Property
        private StringProperty RecurrenceString;
        public String getRecurrenceString(){return RecurrenceStringProperty().get();}
        public void setRecurrenceString(String value){RecurrenceStringProperty().set(value);}
        public StringProperty RecurrenceStringProperty(){
            if(RecurrenceString==null){
                RecurrenceString = new SimpleStringProperty(this, "RecurrenceString");
            }
            return RecurrenceString;
        }
        //StartDate Property
        private StringProperty StartDate;
        public String getStartDate(){return StartDateProperty().get();}
        public void setStartDate(String value){StartDateProperty().set(value);}
        public StringProperty StartDateProperty(){
            if(StartDate==null){
                StartDate = new SimpleStringProperty(this, "StartDate");
            }
            return StartDate;
        }
        //EndDate Property
        private StringProperty EndDate;
        public String getEndDate(){return EndDateProperty().get();}
        public void setEndDate(String value){EndDateProperty().set(value);}
        public StringProperty EndDateProperty(){
            if(EndDate==null){
                EndDate = new SimpleStringProperty(this, "EndDate");
            }
            return EndDate;
        }
        //Time Property
        private StringProperty Time;
        public String getTime(){return TimeProperty().get();}
        public void setTime(String value){TimeProperty().set(value);}
        public StringProperty TimeProperty(){
            if(Time==null){
                Time = new SimpleStringProperty(this, "Time");
            }
            return Time;
        }
        //InstructorName Property
        private StringProperty InstructorName;
        public String getInstructorName(){return InstructorNameProperty().get();}
        public void setInstructorName(String value){InstructorNameProperty().set(value);}
        public StringProperty InstructorNameProperty(){
            if(InstructorName==null){
                InstructorName = new SimpleStringProperty(this, "InstructorName");
            }
            return InstructorName;
        }   
    }
