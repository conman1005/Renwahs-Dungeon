/*
 * Made By: Shawn Benedict
 * Date: Dec 14, 2018
 * Made to
 */
package cullity.renwahsdungeon;

import java.io.IOException;
import java.util.Optional;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author shawnb58
 */
public class keyStuff {

    public Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

    public keyStuff() {

    }

    public void keys(KeyEvent k, boolean inTown) {//if in town scene, inTown=true
        if (k.getSource() == KeyCode.G && MainApp.currentI != null) {
            //drop item
            MainApp.deleteItem();

        }
        if (k.getSource() == KeyCode.DIGIT1) {
            //drop item
            MainApp.itSpot = 0;
            MainApp.showItems();
        }
        if (k.getSource() == KeyCode.DIGIT2) {
            //drop item
            MainApp.itSpot = 1;
            MainApp.showItems();
        }
        if (k.getSource() == KeyCode.DIGIT3) {
            //drop item
            MainApp.itSpot = 2;
            MainApp.showItems();
        }
        if (k.getSource() == KeyCode.DIGIT4) {
            //drop item
            MainApp.itSpot = 3;
            MainApp.showItems();
        }
        if (k.getSource() == KeyCode.DIGIT5) {
            //drop item
            MainApp.itSpot = 4;
            MainApp.showItems();
        }
        if (k.getSource() == KeyCode.DIGIT6) {
            //drop item
            MainApp.itSpot = 5;
            MainApp.showItems();
        }
        if (k.getSource() == KeyCode.ESCAPE && MainApp.currentA != null) {//if null then they are in main menu
            //pause or play

            if (MainApp.paused) {
                MainApp.paused = false;

                //get rid of menu
            } else {
                MainApp.paused = true;
                //show menu
                alert.setTitle("Paused");
                alert.setHeaderText("Warning");
                alert.setContentText("Changing screens will result in the loss of any unsaved data" + "\n ");

                ButtonType buttonTypeOne = new ButtonType("Main Menu");
                ButtonType buttonTypeTwo = new ButtonType("Back To Town");
                ButtonType buttonTypeThree = new ButtonType("Quit");
                ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
                if (!inTown) {
                    alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeThree, buttonTypeCancel);
                } else {
                    alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeThree, buttonTypeCancel);
                }

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == buttonTypeOne) {
                    // ... user chose "main menu"
                } else if (result.get() == buttonTypeTwo) {
                    // ... user chose "town"

                    try {
                        Parent town_parent = FXMLLoader.load(getClass().getResource("/fxml/town.fxml"));

                        Scene town_scene = new Scene(town_parent);
                        MainApp.currentS = town_scene;
                        // get reference to the stage
                        Stage stage = (Stage) ((Node) MainApp.slot.get(0)).getScene().getWindow();

                        stage.hide(); //optional
                        town_scene.getRoot().requestFocus();
                        stage.setScene(town_scene); //puts the new scence in the stage

                        stage.setTitle("Town"); //changes the title
                        stage.show(); //shows the new page
                    } catch (IOException iOException) {
                    }
                } else if (result.get() == buttonTypeThree) {
                    // ... user chose "Three"
                    System.exit(0);
                } else {
                    // ... user chose CANCEL or closed the dialog
                    MainApp.paused = false;
                    MainApp.currentA.setDisable(MainApp.paused);
                }
            }
            if (MainApp.currentA != null) {//make sure it doesnt error on menu
                MainApp.currentA.setDisable(MainApp.paused);

            }
        }
    }

}
