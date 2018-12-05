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
public class Item {

    private String itemName;
    private boolean weapon;//if it is a weapon or not

    public Item() {
        itemName = "";
        weapon = false;

    }

    public Item(String i, boolean w) {
        itemName = i;
        weapon = w;

    }

    public String getItemName() {
        return itemName;
    }

    public boolean isWeapon() {
        return weapon;
    }

    public void setItemName(String i) {
        itemName = i;
    }

    public void setWeapon(boolean w) {
        weapon = w;
    }

}
