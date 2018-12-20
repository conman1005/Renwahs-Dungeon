/*
 * Made By: Shawn Benedict
 * Date: Dec 5, 2018
 * Made to be an enemy class
 */
package cullity.renwahsdungeon;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author shawnb58
 */
public class Enemy extends Rectangle{

    private double strength;
    private double health;
    //maybe add in a string ability for like slowing down the character
    //private boolean employed;
    private String type;//class
    private double defense;
    private ImagePattern imgP;
    //private Rectangle recEnemy;

    public Enemy() {
        super();
        strength = 10;
        health = 100;
        defense = 10;
        type = "slime";
        imgP = new ImagePattern(new Image(getClass().getResource("/sprites/slimeGreen.png").toString()));
        /*recEnemy.setFill(imgP);
        recEnemy.setWidth(35);
        recEnemy.setHeight(30);*/
    }

    public Enemy(double s, double h, double d, String t, String im, double l, double w, double ex, double ey) {
        super();
        //recEnemy = new Rectangle(l, w, ex, ey);
        strength = s;
        health = h;
        defense = d;
        type = t;
        imgP = new ImagePattern(new Image(getClass().getResource("/" + im + ".png").toString()));
        //recEnemy.setFill(imgP);
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

    public void setImageP(String im) {
        imgP = new ImagePattern(new Image(getClass().getResource("/" + im + ".png").toString()));
    }

    public Rectangle getEnemy(int x,int y) {
        Rectangle rec = new Rectangle(35,30,x,y);
        
        rec.setFill(imgP);
        return rec;
    }
}
