/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mjwtech.course.model;

/**
 *
 * @author mrgnwatson
 */
public interface Class {  
    
    //ClassID Property
    public void setClassID(Integer value);
    public int getClassID();
    
    //RecurrenceString Property
    public void setRecurrenceString(String value);
    public String getRecurrenceString();
    
    //StartDate Property
    public void setStartDate(String value);
    public String getStartDate();
    
    //EndDate Property
    public void setEndDate(String value);
    public String getEndDate();
    
    //StartDate Property
    public void setStartTime(String value);
    public String getStartTime();
    
    //EndDate Property
    public void setEndTime(String value);
    public String getEndTime();
    
    //InstructorName Property
    public void setInstructorName(String value);
    public String getInstructorName();
}
