/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mjwtech.main.model;

import data.database_connection.dbconnection;
import insidefx.undecorator.Undecorator;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
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
        Parent p = FXMLLoader.load(getClass().getResource("/com/mjwtech/main/view/Main.fxml"));
        Undecorator undecorator = new Undecorator(stage, (Region) p);
        Node node = p.lookup("#root");
        undecorator.setAsStageDraggable(stage, node);
        undecorator.getStylesheets().add("resources/css/undecorator.css");

        Scene scene = new Scene(undecorator);
        
        // Transparent scene and stage
        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setMaxWidth(1280);
        stage.setMinWidth(1280);
        stage.setMaxHeight(775);
        stage.setMinHeight(775);
        stage.setOnShown(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                dbconnection.getInstance();
            }
        });
        stage.show();
    }
}
