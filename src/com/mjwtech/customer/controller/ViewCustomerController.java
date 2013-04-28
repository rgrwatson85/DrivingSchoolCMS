package com.mjwtech.customer.controller;

import com.mjwtech.main.controller.MainController;
import com.mjwtech.customer.model.Customer;
import com.mjwtech.customer.model.CustomerNote;
import com.mjwtech.customer.model.Enrollment;
import data.database_connection.SettingsController;
import data.dropdown.dropdowndata;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import jfxtras.labs.scene.control.CalendarTextField;
import name.antonsmirnov.javafx.dialog.Dialog;
import resources.eyecandy.Fade;


public class ViewCustomerController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnAddNote;

    @FXML
    private Button btnCancelChanges;

    @FXML
    private Button btnEditCustomer;

    @FXML
    private Button btnEmergencyContact;

    @FXML
    private Button btnEnrollCourse;

    @FXML
    private Button btnFee;

    @FXML
    private Button btnMakePayment;

    @FXML
    private Button btnRemoveCustomer;

    @FXML
    private Button btnSaveChanges;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnTransactions;

    @FXML
    private ComboBox cmbCountry;

    @FXML
    private ChoiceBox cmbParameter;

    @FXML
    private ComboBox cmbSalutation;

    @FXML
    private ComboBox cmbState;

    @FXML
    private ComboBox cmbSuffix;

    @FXML
    private TableColumn colCourses;

    @FXML
    private TableColumn colDays;

    @FXML
    private TableColumn colEndDate;

    @FXML
    private TableColumn colInstructor;

    @FXML
    private TableColumn<CustomerNote,String> colNoteDate;

    @FXML
    private TableColumn<CustomerNote,String> colNoteDescription;

    @FXML
    private TableColumn colStartDate;

    @FXML
    private TableColumn colTime;

    @FXML
    private Label lblToolTip;

    @FXML
    private AnchorPane root;

    @FXML
    private AnchorPane searchWrapper;

    @FXML
    private TableView<Enrollment> tableClass;

    @FXML
    private TableView<CustomerNote> tableNote;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtAddress2;

    @FXML
    private TextField txtBalanceDue;

    @FXML
    private TextField txtCellPhone1;

    @FXML
    private TextField txtCellPhone2;

    @FXML
    private TextField txtCellPhone3;

    @FXML
    private TextField txtCity;

    @FXML
    private TextField txtCountry;

    @FXML
    private TextField txtCustomerID;
    
    @FXML
    private CalendarTextField txtDOB;

    @FXML
    private TextField txtDOB_mask;

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
    private TextField txtSalutation;

    @FXML
    private TextField txtSearchField;

    @FXML
    private TextField txtState;

    @FXML
    private TextField txtSuffix;

    @FXML
    private TextField txtZip;
    
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    SimpleDateFormat parseDate = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat displayTime = new SimpleDateFormat("h:mm a");
    SimpleDateFormat parseTime = new SimpleDateFormat("HH:mm:ss");
    DecimalFormat formatter = new DecimalFormat("$#,##0.00");
    ObservableList<Enrollment> EnrollmentData;
    ObservableList<CustomerNote> NoteData;
    final Fade f = new Fade();

    @FXML
    void initialize() {
        
        // <editor-fold defaultstate="collapsed" desc="FXML assert">
        assert btnAddNote != null : "fx:id=\"btnAddNote\" was not injected: check your FXML file 'ViewCustomer.fxml'.";
        assert btnCancelChanges != null : "fx:id=\"btnCancelChanges\" was not injected: check your FXML file 'ViewCustomer.fxml'.";
        assert btnEditCustomer != null : "fx:id=\"btnEditCustomer\" was not injected: check your FXML file 'ViewCustomer.fxml'.";
        assert btnEmergencyContact != null : "fx:id=\"btnEmergencyContact\" was not injected: check your FXML file 'ViewCustomer.fxml'.";
        assert btnEnrollCourse != null : "fx:id=\"btnEnrollCourse\" was not injected: check your FXML file 'ViewCustomer.fxml'.";
        assert btnFee != null : "fx:id=\"btnFee\" was not injected: check your FXML file 'ViewCustomer.fxml'.";
        assert btnMakePayment != null : "fx:id=\"btnMakePayment\" was not injected: check your FXML file 'ViewCustomer.fxml'.";
        assert btnRemoveCustomer != null : "fx:id=\"btnRemoveCustomer\" was not injected: check your FXML file 'ViewCustomer.fxml'.";
        assert btnSaveChanges != null : "fx:id=\"btnSaveChanges\" was not injected: check your FXML file 'ViewCustomer.fxml'.";
        assert btnSearch != null : "fx:id=\"btnSearch\" was not injected: check your FXML file 'ViewCustomer.fxml'.";
        assert btnTransactions != null : "fx:id=\"btnTransactions\" was not injected: check your FXML file 'ViewCustomer.fxml'.";
        assert cmbCountry != null : "fx:id=\"cmbCountry\" was not injected: check your FXML file 'ViewCustomer.fxml'.";
        assert cmbParameter != null : "fx:id=\"cmbParameter\" was not injected: check your FXML file 'ViewCustomer.fxml'.";
        assert cmbSalutation != null : "fx:id=\"cmbSalutation\" was not injected: check your FXML file 'ViewCustomer.fxml'.";
        assert cmbState != null : "fx:id=\"cmbState\" was not injected: check your FXML file 'ViewCustomer.fxml'.";
        assert cmbSuffix != null : "fx:id=\"cmbSuffix\" was not injected: check your FXML file 'ViewCustomer.fxml'.";
        assert colCourses != null : "fx:id=\"colCourses\" was not injected: check your FXML file 'ViewCustomer.fxml'.";
        assert colDays != null : "fx:id=\"colDays\" was not injected: check your FXML file 'ViewCustomer.fxml'.";
        assert colEndDate != null : "fx:id=\"colEndDate\" was not injected: check your FXML file 'ViewCustomer.fxml'.";
        assert colInstructor != null : "fx:id=\"colInstructor\" was not injected: check your FXML file 'ViewCustomer.fxml'.";
        assert colNoteDate != null : "fx:id=\"colNoteDate\" was not injected: check your FXML file 'ViewCustomer.fxml'.";
        assert colNoteDescription != null : "fx:id=\"colNoteDescription\" was not injected: check your FXML file 'ViewCustomer.fxml'.";
        assert colStartDate != null : "fx:id=\"colStartDate\" was not injected: check your FXML file 'ViewCustomer.fxml'.";
        assert colTime != null : "fx:id=\"colTime\" was not injected: check your FXML file 'ViewCustomer.fxml'.";
        assert lblToolTip != null : "fx:id=\"lblToolTip\" was not injected: check your FXML file 'ViewCustomer.fxml'.";
        assert root != null : "fx:id=\"root\" was not injected: check your FXML file 'ViewCustomer.fxml'.";
        assert searchWrapper != null : "fx:id=\"searchWrapper\" was not injected: check your FXML file 'ViewCustomer.fxml'.";
        assert tableClass != null : "fx:id=\"tableClass\" was not injected: check your FXML file 'ViewCustomer.fxml'.";
        assert tableNote != null : "fx:id=\"tableNote\" was not injected: check your FXML file 'ViewCustomer.fxml'.";
        assert txtAddress != null : "fx:id=\"txtAddress\" was not injected: check your FXML file 'ViewCustomer.fxml'.";
        assert txtAddress2 != null : "fx:id=\"txtAddress2\" was not injected: check your FXML file 'ViewCustomer.fxml'.";
        assert txtBalanceDue != null : "fx:id=\"txtBalanceDue\" was not injected: check your FXML file 'ViewCustomer.fxml'.";
        assert txtCellPhone1 != null : "fx:id=\"txtCellPhone1\" was not injected: check your FXML file 'ViewCustomer.fxml'.";
        assert txtCellPhone2 != null : "fx:id=\"txtCellPhone2\" was not injected: check your FXML file 'ViewCustomer.fxml'.";
        assert txtCellPhone3 != null : "fx:id=\"txtCellPhone3\" was not injected: check your FXML file 'ViewCustomer.fxml'.";
        assert txtCity != null : "fx:id=\"txtCity\" was not injected: check your FXML file 'ViewCustomer.fxml'.";
        assert txtCountry != null : "fx:id=\"txtCountry\" was not injected: check your FXML file 'ViewCustomer.fxml'.";
        assert txtCustomerID != null : "fx:id=\"txtCustomerID\" was not injected: check your FXML file 'ViewCustomer.fxml'.";
        assert txtDOB_mask != null : "fx:id=\"txtDOB_mask\" was not injected: check your FXML file 'ViewCustomer.fxml'.";
        assert txtEmail != null : "fx:id=\"txtEmail\" was not injected: check your FXML file 'ViewCustomer.fxml'.";
        assert txtFirstName != null : "fx:id=\"txtFirstName\" was not injected: check your FXML file 'ViewCustomer.fxml'.";
        assert txtHomePhone1 != null : "fx:id=\"txtHomePhone1\" was not injected: check your FXML file 'ViewCustomer.fxml'.";
        assert txtHomePhone2 != null : "fx:id=\"txtHomePhone2\" was not injected: check your FXML file 'ViewCustomer.fxml'.";
        assert txtHomePhone3 != null : "fx:id=\"txtHomePhone3\" was not injected: check your FXML file 'ViewCustomer.fxml'.";
        assert txtLastName != null : "fx:id=\"txtLastName\" was not injected: check your FXML file 'ViewCustomer.fxml'.";
        assert txtSalutation != null : "fx:id=\"txtSalutation\" was not injected: check your FXML file 'ViewCustomer.fxml'.";
        assert txtSearchField != null : "fx:id=\"txtSearchField\" was not injected: check your FXML file 'ViewCustomer.fxml'.";
        assert txtState != null : "fx:id=\"txtState\" was not injected: check your FXML file 'ViewCustomer.fxml'.";
        assert txtSuffix != null : "fx:id=\"txtSuffix\" was not injected: check your FXML file 'ViewCustomer.fxml'.";
        assert txtZip != null : "fx:id=\"txtZip\" was not injected: check your FXML file 'ViewCustomer.fxml'.";
        // </editor-fold>
        
        Task task = new Task() {
            @Override
            protected Object call() throws Exception {
                createEventHandlers();
                return null;
            }
        };
        task.setOnFailed(new EventHandler() {
            @Override
            public void handle(Event t) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        Dialog.showError("ERROR", "FATAL ERROR:\n\nCould not properly load the page. Exit form and reload.");
                    }
                });
            }
        });
        task.setOnSucceeded(new EventHandler() {
            @Override
            public void handle(Event t) {
               Platform.runLater(new Runnable() {
                   @Override
                   public void run() {
                       setDefaults();
                       if(Customer.oCust.getID()>0){
                           searchCustomer("CustomerID", Customer.oCust.getID());
                       }
                   }
               });
            }
        });
        new Thread(task).start();
    }
    
    //Sets default values for form if a customer object is not loaded
    private void setDefaults(){
        txtDOB.setDateFormat(sdf);
        cmbParameter.getSelectionModel().select(1);
        cmbSalutation.setItems(dropdowndata.salutationList);
        cmbSuffix.setItems(dropdowndata.suffixList);
        setEditMode(false);
        colNoteDate.setCellValueFactory(new PropertyValueFactory<CustomerNote,String>("Date"));
        colNoteDescription.setCellValueFactory(new PropertyValueFactory<CustomerNote,String>("ShortNote")); 
        colCourses.setCellValueFactory(new PropertyValueFactory<Enrollment,String>("CourseName"));
        colStartDate.setCellValueFactory(new PropertyValueFactory<Enrollment,String>("StartDate"));
        colEndDate.setCellValueFactory(new PropertyValueFactory<Enrollment,String>("EndDate"));
        colDays.setCellValueFactory(new PropertyValueFactory<Enrollment,String>("RecurrenceString"));
        colTime.setCellValueFactory(new PropertyValueFactory<Enrollment,String>("Time"));
        colInstructor.setCellValueFactory(new PropertyValueFactory<Enrollment,String>("InstructorName"));
        MainController.ProgressGroup.setOpacity(0);
        MainController.lblProgressStatus.setText("PROCESSING");
        Fade.gb.setRadius(0);
    }
    
    // Creates all of the event handlers for the class
    private void createEventHandlers(){
        //search text field
        txtSearchField.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                String parameter = cmbParameter.getValue().toString();
                String valueString = txtSearchField.getText();
                try {
                    Integer valueInteger = Integer.parseInt(txtSearchField.getText());
                    searchCustomer(parameter, valueInteger);
                } catch (Exception e) {
                    searchCustomer(parameter, valueString);
                }
            }
        });
        //search button
        btnSearch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                txtSearchField.getOnAction().handle(t);
            }
        });
        //txtDOB changes the value of its TextField mask
        txtDOB.valueProperty().addListener(new ChangeListener<Calendar>() {
            @Override
            public void changed(ObservableValue<? extends Calendar> ov, Calendar t, Calendar t1) {
                txtDOB_mask.setText(sdf.format(t1.getTime()));
            }
        });
        //set form into edit mode
        btnEditCustomer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                setEditMode(true);
            }
        });
        //take form out of edit mode
        btnCancelChanges.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                setEditMode(false);
                Dialog.showInfo("NOTICE", "Changes have been cancelled");
                searchCustomer("CustomerID", Customer.oCust.getID());
            }
        });
        //save changes to the database
        btnSaveChanges.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                updateCustomer();
            }
        });    
        //delete customer
        btnRemoveCustomer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                deleteCustomer();
            }
        });
    }
    
    //search for customer and assign attributes to global customer object
    private void searchCustomer(String parameter, Object value){
        setEditMode(false);
        String valueType = value.getClass().getSimpleName();
        if("String".equals(valueType)){
            value = "'"+value;
        }
        parameter = parameter.trim().replaceAll(" ", "");
        try {
            SettingsController.openConnection();
            Statement stmt = SettingsController.conn.createStatement();
            String sql = "EXEC viewCustomer "+parameter+","+value.toString();
            ResultSet rs = stmt.executeQuery(sql);
            int size = 0;
            while(rs.next()){
                size++;
                if(size==2){break;}
                Customer.oCust.setID(rs.getInt("CustomerID"));
                Customer.oCust.setSalutationName(rs.getString("SalutationName"));
                Customer.oCust.setFirstName(rs.getString("FirstName"));
                Customer.oCust.setLastName(rs.getString("LastName"));
                Customer.oCust.setSuffix(rs.getString("SuffixName"));
                Customer.oCust.setAddress(rs.getString("Address"));
                Customer.oCust.setAddress2(rs.getString("Address2"));
                Customer.oCust.setCity(rs.getString("City"));
                Customer.oCust.setState(rs.getString("StateName"));
                Customer.oCust.setZip(rs.getString("Zip"));
                Customer.oCust.setCountry(rs.getString("CountryName"));
                Customer.oCust.setHomePhone(rs.getString("HomePhone"));
                Customer.oCust.setCellPhone(rs.getString("CellPhone"));
                Customer.oCust.setEmail(rs.getString("EmailAddress"));
                Customer.oCust.setDOB(sdf.format(rs.getDate("DOB")));
                Customer.oCust.setBalanceDue(rs.getDouble("BalanceDue"));
            }
            SettingsController.closeConnection();
            if (size==1) {
                Task getSecondaryData = new Task() {
                    @Override
                    protected Object call() throws Exception{
                        try{
                        SettingsController.openConnection();
                        Statement stmt = SettingsController.conn.createStatement();
                        String sql = "viewCustomerClassEnrollment "+Customer.oCust.getID();
                        ResultSet rs = stmt.executeQuery(sql);
                        EnrollmentData = FXCollections.observableArrayList();
                        while(rs.next()){
                            Enrollment e = new Enrollment();
                            e.setCourseName(rs.getString("CourseName"));
                            e.setCourseCost(rs.getDouble("CourseCost"));
                            e.setClassID(rs.getInt("ClassID"));
                            e.setRecurrenceString(rs.getString("RecurrenceString"));
                            e.setStartDate(sdf.format(rs.getDate("StartDate")));
                            e.setEndDate(sdf.format(rs.getDate("EndDate")));
                            e.setTime(displayTime.format(parseTime.parse(rs.getString("StartTime")))+" - "+displayTime.format(parseTime.parse(rs.getString("EndTime"))));
                            e.setInstructorName(rs.getString("InstructorName"));
                            EnrollmentData.add(e);
                        }
                        SettingsController.closeConnection();
                        }catch(ClassNotFoundException | SQLException e){
                            System.err.println(e.getMessage());
                        }
                        try {
                            SettingsController.openConnection();
                            Statement stmt = SettingsController.conn.createStatement();
                            String sql = "viewCustomerNote "+Customer.oCust.getID();
                            ResultSet rs = stmt.executeQuery(sql);
                            NoteData = FXCollections.observableArrayList();
                            while(rs.next()){
                                CustomerNote cn = new CustomerNote();
                                cn.setDate(sdf.format(rs.getDate("Date")));
                                cn.setID(rs.getInt("CustomerNoteID"));
                                cn.setNote(rs.getString("Note"));
                                cn.setShortNote(rs.getString("Note").substring(0, 15)+"...(view more)");
                                NoteData.add(cn);
                            }
                            SettingsController.closeConnection();
                        } catch (ClassNotFoundException | SQLException e) {
                            System.err.println(e.getMessage());
                        }
                        return null;
                    }
                };
                getSecondaryData.setOnSucceeded(new EventHandler() {
                    @Override
                    public void handle(Event t) {
                        try {
                            txtSalutation.setText(Customer.oCust.getSalutationName());
                            txtFirstName.setText(Customer.oCust.getFirstName());
                            txtLastName.setText(Customer.oCust.getLastName());
                            try{
                                txtSuffix.setText(Customer.oCust.getSuffix());
                            }catch(Exception e){
                                txtSuffix.setText("");
                            }
                            txtAddress.setText(Customer.oCust.getAddress());
                            txtAddress2.setText(Customer.oCust.getAddress2());
                            txtCity.setText(Customer.oCust.getCity());
                            txtState.setText(Customer.oCust.getState());
                            txtZip.setText(Customer.oCust.getZip());
                            txtCountry.setText(Customer.oCust.getCountry());
                            try{
                                txtCellPhone1.setText(Customer.oCust.getCellPhone().substring(1,4));
                                txtCellPhone2.setText(Customer.oCust.getCellPhone().substring(6,9));
                                txtCellPhone3.setText(Customer.oCust.getCellPhone().substring(10,14));
                            }catch(Exception e){
                                txtCellPhone1.setText("");
                                txtCellPhone2.setText("");
                                txtCellPhone3.setText("");
                            }
                            try{
                                txtHomePhone1.setText(Customer.oCust.getHomePhone().substring(1,4));
                                txtHomePhone2.setText(Customer.oCust.getHomePhone().substring(6,9));
                                txtHomePhone3.setText(Customer.oCust.getHomePhone().substring(10,14));
                                }catch(Exception e){
                                txtHomePhone1.setText("");
                                txtHomePhone2.setText("");
                                txtHomePhone3.setText("");
                            }
                            txtEmail.setText(Customer.oCust.getEmail());
                            txtDOB_mask.setText(Customer.oCust.getDOB());
                            txtBalanceDue.setText(formatter.format(Customer.oCust.getBalanceDue()));
                            txtCustomerID.setText(""+Customer.oCust.getID());
                            txtSearchField.setText(""+Customer.oCust.getID());
                            tableClass.setItems(EnrollmentData);
                            tableNote.setItems(NoteData);

                        } catch (Exception e) {
                            Dialog.showError("ERROR", "Error loading customer data.");
                        }
                        Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    txtSearchField.requestFocus();
                                }
                            });
                    }
                });
                new Thread(getSecondaryData).start();
                
            }else{
                if(size==0){Dialog.showWarning("WARNING", "Customer not found");}
                if(size==2){Dialog.showWarning("WARNING", "More than one customer found. Refine your search");}
            }
        } catch (ClassNotFoundException | SQLException e) {
            Dialog.showError("ERROR", "Error searching for customer\n\n"+e.getMessage());
        }
    } 
    
    //update customer and reload the form
    private void updateCustomer(){
        final Task updateCustomer = new Task() {
            @Override
            protected Object call() throws Exception {
                Thread.sleep(2000);
                //create SQL parameters
                String CustomerID = ""+Customer.oCust.getID(),
                SAL = txtSalutation.getText(),
                FN = txtFirstName.getText(),
                LN = txtLastName.getText(),
                SUF = txtSuffix.getText(),
                ADD = txtAddress.getText(),
                ADD2 = txtAddress2.getText(),
                CTY = txtCity.getText(),
                ST = txtState.getText(),
                ZIP = txtZip.getText(),
                HP = "("+txtHomePhone1.getText()+")"+" "+txtHomePhone2.getText()+"-"+txtHomePhone3.getText(),
                CP = "("+txtCellPhone1.getText()+")"+" "+txtCellPhone2.getText()+"-"+txtCellPhone3.getText(),
                EM = txtEmail.getText(),
                DOB = txtDOB_mask.getText();
                SettingsController.openConnection();
                Statement stmt = SettingsController.conn.createStatement();
                String sql = "EXEC updateCustomer "+CustomerID+",'"+SAL+"','"+FN+"','"+LN+"','"+SUF+"','"+ADD+"','"+ADD2+"','"+CTY+"','"+ST+"','"+ZIP+"','"+HP+"','"+CP+"','"+EM+"','"+DOB+"'";
                stmt.executeUpdate(sql);
                SettingsController.closeConnection();
                return null;
            }
   };
        updateCustomer.setOnScheduled(new EventHandler() {
            @Override
            public void handle(Event t) {
                f.FadeOut();
            }
        });
        updateCustomer.setOnFailed(new EventHandler() {
            @Override
            public void handle(Event t) {
                MainController.lblProgressStatus.setText("ERROR");
                try{
                    Task wait = new Task() {
                        @Override
                        protected Object call() throws Exception {
                            Thread.sleep(2000);
                            this.setOnSucceeded(new EventHandler() {
                                @Override
                                public void handle(Event t) {
                                    f.FadeIn();
                                }
                            });
                            this.setOnFailed(new EventHandler() {
                            @Override
                            public void handle(Event t) {
                                f.FadeIn();
                            }
                        });
                            return null;
                        }
                    };
                    new Thread(wait).start();
                }catch(Exception e){
                    System.err.println(e.getMessage());
                }
            }
        });
        updateCustomer.setOnSucceeded(new EventHandler() {
            @Override
            public void handle(Event t) {
                MainController.lblProgressStatus.setText("SUCCESS");
                try{
                    Task wait = new Task() {
                        @Override
                        protected Object call() throws Exception {
                            Thread.sleep(2000);
                            this.setOnSucceeded(new EventHandler() {
                                @Override
                                public void handle(Event t) {
                                    f.FadeIn();
                                    txtSearchField.getOnAction().handle(null);
                                }
                            });
                            this.setOnFailed(new EventHandler() {
                            @Override
                            public void handle(Event t) {
                                f.FadeIn();
                            }
                        });
                            return null;
                        }
                    };
                    new Thread(wait).start();
                }catch(Exception e){
                    System.err.println(e.getMessage());}
            }
        });
        new Thread(updateCustomer).start();
    }
    
    //delete customer
    private void deleteCustomer(){
        final Task deleteCustomer = new Task() {
            @Override
            protected Object call() throws Exception {
                try {
                    Thread.sleep(2000);
                    SettingsController.openConnection();
                    Statement stmt = SettingsController.conn.createStatement();
                    String sql = "EXEC deleteCustomer "+Customer.oCust.getID();
                    ResultSet rs = stmt.executeQuery(sql);
                    rs.next();
                    Customer.oCust.setID(rs.getInt("CustomerID"));
                    SettingsController.closeConnection();
                } catch (ClassNotFoundException | SQLException e) {
                    System.err.println(e.getMessage());
                    throw new Exception();
                }
                return null;
            }
        };
        deleteCustomer.setOnScheduled(new EventHandler() {
            @Override
            public void handle(Event t) {
                f.FadeOut();
            }
        });
        deleteCustomer.setOnFailed(new EventHandler() {
            @Override
            public void handle(Event t) {
                MainController.lblProgressStatus.setText("ERROR");
                try{
                    Task wait = new Task() {
                        @Override
                        protected Object call() throws Exception {
                            Thread.sleep(2000);
                            this.setOnSucceeded(new EventHandler() {
                                @Override
                                public void handle(Event t) {
                                    f.FadeIn();
                                }
                            });
                            this.setOnFailed(new EventHandler() {
                            @Override
                            public void handle(Event t) {
                                f.FadeIn();
                            }
                        });
                            return null;
                        }
                    };
                    new Thread(wait).start();
                }catch(Exception e){
                    System.err.println(e.getMessage());
                }
            }
        });
        deleteCustomer.setOnSucceeded(new EventHandler() {
            @Override
            public void handle(Event t) {
                MainController.lblProgressStatus.setText("SUCCESS");
                try{
                    Task wait = new Task() {
                        @Override
                        protected Object call() throws Exception {
                            Thread.sleep(2000);
                            this.setOnSucceeded(new EventHandler() {
                                @Override
                                public void handle(Event t) {
                                    f.FadeIn();
                                    txtSearchField.setText(Customer.oCust.getID()+"");
                                    txtSearchField.getOnAction().handle(null);
                                }
                            });
                            this.setOnFailed(new EventHandler() {
                            @Override
                            public void handle(Event t) {
                                f.FadeIn();
                            }
                        });
                            return null;
                        }
                    };
                    new Thread(wait).start();
                }catch(Exception e){
                    System.err.println(e.getMessage());}
            }
        });
        Dialog.buildConfirmation("", "").create()
            .setTitle("WARNING")
            .setMessage("THIS WILL DELETE EVERY RECORD FOR THIS CUSTOMER INDLUDING:\n\n"
            + "CLASS ATTENDANCE\nDRIVE HISTORY\nGRADES\nPAYMENTS OWED\nPAYMENTS RECEIVED\n\n"
            + "THIS CANNOT BE UNDONE! ARE YOU SURE YOU WANT TO DO THIS?")
            .addNoButton(new EventHandler() {
                @Override
                public void handle(Event t) {
                    Dialog.showInfo("SUCCESS", "Customer delete was cancelled");
                }
            })
            .addYesButton(new EventHandler() {
                @Override
                public void handle(Event t) {
                     new Thread(deleteCustomer).start();
                     setEditMode(false);
                }
            })
            .build()
            .show();
    }
    
    //set edit mode for form
    private void setEditMode(Boolean editable){
        txtSalutation.editableProperty().set(editable);
        txtSuffix.editableProperty().set(editable);
        cmbSalutation.setVisible(editable);
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
        txtDOB.visibleProperty().set(editable);
        btnEditCustomer.setVisible(!editable);
        btnEmergencyContact.setVisible(!editable);
        btnEnrollCourse.setVisible(!editable);
        btnMakePayment.setDisable(editable);
        btnFee.setDisable(editable);
        btnAddNote.setDisable(editable);
    }
}