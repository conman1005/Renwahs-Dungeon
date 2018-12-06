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
        super("hPotion", false, "h".charAt(0));
        extraHealth = 10;

        image = new Image(getClass().getResource("/hPotion.png").toString());
        price = 5;
    }

    public HPotion(String it, int h, char s, Image im, double p) {
        super(it, false, s);//true because sword is weapoon
        extraHealth = h;
        image = new Image(getClass().getResource("/" + im + ".png").toString());
        price = p;

    }

    public double getExtraHealth() {
        return extraHealth;
    }

    public Image getImage() {
        return image;
    }

    public double getPrice() {
        return price;
    }

    public void setExtraHealth(double h) {
        extraHealth = h;

    }

    public void setImage(String im) {
        image = new Image(getClass().getResource("/" + im + ".png").toString());
    }

    public void setPrice(double p) {
        price = p;
    }

}
