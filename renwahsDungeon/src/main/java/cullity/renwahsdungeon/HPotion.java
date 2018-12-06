/*
 * Made By: Shawn Benedict
 * Date: Dec 5, 2018
 * Made to be a heath potion item
 */
package cullity.renwahsdungeon;
//make datat class just part of person and delete data class

import javafx.scene.image.Image;

/**
 *
 * @author shawnb58
 */
public class HPotion extends Item {

    private double extraHealth;//health added when used

    private Image image;
    private double price;

    public HPotion() {
        super("hPotion", false, "h".charAt(0), "hPotion");
        extraHealth = 10;
        price = 5;
    }

    public HPotion(String it, int h, char s, Image im, double p) {
        super(it, false, s, "hPotion");//true because sword is weapoon
        extraHealth = h;
        price = p;

    }

    public double getExtraHealth() {
        return extraHealth;
    }

   

    public double getPrice() {
        return price;
    }

    public void setExtraHealth(double h) {
        extraHealth = h;

    }

    

    public void setPrice(double p) {
        price = p;
    }

}



