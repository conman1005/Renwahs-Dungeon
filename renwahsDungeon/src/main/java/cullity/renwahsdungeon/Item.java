/*
 * Made By: Shawn Benedict
 * Date: Dec 5, 2018
 * Made to be an item class
 */
package cullity.renwahsdungeon;

import javafx.scene.image.Image;

/**
 *
 * @author shawnb58
 */
public class Item {//maybe make symbol letters for items and numbers for weapons

    private String itemName;
    private boolean weapon;//if it is a weapon or not
    private char symbol;//in inventory string set and gets
    private Image img;

    public Item() {
        itemName = "";
        img = null;
        weapon = false;
        symbol = "".charAt(0);
    }

    public Item(String i, boolean w, char s, String im) {
        itemName = i;
        weapon = w;
        symbol = s;
        img = new Image(getClass().getResource("/" + im + ".png").toString());
    }

    public Image getImage() {
        return img;
    }

    public void setImage(String im) {
        img = new Image(getClass().getResource("/" + im + ".png").toString());
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

}



