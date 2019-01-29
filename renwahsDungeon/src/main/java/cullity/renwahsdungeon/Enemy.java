/*
 * Made By: Shawn Benedict
 * Date: Dec 5, 2018
 * Made to be an enemy class
 */
package cullity.renwahsdungeon;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author shawnb58
 */

//change to pane later
public class Enemy extends Rectangle {

    private double strength;
    private double health;
    //maybe add in a string ability for like slowing down the character
    //private boolean employed;
    private String type;//class
    private double defense;
    private ImagePattern imgP;
    //private Rectangle recEnemy;
    private String direction;
    
    private int directionTime = 0;

    public Enemy() {
        super(50, 50, 35, 30);
        strength = 10;
        health = 100;
        defense = 10;
        type = "slime";
        imgP = new ImagePattern(new Image(getClass().getResource("/sprites/slimeGreen.png").toString()));
        //recEnemy = new Rectangle();
        //recEnemy.setFill(imgP);
        this.setFill(imgP);
    }

    public Enemy(/*  double d, String t, */double h,double s,String im, double l, double w, double ex, double ey, String di) {
        super(ex, ey, l, w);
        //recEnemy = new Rectangle();
        strength = s;
        health = h;
       /* defense = d;
        type = t;*/
        imgP = new ImagePattern(new Image(getClass().getResource("/" + im + ".png").toString()));
        this.setFill(imgP);
        direction = di;
    }

    public double getStrength() {
        return strength;
    }

    public double getHealth() {
        return health;
    }

    public double getDefense() {
        return defense;
    }

    public String getType() {
        return type;
    }

    public void setStrength(double b) {
        strength = b;
    }

    public void setHealth(double h) {
        health = h;
    }

    public void setDefense(double d) {
        defense = d;
    }

    public void setType(String t) {
        type = t;
    }

    public ImagePattern getImageP() {
        return imgP;
    }
    
    public void setDirection(String d) {
        direction = d;
    }
    
    public String getDirection() {
        return direction;
    }
    
    public void setDirectionTime(int dt) {
        directionTime = dt;
    }
    public int getDirectionTime() {
        return directionTime;
    }

    public void setImageP(String im) {
        imgP = new ImagePattern(new Image(getClass().getResource("/" + im + ".png").toString()));
    }
}
