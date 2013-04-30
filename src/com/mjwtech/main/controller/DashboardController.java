/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mjwtech.main.controller;

import com.mjwtech.customer.model.Enrollment;
import data.database_connection.SettingsController;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import name.antonsmirnov.javafx.dialog.Dialog;

/**
 * FXML Controller class
 *
 * @author mrgnwatson
 */
public class DashboardController implements Initializable {

    @FXML
    private TableView<Enrollment> tableClass;
    @FXML
    private TableColumn<Enrollment, String> colCourseName;
    @FXML
    private TableColumn<Enrollment, String> colTime;
    @FXML
    private TableColumn<Enrollment, String> colInstructor;
    @FXML
    private TableColumn<Enrollment, String> colAction;
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    SimpleDateFormat parseDate = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat displayTime = new SimpleDateFormat("h:mm a");
    SimpleDateFormat parseTime = new SimpleDateFormat("HH:mm:ss");
    ObservableList<Enrollment> classData;
    ObservableList<String> ol = FXCollections.observableArrayList("Action", "Take Attendance", "View Enrollment", "Edit", "Delete");
    ComboBox combo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.gc();
        try {
            loadClasses();
        } catch (ParseException ex) {
            Dialog.showError("ERROR", "Error loading today's classes");
        }
    }

    private void loadClasses() throws ParseException {
        try {
            SettingsController.openConnection();
            Statement stmt = SettingsController.conn.createStatement();
            String sql = "EXEC viewTodaysClasses";
            ResultSet rs = stmt.executeQuery(sql);
            classData = FXCollections.observableArrayList();
            while (rs.next()) {
                Enrollment e = new Enrollment();
                e.setClassID(rs.getInt("ClassID"));
                e.setCourseName(rs.getString("CourseName"));
                e.setTime(displayTime.format(parseTime.parse(rs.getString("StartTime"))) + " – " + displayTime.format(parseTime.parse(rs.getString("EndTime"))));
                e.setInstructorName(rs.getString("Instructor"));
                classData.add(e);
            }
            tableClass.setItems(classData);
            colCourseName.setCellValueFactory(new PropertyValueFactory("CourseName"));
            colTime.setCellValueFactory(new PropertyValueFactory("Time"));
            colInstructor.setCellValueFactory(new PropertyValueFactory("InstructorName"));
            colAction.setCellFactory(new Callback<TableColumn<Enrollment, String>, TableCell<Enrollment, String>>() {
                Button b1, b2;
                HBox h;
                @Override
                public TableCell<Enrollment, String> call(TableColumn<Enrollment, String> p) {
                    TableCell<Enrollment,String> c = new TableCell<Enrollment, String>(){
                        @Override
                        public void updateItem(String item, boolean empty) {
                               
                                b1 = new Button();
                                b1.getStyleClass().add("btnAttendanceRecords");
                                b1.setPrefSize(30, 30);
                                b1.setTooltip(new Tooltip("Take Attendance"));
                                b1.setOnAction(new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent t) {
                                        tableClass.getSelectionModel().select(getTableRow().getIndex());
                                        int ClassID = tableClass.getSelectionModel().getSelectedItem().getClassID();
                                        Dialog.showInfo("NOTICE", "THIS HAS NOT YET BEEN IMPLEMENTED.\nWILL OPEN TAKE ATTENDANCE FOR CLASS ID: "+ClassID);
                                    }
                                });
                                
                                b2 = new Button();
                                b2.getStyleClass().add("btnCourseEnrollment");
                                b2.setPrefSize(30, 30);
                                b2.setTooltip(new Tooltip("View Enrollment"));
                                b2.setOnAction(new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent t) {
                                        tableClass.getSelectionModel().select(getTableRow().getIndex());
                                        int ClassID = tableClass.getSelectionModel().getSelectedItem().getClassID();
                                        Dialog.showInfo("NOTICE", "THIS HAS NOT YET BEEN IMPLEMENTED.\nWILL OPEN VIEW ENROLLMENT FOR CLASS ID: "+ClassID);
                                    }
                                });
                                
                                h = new HBox(5);
                                h.getChildren().addAll(b1,b2);
                                setGraphic(h);
                        }
                    };
                    
                    return c;
                }
            });
            SettingsController.closeConnection();
        } catch (ClassNotFoundException | SQLException e) {
        }
    }
}
