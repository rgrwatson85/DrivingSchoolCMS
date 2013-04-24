/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mjwtech.customer.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author mrgnwatson
 */
public class customerNote {
    
    //ID property
    private IntegerProperty ID;
    public void setID(int value) {IDProperty().setValue(value);}
    public int getID() {return IDProperty().get();}
    public IntegerProperty IDProperty(){
        if (ID == null){
            ID = new SimpleIntegerProperty(this, "ID");
        }
        return ID;
    }
    
    //Date property
    private StringProperty Date;
    public void setDate(String value) {DateProperty().set(value);}
    public String getDate() {return DateProperty().get();}
    public StringProperty DateProperty() { 
        if (Date == null) {
            Date = new SimpleStringProperty(this, "Date");
        }
        return Date; 
    }
    
    //Note property
    private StringProperty Note;
    public void setNote(String value) {NoteProperty().set(value);}
    public String getNote() {return NoteProperty().get();}
    public StringProperty NoteProperty() { 
        if (Note == null) {
            Note = new SimpleStringProperty(this, "Note");
        }
        return Note; 
    }
    
    //Note property
    private StringProperty ShortNote;
    public void setShortNote(String value) {ShortNoteProperty().set(value);}
    public String getShortNote() {return ShortNoteProperty().get();}
    public StringProperty ShortNoteProperty() { 
        if (ShortNote == null) {
            ShortNote = new SimpleStringProperty(this, "ShortNote");
        }
        return ShortNote; 
    }
}
