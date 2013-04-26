/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.eyecandy;

import com.mjwtech.main.controller.MainController;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.effect.GaussianBlur;
import javafx.util.Duration;

public class Fade {
    public static GaussianBlur gb = new GaussianBlur(0.0);
    public void FadeOut(){
        Timeline tl = new Timeline();
        final KeyValue kv = new KeyValue(gb.radiusProperty(), 15.0);
        final KeyFrame kf = new KeyFrame(Duration.seconds(.5), kv);
        final KeyValue kv2 = new KeyValue(MainController.ProgressGroup.opacityProperty(), 1);
        final KeyFrame kf2 = new KeyFrame(Duration.seconds(.5), kv2);
        tl.getKeyFrames().addAll(kf,kf2);
        tl.play();
    }
    public void FadeIn(){
        Timeline tl = new Timeline();
        final KeyValue kv = new KeyValue(gb.radiusProperty(), 0.0);
        final KeyFrame kf = new KeyFrame(Duration.seconds(.5), kv);
        final KeyValue kv2 = new KeyValue(MainController.ProgressGroup.opacityProperty(), 0);
        final KeyFrame kf2 = new KeyFrame(Duration.seconds(.5), kv2);
        tl.getKeyFrames().addAll(kf,kf2);
        tl.play();
        tl.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                MainController.lblProgressStatus.setText("PROCESSING");
            }
        });
    }
}
