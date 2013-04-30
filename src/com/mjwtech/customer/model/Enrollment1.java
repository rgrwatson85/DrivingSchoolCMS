/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mjwtech.customer.model;

import com.mjwtech.course.model.Class;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author mrgnwatson
 */
public class Enrollment1{   
    
    //CustomerID Property
    private IntegerProperty CustomerID;
    public void setClassID(int value){CustomerIDProperty().set(value);}
    public int getClassID(){return  CustomerIDProperty().get();}
    public IntegerProperty CustomerIDProperty(){
        if(CustomerID==null){
            CustomerID = new SimpleIntegerProperty(this, "CustomerID");
        }
        return CustomerID;
    }

    
    //EnrolledClasses Property
    private  static Class CustomerClasses = new Class() {
        
        @Override
        public void setClassID(Integer value) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public int getClassID() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void setRecurrenceString(String value) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public String getRecurrenceString() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void setStartDate(String value) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public String getStartDate() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void setEndDate(String value) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public String getEndDate() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void setStartTime(String value) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public String getStartTime() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void setEndTime(String value) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public String getEndTime() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void setInstructorName(String value) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public String getInstructorName() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    };
}   

