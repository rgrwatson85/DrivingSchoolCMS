/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mjwtech;

import data.database_connection.dbconnection;
import insidefx.undecorator.Undecorator;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

/**
 *
 * @author mrgnwatson
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(this.getClass().getResource("Main.fxml"));
       
        Undecorator undecorator = new Undecorator(stage, root);
        Node node = root.lookup("#root");
        undecorator.setAsStageDraggable(stage, node);
        undecorator.getStylesheets().add("resources/css/undecorator.css");
        
        Scene scene = new Scene(undecorator);

        // Transparent scene and stage
        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setWidth(1251);
        stage.setMinWidth(1251);
        stage.setMinHeight(733);
        stage.setMaxWidth(1600);
        stage.setMaxHeight(1200);
        stage.setOnShown(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                dbconnection.getInstance();
            }
        });
        stage.show();
    }

}
