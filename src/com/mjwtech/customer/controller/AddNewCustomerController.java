 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mjwtech.customer.controller;

import com.mjwtech.MainController;
import com.mjwtech.customer.model.Customer;
import data.database_connection.SettingsController;
import data.validation.validation;
import data.database_connection.dbconnection;
import data.dropdown.dropdowndata;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import jfxtras.labs.scene.control.*;
import name.antonsmirnov.javafx.dialog.Dialog;

/**
 * FXML Controller class
 *
 * @author mrgnwatson
 */
public class AddNewCustomerController implements Initializable {

    //date of birth date selecter
    @FXML
    private CalendarTextField calendar;
    //adds new customer to database
    @FXML
    private Button btnSubmit;
    //stores the list of countries
    @FXML
    private ComboBox<String> cmbCountry;
    //stores the list of states
    @FXML
    private ComboBox<String> cmbState;
    //stores the list of last name suffixes
    @FXML
    private ComboBox<String> cmbSuffix;
    //stores list of salutations
    @FXML
    private ComboBox<String> cmbSalutation;
    //stores list of highschools
    @FXML
    private ComboBox<String> cmbHighSchool;
    //address line one
    @FXML
    private TextField txtAddress1;
    //address line 2
    @FXML
    private TextField txtAddress2;
    //cellphone 1
    @FXML
    private TextField txtCellPhone1;
    //cellphone 2
    @FXML
    private TextField txtCellPhone2;
    //cellphone 3
    @FXML
    private TextField txtCellPhone3;
    //city
    @FXML
    private TextField txtCity;
    //email
    @FXML
    private TextField txtEmail;
    //first name
    @FXML
    private TextField txtFirstName;
    //homephone 1
    @FXML
    private TextField txtHomePhone1;
    //home phone 2
    @FXML
    private TextField txtHomePhone2;
    //homephone 3
    @FXML
    private TextField txtHomePhone3;
    //last name
    @FXML
    private TextField txtLastName;
    //zip code
    @FXML
    private TextField txtZip;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //makes the correct toggle button selected
        MainController.btnCustomer.setSelected(true);

        //set focus to txtfirst name on load
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                txtFirstName.requestFocus();
            }
        });

        cmbCountry.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                try {
                    dropdowndata.createStateDropDownData(t1);
                } catch (ClassNotFoundException | SQLException ex) {
                    Dialog.showError("ERROR", "Could not load state dropdown data");
                }
                cmbState.setItems(dropdowndata.stateList);
                cmbState.getSelectionModel().selectFirst();
            }
        });

        //load data into the drop downs
        try {
            loadDropDownData();
        } catch (ClassNotFoundException | SQLException e) {
            Dialog.showError("ERROR", "Could not load dropdown data\n\n" + e.getMessage());
        }

        //set calendar date format
        calendar.setDateFormat(new SimpleDateFormat("MM/dd/yyyy"));

        //create changelisteners for phone number fields
        createChangeListeners();

        //Event handler for submit button
        btnSubmit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                if(validateForm()){
                    Dialog.showInfo("Success", "Valid");//submitForm();
                }else{
                    Dialog.showError("ERROR", "There is invalid data in your form");
                }
                
            }
        });
    }

    //load date drop down data
    private void loadDropDownData() throws ClassNotFoundException, SQLException {
        dropdowndata.createDropDownData();
        cmbCountry.setItems(dropdowndata.countryList);
        cmbCountry.getSelectionModel().select("USA");
        cmbState.setItems(dropdowndata.stateList);
        cmbState.getSelectionModel().select("Texas");
        cmbSuffix.setItems(dropdowndata.suffixList);
        cmbSuffix.getSelectionModel().select("None");
        cmbSalutation.setItems(dropdowndata.salutationList);
        cmbSalutation.getSelectionModel().select("Mr.");
        Collections.sort(dropdowndata.highSchoolList);
        dropdowndata.highSchoolList.add(0, "NONE");
        dropdowndata.highSchoolList.add(1, "ADD NEW HIGHSCHOOL...");
        cmbHighSchool.setItems(dropdowndata.highSchoolList);
        cmbHighSchool.getSelectionModel().selectFirst();
    }

    //change listeners for the phone number fields
    //this section will automatically move to the next space when 3 characters are entered
    private void createChangeListeners() {
        final validation fv = new validation();
        txtHomePhone1.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {

                if (fv.validateNumberOnly(t1) && txtHomePhone1.getText().length() == 3) {
                    txtHomePhone2.requestFocus();
                }
                if (txtHomePhone1.getText().length() > 3) {
                    txtHomePhone1.setText(txtHomePhone1.getText().substring(0, 3));
                }
            }
        });
        txtHomePhone2.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                if (fv.validateNumberOnly(t1) && txtHomePhone2.getText().length() == 3) {
                    txtHomePhone3.requestFocus();
                }
                if (txtHomePhone2.getText().length() > 3) {
                    txtHomePhone2.setText(txtHomePhone2.getText().substring(0, 3));
                }
            }
        });
        txtHomePhone3.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                if (fv.validateNumberOnly(t1) && txtHomePhone3.getText().length() == 4) {
                    txtCellPhone1.requestFocus();
                }
                if (txtHomePhone3.getText().length() > 4) {
                    txtHomePhone3.setText(txtHomePhone3.getText().substring(0, 4));
                }
            }
        });

        txtCellPhone1.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                if (fv.validateNumberOnly(t1) && txtCellPhone1.getText().length() == 3) {
                    txtCellPhone2.requestFocus();
                }
                if (txtCellPhone1.getText().length() > 3) {
                    txtCellPhone1.setText(txtCellPhone1.getText().substring(0, 3));
                }
            }
        });
        txtCellPhone2.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                if (fv.validateNumberOnly(t1) && txtCellPhone2.getText().length() == 3) {
                    txtCellPhone3.requestFocus();
                }
                if (txtCellPhone2.getText().length() > 3) {
                    txtCellPhone2.setText(txtCellPhone2.getText().substring(0, 3));
                }
            }
        });
        txtCellPhone3.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                if (fv.validateNumberOnly(t1) && txtCellPhone3.getText().length() == 4) {
                    txtEmail.requestFocus();
                }
                if (txtCellPhone1.getText().length() > 4) {
                    txtCellPhone3.setText(txtCellPhone3.getText().substring(0, 4));
                }
            }
        });

    }

    //if validation is passed then the form is submitted to the database
    private void submitForm() {

        //add form details to customer object
        Customer.oCust.setFirstName(txtFirstName.getText());
        Customer.oCust.setLastName(txtLastName.getText());
        Customer.oCust.setSalutationName(cmbSalutation.getValue().toString());
        Customer.oCust.setSuffix(cmbSuffix.getValue().toString());
        Customer.oCust.setAddress1(txtAddress1.getText());
        Customer.oCust.setAddress2(txtAddress2.getText());
        Customer.oCust.setCity(txtCity.getText());
        Customer.oCust.setState(cmbState.getSelectionModel().getSelectedItem().toString());
        Customer.oCust.setZip(txtZip.getText());
        Customer.oCust.setHomePhone(txtHomePhone1.getText(), txtHomePhone2.getText(), txtHomePhone3.getText());
        Customer.oCust.setCellPhone(txtCellPhone1.getText(), txtCellPhone2.getText(), txtCellPhone3.getText());
        Customer.oCust.setEmail(txtEmail.getText());
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Customer.oCust.setDOB(sdf.format(calendar.valueProperty().getValue().getTime()));
        Customer.oCust.setHighschool(cmbHighSchool.getSelectionModel().getSelectedItem().toString());

        //create variables for query
        String fn = Customer.oCust.getFirstName();
        fn = fn.substring(0, 1).toUpperCase() + fn.substring(1).toLowerCase();
        String ln = Customer.oCust.getLastName();
        ln = ln.substring(0, 1).toUpperCase() + ln.substring(1).toLowerCase();
        String sf = Customer.oCust.getSuffix();
        String sa = Customer.oCust.getSalutationName();
        String add1 = Customer.oCust.getAddress1();
        String add2 = Customer.oCust.getAddress2();
        String city = Customer.oCust.getCity();
        city = city.substring(0, 1).toUpperCase() + city.substring(1).toLowerCase();
        String state = Customer.oCust.getState();
        String zip = Customer.oCust.getZip();
        String hp = Customer.oCust.getHomePhone();
        String cp = Customer.oCust.getCellPhone();
        String email = Customer.oCust.getEmail();
        String dob = Customer.oCust.getDOB();
        String hs = Customer.oCust.getHighschool();

        //create database connection and try to insert the new customer into database
        try {
            //connect to the database
            SettingsController.openConnection();
            ResultSet rs;
            //TODO: need to add highschool to query
            String sql = "EXEC createNewCustomer '" + fn + "','" + ln + "','" + sf + "','" + hp + "','" + cp + "','" + add1 + "','" + add2 + "',"
                    + "'" + city + "','" + state + "','" + zip + "','" + email + "','" + dob + "','" + sa + "','" + hs + "'";

            if (true) {
                rs = SettingsController.stmt.executeQuery(sql);
                while (rs.next()) {
                    //set the customer object's id value
                    Customer.oCust.setID(rs.getInt("CustomerID"));
                }
                //create confirmation dialog
                Dialog.buildConfirmation(null, null)
                        .create()
                        .setTitle("Emergency Contact")
                        .setMessage("Would you like to add an emergency contact\nfor this customer?")
                        //opens add new emergency page in content pane
                        .addYesButton(
                        new EventHandler() {
                    @Override
                    public void handle(Event t) {
                        //open the add new emergency contact in the content pane
                        MainController.transitionContent(null);
                    }
                })
                        //go to view customer record page        
                        .addNoButton(
                        new EventHandler() {
                    @Override
                    public void handle(Event t) {
                        //open the customer record in the content pane
                        MainController.transitionContent(null);
                    }
                })
                        .build()
                        .show();
            }
            SettingsController.closeConnection();
        } catch (ClassNotFoundException | SQLException ex) {
            Dialog.showError("Error", "Error adding customer to database.\n\nERROR:\n" + ex.getMessage());
            System.out.println(ex.getMessage());
        }
    }

    //called before before the form is submitted to check for valid input
    //TODO: alter function to tell user which field is not valid
    private Boolean validateForm() {
        //create variables for validation
        String fn = txtFirstName.getText();
        String ln = txtLastName.getText();
        String city = txtCity.getText();
        String zip = txtZip.getText();
        String hp = "(" + txtHomePhone1.getText() + ")" + txtHomePhone2.getText() + "-" + txtHomePhone3.getText();
        String cp = "(" + txtCellPhone1.getText() + ")" + txtCellPhone2.getText() + "-" + txtCellPhone3.getText();
        String email = txtEmail.getText();
        String dob = Customer.oCust.getDOB();

        //validate form before attempting to insert into database
        //create new validation object from the validation package
        validation fv = new validation();
        Boolean valid;
        valid = fv.validateTextOnly(fn);
        if (valid) {
            valid = fv.validateTextOnly(ln);
        }
        if (valid) {
            valid = fv.validateTextOnly(city);
        }
        if (valid) {
            valid = fv.validatePhoneNumber(hp);
        }
        if (valid) {
            valid = fv.validatePhoneNumber(cp);
        }
        if (valid) {
            valid = fv.validateZipCode(zip);
        }
        return valid;
    }
}