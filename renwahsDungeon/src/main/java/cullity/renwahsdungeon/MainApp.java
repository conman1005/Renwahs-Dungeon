package cullity.renwahsdungeon;

import java.util.ArrayList;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
//make inv when they push play//put delete items and show items in all scenes

public class MainApp extends Application {

    public ArrayList<Item> inv = new ArrayList();
    public static ArrayList<Rectangle> slot = new ArrayList();//item slots//add this in every initialize
    public Item currentI;//current item selected
    public Person currentP; //current user/save file
    public int itSpot;//spot in item arraylist

    public void deleteItem() {//put in 
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

    public void showItems() {//put in all scenes
        //show items in the boxes
        ImagePattern im;
        ColorAdjust colorAdjust = new ColorAdjust();//shows it was selected
        colorAdjust.setBrightness(-0.5);
        for (int r = 0; r < slot.size(); r++) {//clear slots
            slot.get(r).setFill(null);
            slot.get(r).setEffect(null);

        }
        for (int i = 0; i < currentP.getInventory().length(); i++) {
            im = new ImagePattern(inv.get(i).getImage());
            slot.get(i).setFill(im);
        }
        slot.get(itSpot).setEffect(colorAdjust);

    }

    public void clearSlots() {
        slot.clear();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Renwah's Dungeon");
        stage.setScene(scene);
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
