/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mjwtech.customer.controller;

import com.mjwtech.main.controller.MainController;
import data.dropdown.dropdowndata;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import name.antonsmirnov.javafx.dialog.Dialog;
import com.mjwtech.customer.model.Customer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;


/**
 * FXML Controller class
 *
 * @author mrgnwatson
 */
public class CustomerController implements Initializable {
    
    //root anchor pane
    @FXML
    AnchorPane root;
    //content wrapper
    @FXML
    AnchorPane wrapper;
    //opens add new customer in the content pane
    @FXML
    Button btnAddCustomer;
    //opens the customer records in the content pane
    @FXML
    Button btnSearchCustomer;
    //opens the outstanding balances page in the content pane
    @FXML
    Button btnOutstandingBalances;
    //Opens the mailing list in the content pane
    @FXML
    Button btnMailingList;
    //Opens the view permit in the content pane
    @FXML
    Button btnCustomerPermit;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //makes the correct toggle button selected
        MainController.btnCustomer.setSelected(true);
        
        /*
         * This will create all the drop downs needed to interact with a customer
         */
        try {
            dropdowndata.createDropDownData();
            dropdowndata.createStateDropDownData("USA");
        } catch (ClassNotFoundException | SQLException ex) {
            Dialog.showError("ERROR", "Could not load dropdown data\n\nMainController:123");
        }
        
        /*
         * This will load the add new customer into the content pane
         */
        btnAddCustomer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                MainController.transitionContent(Customer.customerFXMLString+"AddNewCustomer.fxml");
            }
        });
        
        /*
         * This will load the view customer into the content pane
         */
        btnSearchCustomer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                MainController.transitionContent(Customer.customerFXMLString+"ViewCustomer.fxml");
            }
        });
        
    }    
}
