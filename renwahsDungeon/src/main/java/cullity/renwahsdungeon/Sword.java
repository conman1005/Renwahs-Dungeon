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
    private Image image;
    private double price;

    public Sword() {
        super("sword", true, "s".charAt(0),"baseSword");
        dMultiplier = 2;
        price = 10;
    }

    public Sword(String it, int m, char s, Image im, double p) {
        super(it, true, s,"baseSword");//true because sword is weapoon
        dMultiplier = m;
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

    public void setDMultiplier(double m) {
        dMultiplier = m;

    }

    public void setImage(String im) {
        image = new Image(getClass().getResource("/" + im + ".png").toString());
    }

    public void setPrice(double p) {
        price = p;
    }

}
