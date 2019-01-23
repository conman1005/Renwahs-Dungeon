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
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author shawnb58
 */
public class keyStuff {

    public Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

    public keyStuff() {

    }

    public void keys(KeyEvent k, boolean inTown, AnchorPane anc, Rectangle recI) {//if in town scene, inTown=true//recItem is the rectangle that shows the item
        if (k.getCode() == KeyCode.T) {//testing stuff//cheat code

            MainApp.currentP.setCoins(999);
            MainApp.currentP.setInventory("sbhhhh");
            MainApp.currentP.setBStrength(100);
            MainApp.getItemsFromData("sbhhhh");
            MainApp.showItems();
            if (MainApp.currentI.getSymbol() == "b".charAt(0) && inTown) {
                recI.setTranslateY(35);

            } else if (MainApp.currentI.getSymbol() != "b".charAt(0) && inTown) {
                recI.setTranslateY(-35);
            }
        }
        if (k.getCode() == KeyCode.H) {
           
            MainApp.paused = true;
                anc.setDisable(MainApp.paused);
            alert.setTitle("Instructions");
            alert.setHeaderText("Instructions and tips are as follows:");
            alert.setContentText("1. Use WASD to move \n 2. Use scroll wheel or first 6 numerical buttons to change item selected (items shown in the six inventory slots) \n 3. Clicking the left mouse button while holding a sword will slash \n 4. Clicking the arrow keys while holding a bow will shoot an arrow in the corresponding direction (Using the bow is illegal in the town) \n 5. Clicking Q with a health potion will use it \n 6. Clicking G will drop the current item \n 7. Begin your adventure by going to the bottom left corner of the town \n 8. Clicking the ESCAPE button will pause the game \n 9. Fight slimes to get rewards from chests \n 10. After choosing your items from a chest your progress will be saved \n 11. Pressing H will reopen the instructions to help");
            alert.showAndWait();MainApp.paused=false;anc.setDisable(MainApp.paused);MainApp.currentS.getRoot().requestFocus();
                
        }
        if (k.getCode() == KeyCode.G && MainApp.currentI != null) {
            //drop item
            MainApp.deleteItem();
        }
        if (k.getCode() == KeyCode.DIGIT1) {
            MainApp.itSpot = 0;
            MainApp.showItems();
            if (MainApp.currentI.getSymbol() == "b".charAt(0) && inTown) {
                recI.setTranslateY(35);

            } else if (MainApp.currentI.getSymbol() != "b".charAt(0) && inTown) {
                recI.setTranslateY(-35);
            }
        }
        if (k.getCode() == KeyCode.DIGIT2) {
            MainApp.itSpot = 1;
            MainApp.showItems();
            if (MainApp.currentI == null) {
                return;
            }
            if (MainApp.currentI.getSymbol() == "b".charAt(0) && inTown) {
                recI.setTranslateY(35);

            } else if (MainApp.currentI.getSymbol() != "b".charAt(0) && inTown) {
                recI.setTranslateY(-35);
            }
        }
        if (k.getCode() == KeyCode.DIGIT3) {
            MainApp.itSpot = 2;
            MainApp.showItems();
            if (MainApp.currentI == null) {
                return;
            }
            if (MainApp.currentI.getSymbol() == "b".charAt(0) && inTown) {
                recI.setTranslateY(35);

            } else if (MainApp.currentI.getSymbol() != "b".charAt(0) && inTown) {
                recI.setTranslateY(-35);
            }

        }
        if (k.getCode() == KeyCode.DIGIT4) {
            MainApp.itSpot = 3;
            MainApp.showItems();
            if (MainApp.currentI == null) {
                return;
            }
            if (MainApp.currentI.getSymbol() == "b".charAt(0) && inTown) {
                recI.setTranslateY(35);

            } else if (MainApp.currentI.getSymbol() != "b".charAt(0) && inTown) {
                recI.setTranslateY(-35);
            }
        }
        if (k.getCode() == KeyCode.DIGIT5) {
            MainApp.itSpot = 4;
            MainApp.showItems();
            if (MainApp.currentI == null) {
                return;
            }
            if (MainApp.currentI == null) {
                return;
            }
            if (MainApp.currentI.getSymbol() == "b".charAt(0) && inTown) {
                recI.setTranslateY(35);

            } else if (MainApp.currentI.getSymbol() != "b".charAt(0) && inTown) {
                recI.setTranslateY(-35);
            }
        }
        if (k.getCode() == KeyCode.DIGIT6) {
            MainApp.itSpot = 5;
            MainApp.showItems();
            if (MainApp.currentI == null) {
                return;
            }
            if (MainApp.currentI.getSymbol() == "b".charAt(0) && inTown) {
                recI.setTranslateY(35);

            } else if (MainApp.currentI.getSymbol() != "b".charAt(0) && inTown) {
                recI.setTranslateY(-35);
            }
        }

        if (k.getCode() == KeyCode.ESCAPE /*&& MainApp.currentA != null*/) {//if able to use keys on main menu then check if anc is menu 
            //pause or play

            if (MainApp.paused) {//true when game is paused
                try {//tried in case there is no alert open 
                    alert.close();//closes the alert
                } catch (Exception e) {
                }
                MainApp.paused = false;
                anc.setDisable(MainApp.paused);

            } else {

                MainApp.paused = true;
                anc.setDisable(MainApp.paused);
                //show menu
                alert.setTitle("Paused");
                alert.setHeaderText("Warning");
                alert.setContentText("Changing screens will result in the loss of any unsaved data");

                ButtonType buttonTypeMenu = new ButtonType("Main Menu");
                ButtonType buttonTypeTown = new ButtonType("Back To Town");
                ButtonType buttonTypeQuit = new ButtonType("Quit");
                ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
                if (!inTown) {
                    alert.getButtonTypes().setAll(buttonTypeMenu, buttonTypeTown, buttonTypeQuit, buttonTypeCancel);
                } else {
                    alert.getButtonTypes().setAll(buttonTypeMenu, buttonTypeQuit, buttonTypeCancel);
                }

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == buttonTypeMenu) {
                    // ... user chose "main menu"
                    MainApp.paused = false;
                    try {
                        Parent menu_parent = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));

                        Scene menu_scene = new Scene(menu_parent);
                        MainApp.currentS = menu_scene;
                        // get reference to the stage
                        Stage stage = (Stage) ((Node) MainApp.slot.get(0)).getScene().getWindow();

                        stage.hide(); //optional
                        menu_scene.getRoot().requestFocus();
                        stage.setScene(menu_scene); //puts the new scence in the stage

                        //stage.setTitle("Main Menu"); //changes the title
                        stage.show(); //shows the new page
                    } catch (IOException iOException) {
                    }
                } else if (result.get() == buttonTypeTown) {
                    // ... user chose "town"
                    MainApp.paused = false;
                    try {
                        Parent town_parent = FXMLLoader.load(getClass().getResource("/fxml/town.fxml"));

                        Scene town_scene = new Scene(town_parent);
                        MainApp.currentS = town_scene;
                        // get reference to the stage
                        Stage stage = MainApp.mainStage;

                        stage.hide(); //optional
                        town_scene.getRoot().requestFocus();
                        stage.setScene(town_scene); //puts the new scence in the stage

                        //   stage.setTitle("Town"); //changes the title
                        stage.show(); //shows the new page
                    } catch (IOException iOException) {
                    }
                } else if (result.get() == buttonTypeQuit) {
                    // ... user chose "Quit"

                    //add an "are you sure" alert 
                    System.exit(0);
                } else {
                    // ... user chose CANCEL or closed the dialog
                    MainApp.paused = false;
                    anc.setDisable(MainApp.paused);
                }
            }
            MainApp.currentS.getRoot().requestFocus();

        }
    }

}
