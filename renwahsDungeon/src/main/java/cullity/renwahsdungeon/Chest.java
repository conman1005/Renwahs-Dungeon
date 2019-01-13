/*
 * Made By: Shawn Benedict
 * Date: Dec 17, 2018
 * Made to
 */
package cullity.renwahsdungeon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;

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
    ImagePattern imageP;//picture of chest

    public Chest() {
        int sizeofGains = ThreadLocalRandom.current().nextInt(1, 3 + 1);//size of gains arraylist
        for (int i = 0; i < sizeofGains; i++) {
            rand = ThreadLocalRandom.current().nextInt(0, 2 + 1);//max is the amount of items we have
            if (rand == 0) {
                gains.add(new Sword());
            } else if (rand == 1) {
                gains.add(new HPotion());
            } else if (rand == 2) {
                gains.add(new Bow());
            }
        }
        itm1 = gains.get(0);

        try {//if size is one then there wont be a second and third item
            itm2 = gains.get(1);
            itm3 = gains.get(2);
        } catch (Exception e) {
        }
        extraCoins = ThreadLocalRandom.current().nextInt(100, 500 + 1);//extra coins
        imageP = new ImagePattern(new Image(getClass().getResource("/" + "chest" + ".png").toString()));
    }

    public Chest(int s, int c, String im) {//size, coins, image 
        rand = s;//size of gains arraylist
        for (int i = 0; i < rand; i++) {
            rand = ThreadLocalRandom.current().nextInt(0, 2 + 1);//max is the amount of items we have
            if (rand == 0) {
                gains.add(new Sword());
            } else if (rand == 1) {
                gains.add(new HPotion());
            } else if (rand == 2) {
                gains.add(new Bow());
            }
        }
        itm1 = gains.get(0);

        try {//if size is one then there wont be a second and third item
            itm2 = gains.get(1);
            itm3 = gains.get(2);
        } catch (Exception e) {
        }
        extraCoins = c;//extra coins
        imageP = new ImagePattern(new Image(getClass().getResource("/" + im + ".png").toString()));
    }

    public void open() throws ClassNotFoundException, IllegalAccessException {//to choose which items they will keep by opening the chest
        ArrayList<Item> choices = new ArrayList();//items in the chest
//        for (int i = 0; i < MainApp.inv.size(); i++) {
        choices.addAll(MainApp.inv);
        choices.addAll(gains);
//        }
        String choiceString = "Current Inventory Includes:";//will be the choices they have for items//includes inventory and chest stuff
        int nextNum = 1;//starts at 1 so that the i9frst choice for the user is 1
        for (int i = 0; i < MainApp.inv.size(); i++) {//this loop will add the names of the items in the current inventory to the choices string

            choiceString += "\n" + (i + 1) + " " + MainApp.inv.get(i).getClass().getSimpleName();//first spot for user to choose from will be one so I added 1 to the index
            nextNum++;
        }
        choiceString += "\n Items in chest includes:";
        for (int i = MainApp.inv.size(); i < choices.size(); i++) {//this loop adds the items in the chest to the choices string

            choiceString += "\n" + nextNum + " " + choices.get(i).getClass().getSimpleName();
            nextNum++;
        }
        String newIn = "";
        if (choices.size() > 6) {
            TextInputDialog dialog = new TextInputDialog("123456");
            dialog.setTitle("Choose Which Items to Keep ");
            dialog.setHeaderText("Type in the corresponding number to the item(s) \n you would like in your inventory (maximum of six)\n (Be sure to put in the correct values or you might not get the items you wanted) \n *No spaces ");//might need to make easier to understand
            dialog.setContentText(choiceString);

            Optional<String> result = dialog.showAndWait();
            String chosen;
            if (!result.isPresent()) {//if they cancel
                Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setTitle("Error");
                a.setHeaderText("Must input a valid answer to continue");
                a.setContentText("Please exit this message to try again");
                a.showAndWait();
                open();
                return;
            } else {
                if (result.get().length() > 6) {//too many items chosen
                    //stuff
                    Alert al = new Alert(Alert.AlertType.CONFIRMATION);
                    al.setTitle("Error");
                    al.setHeaderText("Maximum of 6 items can be inputed");
                    al.setContentText("Please exit this message to try again");
                    al.showAndWait();
                    open();
                    return;

                } else if (result.get().isBlank()) {
                    Alert al = new Alert(Alert.AlertType.CONFIRMATION);

                    al.setTitle("Error");
                    al.setHeaderText("Must enter in at least one corresponding number");
                    al.setContentText("Please exit this message to try again");
                    al.showAndWait();
                    open();
                    return;
                } else {
                    try {
                        Integer.parseInt(result.get());
                    } catch (NumberFormatException numberFormatException) {
                        Alert al = new Alert(Alert.AlertType.CONFIRMATION);
                        al.setTitle("Error");
                        al.setHeaderText("Must be all numbers with no spaces");
                        al.setContentText("Please exit this message to try again");
                        al.showAndWait();
                        open();
                        return;
                    }
                    chosen = result.get();

                }
            }
            MainApp.inv.clear();

            for (int i = 0; i < chosen.length(); i++) {//try for number format

                try {
//put next line in presentation
                    newIn += ((Item) choices.get((Integer.parseInt(chosen.substring(i, i + 1))) - 1)).getSymbol();//putting together all the new items they chose
                    //Gson gson=new Gson();
                    //  c = ((Item) choices.get((Integer.parseInt(chosen.substring(i, i+1))) - 1));//gets the symbol of the class of the element in the array they selected with the dialog at the given substring
//
//                if ("s".charAt(0) == c) {
//                    MainApp.inv.add(new Sword());
//                } else if ("h".charAt(0) == c) {
//                    MainApp.inv.add(new HPotion());
//                }

                } catch (IndexOutOfBoundsException numberFormatException) {

                }
            }
        } else {

//loop for string newIn
            for (Item i : choices) {
                newIn += i.getSymbol();
            }
        }

        MainApp.getItemsFromData(newIn);//makes it so that the invenotry arraylist gets updated
        MainApp.currentP.setInventory(newIn);
        MainApp.itSpot = 0;
        MainApp.currentP.setCoins(MainApp.currentP.getCoins() + (extraCoins * MainApp.currentL));

        MainApp.currentL++;
        if (MainApp.currentL > MainApp.currentP.getHighestLevel()) {
            MainApp.currentP.setHighestLevel(MainApp.currentL);
        }
        MainApp.showItems();
        Alert al = new Alert(Alert.AlertType.CONFIRMATION);
        al.setTitle("Coins");
        al.setHeaderText("You received " + extraCoins * MainApp.currentL + " coins. \n You are now being sent to level " + MainApp.currentL);
        al.setContentText(null);
        al.showAndWait();
        //show money
        MainApp.currentP.save("database.raf", MainApp.recordNum);
//send to next level
        try {
            Parent path_parent = FXMLLoader.load(getClass().getResource("/fxml/cavePath.fxml")); //where FXMLPage2 is the name of the scene

            Scene path_scene = new Scene(path_parent);
            MainApp.currentS = path_scene;
            //get reference to the stage
            Stage stage = MainApp.mainStage;

            stage.hide(); //optional
            path_scene.getRoot().requestFocus();
            stage.setScene(path_scene); //puts the new scence in the stage

            //     stage.setTitle("Town"); //changes the title
            stage.show(); //shows the new page
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public ImagePattern getImageP() {
        return imageP;
    }

    public void setImage(String im) {
        imageP = new ImagePattern(new Image(getClass().getResource("/" + im + ".png").toString()));
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
