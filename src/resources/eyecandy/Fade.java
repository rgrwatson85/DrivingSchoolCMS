/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.eyecandy;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.effect.GaussianBlur;
import javafx.util.Duration;

public class Fade {
    public static Timeline tl = new Timeline();
    public static GaussianBlur gb = new GaussianBlur(0.0);
    public void FadeOut(){
        final KeyValue kv = new KeyValue(gb.radiusProperty(), 15.0);
        final KeyFrame kf = new KeyFrame(Duration.seconds(5), kv);
        tl.getKeyFrames().add(kf);
        tl.play();
    }
    public void FadeIn(){
        final KeyValue kv = new KeyValue(gb.radiusProperty(), 0.0);
        final KeyFrame kf = new KeyFrame(Duration.seconds(5), kv);
        tl.getKeyFrames().add(kf);
        tl.play();
    }
}
