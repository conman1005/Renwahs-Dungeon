/*
 * Made By: Shawn Benedict
 * Date: Dec 5, 2018
 * Made to be an enemy class
 */
package cullity.renwahsdungeon;

import javafx.scene.image.Image;

/**
 *
 * @author shawnb58
 */
public class Enemy {

    private double strength;
    private double health;
    //maybe add in a string ability for like slowing down the character
    //private boolean employed;
    private String type;//class
    private double defense;
    private Image img;

    public Enemy() {
        strength = 10;
        health = 100;
        defense = 10;
        type = "slime";
        img = null;//change to enemy image

    }

    public Enemy(double s, double h, double d, String t, String im) {
        strength = s;
        health = h;
        defense = d;
        type = t;
        img = new Image(getClass().getResource("/" + im + ".png").toString());

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

    public Image getImage() {
        return img;
    }

    public void setImage(String im) {
        img = new Image(getClass().getResource("/" + im + ".png").toString());
    }

}


