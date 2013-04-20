/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mjwtech.customer;

import com.mjwtech.MainController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;


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

    }    
}
