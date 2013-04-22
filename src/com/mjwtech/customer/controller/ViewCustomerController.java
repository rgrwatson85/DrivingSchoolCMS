/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mjwtech.customer.controller;

import com.mjwtech.MainController;
import com.mjwtech.customer.model.Customer;
import com.mjwtech.customer.model.customerNote;
import data.database_connection.SettingsController;
import data.dropdown.dropdowndata;
import data.validation.validation;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.application.*;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import name.antonsmirnov.javafx.dialog.Dialog;

/**
 * FXML Controller class
 *
 * @author mrgnwatson
 */
public class ViewCustomerController implements Initializable {

    //<editor-fold defaultstate="collapsed" desc="FXML Nodes">
    
    //root anchor pane
    @FXML
    AnchorPane root;
    //makes form editable
    @FXML
    private Button btnEditCustomer; 
    //saves changes after editing form
    @FXML
    private Button btnSaveChanges;
    //cancels changes after editing form
    @FXML
    private Button btnCancelChanges; 
    //opens the enroll in course page in the content pane
    @FXML
    private Button btnEnrollCourse;
    //removes the customer from the database
    @FXML 
    private Button btnRemoveCustomer; 
    //opens the view emergency contacts in the content pane
    @FXML 
    private Button btnEmergencyContact; 
    //searches for customer based on criteria in the search text field
    @FXML
    private Button btnSearch;
    //button to print the customer contract
    @FXML
    private Button btnPrintContract;
    //wrapper for buttons
    @FXML
    private AnchorPane buttonWrapper;
    //used in where clause for searching for customer
    @FXML
    private ChoiceBox<?> cmbParameter; 
    
    @FXML
    private TextField txtAddress; 
    
    @FXML
    private TextField txtAddress2; 
    
    @FXML
    private TextField txtCellPhone1; 
    
    @FXML
    private TextField txtCellPhone2; 
    
    @FXML
    private TextField txtCellPhone3; 
    
    @FXML 
    private TextField txtCity; 
    
    @FXML
    private TextField txtCustomerID; 
    
    @FXML
    private TextField txtDOB; 
    
    @FXML 
    private TextField txtEmail; 
    
    @FXML
    private TextField txtFirstName; 
    
    @FXML
    private TextField txtHomePhone1; 
    
    @FXML 
    private TextField txtHomePhone2; 
    
    @FXML 
    private TextField txtHomePhone3; 
    
    @FXML 
    private TextField txtLastName; 
    
    @FXML
    private TextField txtSearchField; 
    
    @FXML
    private TextField txtState;
    
    @FXML
    private ComboBox cmbState;
    
    @FXML
    private TextField txtCountry;
    
    @FXML
    private ComboBox<String> cmbCountry;
    
    @FXML 
    private TextField txtZip; 
    
    @FXML
    private TextArea txtNotes;
    
    @FXML
    private TextField txtSuffix;
    
    @FXML
    private ComboBox cmbSuffix;
    
    @FXML
    private TextField txtSalutation;
    
    @FXML
    private ComboBox cmbSalutation;
    
    @FXML
    private ComboBox cmbHighSchool;
    
    @FXML
    private TextField txtHighSchool;
    
    @FXML
    private TextField txtBalanceDue; 
    //opens the view transaction history page in the content pane
    @FXML
    private Button btnTransactions;
    //opens the add penalty fee page in the content pane
    @FXML
    private Button btnFee;
    //opens the make payment page
    @FXML 
    private Button btnMakePayment;
    //opens the add new note page
    @FXML
    private Button btnAddNote;
    //tooltip label
    @FXML
    private Label lblToolTip;
    
    //table that stores customer notes
    @FXML
    TableView<customerNote> tableNote = new TableView<>();
    @FXML
    TableColumn<customerNote,Integer> colNoteID;
    @FXML
    TableColumn<customerNote,String> colNoteDate;
    @FXML
    TableColumn<customerNote,String> colNoteDescription;
    
    //table that stores the courses that customer is enrolled in
    @FXML
    TableView<classDisplay> table = new TableView<>();
    //column that displays the course name in the table
    @FXML
    TableColumn<classDisplay, String> colCourses;
    //column that displays the class start date
    @FXML
    TableColumn<classDisplay, String> colStartDate;
    //column that displays the class end date
    @FXML
    TableColumn<classDisplay, String> colEndDate;
    //column to store the recurrence string
    @FXML
    TableColumn<classDisplay, String> colDays;
    //column the stores the class time
    @FXML
    TableColumn<classDisplay, String> colTimeSlot;
    //column that stores the class ID in the table
    @FXML
    TableColumn<classDisplay, String> colID;
    //column that stores the course ID in the table
    @FXML
    TableColumn<classDisplay, String> colCourseID;
    //column to store instructor first and last name
    @FXML
    TableColumn<classDisplay, String> colInstructor;

    //stores the customer ID
    public static int custID;
    //stores the class ID
    public static int classID;
    //stores the class start date
    private String startDate;
    //stores the class end date
    private String endDate;
    //stores the class start time
    private String timeSlot;
    //stores the course ID
    public static int courseID;
    //stores course cost
    public static double courseCost=0;
    //stores the course name
    public static String courseName;
    //stores instructor name
    public String instructorName;
    //stores instructor/employee calendar address
    public static String calendarAddress;
    //boolean used in validation when form edit is saved
    private boolean valid;
    //note id for customer notes
    private int noteID;
    //the notes value
    private String note;
    //date for note
    private String noteDate;
    //data store for table
    ObservableList<classDisplay> data;
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    SimpleDateFormat parseDate = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat displayTime = new SimpleDateFormat("h:mm a");
    SimpleDateFormat parseTime = new SimpleDateFormat("HH:mm:ss");
    DecimalFormat formatter = new DecimalFormat("$#,##0.00");
    
    //</editor-fold>
     /**
     * Initializes the controller class.
     */
   
    @Override
    public void initialize(URL url, ResourceBundle rb){
        
        //makes the correct toggle button selected
        MainController.btnCustomer.setSelected(true);
        
        //create change listeners for phone number fields
        createChangeListeners();
        
        //set drop downs
        loadDropDownData();
              
        //set focus to txtSearchField name on load
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                txtSearchField.requestFocus();
            }
        });
               
        //hide button wrapper initially
        //show when a customer has been loaded into the form
        buttonWrapper.setVisible(false);
        
        //hide cmbCountry, cmbState, and cmbSuffix initially
        //shows when editing
        cmbCountry.setVisible(false);
        cmbState.setVisible(false);
        cmbSalutation.setVisible(false);
        cmbSuffix.setVisible(false);
        cmbHighSchool.setVisible(false);
        
        //load customer data if customer object has been created
        loadForm();

        //Set default combo box search parameter to Customer ID
        cmbParameter.getSelectionModel().select(1);
        
        //changes state drop drown when country is changed
        cmbCountry.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                try{
                dropdowndata.createStateDropDownData(cmbCountry.getValue().toString());
                cmbState.setItems(dropdowndata.stateList);
                cmbState.getSelectionModel().selectFirst();
                }catch(ClassNotFoundException | SQLException e){
                    Dialog.showError("ERROR", "Could not load state drop down data");
                }
            }
        });
        
        //cmbHighschool event handler
        cmbHighSchool.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue ov, Object t, Object t1) {
                if("ADD NEW HIGHSCHOOL...".equals(t1.toString())){
                    Dialog.showError("ERROR", "This functionality is not yet built in");
                }
            }
        });
        
        //btnSearch Event Handler
        btnSearch.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent t) {
                //makes the form uneditable
                formEditable(false);
                //searches for the customer
                try{
                    searchCustomer();
                }catch(ClassNotFoundException e){
                    Dialog.showError("ERROR", "Error loading customer record\n\n"+e.getMessage());
                }
            }
        });
        //txtSearchField Event Handler
        txtSearchField.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                //makes the form uneditable
                formEditable(false);
                //searches for the customer
                try{
                searchCustomer();
                }catch(Exception e){
                    Dialog.showError("ERROR", "Error loading customer record\n\n"+e.getMessage());
                }
            }
        });
        
        //btnEdit Event Handler
        btnEditCustomer.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent t) {
                //show confirmation dialog for making form editable
                Dialog.buildConfirmation("", "").create()
                        .setTitle("Confirm")
                        .setMessage("This will make the form editable.\nAre you sure you wish to do this?")
                        .addYesButton(new EventHandler() {
                            @Override
                            public void handle(Event t) {
                                //make form editable
                                formEditable(true);
                                //set combo box to be the already selected state
                                cmbState.getSelectionModel().select(txtState.getText());
                                cmbCountry.getSelectionModel().select(txtCountry.getText());
                                if(!"".equals(txtSuffix.getText())){
                                    cmbSuffix.getSelectionModel().select(txtSuffix.getText());
                                }
                                else{
                                    cmbSuffix.getSelectionModel().select("NONE");
                                }
                                cmbSalutation.getSelectionModel().select(txtSalutation.getText());
                            }
                        })
                        .addCancelButton(new EventHandler() {
                            @Override
                            public void handle(Event t) {
                                //make form uneditable
                                formEditable(false);
                            }
                        })
                        .build()
                        .show();
            }
        });
        btnEditCustomer.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                lblToolTip.setText("Edit Customer");
            }
        });
        btnEditCustomer.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                lblToolTip.setText("");
            }
        });
             
        //btnSaveChanges Event Handler
        btnSaveChanges.setOnMouseClicked(new EventHandler<MouseEvent>(){
        @Override
        public void handle(MouseEvent t) {
            saveChanges();
        }
        });
        btnSaveChanges.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                lblToolTip.setText("Save Changes");
            }
        });
        btnSaveChanges.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                lblToolTip.setText("");
            }
        });
        
        //btnCancelChanges Event Handler
        btnCancelChanges.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                //make form uneditable
                formEditable(false);
                //reload customer info
                try{
                    searchCustomer();
                }catch(Exception e){
                    Dialog.showError("ERROR", "Error searching customer");
                }
                Dialog.showInfo("Notice", "Changes have been cancelled");
            }
        });
        btnCancelChanges.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                lblToolTip.setText("Cancel Changes");
            }
        });
        btnCancelChanges.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                lblToolTip.setText("");
            }
        });
        
        //btnRemoveCustomer Event Handler
        btnRemoveCustomer.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent t) {
                //get customer's full name
                String name = Customer.oCust.getFirstName()+" "+Customer.oCust.getLastName();
                //confirm that the user wants to delete the customer permanently
                Dialog.buildConfirmation("","").create()
                    .setTitle("Confirm")
                    .setMessage("Are you sure you wish to remove "+name+" from the system?"
                              + "\nThis action cannot be undone.")
                    //try to remove customer record from database
                    .addYesButton(new EventHandler() {
                        @Override
                        public void handle(Event t) {
                            removeCustomer();
                        }
                    })
                    //cancel the removal
                    .addNoButton(new EventHandler() {
                        @Override
                        public void handle(Event t) {
                            Dialog.showInfo("Cancelled", "Removal of customer has been cancelled");
                        }
                    })
                    .build()
                    .show();
            }
        });
        btnRemoveCustomer.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                lblToolTip.setText("Delete Customer");
            }
        });
        btnRemoveCustomer.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                lblToolTip.setText("");
            }
        });
        
        //open the course enrollment page in the content pane
        btnEnrollCourse.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent t) {
                enrollCustomer();
            }
        });
        btnEnrollCourse.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                lblToolTip.setText("Enroll In A Course");
            }
        });
        btnEnrollCourse.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                lblToolTip.setText("");
            }
        });
        
        //open the view emergency contact page in the content pane
        btnEmergencyContact.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent t) {
                viewEmergencyContact();
            }
        });
        btnEmergencyContact.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                lblToolTip.setText("View Emergency Contacts");
            }
        });
        btnEmergencyContact.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                lblToolTip.setText("");
            }
        });
            
        tableNote.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<customerNote>() {
            @Override
            public void changed(ObservableValue<? extends customerNote> ov, customerNote t, customerNote t1) {
                noteID = t1.getID();
                noteDate = t1.getDate();
                note = t1.getNote();
            }
        });
        
        tableNote.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                if(t.getClickCount()==2){viewNote();}
                ContextMenu cm = new ContextMenu();
                MenuItem item1 = new MenuItem("Delete");
                MenuItem item2 = new MenuItem("View");
                MenuItem item3 = new MenuItem("Create");
                cm.getItems().addAll(item1,item2,item3);
                item1.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent t) {
                        try {
                            deleteNote();
                        } catch (ClassNotFoundException | SQLException ex) {
                            Dialog.showError("ERROR", "Error deleting customer note");
                        }
                    }
                });
                item2.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent t) {
                        viewNote();
                    }
                });
                item3.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent t) {
                        createNote();
                    }
                });
                tableNote.setContextMenu(cm);
            }
        });
        
        //change listener for table selection model
        table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<classDisplay>() {
            @Override
            public void changed(ObservableValue<? extends classDisplay> ov, classDisplay t, classDisplay t1) {
                try{
                    if(data.size()>0){
                        courseName = table.getSelectionModel().getSelectedItem().getCourseName();
                        courseCost = table.getSelectionModel().getSelectedItem().getCourseCostAsDouble();
                        classID = table.getSelectionModel().getSelectedItem().getID();
                        timeSlot = table.getSelectionModel().getSelectedItem().getTimeSlot();
                        //set courseID
                        SettingsController.openConnection();
                        String sql = "SELECT CourseID FROM Class WHERE ClassID="+classID;
                        Statement stmt = SettingsController.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                        ResultSet rs = stmt.executeQuery(sql);
                        while (rs.next()){                                
                            courseID = rs.getInt("CourseID");
                        }
                        SettingsController.closeConnection();
                    }
                }
                catch(ClassNotFoundException | SQLException ex){
                    Dialog.showError("ERROR", ex.getMessage());
                }
            }
        });
        
        //handles context menu requests
        table.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                ContextMenu cm = new ContextMenu();
                MenuItem removeCourse = new MenuItem("Remove From Course");
                MenuItem transferClass = new MenuItem("Transfer To Different Class");
                MenuItem viewProgress = new MenuItem("Course Progress");
                MenuItem scheduleDrive = new MenuItem("Schedule Drive");
                cm.getItems().addAll(viewProgress,transferClass,scheduleDrive,removeCourse);      
                table.setContextMenu(cm);
                removeCourse.setOnAction(new EventHandler<ActionEvent>(){
                        @Override
                        public void handle(ActionEvent t) {
                            //check for course dependencies
                            String dependantCourseName = "";
                            try {
                                SettingsController.openConnection();
                                String sql = "EXEC checkCourseDependency "+classID+","+custID;
                                Statement stmt = SettingsController.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                                ResultSet rs = stmt.executeQuery(sql);
                                while(rs.next()){
                                    dependantCourseName = rs.getString("CourseName");
                                }
                                if(dependantCourseName==null||"".equals(dependantCourseName)){
                                    dependantCourseName = "NONE";
                                }
                                SettingsController.closeConnection();
                            } catch (ClassNotFoundException | SQLException e) {
                            }
                            //confirm removal from course
                            Dialog.buildConfirmation("", "").create()
                                .setTitle("Are You Sure?")
                                .setMessage("This will remove all course information including attendance and grade records"
                                        + "\nfor this student. Customer will also be removed from any dependant classes."
                                        + "\n\nDependant classes: "+dependantCourseName
                                        + "\n\nYou cannot undo this operation!"
                                        + "\n\nAre you SURE you want to do this?")
                            .addYesButton(new EventHandler() {
                                @Override
                                public void handle(Event t) {
                                    removeCustomerFromCourse();
                                }   
                            })
                            .addNoButton(new EventHandler() {
                                @Override
                                public void handle(Event t) {
                                    Dialog.showInfo("Notice", "Removal from course has been cancelled.");
                                }
                            })
                            .build()
                            .show();
                        }
                    });
                viewProgress.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent t) {
                            viewCourseProgress();
                        }
                    });
                transferClass.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent t) {
                        transferClass();
                    }
                });
                scheduleDrive.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent t) {
                        Dialog.showError("ERROR", "Not yet implemented");
                    }
                });
            }
        });      
        
        //btnTransaction Event Handler
        btnTransactions.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                viewTransactionLog();
            }
        });
        
        //set tooltip for the view transaction history button
        btnTransactions.setTooltip(new Tooltip("View Balance History"));
        
        //set tool tip for the add penalty fee button
        btnFee.setTooltip(new Tooltip("Apply a Penalty Fee"));
        
        //set tool tip for make payment button
        btnMakePayment.setTooltip(new Tooltip("Make a Payment"));
        btnMakePayment.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
               if(Customer.oCust.getBalanceDue()>0){
                   makePayment();
               }
               else{
                   Dialog.showInfo("Notice", "Customer does not owe any money");
               }
            }
        });
        
        //set tool tip for the add note button
        btnAddNote.setTooltip(new Tooltip("Add a New Note"));
        //click handler for add new customer note
        btnAddNote.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                createNote();
            }
        });
        
    } 
    
     /*
     * This section provides on the fly form validation for each form control
     * It is very basic and is still prone to bad data being entered.
     */
    private void createChangeListeners() {
        final validation fv = new validation();
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
        txtAddress.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                if (fv.validateAddress(t1) && t1.length() <= 150 && t1.length() > 0) {
                    if (t1.length() < 3) {
                        txtAddress.getStyleClass().add("warning");
                    } else {
                        txtAddress.getStyleClass().remove("error");
                    }
                    txtAddress.getStyleClass().remove("warning");
                    txtAddress.getStyleClass().add("valid");
                    if (fv.validateAddress(txtAddress2.getText()) || txtAddress2.getText().length() == 0) {
                        txtAddress2.getStyleClass().add("valid");
                    }
                } else {
                    txtAddress.getStyleClass().remove("warning");
                    txtAddress.getStyleClass().remove("valid");
                    txtAddress.getStyleClass().add("error");
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
        txtDOB.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                if (fv.validateDate(t1)) {
                    txtDOB.getStyleClass().remove("error");
                    txtDOB.getStyleClass().add("valid");
                } else {
                    txtDOB.getStyleClass().remove("valid");
                    txtDOB.getStyleClass().add("error");
                }
            }
        });
    }
    
    //load form with customer object
    private void loadForm(){
        if(Customer.oCust.getID() != 0){
            //put the customer ID in the search field
            txtSearchField.setText(""+Customer.oCust.getID());
            //set the combo box selection to customer ID
            cmbParameter.getSelectionModel().select(1);
            //search for the customer
            try{
                searchCustomer();
            }catch(Exception e){
                Dialog.showError("ERROR", "Error searching for customer");
            }
        }
    }
    
    //search for the customer
    private void searchCustomer() throws ClassNotFoundException{
        formEditable(false);
        //search parameter used in the WHERE clause of SQL query
        String param = cmbParameter.getSelectionModel().getSelectedItem().toString().replaceAll(" ", "");
        //Email parameter is stored as EmailAddress in the database. 
        if("Email".equals(param)){
            param = param.concat("Address");
        }
        String paramString = txtSearchField.getText();

        try{
            //connect to the database
            SettingsController.openConnection();
            Statement stmt = SettingsController.conn.createStatement();
            String sql = "";
            if("CustomerID".equals(param)){
                sql+="EXEC viewCustomer 'CustomerID',null,"+paramString;
            }
            else{
                sql+="EXEC viewCustomer '"+param+"','"+paramString+"'";
            }
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println(sql);
            int size = 0;
            while(rs.next()){
                if(size>0){
                    Dialog.showError("ERROR", "More than one customer found. Use a more narrow search field.");
                    break;
                }
                size++;
                //set the values for the customer object

                custID = rs.getInt("CustomerID");
                Customer.oCust.setID(custID);
                txtCustomerID.setText(""+rs.getInt("CustomerID"));

                Customer.oCust.setSalutationName(rs.getString("SalutationName"));
                txtSalutation.setText(rs.getString("SalutationName"));

                Customer.oCust.setFirstName(rs.getString("FirstName"));
                txtFirstName.setText(rs.getString("FirstName"));
                
                Customer.oCust.setLastName(rs.getString("LastName"));
                txtLastName.setText(rs.getString("LastName"));
                
                Customer.oCust.setSuffix(rs.getString("SuffixName"));
                if(!"NONE".equals(rs.getString("SuffixName"))){
                    txtSuffix.setText(rs.getString("SuffixName"));
                }
                
                //email can be null so only set textbox conditionally
                if(rs.getString("EmailAddress")==null){
                    txtEmail.setText("");
                } else {
                    Customer.oCust.setEmail(rs.getString("EmailAddress"));
                    txtEmail.setText(rs.getString("EmailAddress"));
                }
                
                Customer.oCust.setDOB(sdf.format(parseDate.parse(rs.getString("DOB"))));
                txtDOB.setText(sdf.format(parseDate.parse(rs.getString("DOB"))));
                
                //home phone can be null so set text conditionally
                if(rs.getString("HomePhone")==null){
                    txtHomePhone1.setText("");
                    txtHomePhone2.setText("");
                    txtHomePhone3.setText("");
                } else {
                    Customer.oCust.setHomePhone(rs.getString("HomePhone"));
                    txtHomePhone1.setText(Customer.oCust.getHomePhone().substring(1, 4));
                    txtHomePhone2.setText(Customer.oCust.getHomePhone().substring(6, 9));
                    txtHomePhone3.setText(Customer.oCust.getHomePhone().substring(10, 14));
                }
                
                //cellphone can be null so only set text conditionally
                if(rs.getString("CellPhone")==null){
                    txtCellPhone1.setText("");
                    txtCellPhone2.setText("");
                    txtCellPhone3.setText("");
                } else {
                    Customer.oCust.setCellPhone(rs.getString("CellPhone"));
                    txtCellPhone1.setText(Customer.oCust.getCellPhone().substring(1, 4));
                    txtCellPhone2.setText(Customer.oCust.getCellPhone().substring(6, 9));
                    txtCellPhone3.setText(Customer.oCust.getCellPhone().substring(10, 14));
                }
                
                Customer.oCust.setAddress1(rs.getString("Address"));
                txtAddress.setText(rs.getString("Address"));
                
                //address2 can be null so only set conditionally
                if(rs.getString("Address2")==null){
                    txtAddress2.setText("");
                } else {
                    Customer.oCust.setAddress2(rs.getString("Address2"));
                    txtAddress2.setText(rs.getString("Address2"));
                }
                
                Customer.oCust.setCity(rs.getString("City"));
                txtCity.setText(rs.getString("City"));
                
                Customer.oCust.setState(rs.getString("StateName"));
                txtState.setText(rs.getString("StateName"));
                
                Customer.oCust.setCountry(rs.getString("CountryName"));
                txtCountry.setText(rs.getString("CountryName"));
                
                Customer.oCust.setZip(rs.getString("Zip"));
                txtZip.setText(rs.getString("Zip"));
                
                Customer.oCust.setBalanceDue(rs.getDouble("BalanceDue"));
                txtBalanceDue.setText(formatter.format(rs.getDouble("BalanceDue")));
                
                //highschool can be null so only set conditionally
                if(rs.getString("HighSchoolName")==null){
                    txtHighSchool.setText("");
                } else {
                    Customer.oCust.setHighschool(rs.getString("HighSchoolName"));
                    txtHighSchool.setText(rs.getString("HighSchoolName"));
                }

                //populate the table showing the courses the Customer is enrolled in
                loadEnrolledCourses();
                //make button wrapper visible
                buttonWrapper.setVisible(true);
                //get any notes for the Customer
                loadCustomerNotes();
            }
            if(size<1){
                Dialog.showError("ERROR", "Customer was not found");
            }
            SettingsController.closeConnection();
        }
        catch(SQLException | ParseException | ClassNotFoundException ex){
           Dialog.showError("ERROR",ex.getMessage());
        }
        formEditable(false);
    }
    
    //load courses the customer is enrolled into the table
    private void loadEnrolledCourses() throws ClassNotFoundException{
        
        //create array to store table data
        data = FXCollections.observableArrayList();
        //table.setItems(null);
        //fill in courses table with customers enrolled courses
        try{
            SettingsController.openConnection();            
            //get class IDs that customer is enrolled in from Enrollment
            String sql = "EXEC viewCustomerClassEnrollment "+custID;
            Statement stmt = SettingsController.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                classID = rs.getInt("ClassID");
                courseName = rs.getString("CourseName");
                courseCost = rs.getDouble("CourseCost");
                instructorName = rs.getString("FirstName")+" "+rs.getString("LastName");
                calendarAddress = rs.getString("CalendarAddress");
                startDate = sdf.format(parseDate.parse(rs.getString("StartDate")));
                endDate = sdf.format(parseDate.parse(rs.getString("EndDate")));
                timeSlot =displayTime.format(parseTime.parse(rs.getString("StartTime")));
                timeSlot += " - ";
                timeSlot += displayTime.format(parseTime.parse(rs.getString("EndTime")));           
                classDisplay cd = new classDisplay();
                cd.setID(classID);
                cd.setCourseName(courseName);
                cd.setCourseCost(courseCost);
                cd.setStartDate(startDate);
                cd.setEndDate(endDate);
                cd.setTimeSlot(timeSlot);
                cd.setRecurrenceString(rs.getString("RecurrenceString"));
                cd.setInstructorName(instructorName);
                cd.setCalendarAddress(calendarAddress);
                data.add(cd);
            }
            table.setItems(data);
            colCourses.setCellValueFactory(new PropertyValueFactory("courseName"));
            colStartDate.setCellValueFactory(new PropertyValueFactory("startDate"));
            colEndDate.setCellValueFactory(new PropertyValueFactory("endDate"));
            colTimeSlot.setCellValueFactory(new PropertyValueFactory("timeSlot"));
            colDays.setCellValueFactory(new PropertyValueFactory("recurrenceString"));
            colID.setCellValueFactory(new PropertyValueFactory("classID"));
            colInstructor.setCellValueFactory(new PropertyValueFactory("instructorName"));
            SettingsController.closeConnection();
            if(data.size()>0){
                table.getSelectionModel().selectFirst();
            }
        }
        catch(SQLException | ParseException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    //load notes made about the customer
    private void loadCustomerNotes() throws ClassNotFoundException, SQLException{
        SettingsController.openConnection();
        try {
            ObservableList<customerNote> noteList = FXCollections.observableArrayList();
            tableNote.setItems(null);
            String sql = "SELECT * FROM CustomerNote WHERE CustomerID ="+custID+" ORDER BY Date DESC";
            Statement stmt = SettingsController.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println(sql);
            while (rs.next()){
                customerNote cNote = new customerNote();
                cNote.setID(rs.getInt("CustomerNoteID"));
                cNote.setDate(sdf.format(parseDate.parse(rs.getString("Date"))));
                cNote.setNote(rs.getString("Note"));
                if(rs.getString("Note").length()>14){
                     cNote.setShortNote(rs.getString("Note").substring(0,15)+"...(double click for more)");
                }else{
                    cNote.setShortNote(rs.getString("Note"));
                }
                noteList.add(cNote);
            }
            tableNote.setItems(noteList);
            colNoteID.setCellValueFactory(new PropertyValueFactory("ID"));
            colNoteDate.setCellValueFactory(new PropertyValueFactory("Date"));
            colNoteDescription.setCellValueFactory(new PropertyValueFactory("ShortNote"));
        } catch (SQLException | ParseException e) {
            Dialog.showError("Error", "Error loading customer notes.");
        }
        SettingsController.closeConnection();
    }
    
    //set the form as editable or uneditable
    private void formEditable(boolean editable){
            txtSalutation.editableProperty().set(editable);
            txtSuffix.editableProperty().set(editable);
            cmbSalutation.setVisible(editable);
            cmbHighSchool.setVisible(editable);
            txtHighSchool.setVisible(!editable);
            txtFirstName.editableProperty().set(editable);
            txtLastName.editableProperty().set(editable);
            txtSuffix.editableProperty().set(editable);
            cmbSuffix.setVisible(editable);
            txtAddress.editableProperty().set(editable);
            txtAddress2.editableProperty().set(editable);
            txtCity.editableProperty().set(editable);
            txtState.editableProperty().set(editable);
            txtCountry.editableProperty().set(editable);
            txtZip.editableProperty().set(editable);
            cmbState.setVisible(editable);
            txtState.setVisible(!editable);
            cmbCountry.setVisible(editable);
            txtCountry.setVisible(!editable);
            txtHomePhone1.editableProperty().set(editable);
            txtHomePhone2.editableProperty().set(editable);
            txtHomePhone3.editableProperty().set(editable);
            txtCellPhone1.editableProperty().set(editable);
            txtCellPhone2.editableProperty().set(editable);
            txtCellPhone3.editableProperty().set(editable);
            txtEmail.editableProperty().set(editable);
            txtDOB.editableProperty().set(editable);
            btnEditCustomer.setVisible(!editable);
            btnEmergencyContact.setVisible(!editable);
            btnEnrollCourse.setVisible(!editable);
            btnMakePayment.setDisable(editable);
            btnFee.setDisable(editable);
            btnAddNote.setDisable(editable);
            
            if(editable){
                //apply error class to all text fields on load
                for (Node n : root.getChildren()) {
                    if (n instanceof TextField) {
                        if(!"txtCustomerID".equals(n.getId()))
                            n.getStyleClass().add("valid");
                    }
                }
            }else{
                for (Node n : root.getChildren()) {
                    if (n instanceof TextField) {
                        if(!"txtCustomerID".equals(n.getId()))
                            n.getStyleClass().removeAll("valid","error");
                    }
                }
            }
    };
    
    //save the changes to the database
    private void saveChanges(){
        try{
            //connect to the database
            SettingsController.openConnection();
            
            //get StateID
            int stateID = 0;
            String sql = "SELECT StateID FROM State WHERE StateName = '"+cmbState.getValue().toString()+"'";
            Statement stmt = SettingsController.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){                
                stateID = rs.getInt("StateID");
            }
            
            //get SalutationID
            int salutationID = 0;
            sql = "SELECT SalutationID FROM Salutation WHERE SalutationName = '"+cmbSalutation.getValue().toString()+"'";
            stmt = SettingsController.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(sql);
            while (rs.next()){                
                salutationID = rs.getInt("SalutationID");
            }
            
            //get SuffixID
            int suffixID = 0;
            sql = "SELECT SuffixID FROM LastNameSuffix WHERE SuffixName = '"+cmbSuffix.getValue().toString()+"'";
            stmt = SettingsController.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(sql);
            while (rs.next()){                
                suffixID = rs.getInt("SuffixID");
            }
            
            //get highschool id
            int  hsID = -1;
            try {
                sql = "SELECT HighSchoolID FROM HighSchool WHERE HighSchoolName = '"+cmbHighSchool.getValue().toString()+"'";
                stmt = SettingsController.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                rs = stmt.executeQuery(sql);
                while (rs.next()){                
                    hsID = rs.getInt("HighSchoolID");
                }
            } catch (Exception e) {
                
            }
            
            System.out.println(""+hsID);
            //update the customer record
            if(hsID>0){
                sql = "UPDATE Customer "
                    + "SET "
                    + "SalutationID= "+salutationID+", "
                    + "FirstName='"+txtFirstName.getText()+"', "
                    + "LastName='"+txtLastName.getText()+"', "
                    + "SuffixID="+suffixID+", "
                    + "Address='"+txtAddress.getText()+"', "
                    + "Address2='"+txtAddress2.getText()+"', "
                    + "City='"+txtCity.getText()+"', "
                    + "StateID="+stateID+", "
                    + "Zip='"+txtZip.getText()+"', "
                    + "HomePhone='("+txtHomePhone1.getText()+") "+txtHomePhone2.getText()+"-"+txtHomePhone3.getText()+"', "
                    + "CellPhone='("+txtCellPhone1.getText()+") "+txtCellPhone2.getText()+"-"+txtCellPhone3.getText()+"', "
                    + "EmailAddress='"+txtEmail.getText()+"', "
                    + "DOB='"+txtDOB.getText()+"', "
                    + "HighSchoolID="+hsID+" "
                    + "WHERE CustomerID="+custID+"; "
                    + ""
                    + "UPDATE Customer " 
                    + "SET CellPhone = null " 
                    + "WHERE CellPhone LIKE '%() -%'";
            }else{
                sql = "UPDATE Customer "
                    + "SET "
                    + "SalutationID= "+salutationID+", "
                    + "FirstName='"+txtFirstName.getText()+"', "
                    + "LastName='"+txtLastName.getText()+"', "
                    + "SuffixID="+suffixID+", "
                    + "Address='"+txtAddress.getText()+"', "
                    + "Address2='"+txtAddress2.getText()+"', "
                    + "City='"+txtCity.getText()+"', "
                    + "StateID="+stateID+", "
                    + "Zip='"+txtZip.getText()+"', "
                    + "HomePhone='("+txtHomePhone1.getText()+") "+txtHomePhone2.getText()+"-"+txtHomePhone3.getText()+"', "
                    + "CellPhone='("+txtCellPhone1.getText()+") "+txtCellPhone2.getText()+"-"+txtCellPhone3.getText()+"', "
                    + "EmailAddress='"+txtEmail.getText()+"', "
                    + "DOB='"+txtDOB.getText()+"' "
                    + "WHERE CustomerID="+custID+"; "
                    + ""
                    + "UPDATE Customer " 
                    + "SET CellPhone = null " 
                    + "WHERE CellPhone LIKE '%() -%'";
            }
            stmt = SettingsController.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stmt.executeUpdate(sql);

            SettingsController.closeConnection();
            Dialog.showInfo("Success", "Customer information was successfully updated.");
            searchCustomer();

        }
        catch(ClassNotFoundException | SQLException ex){
            Dialog.showError("Error", ex.getMessage());
            try{
                searchCustomer();
            }catch(Exception e){
                Dialog.showError("ERROR", "Error searching customer");
            }
        }
    }
    
    //remove customer from the database
    private void removeCustomer(){
        try{
            //connect to the database
            SettingsController.openConnection();
            
            //delete the customer from the database
            String sql = "DELETE FROM Customer WHERE CustomerID="+custID;
            Statement stmt = SettingsController.conn.createStatement();
            stmt.executeUpdate(sql);
            
            //clear all text fields in the form to help show user that the customer has been deleted
            resetForm();
            formEditable(false);
            Dialog.showInfo("Success", "Customer has been removed from the system.");
            SettingsController.closeConnection();
        }
        catch(ClassNotFoundException | SQLException ex){
           Dialog.showError("Error", ex.getMessage());
        }
    }
    
    //open the course enrollment page in the content pane
    private void enrollCustomer(){
        Dialog.showError("ERROR", "Functionality not yet added");
    }
    
    //unenroll customer from course
    private void removeCustomerFromCourse(){
        try {
            SettingsController.openConnection();
            String sql = "EXEC removeFromClass "+courseID+", "+classID+","+custID;
            Statement stmt = SettingsController.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(sql);     
            SettingsController.closeConnection();
            Dialog.showInfo("Success", "Customer successfully removed from the class.");
            searchCustomer();
        } 
        catch (ClassNotFoundException | SQLException e) {
             Dialog.showError("Error", e.getMessage());
        }
    }

    //view course progress
    private void viewCourseProgress(){
        Stage stage = new Stage();
        Parent window = null;
        stage.setTitle("Course Progress");
        Dialog.showError("ERROR", "Fuctionality not yet added");
        Scene scene = new Scene(window);       
        stage.setScene(scene);
        stage.setWidth(668);
        stage.setHeight(435);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();   
    }
    
    //transfer to a different class within same course
    private void transferClass(){
        Stage stage = new Stage();
        stage.setTitle("Class Transfer Form");
        Parent window = null;
        Dialog.showError("ERROR", "Functionality not yet added");
        Scene scene = new Scene(window);       
        stage.setScene(scene);
        stage.setWidth(562);
        stage.setHeight(250);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        stage.setOnHidden(new EventHandler<WindowEvent>(){
            @Override
            public void handle(WindowEvent t) {
                try {
                    searchCustomer();
                } catch (ClassNotFoundException ex) {
                    Dialog.showError("ERROR", "Error searching customer");
                }
            }
        });
    }
    
    //make payment on a account
    private void makePayment(){
        Stage stage = new Stage();
        stage.setTitle("Make A Payment");
        Parent window = null;
        Dialog.showError("ERROR", "Functionality not yet added");
        Scene scene = new Scene(window);       
        stage.setScene(scene);
        stage.setWidth(600);
        stage.setHeight(400);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        stage.setOnHidden(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                try {
                    searchCustomer();
                } catch (ClassNotFoundException ex) {
                    Dialog.showError("ERROR", "Error searching customer");
                }
            }
        });
    }
    
    //view customer transactions
    private void viewTransactionLog(){
        Stage stage = new Stage();
        stage.setTitle("Customer Transactions");
        Parent window = null;
        Dialog.showError("ERROR", "Functionality not yet added");
        Scene scene = new Scene(window);       
        stage.setScene(scene);
        stage.setWidth(1100);
        stage.setHeight(400);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }
    
    //open the view emergency contact page in the content pane
    private void viewEmergencyContact(){
        Dialog.showError("ERROR", "Functionality not yet added");
    }
    
    //clear the form
    private void resetForm() {
        //get number of children in the root anchor pane
        int numFields = root.getChildren().size();
        //iterate through each child
        for (int i = 0;i<numFields;i++){
            //if child is an instance of a textfield
            if(root.getChildren().get(i) instanceof TextField){
                //create a node to represent the textfield and then clear text
                TextField node = (TextField) root.getChildren().get(i);
                node.setText("");
            }
        }
        //set the customer object as last customer in database
        try{
            //connect to the database
            SettingsController.openConnection();
            String sql="SELECT TOP 1 CustomerID FROM Customer ORDER BY CustomerID DESC";
            Statement stmt = SettingsController.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(sql); 
            while(rs.next()){
                Customer.oCust.setID(rs.getInt("CustomerID"));
                loadForm();
            }
            SettingsController.closeConnection();
        }
        catch(ClassNotFoundException | SQLException exception){
            System.out.println(exception.getMessage());
        }
    }

    //delete customer note
    private void deleteNote() throws ClassNotFoundException, SQLException{
        SettingsController.openConnection();
        try {
            String sql="DELETE FROM CustomerNote WHERE CustomerID="+custID+" AND CustomerNoteID="+noteID;
            Statement stmt = SettingsController.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stmt.executeUpdate(sql); 
            searchCustomer();
        } catch (SQLException | ClassNotFoundException e) {
             Dialog.showError("Error", "Error deleting customer note");
        }
        SettingsController.closeConnection();
        
    }
    
    //edit customer note
    private void viewNote(){
        Stage stage = new Stage();
        stage.setTitle("View Customer Note");
        Parent window = null;
       Dialog.showError("ERROR", "Functionality not yet added");
        Scene scene = new Scene(window);
        stage.setScene(scene);
        stage.setWidth(600);
        stage.setHeight(425);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        stage.setResizable(false);
        try {
            //AddNoteController.instance.updateNote(note,noteDate);
        } catch (Exception e) {
            Dialog.showError("Error", e.getMessage());
        }
        
    }
    
    //create customer note
    private void createNote(){
        Stage stage = new Stage();
        stage.setTitle("Add New Note");
        Parent window = null;
        Dialog.showError("ERROR", "Functionality not yet added");
        Scene scene = new Scene(window);
        stage.setScene(scene);
        stage.setWidth(600);
        stage.setHeight(425);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        stage.setResizable(false);
        stage.setOnHidden(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                try {
                    searchCustomer();
                } catch (ClassNotFoundException ex) {
                    Dialog.showError("ERROR", "Error searching customer");
                }
            }
        });
    }
    
    private void loadDropDownData(){
        cmbState.setItems(dropdowndata.stateList);
        cmbCountry.setItems(dropdowndata.countryList);
        cmbSalutation.setItems(dropdowndata.salutationList);
        cmbSuffix.setItems(dropdowndata.suffixList);
        Collections.sort(dropdowndata.highSchoolList);
        dropdowndata.highSchoolList.add(0,"ADD NEW HIGHSCHOOL...");
        dropdowndata.highSchoolList.add(1,"NONE");
        cmbHighSchool.setItems(dropdowndata.highSchoolList);
    }
    
    //class used to populate the enrolled classes table in the form
    public class classDisplay{

        //classID property
        private IntegerProperty ID;
        public void setID(int value) {IDProperty().setValue(value);}
        public int getID() {return IDProperty().get();}
        public IntegerProperty IDProperty(){
            if (ID == null){
                ID = new SimpleIntegerProperty(this, "classID");
            }
            return ID;
        }

        //courseName property
        private StringProperty courseName;
        public void setCourseName(String value) {courseNameProperty().setValue(value);}
        public String getCourseName() {return courseNameProperty().get();}
        public StringProperty courseNameProperty(){
            if (courseName == null){
                courseName = new SimpleStringProperty(this, "courseName");
            }
            return courseName;
        }
        
        //courseCost property
        private Double courseCost;
        public void setCourseCost(Double value) {courseCost = value;}
        public Double getCourseCostAsDouble(){
            if(courseCost==null){
                courseCost = 0.00;
                return courseCost;
            }
            else{
                return courseCost;
            }
        }

        //start date property
        private StringProperty startDate;
        public void setStartDate(String value) {startDateProperty().setValue(value);}
        public String getStartDate() {return startDateProperty().get();}
        public StringProperty startDateProperty(){
            if (startDate == null){
                startDate = new SimpleStringProperty(this, "startDate");
            }
            return startDate;
        }

        //end date property
        private StringProperty endDate;
        public void setEndDate(String value) {endDateProperty().setValue(value);}
        public String getEndDate() {return endDateProperty().get();}
        public StringProperty endDateProperty(){
            if (endDate == null){
                endDate = new SimpleStringProperty(this, "endDate");
            }
            return endDate;
        }

        //time slot property
        private StringProperty timeSlot;
        public void setTimeSlot(String value) {timeSlotProperty().setValue(value);}
        public String getTimeSlot() {return timeSlotProperty().get();}
        public StringProperty timeSlotProperty(){
            if (timeSlot == null){
                timeSlot = new SimpleStringProperty(this, "timeSlot");
            }
            return timeSlot;
        }

        //recurrence property
        private StringProperty recurrenceString;
        public void setRecurrenceString(String value) {recurrenceStringProperty().setValue(value);}
        public String getRecurrenceString() {return recurrenceStringProperty().get();}
        public StringProperty recurrenceStringProperty(){
            if (recurrenceString == null){
                recurrenceString = new SimpleStringProperty(this, "recurrenceString");
            }
            return recurrenceString;
        }
        
        //instructorName property
        private StringProperty instructorName;
        public void setInstructorName(String value) {instructorNameProperty().setValue(value);}
        public String getInstructorName() {return instructorNameProperty().get();}
        public StringProperty instructorNameProperty(){
            if (instructorName == null){
                instructorName = new SimpleStringProperty(this, "instructorName");
            }
            return instructorName;
        }
        
        //calendarAddress property
        private StringProperty calendarAddress;
        public void setCalendarAddress(String value) {calendarAddressProperty().setValue(value);}
        public String getCalendarAddress() {return calendarAddressProperty().get();}
        public StringProperty calendarAddressProperty(){
            if (calendarAddress == null){
                calendarAddress = new SimpleStringProperty(this, "calendarAddress");
            }
            return calendarAddress;
        }
    }
}