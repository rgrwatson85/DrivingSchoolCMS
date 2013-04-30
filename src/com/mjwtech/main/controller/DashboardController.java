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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import name.antonsmirnov.javafx.dialog.Dialog;
import sun.plugin.cache.OldCacheEntry;

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
    
    ObservableList<String> ol = FXCollections.observableArrayList("Action","Take Attendance","View Enrollment","Edit","Delete");
    ComboBox combo = new ComboBox(ol);
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadClasses();
        } catch (ParseException ex) {
            Dialog.showError("ERROR", "Error loading today's classes");
        }
    }    
    
    private void loadClasses() throws ParseException{
        try {
            SettingsController.openConnection();
            Statement stmt = SettingsController.conn.createStatement();
            String sql = "EXEC viewTodaysClasses";
            ResultSet rs = stmt.executeQuery(sql);
            classData = FXCollections.observableArrayList();
            while(rs.next()){
                Enrollment e = new Enrollment();
                e.setClassID(rs.getInt("ClassID"));
                e.setCourseName(rs.getString("CourseName"));
                e.setTime(displayTime.format(parseTime.parse(rs.getString("StartTime")))+" – "+displayTime.format(parseTime.parse(rs.getString("EndTime"))));
                e.setInstructorName(rs.getString("Instructor"));
                classData.add(e);
            }
            tableClass.setItems(classData);
            colCourseName.setCellValueFactory(new PropertyValueFactory("CourseName"));
            colTime.setCellValueFactory(new PropertyValueFactory("Time"));
            colInstructor.setCellValueFactory(new PropertyValueFactory("InstructorName"));
            colAction.setCellFactory(new Callback<TableColumn<Enrollment, String>, TableCell<Enrollment, String>>() {
               @Override
                public TableCell<Enrollment, String> call(TableColumn<Enrollment, String> p) {
                   TableCell<Enrollment,String> cell = new TableCell<Enrollment,String>(){
                        @Override
                        public void updateItem(final String item, boolean empty) {
                            if(item!=null){
                               super.updateItem(item, empty);
                               combo = new ComboBox(classData);                                                      
                               combo.getSelectionModel().select(classData.indexOf(item));
                               setGraphic(combo);
                            } 
                            combo.valueProperty().addListener(new ChangeListener() {
                                @Override
                                public void changed(ObservableValue ov, Object t, Object t1) {
                                    
                                }
                            });
                        }
                   };
                   return cell;
               }
            });
            SettingsController.closeConnection();
        } catch (ClassNotFoundException | SQLException e) {
        }
    }
}
