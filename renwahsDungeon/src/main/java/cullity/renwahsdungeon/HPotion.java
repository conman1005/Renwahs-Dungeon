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

    public HPotion() {
        super("Health Potion", false, "h".charAt(0), "hPotion", 10);
        extraHealth = 10;

    }

    public HPotion(String it, int h, char s, Image im, double p) {
        super(it, false, s, "hPotion", p);//true because sword is weapoon
        extraHealth = h;

    }

    public double getExtraHealth() {
        return extraHealth;
    }

    public void setExtraHealth(double h) {
        extraHealth = h;

    }

}
