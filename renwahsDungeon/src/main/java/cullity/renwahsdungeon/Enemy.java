/*
 * Made By: Shawn Benedict
 * Date: Dec 5, 2018
 * Made to
 */
package cullity.renwahsdungeon;

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
    

    public Enemy() {
        strength = 10;
        health = 100;
        defense = 10;
        type = "slime";
       

    }

    public Enemy(double s,double h, double d, String t) {
        strength = s;
        health = h;
        defense = d;
        type = t; 
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
        strength=b;
    }

    public void setHealth(double h) {
        health = h;
    }

    public void setDefense(double d) {
        defense=d;
    }

    public void setType(String t) {
        type = t;
    }
}
