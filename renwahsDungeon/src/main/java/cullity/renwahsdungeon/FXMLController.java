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

    ArrayList<Person> dbs = new ArrayList();
    Person psn = new Person();

    String file = "database.raf";
    int recNum = psn.numRecord("database.raf");

    Alert alert = new Alert(AlertType.INFORMATION);

    @FXML
    private ListView lstSaves;

    @FXML
    private void btnPlay(ActionEvent event) {

        //after stuff happens
        //currentP=selected person
        //get inventory
        String invent;

        if (MainApp.currentP != null) {
            invent = MainApp.currentP.getInventory();
        } else {
            MainApp.currentP = dbs.get(lstSaves.getSelectionModel().getSelectedIndex());
            invent = MainApp.currentP.getInventory();
        }
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
                result = dialog.showAndWait();
            } else {
                lstSaves.getItems().add(result.get());
                recNum = lstSaves.getItems().size() - 1;

                dbs.add(new Person(result.get()));
                //dbs.get(dbs.size() - 1).setName(result.get());
                //psn.setName(result.get());
                psn = dbs.get(dbs.size() - 1);
                psn.save(file, recNum);
            }
        }
    }

    @FXML
    private void btnDelete(ActionEvent event) {
        alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Delete");
        alert.setHeaderText("Are you sure you want to delete " + lstSaves.getSelectionModel().getSelectedItem() + "?");
        alert.setContentText("Press OK to delete " + lstSaves.getSelectionModel().getSelectedItem() + ".");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            psn.delete(file, lstSaves.getSelectionModel().getSelectedIndex());

            lstSaves.getItems().clear();

            psn.open(file, psn.numRecord(file));
            for (int i = 0; i < psn.numRecord(file); i++) {
                psn.open(file, i);
                lstSaves.getItems().add(psn.getName());
            }
            dbs.remove(lstSaves.getSelectionModel().getSelectedIndex() + 1);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for (int i = 0; i < psn.numRecord(file); i++) {
            psn.open(file, i);
            lstSaves.getItems().add(psn.getName());
            dbs.add(psn);
        }
        MainApp.currentA = null;//not need because not used in menu
        //MainApp.currentA=null;
//        MainApp.clearSlots();
//        MainApp.slot.get(0)=recs1;

    }

}
