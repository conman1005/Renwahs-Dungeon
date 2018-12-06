/*
 * Made By: Shawn Benedict
 * Date: Dec 5, 2018
 * Made to be a sword
 */
package cullity.renwahsdungeon;

import javafx.scene.image.Image;

/**
 *
 * @author shawnb58
 */
public class Sword extends Item {

    private double dMultiplier;//damage multiplier
    private char symbol;//in inventory string
    private Image image;
    private double price;

    public Sword() {
        super("sword", true);
        dMultiplier = 2;
        symbol = "s".charAt(0);
        image = new Image(getClass().getResource("/baseSword.png").toString());
        price = 10;
    }

    public Sword(String it, int m, char s, Image im, double p) {
        super(it, true);//true because sword is weapoon
        dMultiplier = m;
        symbol = s;
        image = new Image(getClass().getResource("/" + im + ".png").toString());
        price = p;

    }

    public double getDMultiplier() {
        return dMultiplier;
    }

    public Image getImage() {
        return image;
    }

    public double getPrice() {
        return price;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setDMultiplier(double m) {
        dMultiplier = m;

    }

    public void setImage(String im) {
        image = new Image(getClass().getResource("/" + im + ".png").toString());
    }

    public void setPrice(double p) {
        price = p;
    }

    public void setSymbol(char s) {
        symbol = s;
    }
}
