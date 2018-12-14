package cullity.renwahsdungeon;

import java.util.ArrayList;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
//make inv when they push play//put delete items and show items in all scenes

public class MainApp extends Application {
//static allows to use a global object without creating it or something similar

    public static ArrayList<Item> inv = new ArrayList();
    public static ArrayList<Rectangle> slot = new ArrayList();//item slots//add this in every initialize
    public static Item currentI;//current item selected
    public static Person currentP; //current user/save file
    public static Enemy currentE;//current enemy fighting the user
    public static int itSpot = 0;//spot in item arraylist
    public static Scene currentS;//current scene//probably not needed
    public static boolean fighting;//if in combat
    public static double currentHealth;
    public static boolean paused = false;
    public static AnchorPane currentA;

    public static void deleteItem() {//put in 
        inv.remove(currentI);

        String newI = "";//new inventory
        for (int i = 0; i < inv.size(); i++) {
            newI += inv.get(i);
        }
        currentP.setInventory(newI);
        if (inv.size() > 0) {
            currentI = inv.get(0);//change to spot if that gets put in

        } else {
            currentI = null;
        }
        itSpot = 0;//change later to not make it always go to the beginning again
        showItems();
        //if there is a spot variable for item then change it here
    }

    public static void getItemsFromData(String inven) {//database
        inv.clear();

        //add all items
        for (int i = 0; i < inven.length(); i++) {
            //put items in arraylist inv
            try {
                if (inven.substring(i, 1).equals("s")) {
                    inv.add(new Sword());
                } else if (inven.substring(i, 1).equals("h")) {
                    inv.add(new HPotion());
                }
            } catch (IndexOutOfBoundsException e) {
            }

        }
    }

    public static void keys(KeyEvent k) {
        if (k.getSource() == KeyCode.G && currentI != null) {
            //drop item
            deleteItem();

        }
        if (k.getSource() == KeyCode.DIGIT1) {
            //drop item
            itSpot = 0;
            showItems();
        }
        if (k.getSource() == KeyCode.DIGIT2) {
            //drop item
            itSpot = 1;
            showItems();
        }
        if (k.getSource() == KeyCode.DIGIT3) {
            //drop item
            itSpot = 2;
            showItems();
        }
        if (k.getSource() == KeyCode.DIGIT4) {
            //drop item
            itSpot = 3;
            showItems();
        }
        if (k.getSource() == KeyCode.DIGIT5) {
            //drop item
            itSpot = 4;
            showItems();
        }
        if (k.getSource() == KeyCode.DIGIT6) {
            //drop item
            itSpot = 5;
            showItems();
        }
        if (k.getSource() == KeyCode.ESCAPE) {
            //pause or play
            
            if (paused) {
                paused = false;

                //get rid of menu
            } else {
                paused = true;
                //show menu
            }
            if (currentA!=null){//make sure it doesnt error on menu
                currentA.setDisable(paused);

            }
        }
    }

    public static void scrollI(ScrollEvent m) {//scroll through Items on screen
        if (m.getDeltaY() > 0) {

            if (itSpot < 5) {
                itSpot++;
            } else {
                itSpot = 0;
            }
        } else if (m.getDeltaY() < 0) {

            if (itSpot > 0) {
                itSpot--;
            } else {
                itSpot = 5;
            }
        }
        showItems();

    }

    public static void showItems() {//put in all scenes
        //show items in the boxes
        ImagePattern im;
        ColorAdjust colorAdjust1 = new ColorAdjust();//shows it was selected
        colorAdjust1.setBrightness(-0.5);
        for (int r = 0; r < slot.size(); r++) {//reset slots
            try {

                slot.get(r).setFill(Color.web("#393b53"));
                slot.get(r).setEffect(null);
            } catch (IndexOutOfBoundsException e) {
            }

        }
        for (int i = 0; i < currentP.getInventory().length(); i++) {
            try {//might not need try catch
                if (!currentP.getInventory().substring(i, 1).equals("!")) {
                    im = new ImagePattern(inv.get(i).getImage());
                    slot.get(i).setFill(im);
                }

            } catch (IndexOutOfBoundsException e) {
            }
        }
        slot.get(itSpot).setEffect(colorAdjust1);
        if (inv.size() <= itSpot) {
            currentI = null;
        } else {

            currentI = inv.get(itSpot);

        }

    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Renwah's Dungeon");
        stage.setScene(scene);
        scene.getRoot().requestFocus();
        currentS = scene;
        stage.show();
        stage.setOnCloseRequest(e -> System.exit(0));
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
