/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mjwtech.customer;

import java.text.DecimalFormat;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author mrgnwatson
 */
public class Customer {

    public static Customer oCust = new Customer();
    //ID property
    private IntegerProperty ID;

    public void setID(int value) {
        IDProperty().setValue(value);
    }

    public int getID() {
        return IDProperty().get();
    }

    public IntegerProperty IDProperty() {
        if (ID == null) {
            ID = new SimpleIntegerProperty(this, "ID");
        }
        return ID;
    }
    //Salutation property
    private StringProperty salutation;

    public void setSalutation(String value) {
        salutationProperty().set(value);
    }

    public String getSalutation() {
        return salutationProperty().get();
    }

    public StringProperty salutationProperty() {
        if (salutation == null) {
            salutation = new SimpleStringProperty(this, "salutation");
        }
        return salutation;
    }
    //First name property
    private StringProperty firstName;

    public void setFirstName(String value) {
        firstNameProperty().set(value);
    }

    public String getFirstName() {
        return firstNameProperty().get();
    }

    public StringProperty firstNameProperty() {
        if (firstName == null) {
            firstName = new SimpleStringProperty(this, "FirstName");
        }
        return firstName;
    }
    //Last name property
    private StringProperty lastName;

    public void setLastName(String value) {
        lastNameProperty().set(value);
    }

    public String getLastName() {
        return lastNameProperty().get();
    }

    public StringProperty lastNameProperty() {
        if (lastName == null) {
            lastName = new SimpleStringProperty(this, "LastName");
        }
        return lastName;
    }
    //suffix property
    private StringProperty suffix;

    public void setSuffix(String value) {
        suffixProperty().set(value);
    }

    public String getSuffix() {
        return suffixProperty().get();
    }

    public StringProperty suffixProperty() {
        if (suffix == null) {
            suffix = new SimpleStringProperty(this, "suffix");
        }
        return suffix;
    }
    //Address1 property
    private StringProperty address1;

    public void setAddress1(String value) {
        address1Property().set(value);
    }

    public String getAddress1() {
        return address1Property().get();
    }

    public StringProperty address1Property() {
        if (address1 == null) {
            address1 = new SimpleStringProperty(this, "address1");
        }
        return address1;
    }
    //Address2 property
    private StringProperty address2;

    public void setAddress2(String value) {
        address2Property().set(value);
    }

    public String getAddress2() {
        return address2Property().get();
    }

    public StringProperty address2Property() {
        if (address2 == null) {
            address2 = new SimpleStringProperty(this, "address2");
        }
        return address2;
    }
    //City property
    private StringProperty city;

    public void setCity(String value) {
        cityProperty().set(value);
    }

    public String getCity() {
        return cityProperty().get();
    }

    public StringProperty cityProperty() {
        if (city == null) {
            city = new SimpleStringProperty(this, "city");
        }
        return city;
    }
    //State property
    private StringProperty state;

    public void setState(String value) {
        stateProperty().set(value);
    }

    public String getState() {
        return stateProperty().get();
    }

    public StringProperty stateProperty() {
        if (state == null) {
            state = new SimpleStringProperty(this, "state");
        }
        return state;
    }
    //Country property
    private StringProperty country;

    public void setCountry(String value) {
        countryProperty().set(value);
    }

    public String getCountry() {
        return countryProperty().get();
    }

    public StringProperty countryProperty() {
        if (country == null) {
            country = new SimpleStringProperty(this, "country");
        }
        return country;
    }
    //Zip code property
    private StringProperty zip;

    public void setZip(String value) {
        zipProperty().set(value);
    }

    public String getZip() {
        return zipProperty().get();
    }

    public StringProperty zipProperty() {
        if (zip == null) {
            zip = new SimpleStringProperty(this, "zip");
        }
        return zip;
    }
    //homePhone property
    private StringProperty homePhone;

    public void sethomePhone(String value, String value2, String value3) {
        homePhoneProperty().set("(" + value + ") " + value2 + "-" + value3);
    }

    public void sethomePhone(String value) {
        homePhoneProperty().set(value);
    }

    public String getHomePhone() {
        return homePhoneProperty().get();
    }

    public StringProperty homePhoneProperty() {
        if (homePhone == null) {
            homePhone = new SimpleStringProperty(this, "homePhone");
        }
        return homePhone;
    }
    //cellPhone property
    private StringProperty cellPhone;

    public void setcellPhone(String value, String value2, String value3) {
        cellPhoneProperty().set("(" + value + ") " + value2 + "-" + value3);
    }

    public void setcellPhone(String value) {
        cellPhoneProperty().set(value);
    }

    public String getCellPhone() {
        return cellPhoneProperty().get();
    }

    public StringProperty cellPhoneProperty() {
        if (cellPhone == null) {
            cellPhone = new SimpleStringProperty(this, "cellPhone");
        }
        return cellPhone;
    }
    //Email property
    private StringProperty email;

    public void setEmail(String value) {
        emailProperty().set(value);
    }

    public String getEmail() {
        return emailProperty().get();
    }

    public StringProperty emailProperty() {
        if (email == null) {
            email = new SimpleStringProperty(this, "email");
        }
        return email;
    }
    //dob property
    private StringProperty dob;

    public void setDOB(String value, String value2, String value3) {
        dobProperty().set(value + "/" + value2 + "/" + value3);
    }

    public void setDOB(String value) {
        dobProperty().set(value);
    }

    public String getDOB() {
        return dobProperty().get();
    }

    public StringProperty dobProperty() {
        if (dob == null) {
            dob = new SimpleStringProperty(this, "dob");
        }
        return dob;
    }
    //highschool property
    private StringProperty highschool;

    public void setHighschool(String value) {
        highschoolProperty().set(value);
    }

    public String getHighschool() {
        return highschoolProperty().get();
    }

    public StringProperty highschoolProperty() {
        if (highschool == null) {
            highschool = new SimpleStringProperty(this, "highschool");
        }
        return highschool;
    }
    //balanceDue property
    DecimalFormat formatter = new DecimalFormat("$#,##0.00");
    private StringProperty balanceDue;

    public void setbalanceDue(Double value) {
        balanceDueProperty().set(formatter.format(value));
    }

    public String getbalanceDue() {
        return balanceDueProperty().get();
    }

    public StringProperty balanceDueProperty() {
        if (balanceDue == null) {
            balanceDue = new SimpleStringProperty(this, "balanceDue");
        }
        return balanceDue;
    }
}