/**
 * Sample Skeleton for "ViewCustomerClass.fxml" Controller Class
 * You can copy and paste this code into your favorite IDE
 **/

package com.mjwtech.customer.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class ViewCustomerClassController
    implements Initializable {

    @FXML
    private static Label lblAvailableSeats; 

    @FXML
    private static Label lblClassDays; 

    @FXML
    private static Label lblCourseName; 

    @FXML
    private static Label lblEndDate; 

    @FXML
    private static Label lblTime; 

    @FXML
    private static Label lblInstructor; 

    @FXML
    private static Label lblLocation; 

    @FXML
    private static Label lblStartDate; 
    
    @FXML
    private Button btnRemoveFromCourse;
    
    @FXML
    private Button btnTransferClass;

    private static ViewCustomerClassController instance=null;

    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        assert btnRemoveFromCourse != null : "fx:id=\"btnRemoveFromCourse\" was not injected: check your FXML file 'ViewCustomerClass.fxml'.";
        assert btnTransferClass != null : "fx:id=\"btnTransferClass\" was not injected: check your FXML file 'ViewCustomerClass.fxml'.";
        assert lblAvailableSeats != null : "fx:id=\"lblAvailableSeats\" was not injected: check your FXML file 'ViewCustomerClass.fxml'.";
        assert lblClassDays != null : "fx:id=\"lblClassDays\" was not injected: check your FXML file 'ViewCustomerClass.fxml'.";
        assert lblCourseName != null : "fx:id=\"lblCourseName\" was not injected: check your FXML file 'ViewCustomerClass.fxml'.";
        assert lblEndDate != null : "fx:id=\"lblEndDate\" was not injected: check your FXML file 'ViewCustomerClass.fxml'.";
        assert lblTime != null : "fx:id=\"lblTime\" was not injected: check your FXML file 'ViewCustomerClass.fxml'.";
        assert lblInstructor != null : "fx:id=\"lblInstructor\" was not injected: check your FXML file 'ViewCustomerClass.fxml'.";
        assert lblLocation != null : "fx:id=\"lblLocation\" was not injected: check your FXML file 'ViewCustomerClass.fxml'.";
        assert lblStartDate != null : "fx:id=\"lblStartDate\" was not injected: check your FXML file 'ViewCustomerClass.fxml'.";
    }
    
    public static void setLabels(String cn,String sd,String ed,String t,String cd,String i,String l,int as){
        lblCourseName.setText(cn);
        lblStartDate.setText(sd);
        lblEndDate.setText(ed);
        lblTime.setText(t);
        lblClassDays.setText(cd);
        lblInstructor.setText(i);
        lblLocation.setText(l);
        lblAvailableSeats.setText(as + " seats available");
    }

}
