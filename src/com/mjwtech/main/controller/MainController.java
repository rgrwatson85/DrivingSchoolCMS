/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mjwtech.main.controller;

import com.mjwtech.main.model.Main;
import data.database_connection.SettingsController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import name.antonsmirnov.javafx.dialog.Dialog;
import javafx.scene.control.ProgressIndicator;
import resources.eyecandy.Fade;

/**
 * FXML Controller class
 *
 * @author mrgnwatson
 */
public class MainController implements Initializable {

    public static String DB_NAME;
    
    @FXML
    public static AnchorPane root;
    @FXML
    public static Label lblProgressStatus;
    @FXML
    public static AnchorPane effectsPane;
    @FXML
    public static ProgressIndicator progress;
    //area the encompasses the everything below the company logo and user name label
    @FXML
    public static AnchorPane contentPane;
    //encomapsses the company logo and the user name label
    @FXML
    AnchorPane titleArea;
    //add Customer shortcut button
    @FXML
    Button btnAddCustomer;
    //balance due shortcut button
    @FXML
    Button btnBalanceDue;
    //customer conatact info shortcut button
    @FXML
    Button btnContactCustomer;
    //opens the course main page
    @FXML
    public static ToggleButton btnCourse;
    //opens the Customer main page
    @FXML
    public static ToggleButton btnCustomer;
    //opens the employee main page
    @FXML
    public static ToggleButton btnEmployee;
    //logout shorcut button
    @FXML
    public Button btnLogOut;
    //scheduling tool shortcut button
    @FXML
    public Button btnSchedule;
    //employee timesheet shortcut button
    @FXML
    public Button btnReport;
    //opens the vehicle main page
    @FXML
    public static ToggleButton btnVehicle;
    @FXML
    public HBox toggleButtonContainer;
    //encompasses stores the shortcut buttons
    @FXML
    public GridPane buttonGridPane;
    //encompasses the shortcut buttons and the main page buttons
    @FXML
    public AnchorPane buttonPane;
    //encompasses the main page buttons
    @FXML
    public VBox buttonVBox;
    //label that shows the value of the shortcut buttons
    @FXML
    public Label lblToolTip;
    //label that shows the currently logged in user
    @FXML
    public static Label lblUserName;
    //menu button
    @FXML
    public Button btnMenu;
    //node that is displayed in the content pane. set as static so that it can be referenced throughout the entire application
    public static Node content;
    //stores the last selected toggle button. used to make sure that there is always a toggled selected
    public Toggle lastSelected;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //set toggle buttons to load the correct pages into the content area
        try {
            applyButtonEventHandlers();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        //logs the user out and opens up the login screen
        btnLogOut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                SettingsController.logout();
            }
        });
        
        //sets the blur for content pane - created in Fade.java
        contentPane.setEffect(Fade.gb);
    }

    public static void transitionContent(String s) {
        try {
            content = FXMLLoader.load(Main.class.getResource(s));
            contentPane.getChildren().clear();
            contentPane.getChildren().add(content);
        } catch (Exception ex) {
            Dialog.showError("ERROR", ex.getMessage());
        }
    }
    
    /*
     * applies click handlers to the toggle buttons so that they open up the correct main page
     */
    private void applyButtonEventHandlers() throws ClassNotFoundException {
        for (final Node n : toggleButtonContainer.getChildren()) {
            if (n instanceof ToggleButton) {
                final String classname = n.getId().replace("btn", "");
                n.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {
                            transitionContent("/com/mjwtech/"+classname.toLowerCase()+"/view/"+classname+".fxml");
                    }
                });
            }
        }
    }
}
