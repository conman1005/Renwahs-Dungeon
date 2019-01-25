//made by: Shawn and Conner
//made on: 12/4/18
//made to: be a dungeon style rpg
package cullity.renwahsdungeon;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FXMLController implements Initializable {

//    ArrayList<Person> dbs = new ArrayList();
//    Person psn = new Person();
    String file = "database.raf";
    int recNum = (new Person()).numRecord("database.raf");

    Alert alert = new Alert(AlertType.INFORMATION);

    @FXML
    private ListView lstSaves;

    @FXML
    private void btnPlay(ActionEvent event) {
        if (lstSaves.getSelectionModel().getSelectedIndex() == -1) {
            return;
        }
       // System.out.println(lstSaves.getSelectionModel().getSelectedIndex());
        //after stuff happens
        //currentP=selected person
        //get inventory
        String invent;

//        if (MainApp.currentP != null) {
//            invent = MainApp.currentP.getInventory();
//        } else {
        try {
            MainApp.currentP.open(file, lstSaves.getSelectionModel().getSelectedIndex());

        } catch (IndexOutOfBoundsException e) {//when the deletion happens
            System.out.println("index error");
            return;
        }
        invent = MainApp.currentP.getInventory();
        //}
        //if new then inv="!!!!!!", if old then use currentP.getInventory()
        MainApp.getItemsFromData(invent);//from database
        //MainApp.showItems();
        MainApp.recordNum = lstSaves.getSelectionModel().getSelectedIndex();

        try {
            Parent town_parent = FXMLLoader.load(getClass().getResource("/fxml/town.fxml")); //where FXMLPage2 is the name of the scene

            Scene town_scene = new Scene(town_parent);
            MainApp.currentS = town_scene;
            //get reference to the stage
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.hide(); //optional
            town_scene.getRoot().requestFocus();
            stage.setScene(town_scene); //puts the new scence in the stage

            //     stage.setTitle("Town"); //changes the title
            stage.show(); //shows the new page
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        if (MainApp.currentP.getLevel() < 2) {
            alert.setTitle("Instructions");
            alert.setHeaderText("Instructions and tips are as follows:");
            alert.setContentText("1. Use WASD to move \n 2. Use scroll wheel or first 6 numerical buttons to change item selected (items shown in the six inventory slots) \n 3. Clicking the left mouse button while holding a sword will slash \n 4. Clicking the arrow keys while holding a bow will shoot an arrow in the corresponding direction (Using the bow is illegal in the town) \n 5. Clicking Q with a health potion will use it \n 6. Clicking G will drop the current item \n 7. Begin your adventure by going to the bottom left corner of the town \n 8. Clicking the ESCAPE button will pause the game \n 9. Fight slimes to get rewards from chests \n 10. After choosing your items from a chest your progress will be saved \n 11. Pressing H will reopen the instructions for help");
            alert.showAndWait();
        }

    }

    @FXML
    private void btnNew(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Enter Name");
        dialog.setHeaderText("Enter your name");
        dialog.setContentText("Please enter your name");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            if (result.get().length() > 15) {
                alert.setTitle("Error");
                alert.setHeaderText("Error");
                alert.setContentText("Please use 15 characters or less");

                alert.showAndWait();
                btnNew(event);
                // return;
            } else {
                lstSaves.getItems().add(result.get());
                MainApp.recordNum = lstSaves.getItems().size() - 1;

//                dbs.add(new Person(result.get()));
                //dbs.get(dbs.size() - 1).setName(result.get());
                //psn.setName(result.get());
//                psn = dbs.get(dbs.size() - 1);
                MainApp.currentP = new Person(result.get());
                MainApp.currentP.save(file, MainApp.recordNum);
            }
        }
    }

    @FXML
    private void btnDelete(ActionEvent event) {
        if (lstSaves.getSelectionModel().getSelectedItem() == null) {
            alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Must select a user to delete");
            alert.setContentText(null);
            return;
        }
        alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Delete");
        alert.setHeaderText("Are you sure you want to delete " + lstSaves.getSelectionModel().getSelectedItem() + "?");
        alert.setContentText("Press OK to delete " + lstSaves.getSelectionModel().getSelectedItem() + ".");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {

            MainApp.currentP.delete(file, lstSaves.getSelectionModel().getSelectedIndex());

            lstSaves.getItems().clear();

            //psn.open(file, psn.numRecord(file));
            for (int i = 0; i < (new Person()).numRecord(file); i++) {//note, maybe make currentP
                MainApp.currentP.open(file, i);
                lstSaves.getItems().add(MainApp.currentP.getName());
            }
//            dbs.remove(lstSaves.getSelectionModel().getSelectedIndex() + 1);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for (int i = 0; i < (new Person()).numRecord(file); i++) {
            MainApp.currentP.open(file, i);
            lstSaves.getItems().add(MainApp.currentP.getName());
//            dbs.add(psn);
        }
        MainApp.currentA = null;//not need because not used in menu
        //MainApp.currentA=null;
//        MainApp.clearSlots();
//        MainApp.slot.get(0)=recs1;

    }

}
