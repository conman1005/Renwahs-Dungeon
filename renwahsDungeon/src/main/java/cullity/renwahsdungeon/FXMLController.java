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
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;

public class FXMLController implements Initializable {

    @FXML
    private Button btnPlay;

    @FXML
    private void btnPlay(ActionEvent event) {

    }

    @FXML
    private void btnSlot(ActionEvent event) {

    }

    @FXML
    private void btnNew(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Enter Name");
        dialog.setHeaderText("Enter your name");
        dialog.setContentText("Please enter your name");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            System.out.println("Your name: " + result.get());
        }
    }

    @FXML
    private void btnDelete(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
