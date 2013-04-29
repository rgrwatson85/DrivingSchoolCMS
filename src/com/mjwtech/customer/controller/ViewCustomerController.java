package com.mjwtech.customer.controller;

import com.mjwtech.main.controller.MainController;
import com.mjwtech.customer.model.Customer;
import com.mjwtech.customer.model.CustomerNote;
import com.mjwtech.customer.model.Enrollment;
import data.database_connection.SettingsController;
import data.dropdown.dropdowndata;
import data.validation.validation;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
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
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;
import jfxtras.labs.scene.control.CalendarTextField;
import name.antonsmirnov.javafx.dialog.Dialog;
import resources.eyecandy.Fade;


public class ViewCustomerController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    public static TilePane advancedView;

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
    public static AnchorPane form;
    
    @FXML AnchorPane root;

    @FXML
    private AnchorPane searchWrapper;
    
    @FXML
    private AnchorPane buttonWrapper;

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
    public static TextField txtSearchField;

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
    
    public static AdvancedPane ap = new AdvancedPane();
    public static GaussianBlur gb;

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
        assert form != null : "fx:id=\"form\" was not injected: check your FXML file 'ViewCustomer.fxml'.";
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
        final Task task = new Task() {
            @Override
            protected Object call() throws Exception {
                buttonWrapper.setVisible(false);
                createEventHandlers();
                gb = new GaussianBlur(0);
                for(Node n : ViewCustomerController.form.getChildren()){
                    if(!"title".equals(n.getId()))
                        n.setEffect(gb);
                }
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
                        System.err.println(task.getException().getMessage());
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
                       else{
                           Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    txtSearchField.requestFocus();
                                }
                            });
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
        cmbState.setItems(dropdowndata.stateList);
        cmbCountry.setItems(dropdowndata.countryList);
        
        setEditMode(false);
        
        colNoteDate.setCellValueFactory(new PropertyValueFactory<CustomerNote,String>("Date"));
        colNoteDescription.setCellValueFactory(new PropertyValueFactory<CustomerNote,String>("ShortNote")); 
        colCourses.setCellValueFactory(new PropertyValueFactory<Enrollment,String>("CourseName"));
        colStartDate.setCellValueFactory(new PropertyValueFactory<Enrollment,String>("StartDate"));
        colEndDate.setCellValueFactory(new PropertyValueFactory<Enrollment,String>("EndDate"));
        colDays.setCellValueFactory(new PropertyValueFactory<Enrollment,String>("RecurrenceString"));
        colTime.setCellValueFactory(new PropertyValueFactory<Enrollment,String>("Time"));
        colInstructor.setCellValueFactory(new PropertyValueFactory<Enrollment,String>("InstructorName"));
        
        MainController.effectsPane.setOpacity(0);
        MainController.lblProgressStatus.setText("PROCESSING");
        Fade.gb.setRadius(0);
        
        advancedView.setMouseTransparent(true);
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
                initialize();
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
        //change state drop down
        cmbCountry.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue ov, Object t, Object t1) {
                try{
                dropdowndata.createStateDropDownData(t1.toString());
                    cmbState.setItems(dropdowndata.stateList);
                    cmbState.getSelectionModel().selectFirst();
                }catch(ClassNotFoundException | SQLException e){
                    Dialog.showError("ERROR", "Error changing state list");
                }
            }
        });
        //load notes into the advanced view if table is double clicked
        tableNote.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                try{
                    if(tableNote.getSelectionModel().getSelectedIndex()>=0){
                        if(t.getClickCount()==2){
                            setAdvancedMode(true);
                            CustomerNote n = tableNote.getSelectionModel().getSelectedItem();
                            Node p = FXMLLoader.load(getClass().getResource("/com/mjwtech/customer/view/AddNote.fxml"));
                            advancedView.getChildren().clear();
                            advancedView.getChildren().add(p);
                            AddNoteController.instance.viewNote(n.getNote(), n.getDate(), n.getID());
                        }
                    }
                }catch(ParseException | IOException e){
                    System.err.println("No note has been selected");
                }
            }
        });
        //create new customer note
        btnAddNote.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                setAdvancedMode(true);
                try{
                    Node p = FXMLLoader.load(getClass().getResource("/com/mjwtech/customer/view/AddNote.fxml"));
                    advancedView.getChildren().clear();
                    advancedView.getChildren().add(p);
                }catch(Exception e){
                    System.err.println(e.getMessage());
                }
            }
        });
        //close advanced pane when it loses the focus
        root.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                if(t.getClickCount()==2){
                    setAdvancedMode(false);
                    searchCustomer("CustomerID", Customer.oCust.getID());
                }
            }
        });
    }
    
    //search for customer and assign attributes to global customer object
    private void searchCustomer(String parameter, Object value){
        setEditMode(false);
        buttonWrapper.setVisible(true);
        advancedView.getChildren().clear();
        String valueType = value.getClass().getSimpleName();
        if("String".equals(valueType)){
            value = "['"+value+"']";
        }
        parameter = parameter.trim().replaceAll(" ", "");
        try {
            SettingsController.openConnection();
            Statement stmt = SettingsController.conn.createStatement();
            String sql = "EXEC viewCustomer "+parameter+","+value.toString();
            System.out.println(sql);
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
                                //System.out.println("Notes added: "+rs.getRow());
                                CustomerNote cn = new CustomerNote();
                                cn.setDate(sdf.format(rs.getDate("Date")));
                                cn.setID(rs.getInt("CustomerNoteID"));
                                cn.setNote(rs.getString("Note"));
                                if(rs.getString("Note").length()>=15){
                                    cn.setShortNote(rs.getString("Note").substring(0, 15)+"...(view more)");
                                }else{
                                    cn.setShortNote(rs.getString("Note"));
                                }
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
                            try{
                                txtAddress2.setText(Customer.oCust.getAddress2());
                            }catch(Exception e){
                                txtAddress2.setText("");
                            }
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
                            try{
                                txtEmail.setText(Customer.oCust.getEmail());
                            }catch(Exception e){
                                txtEmail.setText("");
                            }
                            txtDOB_mask.setText(Customer.oCust.getDOB());
                            txtBalanceDue.setText(formatter.format(Customer.oCust.getBalanceDue()));
                            txtCustomerID.setText(""+Customer.oCust.getID());
                            txtSearchField.setText(""+Customer.oCust.getID());
                           
                            tableClass.setItems(null);
                            tableClass.setItems(EnrollmentData);
                            tableNote.setItems(null);
                            tableNote.setItems(NoteData);
                           
                            //remove style class on all text fields on load
                            for (Node n : form.getChildren()) {
                                if (n instanceof TextField) {
                                    n.getStyleClass().removeAll("valid","error");
                                }
                            }

                        } catch (Exception e) {
                            Dialog.showError("ERROR", "Error loading customer data.\n\n"+e.getMessage());
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
                FN = txtFirstName.getText(),
                LN = txtLastName.getText(),
                ADD = txtAddress.getText(),
                ADD2 = txtAddress2.getText(),
                CTY = txtCity.getText(),
                ST = cmbState.getValue().toString(),
                ZIP = txtZip.getText(),
                HP = "("+txtHomePhone1.getText()+")"+" "+txtHomePhone2.getText()+"-"+txtHomePhone3.getText(),
                CP = "("+txtCellPhone1.getText()+")"+" "+txtCellPhone2.getText()+"-"+txtCellPhone3.getText(),
                EM = txtEmail.getText(),
                DOB = txtDOB_mask.getText();
                String SAL,SUF;
                try{
                    SAL = cmbSalutation.getValue().toString();}
                catch(Exception e){
                    SAL = "null";
                }
                try {
                    SUF = cmbSuffix.getValue().toString();
                    if("NONE".equals(SUF)) throw new Exception();
                } catch (Exception e) {
                    SUF = "null";
                }
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
                Fade.f.FadeOut();
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
                                    Fade.f.FadeIn();
                                }
                            });
                            this.setOnFailed(new EventHandler() {
                            @Override
                            public void handle(Event t) {
                                Fade.f.FadeIn();
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
                                    Fade.f.FadeIn();
                                    txtSearchField.getOnAction().handle(null);
                                }
                            });
                            this.setOnFailed(new EventHandler() {
                            @Override
                            public void handle(Event t) {
                                Fade.f.FadeIn();
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
        boolean valid = true;
        //verify there are not textfields with errors
        for (Node n : form.getChildren()) {
            if (n instanceof TextField && n.getStyleClass().contains("error")) {
                valid = false;
            }
        }
        if(valid)
            new Thread(updateCustomer).start();
        else
            Dialog.showError("ERROR", "Fields marked in red are invalid");
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
                Fade.f.FadeOut();
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
                                    Fade.f.FadeIn();
                                }
                            });
                            this.setOnFailed(new EventHandler() {
                            @Override
                            public void handle(Event t) {
                                Fade.f.FadeIn();
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
                                    Fade.f.FadeIn();
                                    txtSearchField.setText(Customer.oCust.getID()+"");
                                    txtSearchField.getOnAction().handle(null);
                                }
                            });
                            this.setOnFailed(new EventHandler() {
                            @Override
                            public void handle(Event t) {
                                Fade.f.FadeIn();
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
    
    //setAdvancedMode
    private static void setAdvancedMode(boolean isAdvanced){
        if(isAdvanced){
            ap.showAdvanced();
        }else{
            advancedView.getChildren().clear();
            ap.hideAdvanced();
        }
    }
    
    //set edit mode for form
    private void setEditMode(Boolean editable){
        txtSalutation.editableProperty().set(editable);
        txtSuffix.editableProperty().set(editable);
        cmbSalutation.setVisible(editable);
        cmbSalutation.getSelectionModel().select(Customer.oCust.getSalutationName());
        txtFirstName.editableProperty().set(editable);
        txtLastName.editableProperty().set(editable);
        txtSuffix.editableProperty().set(editable);
        cmbSuffix.setVisible(editable);
        if(Customer.oCust.getSuffix()==null){
            cmbSuffix.getSelectionModel().select("NONE");
        }else{
            cmbSuffix.getSelectionModel().select(Customer.oCust.getSuffix());
        }
        txtAddress.editableProperty().set(editable);
        txtAddress2.editableProperty().set(editable);
        txtCity.editableProperty().set(editable);
        txtState.editableProperty().set(editable);
        txtCountry.editableProperty().set(editable);
        txtZip.editableProperty().set(editable);
        cmbState.setVisible(editable);
        cmbState.getSelectionModel().select(txtState.getText());
        txtState.setVisible(!editable);
        cmbCountry.setVisible(editable);
        cmbCountry.getSelectionModel().select(txtCountry.getText());
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
        
        if(editable){
            //apply error class to all text fields on load
            for (Node n : form.getChildren()) {
                if (n instanceof TextField) {
                    n.getStyleClass().add("valid");
                }
            }
            //form validation
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
                        if("".equals(t1)){
                            txtEmail.getStyleClass().remove("error");
                            txtEmail.getStyleClass().add("valid");
                        }else{
                            txtEmail.getStyleClass().remove("valid");
                            txtEmail.getStyleClass().add("error");
                        }
                    }
                }
            });
            txtDOB_mask.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                    System.out.println(t1);
                    if (fv.validateDate(t1)) {
                        txtDOB_mask.getStyleClass().remove("error");
                        txtDOB_mask.getStyleClass().add("valid");
                    } else {
                        txtDOB_mask.getStyleClass().remove("valid");
                        txtDOB_mask.getStyleClass().add("error");
                    }
                }
            });
        }
    }
    
    public static class AdvancedPane {
    public void showAdvanced(){
        advancedView.setMouseTransparent(false);
        Timeline tl = new Timeline();
        final KeyValue kv1 = new KeyValue(gb.radiusProperty(), 15);
        final KeyFrame kf1 = new KeyFrame(Duration.seconds(.5), kv1);
        final KeyValue kv2 = new KeyValue(advancedView.opacityProperty(), 1);
        final KeyFrame kf2 = new KeyFrame(Duration.seconds(.5), kv2);
        tl.getKeyFrames().addAll(kf1,kf2);
        tl.play();
    }
    public void hideAdvanced(){
        advancedView.setMouseTransparent(true);
        Timeline tl = new Timeline();
        final KeyValue kv1 = new KeyValue(gb.radiusProperty(), 0);
        final KeyFrame kf1 = new KeyFrame(Duration.seconds(.5), kv1);
        final KeyValue kv2 = new KeyValue(advancedView.opacityProperty(), 0);
        final KeyFrame kf2 = new KeyFrame(Duration.seconds(.5), kv2);
        tl.getKeyFrames().addAll(kf1,kf2);
        tl.play();
    }
}
}