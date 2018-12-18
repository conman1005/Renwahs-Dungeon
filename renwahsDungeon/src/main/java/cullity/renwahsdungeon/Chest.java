/*
 * Made By: Shawn Benedict
 * Date: Dec 17, 2018
 * Made to
 */
package cullity.renwahsdungeon;

import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import javafx.scene.control.TextInputDialog;
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

    public void changeItems() {//to choose which items they will keep
        ArrayList choices = new ArrayList();
        for (int i = 0; i < MainApp.inv.size(); i++) {
            choices.addAll(MainApp.inv);
            choices.addAll(gains);
        }
        String choiceString = "";
        for (int i = 0; i < choices.size(); i++) {
            choiceString += "\n" + (i + 1) + choices.get(i).getClass().getSimpleName();

        }
        TextInputDialog dialog = new TextInputDialog("123456");
        dialog.setTitle("Choose Which Items to Keep");
        dialog.setHeaderText("Type in the corresponding number to the item(s) \n you would like in your inventory (maximum of six) \n *No spaces ");//might need to make easier to understand
        dialog.setContentText(choiceString);

// Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        String chosen = "";
        if (!result.isPresent()) {
            chosen = "123456";
        } else {
            if (result.get().length() > 6) {//too many items chosen
                //stuff
            } else {
                chosen = result.get();

            }
        }
        MainApp.inv.clear();
        for (int i = 0; i < chosen.length(); i++) {//try for number format
            //put next line in presentation
            char c;
            try {
                c = ((Item) choices.get((Integer.parseInt(chosen.substring(i, 1))) - 1)).getSymbol();//gets the symbol of the class of the element in the array they selected with the dialog at the given substring

                if ("s".charAt(0) == c) {
                    MainApp.inv.add(new Sword());
                } else if ("h".charAt(0) == c) {
                    MainApp.inv.add(new HPotion());
                }
            } catch (NumberFormatException numberFormatException) {
            }
        }

// The Java 8 way to get the response value (with lambda expression).
        result.ifPresent(name -> System.out.println("Your name: " + name));
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
