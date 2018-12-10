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
        String invent="!!!!!!";
        //if new then inv="!!!!!!", if old then use currentP.getInventory()
        MainApp.getItemsFromData(invent);//from database
        MainApp.showItems();
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

                //dbs.add(lstSaves.getItems().size() - 1, new Person(result.get()));
                psn.setName(result.get());
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
            //dbs.remove(lstSaves.getSelectionModel().getSelectedIndex() - 1);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for (int i = 0; i < psn.numRecord(file); i++) {
            psn.open(file, i);
            lstSaves.getItems().add(psn.getName());
        }
//        MainApp.clearSlots();
//        MainApp.slot.get(0)=recs1;
        
    }
    
}
