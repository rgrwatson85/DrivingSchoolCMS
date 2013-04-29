/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mjwtech.customer.controller;

import com.mjwtech.customer.model.Customer;
import data.database_connection.SettingsController;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Window;
import name.antonsmirnov.javafx.dialog.Dialog;

/**
 * FXML Controller class
 *
 * @author mrgnwatson
 */
public class AddNoteController implements Initializable {

    @FXML
    public static TextArea txtNote;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblName;
    @FXML
    private Button btnSubmit;
    @FXML
    private Button btnCancel;
    /**
     * Initializes the controller class.
     */
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    SimpleDateFormat parseDate = new SimpleDateFormat("yyyy-MM-dd");
    public static AddNoteController instance;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //get the date from system
        String transDate = sdf.format(cal.getTime());
        lblDate.setText(transDate);    
        //Sets the event handler for when the cancel button is pressed.
        btnCancel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                ViewCustomerController.advancedView.getChildren().clear();
                ViewCustomerController.ap.hideAdvanced();
            }
        });   
        btnSubmit.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent t) {
                submitNote();
            }
        });
        instance = this;
    }
    
    private void submitNote(){
        //get the date from system
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        String transDate = sdf.format(cal.getTime());
        String note = txtNote.getText();
        note = note.replace("'", "''");
               
        try {
            SettingsController.openConnection();
            Statement stmt = SettingsController.conn.createStatement();
            String sql = "EXEC addCustomerNote "+ Customer.oCust.getID()+", '"+note+"', '"+transDate+"'";
            System.out.println(sql);
            stmt.executeUpdate(sql);
            Dialog.showInfo("Success", "Note was successfully added to customer");
            ViewCustomerController.ap.hideAdvanced();
            ViewCustomerController.txtSearchField.getOnAction().handle(null);
            SettingsController.closeConnection();
            }
        catch (ClassNotFoundException | SQLException e) {
            Scene scene = btnSubmit.getScene();
            Window window = scene.getWindow();
            Dialog.showInfo("Notice", e.getMessage());
            window.hide();
        }
        
    }
  
    public void viewNote(String note, String date) throws ParseException{
        txtNote.setText(note);
        btnSubmit.setVisible(false);
        btnCancel.setText("Close");
        btnCancel.layoutXProperty().setValue(btnSubmit.layoutXProperty().get());
        txtNote.setEditable(false);
        lblDate.setText(date);
    }
    
}
