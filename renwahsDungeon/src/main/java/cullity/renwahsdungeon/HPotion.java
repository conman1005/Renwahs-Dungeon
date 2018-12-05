/*
 * Made By: Shawn Benedict
 * Date: Dec 5, 2018
 * Made to
 */
package cullity.renwahsdungeon;
//make datat class just part of person and delete data class

import javafx.scene.image.Image;

/**
 *
 * @author shawnb58
 */
public class HPotion extends Item {

    private double extraHealth;//damage multiplier
    private char symbol;//in inventory string
    private Image image;
    private double price;

    public HPotion() {
        super("hPotion", false);
        extraHealth = 10;
        symbol = "h".charAt(0);
        image = new Image(getClass().getResource("/hPotion.png").toString());
        price = 5;
    }

    public HPotion(String it, int h, char s, Image im, double p) {
        super(it, false);//true because sword is weapoon
        extraHealth = s;
        symbol = s;
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

    public char getSymbol() {
        return symbol;
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

    public void setSymbol(char s) {
        symbol = s;
    }
}
