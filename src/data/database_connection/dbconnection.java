/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.database_connection;

import java.io.IOException;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import name.antonsmirnov.javafx.dialog.Dialog;
import com.mjwtech.main.controller.MainController;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.paint.Color;
import javafx.stage.WindowEvent;

/**
 *
 * @author mrgnwatson
 */
public class dbconnection {
    public static dbconnection instance = null;
    final Stage stage;
    
    private dbconnection() {
        int WIDTH=400;
        int HEIGHT=250;
        stage = new Stage(StageStyle.TRANSPARENT);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);
        stage.setResizable(false);
        Parent root = null;
        try {
            root = FXMLLoader.load(SettingsController.class.getResource("settings.fxml"));
        } catch (IOException ex) {
            Dialog.showError("ERROR", "Could not load settings.fxml\n\ndbconnection.java:50");
        }
        Scene scene = new Scene(root, Color.TRANSPARENT);
        stage.setScene(scene);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                stage.getScene().getWindow().hide();
            }
        });
        stage.show();
        MainController.root.setEffect(new GaussianBlur(10));
    }
    
    public static dbconnection getInstance() {
        if (instance != null) {
            return instance;
        } else {
            instance = new dbconnection();
        }
        return instance;
    }
}
