//made by: Shawn and Conner
//made on: 12/4/18
//made to: be a dungeon style rpg
package cullity.renwahsdungeon;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;

public class FXMLController implements Initializable {
    
    Person dbs = new Person();
    
    String file = "database.raf";

    Alert alert = new Alert(AlertType.INFORMATION);
    
    @FXML
    private ListView lstSaves;

    @FXML
    private void btnPlay(ActionEvent event) {

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
                dbs.setName(result.get());
                dbs.setLevel(0);
                dbs.save(file, lstSaves.getItems().size());
                
                lstSaves.getItems().add(result.get());
            }
        }
    }

    @FXML
    private void btnDelete(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for (int i = 0; i < dbs.numRecord(file); i++) {
            dbs.open(file, i);
            lstSaves.getItems().add(dbs.getName());
        }
    }
}
