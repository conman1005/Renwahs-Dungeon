/*
 * Made By: Shawn Benedict
 * Date: Dec 5, 2018
 * Made to be an item class
 */
package cullity.renwahsdungeon;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

/**
 *
 * @author shawnb58
 */
public class Item {//maybe make symbol letters for items and numbers for weapons

    private String itemName;
    private boolean weapon;//if it is a weapon or not
    private char symbol;//in inventory string set and gets
    private ImagePattern imageP;
    private double price;

    public Item() {
        itemName = "";
        imageP = null;
        weapon = false;
        symbol = "".charAt(0);
        price = 0;
    }

    public Item(String i, boolean w, char s, String im, double p) {
        itemName = i;
        weapon = w;
        symbol = s;
        imageP = new ImagePattern(new Image(getClass().getResource("/" + im + ".png").toString()));
        price = p;
    }

    public ImagePattern getImageP() {
        return imageP;
    }

    public void setImageP(String im) {
        imageP = new ImagePattern(new Image(getClass().getResource("/" + im + ".png").toString()));
    }

    public String getItemName() {
        return itemName;
    }

    public boolean isWeapon() {
        return weapon;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setItemName(String i) {
        itemName = i;
    }

    public void setWeapon(boolean w) {
        weapon = w;
    }

    public void setSymbol(char s) {
        symbol = s;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double p) {
        price = p;
    }
}
