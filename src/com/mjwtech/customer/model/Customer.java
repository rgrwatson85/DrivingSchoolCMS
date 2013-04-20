/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mjwtech.customer.model;

import java.text.DecimalFormat;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author mrgnwatson
 */
public class Customer {

    public static Customer oCust = new Customer();
   
    //ID property
    private IntegerProperty CustomerID;
    public void setID(int value) { IDProperty().setValue(value);}
    public int getID() { return IDProperty().get();}
    public IntegerProperty IDProperty() {
        if (CustomerID == null) {
            CustomerID = new SimpleIntegerProperty(this, "CustomerID");
        }
        return CustomerID;
    }
   
    //Salutation property
    private StringProperty SalutationName;
    public void setSalutationName(String value) {SalutationNameProperty().set(value);}
    public String getSalutationName() { return SalutationNameProperty().get();}
    public StringProperty SalutationNameProperty() {
        if (SalutationName == null) {
            SalutationName = new SimpleStringProperty(this, "SalutationName");
        }
        return SalutationName;
    }
   
    //First name property
    private StringProperty FirstName;
    public void setFirstName(String value) {FirstNameProperty().set(value);}
    public String getFirstName() {return FirstNameProperty().get();}
    public StringProperty FirstNameProperty() {
        if (FirstName == null) {
            FirstName = new SimpleStringProperty(this, "FirstName");
        }
        return FirstName;
    }
    
    //Last name property
    private StringProperty LastName;
    public void setLastName(String value) {LastNameProperty().set(value);}
    public String getLastName() {return LastNameProperty().get();}
    public StringProperty LastNameProperty() {
        if (LastName == null) {
            LastName = new SimpleStringProperty(this, "LastName");
        }
        return LastName;
    }
    
    //suffix property
    private StringProperty Suffix;
    public void setSuffix(String value) {SuffixProperty().set(value);}
    public String getSuffix() {return SuffixProperty().get();}
    public StringProperty SuffixProperty() {
        if (Suffix == null) {
            Suffix = new SimpleStringProperty(this, "Suffix");
        }
        return Suffix;
    }
    
    //Address1 property
    private StringProperty Address1;
    public void setAddress1(String value) {Address1Property().set(value);}
    public String getAddress1() {return Address1Property().get();}
    public StringProperty Address1Property() {
        if (Address1 == null) {Address1 = new SimpleStringProperty(this, "Address1");
        }
        return Address1;
    }
    
    //Address2 property
    private StringProperty Address2;
    public void setAddress2(String value) {Address2Property().set(value);}
    public String getAddress2() {return Address2Property().get();}
    public StringProperty Address2Property() {
        if (Address2 == null) {
            Address2 = new SimpleStringProperty(this, "Address2");
        }
        return Address2;
    }
   
    //City property
    private StringProperty City;
    public void setCity(String value) {
        CityProperty().set(value);
    }
    public String getCity() {
        return CityProperty().get();
    }
    public StringProperty CityProperty() {
        if (City == null) {
            City = new SimpleStringProperty(this, "City");
        }
        return City;
    }
    
    //State property
    private StringProperty State;
    public void setState(String value) {
        StateProperty().set(value);
    }
    public String getState() {
        return StateProperty().get();
    }
    public StringProperty StateProperty() {
        if (State == null) {
            State = new SimpleStringProperty(this, "State");
        }
        return State;
    }
    
    //Country property
    private StringProperty Country;
    public void setCountry(String value) {
        CountryProperty().set(value);
    }
    public String getCountry() {
        return CountryProperty().get();
    }
    public StringProperty CountryProperty() {
        if (Country == null) {
            Country = new SimpleStringProperty(this, "Country");
        }
        return Country;
    }
    
    //Zip code property
    private StringProperty Zip;
    public void setZip(String value) {
        ZipProperty().set(value);
    }
    public String getZip() {
        return ZipProperty().get();
    }
    public StringProperty ZipProperty() {
        if (Zip == null) {
            Zip = new SimpleStringProperty(this, "Zip");
        }
        return Zip;
    }
    
    //homePhone property
    private StringProperty HomePhone;
    public void setHomePhone(String value, String value2, String value3) {
        HomePhoneProperty().set("(" + value + ") " + value2 + "-" + value3);
    }
    public void setHomePhone(String value) {
        HomePhoneProperty().set(value);
    }
    public String getHomePhone() {
        return HomePhoneProperty().get();
    }
    public StringProperty HomePhoneProperty() {
        if (HomePhone == null) {
            HomePhone = new SimpleStringProperty(this, "HomePhone");
        }
        return HomePhone;
    }
   
    //cellPhone property
    private StringProperty CellPhone;
    public void setCellPhone(String value, String value2, String value3) {
        CellPhoneProperty().set("(" + value + ") " + value2 + "-" + value3);
    }
    public void setCellPhone(String value) {
        CellPhoneProperty().set(value);
    }
    public String getCellPhone() {
        return CellPhoneProperty().get();
    }
    public StringProperty CellPhoneProperty() {
        if (CellPhone == null) {
            CellPhone = new SimpleStringProperty(this, "CellPhone");
        }
        return CellPhone;
    }
    
    //Email property
    private StringProperty Email;
    public void setEmail(String value) {
        EmailProperty().set(value);
    }
    public String getEmail() {
        return EmailProperty().get();
    }
    public StringProperty EmailProperty() {
        if (Email == null) {
            Email = new SimpleStringProperty(this, "Email");
        }
        return Email;
    }
    
    //dob property
    private StringProperty DOB;
    public void setDOB(String value, String value2, String value3) {
        DOBProperty().set(value + "/" + value2 + "/" + value3);
    }
    public void setDOB(String value) {
        DOBProperty().set(value);
    }
    public String getDOB() {
        return DOBProperty().get();
    }
    public StringProperty DOBProperty() {
        if (DOB == null) {
            DOB = new SimpleStringProperty(this, "DOB");
        }
        return DOB;
    }
    
    //highschool property
    private StringProperty Highschool;
    public void setHighschool(String value) {
        HighschoolProperty().set(value);
    }
    public String getHighschool() {
        return HighschoolProperty().get();
    }
    public StringProperty HighschoolProperty() {
        if (Highschool == null) {
            Highschool = new SimpleStringProperty(this, "Highschool");
        }
        return Highschool;
    }
    
    //balanceDue property
    DecimalFormat formatter = new DecimalFormat("$#,##0.00");
    private StringProperty BalanceDue;
    public void setBalanceDue(Double value) {
        BalanceDueProperty().set(formatter.format(value));
    }
    public String getBalanceDue() {
        return BalanceDueProperty().get();
    }
    public StringProperty BalanceDueProperty() {
        if (BalanceDue == null) {
            BalanceDue = new SimpleStringProperty(this, "BalanceDue");
        }
        return BalanceDue;
    }
}