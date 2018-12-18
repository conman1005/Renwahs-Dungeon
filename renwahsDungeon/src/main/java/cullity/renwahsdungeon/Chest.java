/*
 * Made By: Shawn Benedict
 * Date: Dec 17, 2018
 * Made to
 */
package cullity.renwahsdungeon;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import javafx.scene.image.Image;

/**
 *
 * @author shawnb58
 */
public class Chest {

    ArrayList<Item> gains = new ArrayList();//arraylist of items they win//gained items
    int rand;//random between 1 and 3//used for size of arraylist and which items get added
    int extraCoins;
    Item itm1 = null;
    Item itm2 = null;
    Item itm3 = null;
    Image img;//picture of chest

    public Chest() {
        rand = ThreadLocalRandom.current().nextInt(1, 3 + 1);//size of gains arraylist
        for (int i = 0; i < rand; i++) {
            rand = ThreadLocalRandom.current().nextInt(0, 2 + 1);//max is the amount of items we have
            if (rand == 0) {
                gains.add(new Sword());
            } else if (rand == 1) {
                gains.add(new HPotion());
            }
        }
        itm1 = gains.get(0);

        try {//if size is one then there wont be a second and third item
            itm2 = gains.get(1);
            itm3 = gains.get(2);
        } catch (Exception e) {
        }
        extraCoins = ThreadLocalRandom.current().nextInt(100, 500 + 1);//extra coins
        img = img = new Image(getClass().getResource("/" + "chest" + ".png").toString());;
    }

    public Chest(int s, int c, String im) {//size, coins, image 
        rand = s;//size of gains arraylist
        for (int i = 0; i < rand; i++) {
            rand = ThreadLocalRandom.current().nextInt(0, 2 + 1);//max is the amount of items we have
            if (rand == 0) {
                gains.add(new Sword());
            } else if (rand == 1) {
                gains.add(new HPotion());
            }
        }
        itm1 = gains.get(0);

        try {//if size is one then there wont be a second and third item
            itm2 = gains.get(1);
            itm3 = gains.get(2);
        } catch (Exception e) {
        }
        extraCoins = c;//extra coins
        img = img = new Image(getClass().getResource("/" + im + ".png").toString());;
    }

    public Image getImage() {
        return img;
    }

    public void setImage(String im) {
        img = new Image(getClass().getResource("/" + im + ".png").toString());
    }

    public int getExtraCoins() {
        return extraCoins;
    }

    public void setExtraCoins(int c) {
        extraCoins = c;
    }

    public Item getItm1() {
        return itm1;
    }

    public void setItm1(Item iOne) {
        itm1 = iOne;
        try {
            gains.set(0, itm2);
        } catch (IndexOutOfBoundsException e) {
        }
    }

    public Item getItm2() {
        return itm2;
    }

    public void setItm2(Item iTwo) {
        itm2 = iTwo;
        try {
            gains.set(1, itm2);
        } catch (IndexOutOfBoundsException e) {
        }
    }

    public Item getItm3() {
        return itm3;
    }

    public void setItm3(Item iThree) {
        itm3 = iThree;
        try {
            gains.set(2, itm3);
        } catch (IndexOutOfBoundsException e) {
        }
    }

    public int getSize() {//return size of arraylist
        return gains.size();
    }

    public void clear() {//clear Arraylist
        gains.clear();
        itm1 = null;
        itm2 = null;
        itm3 = null;
    }
}
