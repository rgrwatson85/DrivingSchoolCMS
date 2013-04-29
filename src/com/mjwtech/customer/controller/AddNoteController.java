/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mjwtech.customer.controller;

import com.mjwtech.customer.model.Customer;
import com.mjwtech.main.controller.MainController;
import data.database_connection.SettingsController;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.Event;
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
import org.omg.CosNaming.NamingContextPackage.NotEmpty;
import resources.eyecandy.Fade;

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
    private Button btnSubmit;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnSave; 
            
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
        final Task wait = new Task() {
                    @Override
                    protected Object call() throws Exception {
                        this.setOnSucceeded(new EventHandler<Event>() {
                            @Override
                            public void handle(Event t) {
                                ViewCustomerController.ap.hideAdvanced();
                                ViewCustomerController.txtSearchField.getOnAction().handle(null);
                                Fade.f.FadeIn();
                            }
                        });
                        Thread.sleep(1500);
                        return null;
                    }
                };
        Task submitNote = new Task() {
            @Override
            protected Object call() throws Exception {
                Thread.sleep(2000);
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
                    SettingsController.closeConnection();
                    }
                catch (ClassNotFoundException | SQLException e) {
                    Dialog.showInfo("Notice", e.getMessage());
                    ViewCustomerController.ap.hideAdvanced();
                }
                return null;
            }
        };
        submitNote.setOnScheduled(new EventHandler() {
            @Override
            public void handle(Event t) {
                MainController.lblProgressStatus.setText("PROCESSING");
                Fade.f.FadeOut();         
            }
        });
        submitNote.setOnFailed(new EventHandler() {
            @Override
            public void handle(Event t) {
                MainController.lblProgressStatus.setText("ERROR");
                new Thread(wait).start();
            }
        });
        submitNote.setOnSucceeded(new EventHandler() {
            @Override
            public void handle(Event t) {
                MainController.lblProgressStatus.setText("SUCCESS");
                new Thread(wait).start();
            }
        });
        new Thread(submitNote).start();
    }
  
    public void viewNote(final String note, final String date, final int ID) throws ParseException{
        txtNote.setText(note);
        btnSubmit.setVisible(false);
        btnEdit.setVisible(true);
        btnCancel.setText("Close");
        btnCancel.layoutXProperty().setValue(btnSubmit.layoutXProperty().get());
        txtNote.setEditable(false);
        lblDate.setText(date);     
        btnEdit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                editNote(note, ID);
            }
        });
    }
    
    private void editNote(String note,  final int ID){
        final String oldNote=note;
        btnSave.setVisible(true);
        btnCancel.setText("Cancel");
        btnCancel.layoutXProperty().setValue(btnEdit.layoutXProperty().get());
        btnEdit.setVisible(false);
        txtNote.setEditable(true);
        if(oldNote.contains(" – Edited By: ")){
            txtNote.setText(oldNote.substring(0, oldNote.indexOf("–")-1));
        } 
        btnSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                final Task wait = new Task() {
                    @Override
                    protected Object call() throws Exception {
                        this.setOnSucceeded(new EventHandler<Event>() {
                            @Override
                            public void handle(Event t) {
                                ViewCustomerController.ap.hideAdvanced();
                                ViewCustomerController.txtSearchField.getOnAction().handle(null);
                                Fade.f.FadeIn();
                            }
                        });
                        Thread.sleep(1500);
                        return null;
                    }
                };
                Task updateNote = new Task() {
                    @Override
                    protected Object call() throws Exception {
                        Thread.sleep(2000);
                        txtNote.setEditable(false);
                        String update = "'"+txtNote.getText()+" – Edited By: "+SettingsController.Username+" On "+sdf.format(cal.getTime());
                        update += "\n\n"+oldNote+"'";
                        try{
                            SettingsController.openConnection();
                            Statement stmt = SettingsController.conn.createStatement();
                            String sql = "EXEC updateCustomerNote " +ID+","+update;
                            System.out.println(sql);
                            stmt.executeUpdate(sql);
                            SettingsController.closeConnection();
                        }catch(ClassNotFoundException | SQLException e){
                            System.err.println(e.getMessage());
                            throw new Exception();
                        }
                        return null;
                    }
                };
                updateNote.setOnScheduled(new EventHandler() {
            @Override
            public void handle(Event t) {
                MainController.lblProgressStatus.setText("PROCESSING");
                Fade.f.FadeOut();         
            }
        });
                updateNote.setOnFailed(new EventHandler() {
            @Override
            public void handle(Event t) {
                MainController.lblProgressStatus.setText("ERROR");
                new Thread(wait).start();
            }
        });
                updateNote.setOnSucceeded(new EventHandler() {
                    @Override
                    public void handle(Event t) {
                        MainController.lblProgressStatus.setText("SUCCESS");
                        new Thread(wait).start();
                    }
                });
                String x = txtNote.getText().replaceAll(" ", "");
                String y;
                if(oldNote.contains(" – Edited By: ")){
                    y = oldNote.substring(0, oldNote.indexOf("–")-1).replaceAll(" ", "");
                }else{
                    y = oldNote.replaceAll(" ", "");
                }
                if(x.equals(y)){
                    Dialog.showWarning("WARNING", "You cannot save until a change has been made");
                }else{
                    if("".equals(x)){
                        Dialog.showWarning("WARNING", "You can not create a note with no text");
                        txtNote.setText(oldNote);
                    }else
                        new Thread(updateNote).start();
                }
            }
        });
    }
    
}
