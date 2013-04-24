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
import java.util.Calendar;
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
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import jfxtras.labs.scene.control.*;
import name.antonsmirnov.javafx.dialog.Dialog;

/**
 * FXML Controller class
 *
 * @author mrgnwatson
 */
public class AddNewCustomerController implements Initializable {

    @FXML
    private AnchorPane root;
    //date of birth date selecter
    @FXML
    private CalendarTextField calendar;
    @FXML
    private TextField calendar_mask;
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
    
    private boolean valid;

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

        //changes the state drop down box to reflect the selected country
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

        //opens the add new highschool dialog
        cmbHighSchool.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                if ("ADD NEW HIGHSCHOOL...".equals(t1)) {
                    Dialog.showError("ERROR", "This functionality has not yet been implemented");
                }
            }
        });

        //load data into the drop downs
        try {
            loadDropDownData();
        } catch (ClassNotFoundException | SQLException e) {
            Dialog.showError("ERROR", "Could not load dropdown data\n\n" + e.getMessage());
        }

        //set calendar date format
        final SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        calendar.setDateFormat(sdf);
        calendar.valueProperty().addListener(new ChangeListener<Calendar>() {
            @Override
            public void changed(ObservableValue<? extends Calendar> ov, Calendar t, Calendar t1) {
                calendar_mask.setText(sdf.format(calendar.getValue().getTime()));
            }
        });
        calendar_mask.setEditable(false);

        //on the fly form validation and auto-move for phone number fields
        createChangeListeners();

        //Event handler for submit button
        btnSubmit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                for(Node n : root.getChildren()){
                    if(n instanceof TextField && n.getStyleClass().contains("error"))
                        valid=false;
                    else
                        valid=true;
                }
                if (valid) {
                    submitForm();
                } else {
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

    /*
     * This section provides on the fly form validation for each form control
     * It is very basic and is still prone to bad data being entered.
     */
    private void createChangeListeners() {
        final validation fv = new validation();
        
        //apply error class to all text fields on load
        for (Node n : root.getChildren()) {
            if (n instanceof TextField) {
                n.getStyleClass().add("error");
            }
        }
        txtFirstName.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                if (fv.validateTextOnly(t1) && t1.length() <= 25 && t1.length() > 0) {
                    if (t1.length() < 3) {
                        txtFirstName.getStyleClass().add("warning");
                    } else {
                        txtFirstName.getStyleClass().remove("error");
                    }
                    txtFirstName.getStyleClass().remove("warning");
                    txtFirstName.getStyleClass().add("valid");
                } else {
                    txtFirstName.getStyleClass().remove("warning");
                    txtFirstName.getStyleClass().remove("valid");
                    txtFirstName.getStyleClass().add("error");
                }
            }
        });
        txtLastName.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                if (fv.validateTextOnly(t1) && t1.length() <= 25 && t1.length() > 0) {
                    if (t1.length() < 3) {
                        txtLastName.getStyleClass().add("warning");
                    } else {
                        txtLastName.getStyleClass().remove("error");
                    }
                    txtLastName.getStyleClass().remove("warning");
                    txtLastName.getStyleClass().add("valid");
                } else {
                    txtLastName.getStyleClass().remove("warning");
                    txtLastName.getStyleClass().remove("valid");
                    txtLastName.getStyleClass().add("error");
                }
            }
        });
        txtAddress1.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                if (fv.validateAddress(t1) && t1.length() <= 150 && t1.length() > 0) {
                    if (t1.length() < 3) {
                        txtAddress1.getStyleClass().add("warning");
                    } else {
                        txtAddress1.getStyleClass().remove("error");
                    }
                    txtAddress1.getStyleClass().remove("warning");
                    txtAddress1.getStyleClass().add("valid");
                    if (fv.validateAddress(txtAddress2.getText()) || txtAddress2.getText().length() == 0) {
                        txtAddress2.getStyleClass().add("valid");
                    }
                } else {
                    txtAddress1.getStyleClass().remove("warning");
                    txtAddress1.getStyleClass().remove("valid");
                    txtAddress1.getStyleClass().add("error");
                }
            }
        });
        txtAddress2.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                if (t1.length() > 0) {
                    if (fv.validateAddress(t1) && t1.length() <= 150) {
                        txtAddress2.getStyleClass().remove("error");
                        txtAddress2.getStyleClass().remove("warning");
                        txtAddress2.getStyleClass().add("valid");
                    } else {
                        txtAddress2.getStyleClass().remove("warning");
                        txtAddress2.getStyleClass().remove("valid");
                        txtAddress2.getStyleClass().add("error");
                    }
                } else {
                    txtAddress2.getStyleClass().remove("warning");
                    txtAddress2.getStyleClass().remove("error");
                    txtAddress2.getStyleClass().add("valid");
                }
            }
        });
        txtCity.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                if (fv.validateTextOnly(t1) && t1.length() <= 25 && t1.length() > 0) {
                    if (t1.length() < 3) {
                        txtCity.getStyleClass().add("warning");
                    } else {
                        txtCity.getStyleClass().remove("error");
                    }
                    txtCity.getStyleClass().remove("warning");
                    txtCity.getStyleClass().add("valid");
                } else {
                    txtCity.getStyleClass().remove("warning");
                    txtCity.getStyleClass().remove("valid");
                    txtCity.getStyleClass().add("error");
                }
            }
        });
        txtZip.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                if (fv.validateZipCode(t1) && t1.length() <= 6 && t1.length() > 0) {
                    if (t1.length() < 3) {
                        txtZip.getStyleClass().add("warning");
                    } else {
                        txtZip.getStyleClass().remove("error");
                    }
                    txtZip.getStyleClass().remove("warning");
                    txtZip.getStyleClass().add("valid");
                } else {
                    txtZip.getStyleClass().remove("warning");
                    txtZip.getStyleClass().remove("valid");
                    txtZip.getStyleClass().add("error");
                }
            }
        });
        txtHomePhone1.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {

                if (fv.validateNumberOnly(t1) && txtHomePhone1.getText().length() == 3) {
                    txtHomePhone1.getStyleClass().remove("error");
                    txtHomePhone1.getStyleClass().add("valid");
                    txtHomePhone2.requestFocus();
                } else {
                    txtHomePhone1.getStyleClass().remove("valid");
                    txtHomePhone1.getStyleClass().add("error");
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
                    txtHomePhone2.getStyleClass().remove("error");
                    txtHomePhone2.getStyleClass().add("valid");
                    txtHomePhone3.requestFocus();
                } else {
                    txtHomePhone2.getStyleClass().remove("valid");
                    txtHomePhone2.getStyleClass().add("error");
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
                    txtHomePhone3.getStyleClass().add("valid");
                    txtCellPhone1.requestFocus();
                } else {
                    txtHomePhone3.getStyleClass().remove("valid");
                    txtHomePhone3.getStyleClass().add("error");
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
                    txtCellPhone1.getStyleClass().remove("error");
                    txtCellPhone1.getStyleClass().add("valid");
                    txtCellPhone2.requestFocus();
                } else {
                    txtCellPhone1.getStyleClass().remove("valid");
                    txtCellPhone1.getStyleClass().add("error");
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
                    txtCellPhone2.getStyleClass().remove("error");
                    txtCellPhone2.getStyleClass().add("valid");
                    txtCellPhone3.requestFocus();
                } else {
                    txtCellPhone2.getStyleClass().remove("valid");
                    txtCellPhone2.getStyleClass().add("error");
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
                    txtCellPhone3.getStyleClass().remove("error");
                    txtCellPhone3.getStyleClass().add("valid");
                    txtEmail.requestFocus();
                } else {
                    txtCellPhone3.getStyleClass().remove("valid");
                    txtCellPhone3.getStyleClass().add("error");
                }
                if (txtCellPhone3.getText().length() > 4) {
                    txtCellPhone3.setText(txtCellPhone3.getText().substring(0, 4));
                }
            }
        });
        txtEmail.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                if (fv.validateEmail(t1)) {
                    txtEmail.getStyleClass().remove("error");
                    txtEmail.getStyleClass().add("valid");
                } else {
                    txtEmail.getStyleClass().remove("valid");
                    txtEmail.getStyleClass().add("error");
                }
            }
        });
        calendar_mask.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                System.out.println(t1);
                if (fv.validateDate(t1)) {
                    calendar_mask.getStyleClass().remove("error");
                    calendar_mask.getStyleClass().add("valid");
                } else {
                    calendar_mask.getStyleClass().remove("valid");
                    calendar_mask.getStyleClass().add("error");
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
        Customer.oCust.setAddress(txtAddress1.getText());
        Customer.oCust.setAddress2(txtAddress2.getText());
        Customer.oCust.setCity(txtCity.getText());
        Customer.oCust.setState(cmbState.getSelectionModel().getSelectedItem().toString());
        Customer.oCust.setZip(txtZip.getText());
        Customer.oCust.setHomePhone(txtHomePhone1.getText(), txtHomePhone2.getText(), txtHomePhone3.getText());
        Customer.oCust.setCellPhone(txtCellPhone2.getText(), txtCellPhone2.getText(), txtCellPhone3.getText());
        Customer.oCust.setEmail(txtEmail.getText());
        Customer.oCust.setDOB(calendar_mask.getText());

        //create variables for query
        String fn = Customer.oCust.getFirstName();
        fn = fn.substring(0, 1).toUpperCase() + fn.substring(1).toLowerCase();
        String ln = Customer.oCust.getLastName();
        ln = ln.substring(0, 1).toUpperCase() + ln.substring(1).toLowerCase();
        String sf = Customer.oCust.getSuffix();
        String sa = Customer.oCust.getSalutationName();
        String add1 = Customer.oCust.getAddress();
        String add2 = Customer.oCust.getAddress2();
        String city = Customer.oCust.getCity();
        city = city.substring(0, 1).toUpperCase() + city.substring(1).toLowerCase();
        String state = Customer.oCust.getState();
        String zip = Customer.oCust.getZip();
        String hp = Customer.oCust.getHomePhone();
        String cp = Customer.oCust.getCellPhone();
        String email = Customer.oCust.getEmail();
        String dob = Customer.oCust.getDOB();

        //create database connection and try to insert the new customer into database
        try {
            //connect to the database
            SettingsController.openConnection();
            ResultSet rs;
            //TODO: need to add highschool to query
            String sql = "EXEC createNewCustomer '" + fn + "','" + ln + "','" + sf + "','" + hp + "','" + cp + "','" + add1 + "','" + add2 + "',"
                    + "'" + city + "','" + state + "','" + zip + "','" + email + "','" + dob + "','" + sa + "'";

            Statement stmt = SettingsController.conn.createStatement();
            rs = stmt.executeQuery(sql);
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
                    .addYesButton(new EventHandler() {
                            @Override
                            public void handle(Event t) {
                                //open the add new emergency contact in the content pane
                                MainController.transitionContent(Customer.customerFXMLString+"AddNewEmergencyContact.fxml");
                            }
                        })
                    //go to view customer record page        
                    .addNoButton(new EventHandler() {
                        @Override
                        public void handle(Event t) {
                            //open the customer record in the content pane
                            MainController.transitionContent(Customer.customerFXMLString+"ViewCustomer.fxml");
                        }
                    })
                    .build()
                    .show();
            
            SettingsController.closeConnection();
        } catch (ClassNotFoundException | SQLException ex) {
            Dialog.showError("Error", "Error adding customer to database.\n\n" + ex.getMessage());
            System.out.println(ex.getMessage());
        }
    }
    
}
