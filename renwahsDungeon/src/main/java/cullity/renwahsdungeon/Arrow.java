/*
 * Made By: Shawn Benedict
 * Date: Jan 11, 2019
 * Made to
 */
package cullity.renwahsdungeon;

import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
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
    private ArrayList<Polygon> obstacles = new ArrayList();//rocks/walls/etc on the screen
//note, might need an enemy arraylist that constantly updates during shoot timer

    public Arrow() {
        super(15, 30, 30, 35);

        imageP = new ImagePattern(new Image(getClass().getResource("/arrow.png").toString()));
        this.setFill(imageP);
        d = 0;
        obstacles = null;
    }

    public Arrow(ArrayList<Polygon> wallEtc) {
        super(15, 30, 30, 35);
        obstacles.addAll(wallEtc);
        imageP = new ImagePattern(new Image(getClass().getResource("/arrow.png").toString()));
        this.setFill(imageP);
    }

    public Arrow(String im) {
        super(15, 30, 30, 35);
        imageP = new ImagePattern(new Image(getClass().getResource("/" + im + ".png").toString()));
        this.setFill(imageP);
    }

    public void shoot(int dir, int x, int y) {//d is direction//x and y of user 
//note, set x and y of user

        MainApp.currentA.getChildren().add(this);

        d = dir;
        Timeline t = new Timeline(new KeyFrame(Duration.millis(500), ae -> shootForTimer()));
        t.play();
    }

    private boolean checkCol(Shape obj1, Shape obj2) {
        Shape intersect = Shape.intersect(obj1, obj2);
        return intersect.getBoundsInParent().getWidth() > 0;
    }

    private void shootForTimer() {
        //check if colliding with anything
        for (Polygon o : obstacles) {
            if (checkCol(this, o)) {
                MainApp.currentA.getChildren().remove(this);
            }
        }

        //check if hitting enemy and attacking
        //move
        int x = 0;
        int y = 0;
        if (d == 1) {
            x = 0;
            y = 5;
            this.setRotate(90);
        } else if (d == 2) {
            x = 5;
            y = 0;
            this.setRotate(0);
        } else if (d == 3) {
            x = 0;
            y = -5;
            this.setRotate(270);
        } else if (d == 4) {
            x = -5;
            y = 0;
            this.setRotate(180);
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

    public void setD(int dir) {
        d = dir;
    }

    public int getD() {
        return d;
    }

    public void setObstacles(ArrayList obs) {
        obstacles.clear();
        obstacles.addAll(obs);
    }

    public ArrayList getObstacles() {
        return obstacles;
    }
}
