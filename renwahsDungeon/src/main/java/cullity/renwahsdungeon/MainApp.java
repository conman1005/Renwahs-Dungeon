//made by: Shawn and Conner
//made on: 12/4/18
//made to: Be an RPG Where the player goes into a dungeon and fights slimes
package cullity.renwahsdungeon;

import java.util.ArrayList;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
//make inv when they push play//put delete items and show items in all scenes

public class MainApp extends Application {
//static allows to use a global object without creating it or something similar

    public static ArrayList<Item> inv = new ArrayList();
    public static ArrayList<Rectangle> slot = new ArrayList();//item slots//add this in every initialize
    public static Item currentI;//current item selected
    public static Person currentP=new Person(); //current user/save file
    //public static Enemy currentE;//current enemy fighting the user
    public static int itSpot = 0;//spot in item arraylist
    public static Scene currentS;//current scene//probably not needed
    //public static boolean fighting;//if in combat
    public static double currentHealth = 30;//currenthealth of user
    public static boolean paused = false;//if paused then true
    public static String townLocation = "";//used to know where in the town the user left so that they can eb put back in the same place when returning to town
    public static AnchorPane currentA = null;
    public static Rectangle recItem;
    public static int currentL; //current level of the cave path the user is on
    
    public static Stage mainStage;
    public static int recordNum;//spot in the random access file of currentP
    public static ArrayList<Arrow> arrows = new ArrayList();//array of arrows in cavePath//must be global so that it can be calledfrom the object of arrow

    public static boolean caveMusic = false;
    public static MediaPlayer caveSong;
    public static MediaPlayer townSong;
    
    public static void deleteItem() {//put in 
        inv.remove(currentI);

        String newI = "";//new inventory
        for (int i = 0; i < inv.size(); i++) {
            newI += inv.get(i);
        }
        currentP.setInventory(newI);
        if (inv.size() > 0) {
            currentI = inv.get(0);//change to spot if that gets put in

        }
        //else {
//            currentI = null;
//        }
        itSpot = 5;//change later to not make it always go to the beginning again
        showItems();
        //if there is a spot variable for item then change it here
    }

    public static void getItemsFromData(String inven) {//database
        inv.clear();
        //add all items
        for (int i = 0; i < inven.length(); i++) {
            //put items in arraylist inv
            try {
                if (inven.substring(i, i + 1).equals("s")) {
                    inv.add(new Sword());
                } else if (inven.substring(i, i + 1).equals("h")) {
                    inv.add(new HPotion());
                } else if (inven.substring(i, i + 1).equals("b")) {
                    inv.add(new Bow());
                }
            } catch (IndexOutOfBoundsException e) {
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
                if (!currentP.getInventory().substring(i, i + 1).equals("!")) {
                    slot.get(i).setFill(inv.get(i).getImageP());
                }

            } catch (IndexOutOfBoundsException e) {
            }
        }
        slot.get(itSpot).setEffect(colorAdjust1);
        if (inv.size() <= itSpot) {
            currentI = null;
            recItem.setVisible(false);
        } else {
            currentI = inv.get(itSpot);
            recItem.setVisible(true);
            recItem.setFill(currentI.getImageP());
        }

    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
        Scene scene = new Scene(root);
        mainStage = stage;
        stage.setTitle("Renwah's Dungeon");
        stage.setScene(scene);
        scene.getRoot().requestFocus();
        currentS = scene;
        stage.show();
        stage.setOnCloseRequest(e -> System.exit(0));
        caveSong = new MediaPlayer((new Media(getClass().getResource("/Vampire_Underground_Drum_and_Bass_Remix.mp3").toString())));
        caveSong.setCycleCount(MediaPlayer.INDEFINITE);
        caveSong.setVolume(0.25);
        townSong = new MediaPlayer((new Media(getClass().getResource("/Lowly_Tavern_Bard_II.mp3").toString())));
        townSong.setCycleCount(MediaPlayer.INDEFINITE);
        townSong.setVolume(0.25);
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
