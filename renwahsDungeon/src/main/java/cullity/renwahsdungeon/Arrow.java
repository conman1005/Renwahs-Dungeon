/*
 * Made By: Shawn Benedict
 * Date: Jan 11, 2019
 * Made to
 */
package cullity.renwahsdungeon;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 *
 * @author shawnb58
 */
public class Arrow extends Rectangle {

    private ImagePattern imageP;
    private int d;//direction 
//1=up
//2=right
//3=down
//4=left

    public Arrow() {
        super(15, 30, 30, 35);
        imageP = new ImagePattern(new Image(getClass().getResource("/arrow.png").toString()));
        this.setFill(imageP);
    }

    public Arrow(String im) {
        imageP = new ImagePattern(new Image(getClass().getResource("/" + im + ".png").toString()));
        this.setFill(imageP);
    }

    public void shoot(int dir) {//d is direction
        if (MainApp.currentA.toString().equalsIgnoreCase("ancTown")) {//no shooting arrows in town
            Alert al = new Alert(Alert.AlertType.CONFIRMATION);
            al.setTitle("Input not allowed");
            al.setHeaderText("shooting arrtows in this town is against the law");
            al.setContentText(null);
            al.showAndWait();
            return;
        }
        MainApp.currentA.getChildren().add(this);
        d = dir;
        Timeline t = new Timeline(new KeyFrame(Duration.millis(500), ae -> shootForTimer()));
        t.play();
    }

    private void shootForTimer() {
        //check if colliding with anything

        int x = 0;
        int y = 0;
        if (d == 1) {
            x = 0;
            y = 5;
        } else if (d == 2) {
            x = 5;
            y = 0;
        } else if (d == 3) {
            x = 0;
            y = -5;
        } else if (d == 4) {
            x = -5;
            y = 0;
        }
        this.setTranslateX(this.getTranslateX() + x);
        this.setTranslateY(this.getTranslateY() - y);
    }

    public void setImageP(String im) {
        imageP = new ImagePattern(new Image(getClass().getResource("/" + im + ".png").toString()));
        this.setFill(imageP);
    }

    public ImagePattern getImageP() {
        return imageP;
    }
}
